package org.fourstack.employeesearch.services;

import org.fourstack.employeesearch.models.EmpSearchData;
import org.fourstack.employeesearch.models.Employee;
import org.springframework.data.domain.Page;

public interface EmployeeSearchService {

	Page<Employee> fetchEmployees(int pageNum, int pageSize, String sortingOrder);

	Page<Employee> fetchEmployeesByCriteria(EmpSearchData data, int pageNum, int pageSize, String sortingOrder);

	Page<Employee> fecthOnlyFirstAndLastNamesOfEmployees(int pageNum, int pageSize, String sortingOrder);

	Page<Employee> fecthOnlyFirstAndLastNamesOfEmployees(String firstName, String lastName, int pageNum, int pageSize,
			String sortingOrder);
}
