package com.healthcare.enrollment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healthcare.enrollment.service.DependentService;

import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("/api")
public class DependentController {

	@Autowired
	DependentService dependentService;
	
	@DeleteMapping("/dependents/{id}")
	public void deleteDependent(String id) {
		dependentService.deleteDepend(id);
	}

}
