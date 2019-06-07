package com.poorna.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poorna.model.Department;


public interface DepartmentRepository extends JpaRepository<Department, Long> {

	List<Department> findByOrganizationId(Long organizationId);



	
	
	
	
	
	
}
