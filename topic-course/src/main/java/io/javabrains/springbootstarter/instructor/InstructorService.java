package io.javabrains.springbootstarter.instructor;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.javabrains.springbootstarter.exception.NoSuchTopicExistsException;
import io.javabrains.springbootstarter.exception.TopicAlreadyExistsException;

@Service
public class InstructorService {

	@Autowired
	private InstructorRepository instructorRepository;

	public List<Instructor> getAllInstructors(int page, int size) {
		String sortDir = "ASC";
		String sortBy = "instructorId";
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		List<Instructor> list = instructorRepository.findAll(pageable).getContent();
		return list;
	}


	public Instructor getInstructorById(int instructorId) {

		return instructorRepository.findById(instructorId)
				.orElseThrow(() -> new NoSuchTopicExistsException("Instructor not found with id :" + instructorId));
	}

	
	public String addInstructor(Instructor instructor) {

		instructorRepository.save(instructor);
		return instructor.getFname() +" " +instructor.getLname()+ " is added";

	}

	public String updateInstructor(int instructorId, Instructor instructor) {
		Instructor existingInstructor = instructorRepository.findById(instructorId)
				.orElseThrow(() -> new NoSuchTopicExistsException("Instructor not found with id :" + instructorId));

		existingInstructor.setFname(instructor.getFname());
		existingInstructor.setLname(instructor.getLname());
		existingInstructor.setAge(instructor.getAge());
		existingInstructor.setEmail(instructor.getEmail());
		existingInstructor.setOffice(instructor.getOffice());
		instructorRepository.save(existingInstructor);
		return existingInstructor.getFname() +" " +instructor.getLname() + " is updated";

	}

	public String deleteInstructor(int instructorId) {
		Instructor deleteInstructor = instructorRepository.findById(instructorId)
				.orElseThrow(() -> new NoSuchTopicExistsException("Instructor not found with id :" + instructorId));
		instructorRepository.deleteById(instructorId);
		return deleteInstructor.getFname()+" " +deleteInstructor.getLname() + " is deleted";

	}


}
