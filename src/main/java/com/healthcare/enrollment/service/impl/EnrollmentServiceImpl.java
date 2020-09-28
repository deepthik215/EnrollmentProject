package com.healthcare.enrollment.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.rest.webmvc.json.DomainObjectReader;
import org.springframework.data.rest.webmvc.mapping.Associations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.enrollment.exception.CustomException;
import com.healthcare.enrollment.model.Dependent;
import com.healthcare.enrollment.model.Enrollment;
import com.healthcare.enrollment.mongo.EnrollmentRepository;
import com.healthcare.enrollment.service.EnrollmentService;

public class EnrollmentServiceImpl implements EnrollmentService{

	private static final Logger LOGGER = LoggerFactory.getLogger(EnrollmentServiceImpl.class);
	
	private final DomainObjectReader reader;
	
	@Autowired
	public EnrollmentServiceImpl(PersistentEntities entities, Associations links) {
		reader = new DomainObjectReader(entities, links);
	}
	
	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Override
	public Enrollment createEnroll(Enrollment enrollment) {
		List<Dependent> dependents = enrollment.getDependent();
		dependents.stream().forEach(dependent -> {
			if(!dependent.getMatchId().equals(enrollment.getMatchId()))
				throw new CustomException("No matching Enrolle", "Cannot find an enrolle for the entered dependent","Please check");
		});
		enrollment.setIsDeleted(false);
		Enrollment en =  (Enrollment) enrollmentRepository.insert(enrollment);	
		LOGGER.info("Completed createEnroll method");
		return en;
	}	

	@Override
	public Enrollment updateEnroll(Enrollment enrollment) {
		LOGGER.info("Started saving dependent/enrolle into DB with request: {}", enrollment);
		Enrollment enrollmentObj =  (Enrollment) enrollmentRepository.save(enrollment);
		LOGGER.info("Completed saving dependent/enrolle into DB : {}", enrollmentObj);
		return enrollmentObj;
	}

	@Override
	public void deleteEnroll(String id) {
		LOGGER.info("Started deleting enrolle from DB whose id is :{}", id);
		Optional<Enrollment> enrollment = enrollmentRepository.findById(id);
		Enrollment eObj = null;
		if(enrollment.isPresent())
			eObj = enrollment.get();
		eObj.setIsDeleted(true);
		try {
			patchEnrollment(eObj, id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		LOGGER.info("Deleted enrolle with id: {} from DB", id);
	}
	
	
	public void patchEnrollment(Enrollment valuesToMerge, String id) throws IOException {
		Enrollment dbEntity = null;
		LOGGER.info("Started patching onto Enrollment Document");
		InputStream is = new ByteArrayInputStream(objectMapper.writeValueAsString(valuesToMerge).getBytes());
		Enrollment patchedObj = null;
		Optional<Enrollment> enrollment = enrollmentRepository.findById(id);
		if(enrollment.isPresent()) {
			dbEntity = enrollment.get();
			patchedObj = reader.read(is, dbEntity, objectMapper);
			enrollmentRepository.save(patchedObj);
		}
		LOGGER.info("Completed patching onto Enrollment Document : {}", patchedObj);
	}
	
}
