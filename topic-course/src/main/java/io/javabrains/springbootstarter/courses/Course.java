package io.javabrains.springbootstarter.courses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import io.javabrains.springbootstarter.instructor.Instructor;
import io.javabrains.springbootstarter.topic.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "COURSE_UPDATED")
public class Course {

	@Id
	@Column(name = "COURSE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CourseSeq")
    @SequenceGenerator(name = "CourseSeq", sequenceName = "course_seq ", allocationSize = 1)
	private int courseId;

	@Column(name = "NAME")
	@NotNull(message = "Course name shouldn't be null")
	@Size(min = 2, message = "Course name should have atleast 2 char")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "DURATION")
	@NotNull(message = "Duration shouldn't be null")
	@Min(value = 2, message = "Duration of course should be minimum 2 hrs")
	@Max(value = 15, message = "Duration of course should not exceed 15hrs")
	private int duration;

	@ManyToOne
	@JoinColumn(name = "TOPIC_ID", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Topic topic;
	
	@ManyToOne
	@JoinColumn(name = "INSTRUCTOR_ID", nullable = true)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Instructor instructor;

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Course() {

	}

//	public Course(int courseId,
//			@NotNull(message = "Course name shouldn't be null") @Size(min = 2, message = "Course name should have atleast 2 char") String name,
//			String description,
//			@NotNull(message = "Duration shouldn't be null") @Min(value = 2, message = "Duration of course should be minimum 2 hrs") @Max(value = 15, message = "Duration of course should not exceed 15hrs") int duration,
//			Topic topic, Instructor instructor) {
//		super();
//		this.courseId = courseId;
//		this.name = name;
//		this.description = description;
//		this.duration = duration;
//		this.topic = topic;
//		this.instructor = instructor;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
