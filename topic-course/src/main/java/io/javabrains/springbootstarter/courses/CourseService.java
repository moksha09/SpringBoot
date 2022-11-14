package io.javabrains.springbootstarter.courses;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.javabrains.springbootstarter.exception.NoSuchTopicExistsException;
import io.javabrains.springbootstarter.exception.TopicAlreadyExistsException;
import io.javabrains.springbootstarter.instructor.Instructor;
import io.javabrains.springbootstarter.topic.Topic;
import io.javabrains.springbootstarter.topic.TopicRepository;
import io.javabrains.springbootstarter.instructor.InstructorRepository;


@Service
@Transactional
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private InstructorRepository instructorRepository;
	@Autowired
	private CourseConverter courseConverter;

	public List<CourseDto> getAllCourses(int page, int size){
		
		String sortDir = "ASC";
		String sortBy = "courseId";
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
		 Pageable pageable = PageRequest.of(page, size, sort);
		 List<Course> courses = new ArrayList<>();
		 courseRepository.findAll(pageable).forEach(courses::add);
		 return courseConverter.entityToDto(courses);
		 
	 }
	
public List<CourseDto> getAllInstructorCourses(int instructorId, int page, int size){
	     String sortDir = "ASC";
	     String sortBy = "courseId";
	     Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
            : Sort.by(sortBy).descending();
	 	 Pageable pageable = PageRequest.of(page, size, sort);
		 if(instructorRepository.findByInstructorId(instructorId)!=null) {
		 List<Course> courses = new ArrayList<>();
		 courseRepository.findByInstructorInstructorId(instructorId,pageable).forEach(courses::add);
		 return courseConverter.entityToDto(courses);
		 }
		 else {
			 throw new NoSuchTopicExistsException("Instructor not found with id :" + instructorId);
		 }
		 
	 }
	
	 public List<CourseDto> getTopicCourses(int topicId, int page, int size){
		 String sortDir = "ASC";
	     String sortBy = "courseId";
	     Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
            : Sort.by(sortBy).descending();
	 	 Pageable pageable = PageRequest.of(page, size, sort);
		 if(topicRepository.findByTopicId(topicId)!=null) {
		 List<Course> courses = new ArrayList<>();
		 courseRepository.findByTopicTopicId(topicId, pageable).forEach(courses::add);
		 return courseConverter.entityToDto(courses);
	 }
		 else {
			 throw new NoSuchTopicExistsException("Topic not found with id :" + topicId);
		 }
	 }
	 
	 public CourseDto getCourse(int courseId) {
		 Course course= courseRepository.findById(courseId).orElseThrow(() -> new NoSuchTopicExistsException("Course not found with  :" + courseId));
		 return courseConverter.entityToDto(course);
	 }

	public String addCourse(CourseDto courseDto) {
		
		int topicId = courseDto.getTopicId();
		int instructorId = courseDto.getInstructorId();
		Topic existingTopic=topicRepository.findById(topicId).orElseThrow(() -> new NoSuchTopicExistsException("Topic not found with id : "+ topicId));
		Instructor existingInstructor=instructorRepository.findById(instructorId).orElseThrow(() -> new NoSuchTopicExistsException("Instructor not found with id :" + instructorId));
		Course course = courseConverter.dtoToEntity(courseDto);
		courseRepository.save(course);
		return course.getName()+" is added";
	}

	public String updateCourse(CourseDto courseDto) {
		int courseId = courseDto.getCourseId();
		int topicId = courseDto.getTopicId();
		int instructorId = courseDto.getInstructorId();
		Topic existingTopic=topicRepository.findById(topicId).orElseThrow(() -> new NoSuchTopicExistsException("Topic not found with id :" + topicId));
		Course existingCourse = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchTopicExistsException("Course not found with id :" + courseId));
		Instructor existingInstructor = instructorRepository.findById(instructorId).orElseThrow(() -> new NoSuchTopicExistsException("Instructor not found with id :" + instructorId));
		Course course = courseConverter.dtoToEntity(courseDto);
//		existingCourse.setName(course.getName());
//		existingCourse.setDescription(course.getDescription());
//		existingCourse.setTopic(updateTopic);
//		existingCourse.setInstructor(updateInstructor);
		courseRepository.save(course);
		return existingCourse.getName() +" is updated";
		
	}

	public String deleteCourse(int courseId) {
		
		Course courseToDelete = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchTopicExistsException("Course not found with id :" + courseId));
		courseRepository.deleteByCourseId(courseId);
		return courseToDelete.getName()+" is deleted.";
	}
}
