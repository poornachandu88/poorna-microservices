package com.poorna.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poorna.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	

}