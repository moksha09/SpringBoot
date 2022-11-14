package io.javabrains.springbootstarter.instructor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

	Instructor findByInstructorId(int instructorId);
}
