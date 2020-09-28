package com.healthcare.enrollment.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healthcare.enrollment.model.Enrollment;
import com.healthcare.enrollment.mongo.EnrollmentRepository;
import com.healthcare.enrollment.service.EnrollmentService;
import com.healthcare.enrollment.service.impl.EnrollmentServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("/api")
public class EnrollmentController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EnrollmentController.class);
	
	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	@Autowired
	EnrollmentService enrollmentService;

	@PostMapping("/enrollment")
	@Operation(summary="API to add a new enrollee, a new dependent to enrollee")
	public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {
		return enrollmentService.createEnroll(enrollment);
	}
	
	@PutMapping("/enrollment/{id}")
	@Operation(summary="API to modify existing enrolles, dependents")
	public Enrollment updateEnrollment(@RequestBody Enrollment enrollment, @PathVariable("id") @NotNull @Pattern(regexp = "^[a-zA-z0-9+_-]*$") String id) {
		return enrollmentService.updateEnroll(enrollment);
		
	}
	
	@DeleteMapping("/enrollment/{id}")
	@Operation(summary="API to remove enrolle, entirely")
	public void deleteEnrollment(@PathVariable("id") @NotNull @Pattern(regexp = "^[a-zA-z0-9+_-]*$")  String id) {
		 enrollmentService.deleteEnroll(id);
	}
	
}
