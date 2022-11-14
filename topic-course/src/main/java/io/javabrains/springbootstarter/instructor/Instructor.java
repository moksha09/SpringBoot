package io.javabrains.springbootstarter.instructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "INSTRUCTOR")
public class Instructor {

	@Id
	@Column(name = "INSTRUCTOR_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "InstructorSeq")
    @SequenceGenerator(name = "InstructorSeq", sequenceName = "instructor_seq ", allocationSize = 1)
	private int instructorId;

	@Column(name = "FIRST_NAME")
	@NotNull(message = "First name shouldn't be null")

	private String fname;
	
	@Column(name = "Last_NAME")
	@NotNull(message = "Last name shouldn't be null")

	private String lname;

	@Column(name = "AGE")
	@NotNull(message = "Age shouldn't be null")
	@Min(value = 21, message = "Age should be minimum 21 years")
	private int age;
	
	@Column(name = "EMAIL")
	@Email(message = "Email is not correct" )
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "OFFICE")
	private Office office;
	
	public Instructor() {

	}

	public Instructor(String fname,String lname, int age, String email,Office office) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		this.email = email;
		this.office = office;
	}

	public int getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}


	
	

}
