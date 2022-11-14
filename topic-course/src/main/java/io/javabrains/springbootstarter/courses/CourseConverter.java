package io.javabrains.springbootstarter.courses;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.javabrains.springbootstarter.instructor.InstructorRepository;
import io.javabrains.springbootstarter.topic.TopicRepository;


@Component
public class CourseConverter {
	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private InstructorRepository instructorRepository;
public CourseDto entityToDto(Course course) {
		
	CourseDto dto = new CourseDto();
	dto.setCourseId(course.getCourseId());
	dto.setName(course.getName());
	dto.setDescription(course.getDescription());
	dto.setDuration(course.getDuration());
	dto.setTopicName(course.getTopic().getName());
	dto.setInstructorName(course.getInstructor().getFname()+" "+course.getInstructor().getLname());
	dto.setTopicId(course.getTopic().getId());
	dto.setInstructorId(course.getInstructor().getInstructorId());
	return dto;
		
	}

public  List<CourseDto> entityToDto(List<Course> course) {
	
	return	course.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	
}

public Course dtoToEntity(CourseDto dto)
{

	Course course = new Course();
	course.setCourseId(dto.getCourseId());
	course.setName(dto.getName());
	course.setDescription(dto.getDescription());
	course.setDuration(dto.getDuration());
	course.setTopic(topicRepository.findByTopicId(dto.getTopicId()));
	course.setInstructor(instructorRepository.findByInstructorId(dto.getInstructorId()));
	return course;
}

public List<Course> dtoToEntity(List<CourseDto> dto)
{

	return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
}

}
