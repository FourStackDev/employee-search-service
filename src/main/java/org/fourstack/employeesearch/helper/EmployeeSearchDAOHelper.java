package org.fourstack.employeesearch.helper;

import java.util.Optional;

import org.fourstack.employeesearch.dao.EmployeeRepository;
import org.fourstack.employeesearch.models.EmpSearchData;
import org.fourstack.employeesearch.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class EmployeeSearchDAOHelper {

	@Autowired
	private EmployeeRepository empRepository;

	public Employee getEmployeeById(String employeeId) {
		try {
			Optional<Employee> optionalEmployee = empRepository.findById(employeeId);
			if (optionalEmployee.isPresent())
				return optionalEmployee.get();

		} catch (Exception e) {

		}
		return null;
	}

	public Page<Employee> getEmployeePage(Pageable page) {
		return empRepository.findAll(page);
	}

	public Page<Employee> getEmployeesFirstAndLastNameObjects(Pageable page) {
		return empRepository.findEmployeeFirstAndLastNameObjects(page);
	}

	public Page<Employee> getEmployeesByCriteria(EmpSearchData data) {
		return null;
	}
}
