package com.poorna.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poorna.model.Organization;


public interface OrganizationRepository extends JpaRepository<Organization, Long> {

	
	
}
