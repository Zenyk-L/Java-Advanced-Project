package ua.lviv.lgs.university.domain;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private String passwordConfirm;
	
	
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private List<Mark> markList = new LinkedList<>();
	
	@ManyToOne
	private Faculty faculty;
	

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public User() {}
	
	public List<Mark> getMarkList() {
		return markList;
	}

	public void setMarkList(List<Mark> markList) {
		this.markList = markList;
	}
	
	public List<Mark> getFillMarkList() {
		Set<Subject> subjectsPresent = new HashSet<>();
		for (Mark mark :markList) {
			subjectsPresent.add(mark.getSubject());
		}
		Subject[] subjects = Subject.values();
		for (Subject subject : subjects) {
			if(!subjectsPresent.contains(subject)) {
				markList.add(new Mark(subject, 0));
			}
		}
		
		return markList;
	}
	
	public Set<Subject> getPresentSubjects() {
		Set<Subject> subjectsPresent = new HashSet<>();
		for (Mark mark :markList) {
			subjectsPresent.add(mark.getSubject());
		}
		
		
		return subjectsPresent;
	}

	public Integer getGradeBySubject(Subject subject) {
		 return markList.stream().filter(mark -> mark.getSubject().equals(subject)).findFirst().get().getGrade();
		
	}
	

	public User(User user) {
		this.id = user.id;
		this.email = user.email;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.password = user.password;
		this.passwordConfirm = user.passwordConfirm;
		this.role = user.role;
		this.markList = markList;
	}
	
	public User(String email, String firstName, String lastName, String password, String passwordConfirm,UserRole role) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.role = role;
	}
	
	
	public User(Integer id, String email, String firstName, String lastName, String password, String passwordConfirm,
			UserRole role) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

		
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", passwordConfirm=" + passwordConfirm + ", role=" + role + ", markList="
				+ markList 
				+ "]";
	}

	
	
	

	
}
