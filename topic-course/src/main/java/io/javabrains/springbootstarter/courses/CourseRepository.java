package io.javabrains.springbootstarter.courses;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	List<Course> findByTopicTopicId(int topicId, Pageable pageable);
	
	List<Course> findByInstructorInstructorId(int instructorId, Pageable pageable);


	void deleteByCourseId(int courseId);
}
