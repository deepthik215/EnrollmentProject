package com.healthcare.enrollment.service;

import com.healthcare.enrollment.model.Enrollment;

public interface EnrollmentService {

	public Enrollment createEnroll(Enrollment enrollment);

	public Enrollment updateEnroll(Enrollment enrollment);

	public void deleteEnroll(String id);
}
