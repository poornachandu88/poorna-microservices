package com.poorna.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poorna.client.EmployeeClient;
import com.poorna.dao.DepartmentRepository;
import com.poorna.model.Department;

@RestController
public class DepartmentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	EmployeeClient employeeClient;
	
	
	@PostMapping("/adddept")
	public Department add(@RequestBody Department department) {
		LOGGER.info("Department add: {}", department);
		return departmentRepository.save(department);
	}
	
	@GetMapping("/{id}")
	public Department findById(@PathVariable("id") Long id) {
		LOGGER.info("Department find: id={}", id);
		List<Department>	departments=departmentRepository.findAll();
		Optional<Department> department = departments.stream().filter(a -> a.getId().equals(id)).findFirst();
		if (department.isPresent())
			return department.get();
		else
			return null;
		
	}
	
	@GetMapping("/deptlist")
	public List<Department> findAll() {
		LOGGER.info("Department find");
		return departmentRepository.findAll();
	}
	
	@GetMapping("/organization/{organizationId}")
	public List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId) {
		LOGGER.info("Department find: organizationId={}", organizationId);
		
		List<Department> departments=departmentRepository.findAll();
		return	departments.stream().filter(a -> a.getOrganizationId().equals(organizationId)).collect(Collectors.toList());
	
	}
	
	@GetMapping("/organization/{organizationId}/with-employees")
	public List<Department> findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId) {
		LOGGER.info("Department find: organizationId={}", organizationId);
		
		//List<Department> departments=departmentRepository.findAll();
		LOGGER.info("Department find: organizationId={}", organizationId);
		List<Department> departments = departmentRepository.findByOrganizationId(organizationId);
		departments.forEach(d -> d.setEmployees(employeeClient.findByDepartment(d.getId())));
		return departments;
	}
	
}
