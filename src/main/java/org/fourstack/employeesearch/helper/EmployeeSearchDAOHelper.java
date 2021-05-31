package org.fourstack.employeesearch.helper;

import static org.fourstack.employeesearch.constants.EmployeeSearchConstants.DIGIT_ONE;
import static org.fourstack.employeesearch.constants.EmployeeSearchConstants.DIGIT_ZERO;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_1;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_10;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_11;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_12;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_13;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_14;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_15;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_16;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_17;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_18;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_19;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_2;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_20;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_21;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_22;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_23;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_24;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_25;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_26;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_27;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_28;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_29;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_3;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_4;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_5;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_6;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_7;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_8;
import static org.fourstack.employeesearch.constants.SearchCriteriaConstants.SEARCH_CRITERIA_9;
import static org.fourstack.employeesearch.utils.CommonUtils.isValidString;

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

	public Employee getEmployeeByEmail(String mailId) {
		try {
			Optional<Employee> optionalEmployee = empRepository.findByMail(mailId);
			if (optionalEmployee.isPresent())
				return optionalEmployee.get();
		} catch (Exception e) {

		}
		return null;
	}

	public Employee getEmployeeByMobileNumber(String mobileNumber) {
		try {
			Optional<Employee> optionalEmployee = empRepository.findByMobileNum(mobileNumber);
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
	
	public Page<Employee> getEmployeesFirstAndLastNameObjects(String firstName, String lastName, Pageable page) {
		return empRepository.findEmployeesWithOnlyFirstAndLastnameAsObject(firstName, lastName, page);
	}

	public Page<Employee> getEmployeesByCriteria(EmpSearchData data, Pageable page) {
		Page<Employee> employeePage = null;
		if (data != null) {
			String searchCriteria = generateSearchCriteria(data);

			switch (searchCriteria) {
			case SEARCH_CRITERIA_1:
				employeePage = empRepository.findEmployeePageByFirstnameIgnoreCase(data.getFirstName(), page);
				break;
			case SEARCH_CRITERIA_2:
				employeePage = empRepository.findEmployeesByLastnameIgnoreCase(data.getLastName(), page);
				break;
			case SEARCH_CRITERIA_3:
				employeePage = empRepository.findEmployeesByMiddlenameIgnoreCase(data.getMiddleName(), page);
				break;
			case SEARCH_CRITERIA_4:
				employeePage = empRepository.findEmployeesByFirstnameAndLastnameIgnoreCase(data.getFirstName(),
						data.getLastName(), page);
				break;
			case SEARCH_CRITERIA_5:
				employeePage = empRepository.findEmployeePageByFirstLastAndMiddleName(data.getFirstName(),
						data.getLastName(), data.getMiddleName(), page);
				break;
			case SEARCH_CRITERIA_6:
				employeePage = empRepository.findEmployeesByRoleIgnoreCase(data.getRoleName(), page);
				break;
			case SEARCH_CRITERIA_7:
				employeePage = empRepository.findEmployeesByProjectName(data.getProjectName(), page);
				break;
			case SEARCH_CRITERIA_8:
				employeePage = empRepository.findEmployeesByDepartmentName(data.getDepartmentName(), page);
				break;
			case SEARCH_CRITERIA_9:
				employeePage = empRepository.findEmployeesByAccountName(data.getAccountName(), page);
				break;
			case SEARCH_CRITERIA_10:
				employeePage = empRepository.findEmployeesByLocation(data.getLocation(), page);
				break;
			case SEARCH_CRITERIA_11:
				employeePage = empRepository.findEmployeesByFirstNameAndRole(data.getFirstName(), data.getRoleName(),
						page);
				break;
			case SEARCH_CRITERIA_12:
				employeePage = empRepository.findEmployeesByFirstNameAndProject(data.getFirstName(),
						data.getProjectName(), page);
				break;
			case SEARCH_CRITERIA_13:
				employeePage = empRepository.findEmployeesByFirstNameAndDepartment(data.getFirstName(),
						data.getDepartmentName(), page);
				break;
			case SEARCH_CRITERIA_14:
				employeePage = empRepository.findEmployeesByFirstNameAndAccount(data.getFirstName(),
						data.getAccountName(), page);
				break;
			case SEARCH_CRITERIA_15:
				employeePage = empRepository.findEmployeesByFirstNameAndLocation(data.getFirstName(),
						data.getLocation(), page);
				break;
			case SEARCH_CRITERIA_16:
				employeePage = empRepository.findEmployeesBylastNameAndRole(data.getLastName(), data.getRoleName(),
						page);
				break;
			case SEARCH_CRITERIA_17:
				employeePage = empRepository.findEmployeesByLastNameAndProject(data.getLastName(),
						data.getProjectName(), page);
				break;
			case SEARCH_CRITERIA_18:
				employeePage = empRepository.findEmployeesByLastNameAndDepartment(data.getLastName(),
						data.getDepartmentName(), page);
				break;
			case SEARCH_CRITERIA_19:
				employeePage = empRepository.findEmployeesByLastNameAndAccountName(data.getLastName(),
						data.getAccountName(), page);
				break;
			case SEARCH_CRITERIA_20:
				employeePage = empRepository.findEmployeesByLastNameAndLocation(data.getLastName(), data.getLocation(),
						page);
				break;
			case SEARCH_CRITERIA_21:
				employeePage = empRepository.findEmployeesByRoleAndProjectName(data.getRoleName(),
						data.getProjectName(), page);
				break;
			case SEARCH_CRITERIA_22:
				employeePage = empRepository.findEmployeesByProjectAndLocation(data.getProjectName(),
						data.getLocation(), page);
				break;
			case SEARCH_CRITERIA_23:
				employeePage = empRepository.findEmployeesByProjectAndAccountName(data.getProjectName(),
						data.getAccountName(), page);
				break;
			case SEARCH_CRITERIA_24:
				employeePage = empRepository.findEmployeesByRoleAndProjectNameAndAccountName(data.getRoleName(),
						data.getProjectName(), data.getAccountName(), page);
				break;
			case SEARCH_CRITERIA_25:
				employeePage = empRepository.findEmployeesByFirstNameLastNameAndRole(data.getFirstName(),
						data.getLastName(), data.getRoleName(), page);
				break;
			case SEARCH_CRITERIA_26:
				employeePage = empRepository.findEmployeesByFirstNameLastNameAndAccountName(data.getFirstName(),
						data.getLastName(), data.getAccountName(), page);
				break;
			case SEARCH_CRITERIA_27:
				employeePage = empRepository.findEmployeesByFirstLastMiddleNameAndRole(data.getFirstName(),
						data.getLastName(), data.getMiddleName(), data.getRoleName(), page);
				break;
			case SEARCH_CRITERIA_28:
				employeePage = empRepository.findEmployeesByFirstLastMiddleNameAndProjectName(data.getFirstName(),
						data.getLastName(), data.getMiddleName(), data.getProjectName(), page);
				break;
			case SEARCH_CRITERIA_29:
				employeePage = empRepository.findEmployeesByFirstLastMiddleAndRoleAndProjectName(data.getFirstName(),
						data.getLastName(), data.getMiddleName(), data.getRoleName(), data.getProjectName(), page);
				break;
			}
		}
		return employeePage;
	}

	private static String generateSearchCriteria(EmpSearchData data) {
		StringBuffer strBuffer = new StringBuffer();
		if (data != null) {
			strBuffer.append(isValidString(data.getFirstName()) ? DIGIT_ONE : DIGIT_ZERO);
			strBuffer.append(isValidString(data.getLastName()) ? DIGIT_ONE : DIGIT_ZERO);
			strBuffer.append(isValidString(data.getMiddleName()) ? DIGIT_ONE : DIGIT_ZERO);
			strBuffer.append(isValidString(data.getRoleName()) ? DIGIT_ONE : DIGIT_ZERO);
			strBuffer.append(isValidString(data.getProjectName()) ? DIGIT_ONE : DIGIT_ZERO);
			strBuffer.append(isValidString(data.getDepartmentName()) ? DIGIT_ONE : DIGIT_ZERO);
			strBuffer.append(isValidString(data.getAccountName()) ? DIGIT_ONE : DIGIT_ZERO);
			strBuffer.append(isValidString(data.getLocation()) ? DIGIT_ONE : DIGIT_ZERO);
		}

		return strBuffer.length() == 0 ? null : strBuffer.toString();
	}

}
