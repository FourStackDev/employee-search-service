package org.fourstack.employeesearch.controllers;

import static org.fourstack.employeesearch.constants.EmployeeSearchConstants.EMPLOYEE_ID;

import org.fourstack.employeesearch.models.Employee;
import org.fourstack.employeesearch.services.EmployeeSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Api(tags = {
		"Employee Search Controller" }, value = "Enables api's to do search operation on Employees based on different Criteria.")
public class EmployeeSearchController {

	@Autowired
	private EmployeeSearchService empService;

	@GetMapping("/test")
	public String test() {
		return "Hello";
	}

	@Tag(name = "Employee Search Controller :: V1 - API's")
	@ApiOperation(value = "API to get Page of Employees", produces = "application/json", 
			httpMethod = "GET", notes = "API end point to fetch Page of Employees")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful retrival of Page"), 
			@ApiResponse(responseCode = "401", description = "UnAuthorized Access - You are not authorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden - Insufficient previlage to access the resource"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Some issue occurred"),
			@ApiResponse(responseCode = "504", description = "Gateway Timeout - Timeout occurred")})	
	@GetMapping(path = "/v1/employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<?>> getEmployeesDetails(
			@ApiParam(value = "page Number", name = "pageNum", type = "Integer") @RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
			@ApiParam(value = "page Size", name = "pageSize", type = "Integer") @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
			@ApiParam(value = "Sorting order", name = "sortOrder", allowableValues = "ASC, DESC") @RequestParam(name = "sortOrder", defaultValue = "ASC") String sortingOrder) {
		return new ResponseEntity<Page<?>>(empService.fetchEmployees(pageNum, pageSize, sortingOrder), HttpStatus.OK);
	}

	@Tag(name = "Employee Search Controller :: V1 - API's")
	@ApiOperation(value = "API to get Page of Employees - Reurns only First and Last names", produces = "application/json", 
			httpMethod = "GET", 
			notes = "API end point to fetch Page of Employees (Filtered by Firstname or Lastname) - Reurns Objects with only First and Last names")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful retrival of Page"),
			@ApiResponse(responseCode = "401", description = "UnAuthorized Access - You are not authorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden - Insufficient previlage to access the resource"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Some issue occurred"),
			@ApiResponse(responseCode = "504", description = "Gateway Timeout - Timeout occurred") })
	@GetMapping(path = "/v1/employees-first-last-names", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<?>> getOnlyEmployeeFirstAndLastNames(
			@ApiParam(value = "page Number", name = "pageNum", type = "Integer") @RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
			@ApiParam(value = "page Size", name = "pageSize", type = "Integer")  @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
			@ApiParam(value = "Sorting order", name = "sortOrder", allowableValues = "ASC, DESC") @RequestParam(name = "sortOrder", defaultValue = "ASC") String sortingOrder,
			@ApiParam(value = "First Name", name = "firstname", example="Vinay") @RequestParam(name = "firstname", required = false) String firstName,
			@ApiParam(value = "Last Name", name = "lastname", example = "Kumar") @RequestParam(name = "lastname", required = false) String lastName) {

		if ((firstName != null && !firstName.isEmpty()) && (lastName != null && !lastName.isEmpty())) {
			return new ResponseEntity<Page<?>>(empService.fetchOnlyFirstAndLastNamesOfEmployees(firstName, lastName,
					pageNum, pageSize, sortingOrder), HttpStatus.OK);
		}

		return new ResponseEntity<Page<?>>(
				empService.fetchOnlyFirstAndLastNamesOfEmployees(pageNum, pageSize, sortingOrder), HttpStatus.OK);

	}
	
	@Tag(name = "Employee Search Controller :: V1 - API's")
	@ApiOperation(value = "API to get Employee by Id", produces = "application/json", 
			httpMethod = "GET", notes = "API end point to get Employee by Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful retrival of Employee Object"),
			@ApiResponse(responseCode = "401", description = "UnAuthorized Access - You are not authorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden - Insufficient previlage to access the resource"),
			@ApiResponse(responseCode = "404", description = "Not Found - Requested Resource not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Some issue occurred"),
			@ApiResponse(responseCode = "504", description = "Gateway Timeout - Timeout occurred") })
	@GetMapping(path = "/v1/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployeeById(
			@ApiParam(value = "Employee Id", name = "id", required = true) @PathVariable(name = "id", value = "id") String id) {
		return new ResponseEntity<Employee>(empService.fetchEmployeeById(id), HttpStatus.OK);
	}
	
	@Tag(name = "Employee Search Controller :: V1 - API's")
	@ApiOperation(value = "API to get Employee by Mobile Number", produces = "application/json", 
			httpMethod = "GET", notes = "API end point to get Employee by Mobile Number")
			@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful retrival of Employee Object"),
			@ApiResponse(responseCode = "401", description = "UnAuthorized Access - You are not authorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden - Insufficient previlage to access the resource"),
			@ApiResponse(responseCode = "404", description = "Not Found - Requested Resource not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Some issue occurred"),
			@ApiResponse(responseCode = "504", description = "Gateway Timeout - Timeout occurred") })
	@GetMapping(path = "/v1/search-employee-by-mobile/{mobileNum}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployeeByMobileNumber(
			@ApiParam(value = "Mobile Number", name = "mobileNum", required = true) @PathVariable(name = "mobileNum", value = "mobileNum") String mobileNum) {
		return new ResponseEntity<Employee>(empService.fetchEmployeeByMobileNumber(mobileNum), HttpStatus.OK);
	}
	
	@Tag(name = "Employee Search Controller :: V1 - API's")
	@ApiOperation(value = "API to get Employee by Mail Id", produces = "application/json", 
			httpMethod = "GET", notes = "API end point to get Employee by Mail Id")
			@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful retrival of Employee Object"),
			@ApiResponse(responseCode = "401", description = "UnAuthorized Access - You are not authorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden - Insufficient previlage to access the resource"),
			@ApiResponse(responseCode = "404", description = "Not Found - Requested Resource not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Some issue occurred"),
			@ApiResponse(responseCode = "504", description = "Gateway Timeout - Timeout occurred") })
	@GetMapping(path = "/v1/search-employee-by-mail/{mailId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployeeByMailId(
			@ApiParam(value = "Mail Id", name = "mailId", required = true) @PathVariable(name = "mailId", value = "mailId") String mailId) {
		return new ResponseEntity<Employee>(empService.fetchEmployeeByMailId(mailId), HttpStatus.OK);
	}
	
	
	@Tag(name = "Employee Search Controller :: V2 - API's")
	@ApiOperation(value = "API to get Page of Employees - Enhanced to get OrderBy value from User", produces = "application/json", 
			httpMethod = "GET", notes = "API end point to fetch Page of Employees")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful retrival of Page"), 
			@ApiResponse(responseCode = "401", description = "UnAuthorized Access - You are not authorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden - Insufficient previlage to access the resource"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Some issue occurred"),
			@ApiResponse(responseCode = "504", description = "Gateway Timeout - Timeout occurred")})	
	@GetMapping(path = "/v2/employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<?>> getEmployeesDetailsV2(
			@ApiParam(value = "page Number", name = "pageNum", type = "Integer") @RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
			@ApiParam(value = "page Size", name = "pageSize", type = "Integer") @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
			@ApiParam(value = "Sorting order", name = "sortOrder", allowableValues = "ASC, DESC") @RequestParam(name = "sortOrder", defaultValue = "ASC") String sortingOrder,
			@ApiParam(value = "Order By", name= "orderBy", allowableValues = "EMPLOYEE ID, FIRST NAME, LAST NAME") @RequestParam(name = "orderBy", defaultValue = EMPLOYEE_ID) String orderBy) {
		return new ResponseEntity<Page<?>>(empService.fetchEmployees(pageNum, pageSize, sortingOrder, orderBy), HttpStatus.OK);
	}

}
