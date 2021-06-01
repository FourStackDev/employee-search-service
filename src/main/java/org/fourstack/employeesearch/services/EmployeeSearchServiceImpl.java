package org.fourstack.employeesearch.services;

import static org.fourstack.employeesearch.constants.EmployeeSearchConstants.ASCENDING_ORDER;
import static org.fourstack.employeesearch.constants.EmployeeSearchConstants.DESCENDING_ORDER;
import static org.fourstack.employeesearch.constants.EmployeeSearchConstants.EMPLOYEE_ID;
import static org.fourstack.employeesearch.constants.EmployeeSearchConstants.FIRST_NAME;
import static org.fourstack.employeesearch.constants.EmployeeSearchConstants.LAST_NAME;
import static org.fourstack.employeesearch.constants.EmployeeSearchConstants.ORDERBY_EMPLOYEEID;
import static org.fourstack.employeesearch.constants.EmployeeSearchConstants.ORDERBY_FIRSTNAME;
import static org.fourstack.employeesearch.constants.EmployeeSearchConstants.ORDERBY_LASTNAME;

import java.util.ArrayList;
import java.util.List;

import org.fourstack.employeesearch.constants.EmployeeSearchConstants;
import org.fourstack.employeesearch.helper.EmployeeSearchDAOHelper;
import org.fourstack.employeesearch.models.EmpSearchData;
import org.fourstack.employeesearch.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

		if (data != null) {

			if (data.getEmpId() != null || data.getEmail() != null || data.getMobileNumber() != null) {
				Employee employee = null;
				if (data.getEmpId() != null && !data.getEmpId().isEmpty()) {
					employee = empDAOHelper.getEmployeeById(data.getEmpId());
				} else if (data.getEmail() != null && !data.getEmail().isEmpty()) {
					employee = empDAOHelper.getEmployeeByEmail(data.getEmail());
				} else if (data.getMobileNumber() != null && !data.getMobileNumber().isEmpty()) {
					employee = empDAOHelper.getEmployeeByMobileNumber(data.getMobileNumber());
				}

				return new PageImpl<>((employee != null) ? List.of(employee) : new ArrayList<>());
			} else {
				return empDAOHelper.getEmployeesByCriteria(data, pageRequest);
			}

		} else {

			// Add throws condition
		}

		return null;
	}

	@Override
	public Page<Employee> fetchOnlyFirstAndLastNamesOfEmployees(int pageNum, int pageSize, String sortingOrder) {
		PageRequest pageRequest = generatePageRequest(pageNum, pageSize, sortingOrder, EMPLOYEE_ID);
		return empDAOHelper.getEmployeesFirstAndLastNameObjects(pageRequest);
	}

	@Override
	public Page<Employee> fetchOnlyFirstAndLastNamesOfEmployees(String firstName, String lastName, int pageNum,
			int pageSize, String sortingOrder) {
		PageRequest pageRequest = generatePageRequest(pageNum, pageSize, sortingOrder, EMPLOYEE_ID);
		return empDAOHelper.getEmployeesFirstAndLastNameObjects(firstName, lastName, pageRequest);
	}
	
	@Override
	public Employee fetchEmployeeById(String empId) {
		return empDAOHelper.getEmployeeById(empId);
	}
	
	@Override
	public Page<Employee> fetchEmployees(int pageNum, int pageSize, String sortingOrder, String orderByKey) {
		String orderByValue = getOrderByValue(orderByKey);
		PageRequest pageRequest = generatePageRequest(pageNum, pageSize, sortingOrder, orderByValue);

		return empDAOHelper.getEmployeePage(pageRequest);
	}
	
	@Override
	public Employee fetchEmployeeByMobileNumber(String mobileNum) {
		return empDAOHelper.getEmployeeByMobileNumber(mobileNum);
	}

	@Override
	public Employee fetchEmployeeByMailId(String mailId) {
		return empDAOHelper.getEmployeeByEmail(mailId);
	}

	/**
	 * Used to generate the orderBy query parameter using the orderByKey. Matching
	 * Values are defined in {@link EmployeeSearchConstants} class.
	 * 
	 * @param orderByKey User populated key, to which we need to find Order By
	 *                   value.
	 * @return Order By Query parameter value.
	 */
	private String getOrderByValue(String orderByKey) {
		String orderByValue = null;
		switch (orderByKey) {
		case ORDERBY_EMPLOYEEID:
			orderByValue = EMPLOYEE_ID;
			break;
		case ORDERBY_FIRSTNAME:
			orderByValue = FIRST_NAME;
			break;
		case ORDERBY_LASTNAME:
			orderByValue = LAST_NAME;
			break;
		default:
			orderByValue = EMPLOYEE_ID;
		}
		return orderByValue;
	}

	private PageRequest generatePageRequest(int pageNum, int pageSize, String sortingOrder, String... properties) {
		Sort sort = null;
		switch (sortingOrder) {
		case ASCENDING_ORDER:
			sort = Sort.by(Direction.ASC, properties);
			break;
		case DESCENDING_ORDER:
			sort = Sort.by(Direction.DESC, properties);
			break;
		default:
			sort = Sort.by(Direction.ASC, properties);
			break;
		}

		return PageRequest.of(pageNum, pageSize, sort);
	}	
}
