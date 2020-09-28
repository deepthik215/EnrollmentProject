package com.healthcare.enrollment.service.impl;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.healthcare.enrollment.model.Enrollment;
import com.healthcare.enrollment.mongo.DependentRepository;
import com.healthcare.enrollment.service.DependentService;

public class DependentServiceImpl implements DependentService{

	private static final Logger LOGGER = LoggerFactory.getLogger(DependentServiceImpl.class);
	
	@Autowired
	DependentRepository dependentRepository;
	
	@Autowired
	EnrollmentServiceImpl enrollmentServiceImpl;
	
	@Override
	public void deleteDepend(String id) {
		LOGGER.info("Started deleteDepend() method");
		Optional<Enrollment> enrollment = dependentRepository.findById(id);
		Enrollment eObj = null;
		if(enrollment.isPresent())
			eObj = enrollment.get();
		eObj.setIsDeleted(true);
		try {
			enrollmentServiceImpl.patchEnrollment(eObj, id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
