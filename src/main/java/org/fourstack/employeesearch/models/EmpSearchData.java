package org.fourstack.employeesearch.models;

import lombok.Data;

@Data
public class EmpSearchData {
	
	private String empId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String roleName;
	private String mobileNumber;
	private String location;
	private String email;
	private String departmentName;
	private String projectName;
	private String accountName;

}
