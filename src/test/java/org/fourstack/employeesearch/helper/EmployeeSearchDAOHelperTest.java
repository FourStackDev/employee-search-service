package org.fourstack.employeesearch.helper;



import java.util.List;
import java.util.Optional;

import org.fourstack.employeesearch.constants.EmployeeSearchConstants;
import org.fourstack.employeesearch.dao.EmployeeRepository;
import org.fourstack.employeesearch.models.Employee;
import org.fourstack.employeesearch.utils.CommonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeSearchDAOHelperTest {

	@Mock
	private EmployeeRepository empRepository;

	@InjectMocks
	private EmployeeSearchDAOHelper daoHelper;

	@BeforeEach
	public void doInitilaization() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void test_getEmployeeById() {
		log.info("EmployeeSearchDAOHelperTest: test_getEmployeeById() Method - Start");

		// load and create Employee Object by using CommonUtils
		Employee emp = CommonUtils.deserializeJsonStringToResource(CommonUtils.getFileContentUsingResourceStream(
				"json-files/emp-data.json", this.getClass().getClassLoader()), Employee.class);

		// Create Optional Container for Employee
		Optional<Employee> optionalEmployee = Optional.of(emp);

		// Mock the method (EmployeeRespoitory.findById() method)
		Mockito.when(empRepository.findById(Mockito.anyString())).thenReturn(optionalEmployee);

		// Assert the target method with Not Null Object
		Assertions.assertNotNull(daoHelper.getEmployeeById(Mockito.anyString()));
		log.info("EmployeeSearchDAOHelperTest: test_getEmployeeById() Method - End");
	}
	
	@Test
	public void test_getEmployeePage() {
		log.info("EmployeeSearchDAOHelperTest: test_getEmployeePage() Method - Start");
		
		// Create a Page Object 
		Pageable page = getPageObject();
		
		// Create Employee Array Objects
		Employee[] empArray = CommonUtils.deserializeJsonStringToResource(CommonUtils.getFileContentUsingResourceStream("json-files/emp-list-data.json", this.getClass().getClassLoader()), Employee[].class);
		
		// Convert Employee Array to List
		List<Employee> empList = List.of(empArray);
		
		// Create Employee Page
		Page<Employee> empPage = new PageImpl<>(empList);
		
		// Mock the Methods
		Mockito.when(empRepository.findAll(page)).thenReturn(empPage);
		Assertions.assertNotNull(daoHelper.getEmployeePage(page));
		
		log.info("EmployeeSearchDAOHelperTest: test_getEmployeePage() Method - End");
	}
	
	private PageRequest getPageObject() {
		return PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, EmployeeSearchConstants.EMPLOYEE_ID));
	}
}
