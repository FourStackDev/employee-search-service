package org.fourstack.employeesearch.services;

import org.fourstack.employeesearch.models.EmpSearchData;
import org.fourstack.employeesearch.models.Employee;
import org.springframework.data.domain.Page;

public interface EmployeeSearchService {

	Page<Employee> fetchEmployees(int pageNum, int pageSize, String sortingOrder);
	
	Page<Employee> fetchEmployees(int pageNum, int pageSize, String sortingOrder, String orderBy);

	Page<Employee> fetchEmployeesByCriteria(EmpSearchData data, int pageNum, int pageSize, String sortingOrder);

	Page<Employee> fetchOnlyFirstAndLastNamesOfEmployees(int pageNum, int pageSize, String sortingOrder);

	Page<Employee> fetchOnlyFirstAndLastNamesOfEmployees(String firstName, String lastName, int pageNum, int pageSize,
			String sortingOrder);
	
	Employee fetchEmployeeById(String empId);

	Employee fetchEmployeeByMobileNumber(String mobileNum);

	Employee fetchEmployeeByMailId(String mailId);
}
