package ua.lviv.lgs.university.domain;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="faculty")
public class Faculty {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	private Integer studentQuantity;
	@ElementCollection
	private List<String> subjects;
	 
	public Faculty() {
	}

	public Faculty(String name, Integer studentQuantity, List<String> subjects) {
		this.name = name;
		this.studentQuantity = studentQuantity;
		this.subjects = subjects;
	}

	public Faculty(Integer id, String name, Integer studentQuantity, List<String> subjects) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStudentQuantity() {
		return studentQuantity;
	}

	public void setStudentQuantity(Integer studentQuantity) {
		this.studentQuantity = studentQuantity;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
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
		return "Faculty [id=" + id + ", name=" + name + ", studentQuantity=" + studentQuantity + ", subjects="
				+ subjects + "]";
	}

}
