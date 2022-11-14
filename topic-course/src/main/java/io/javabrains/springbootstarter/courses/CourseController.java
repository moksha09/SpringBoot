package io.javabrains.springbootstarter.courses;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import io.javabrains.springbootstarter.topic.TopicController;
//import io.javabrains.springbootstarter.topic.TopicRepository;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@RequestMapping("/courses")
	public List<CourseDto> getAllCourses(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "6", required = false) int size ,@RequestParam(name = "instructorId", required=false) Integer instructorId,
	@RequestParam(name="topicId", required=false) Integer topicId, @RequestParam(name="courseId", required=false) Integer courseId){
		if(instructorId!=null) {
			return courseService.getAllInstructorCourses(instructorId, page, size);
		}
		else if(topicId!=null){
			return courseService.getTopicCourses(topicId, page, size);
		}
		else if(courseId!=null) {
			return Arrays.asList(courseService.getCourse(courseId));
		}
		else {
		return courseService.getAllCourses(page, size);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/post/course")
	public String addCourse(@RequestBody @Valid CourseDto courseDto) {

		return courseService.addCourse(courseDto);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/update/course")
	public String updateCourse(@RequestBody @Valid CourseDto courseDto) {
		return courseService.updateCourse(courseDto);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/course/{courseId}")
	public String deleteCourse(@PathVariable int courseId) {
		return courseService.deleteCourse(courseId);
	}

}
