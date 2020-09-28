package com.healthcare.enrollment.mongo;

import java.util.Optional;

import com.healthcare.enrollment.model.Dependent;
import com.healthcare.enrollment.model.Enrollment;

public interface DependentRepository {

	public Dependent removeDependentbyId(String id);

	public Optional<Enrollment> findById(String id);
}
