package io.javabrains.springbootstarter.topic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.*;

import lombok.Data;
@Data
@Entity
@Table(name = "TOPIC_UPDATED")
public class Topic {

	@Id
	@Column(name = "TOPIC_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TopicSeq")
    @SequenceGenerator(name = "TopicSeq", sequenceName = "topic_seq ", allocationSize = 1)
	private int topicId;

	@Column(name = "NAME")
	@NotNull(message = "Topic name shouldn't be null")

	private String name;

	@Column(name = "DESCRIPTION")
	private String description;
	
	public Topic() {

	}

	public Topic(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return topicId;
	}

	public void setId(int topicId) {
		this.topicId = topicId;
	}

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
