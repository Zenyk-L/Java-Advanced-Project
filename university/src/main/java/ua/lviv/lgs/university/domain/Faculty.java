package ua.lviv.lgs.university.domain;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "faculty")
public class Faculty {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private FacultyName name;

	private Integer studentQuantity;

	private Double requiredLevel;
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private List<Subject> subjects;

	@ManyToMany
	@JoinColumn(name = "faculty_id", referencedColumnName = "id")
	private List<User> users = new LinkedList<>();

	@Lob
	private String encodedImage;
	
	public Integer getCount() {
		return users.size();
	}

	public Faculty() {
	}

	public Faculty(FacultyName name, Integer studentQuantity, List<Subject> subjects) {
		this.name = name;
		this.studentQuantity = studentQuantity;
		this.subjects = subjects;
	}

	public Faculty(Integer id, FacultyName name, Integer studentQuantity, List<Subject> subjects) {
		this.id = id;
		this.name = name;
		this.studentQuantity = studentQuantity;
		this.subjects = subjects;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FacultyName getName() {
		return name;
	}

	public void setName(FacultyName name) {
		this.name = name;
	}

	public Integer getStudentQuantity() {
		return studentQuantity;
	}

	public void setStudentQuantity(Integer studentQuantity) {
		this.studentQuantity = studentQuantity;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public String getEncodedImage() {
		return encodedImage;
	}

	public void setEncodedImage(String encodedImage) {
		this.encodedImage = encodedImage;
	}

	public Double getRequiredLevel() {
		return requiredLevel;
	}

	public void setRequiredLevel(Double requiredLevel) {
		this.requiredLevel = requiredLevel;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((studentQuantity == null) ? 0 : studentQuantity.hashCode());
		result = prime * result + ((subjects == null) ? 0 : subjects.hashCode());
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
		Faculty other = (Faculty) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (studentQuantity == null) {
			if (other.studentQuantity != null)
				return false;
		} else if (!studentQuantity.equals(other.studentQuantity))
			return false;
		if (subjects == null) {
			if (other.subjects != null)
				return false;
		} else if (!subjects.equals(other.subjects))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Faculty [id=" + id + ", name=" + name + ", studentQuantity=" + studentQuantity + ", requiredLevel="
				+ requiredLevel + ", subjects=" + subjects + ", users=" + users + ", encodedImage=" + encodedImage
				+ "]";
	}

}
