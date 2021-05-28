package org.fourstack.employeesearch.services;

import static org.fourstack.employeesearch.constants.EmployeeSearchConstants.ASCENDING_ORDER;
import static org.fourstack.employeesearch.constants.EmployeeSearchConstants.DESCENDING_ORDER;
import static org.fourstack.employeesearch.constants.EmployeeSearchConstants.EMPLOYEE_ID;

import org.fourstack.employeesearch.helper.EmployeeSearchDAOHelper;
import org.fourstack.employeesearch.models.EmpSearchData;
import org.fourstack.employeesearch.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class EmployeeSearchServiceImpl implements EmployeeSearchService {
	
	@Autowired
	private EmployeeSearchDAOHelper empDAOHelper;

	@Override
	public Page<Employee> fetchEmployees(int pageNum, int pageSize, String sortingOrder) {
		PageRequest pageRequest = generatePageRequest(pageNum, pageSize, sortingOrder, EMPLOYEE_ID);

		return empDAOHelper.getEmployeePage(pageRequest);
	}

	@Override
	public Page<Employee> fetchEmployeesByCriteria(EmpSearchData data, int pageNum, int pageSize, String sortingOrder) {
		PageRequest pageRequest = generatePageRequest(pageNum, pageSize, sortingOrder, EMPLOYEE_ID);
		return null;
	}

	@Override
	public Page<Employee> fecthOnlyFirstAndLastNamesOfEmployees(int pageNum, int pageSize, String sortingOrder) {
		PageRequest pageRequest = generatePageRequest(pageNum, pageSize, sortingOrder, EMPLOYEE_ID);
		return empDAOHelper.getEmployeesFirstAndLastNameObjects(pageRequest);
	}
	
	private PageRequest generatePageRequest(int pageNum, int pageSize, String sortingOrder, String... properties) {
		Sort sort = null;
		switch (sortingOrder) {
		case ASCENDING_ORDER:
			sort = Sort.by(Direction.ASC, properties);
			break;
		case DESCENDING_ORDER:
			sort = Sort.by(Direction.DESC, properties);
		default:
			sort = Sort.by(Direction.ASC, properties);
			break;
		}

		return PageRequest.of(pageNum, pageSize, sort);
	}

}
