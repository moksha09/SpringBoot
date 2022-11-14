package io.javabrains.springbootstarter.instructor;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstructorController {

	@Autowired
	private InstructorService instructorService;

	@RequestMapping("/instructors")
	public List<Instructor> getAllInstructors(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "3", required = false) int size) {
		return instructorService.getAllInstructors(page, size);
	}

	@RequestMapping("/instructors/{instructorId}")
	public Instructor getInstructorById(@PathVariable int instructorId) {
		return instructorService.getInstructorById(instructorId);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/instructors")
	public String addInstructor(@RequestBody @Valid Instructor instructor) {
		return instructorService.addInstructor(instructor);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/instructors/{instructorId}")
	public String updateInstructor(@RequestBody @Valid Instructor instructor, @PathVariable int instructorId) {
		return instructorService.updateInstructor(instructorId, instructor);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/instructors/{instructorId}")
	public String deleteInstructor(@PathVariable int instructorId) {
		return instructorService.deleteInstructor(instructorId);
	}

}
