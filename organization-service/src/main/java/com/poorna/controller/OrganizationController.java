package com.poorna.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poorna.client.DepartmentClient;
import com.poorna.client.EmployeeClient;
import com.poorna.dao.OrganizationRepository;
import com.poorna.model.Department;
import com.poorna.model.Organization;

@RestController
public class OrganizationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);
	
	@Autowired
	OrganizationRepository organizationRepository;
	@Autowired
	DepartmentClient departmentClient;
	@Autowired
	EmployeeClient employeeClient;
	
	@PostMapping("/addorg")
	public Organization add(@RequestBody Organization organization) {
		LOGGER.info("Organization add: {}", organization);
		return organizationRepository.save(organization);
	}
	
	@GetMapping("/orglist")
	public List<Organization> findAll() {
		LOGGER.info("Organization find");
		return organizationRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Organization findById(@PathVariable("id") Long id) {
		LOGGER.info("Organization find: id={}", id);
		
	List<Organization> organizations=organizationRepository.findAll();
	Optional<Organization> setDeptorganization=organizations.stream().filter(a -> a.getId().equals(id)).findFirst();
	
		return setDeptorganization.get();
		
	}

	@GetMapping("/{id}/with-departments")
	public Organization findByIdWithDepartments(@PathVariable("id") Long id) {
		LOGGER.info("Organization find: id={}", id);
		
		Optional<Organization> organization = organizationRepository.findById(id);
		List<Department> departments=departmentClient.findByOrganization(organization.get().getId());
		
		Organization setDeptorganization=new Organization();
		
		setDeptorganization.setDepartments(departments);
		return setDeptorganization;
	}
	
	@GetMapping("/{id}/with-departments-and-employees")
	public Organization findByIdWithDepartmentsAndEmployees(@PathVariable("id") Long id) {
		LOGGER.info("Organization find: id={}", id);
		Optional<Organization> organization = organizationRepository.findById(id);
		Organization setDeptorganization=new Organization();
		setDeptorganization.setDepartments(departmentClient.findByOrganizationWithEmployees(organization.get().getId()));
		return setDeptorganization;
	}
	
	@GetMapping("/{id}/with-employees")
	public Organization findByIdWithEmployees(@PathVariable("id") Long id) {
		LOGGER.info("Organization find: id={}", id);
		Optional<Organization> organization = organizationRepository.findById(id);
		Organization setDeptorganization=new Organization();
		setDeptorganization.setEmployees(employeeClient.findByOrganization(organization.get().getId()));
		return setDeptorganization;
	}
	
}
