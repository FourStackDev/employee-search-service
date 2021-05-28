package org.fourstack.employeesearch.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Document(collection = "employee")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {

	@Id
	private String empId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String location;
	private String mail;
	private String mobileNum;
	
	private Department department;
	private Account account;
	private Role role;
	private Project project;
	
	
}
