package io.javabrains.springbootstarter.topic;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

	Topic findByName(String name);
	Topic findByTopicId(int topicId);
}
