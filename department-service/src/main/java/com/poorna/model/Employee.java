package com.poorna.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Employee {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long organizationId;
	//private Long departmentId;
	private String name;
	private int age;
	private String position;
	
	@ManyToOne
	private Department department;

	public Employee() {

	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

/*	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	/*@Override
	public String toString() {
		return "Employee [id=" + id + ", organizationId=" + organizationId + ", departmentId=" + departmentId
				+ ", name=" + name + ", position=" + position + "]";
	}*/

}
