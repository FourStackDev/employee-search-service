package org.fourstack.employeesearch.controllers;

import org.fourstack.employeesearch.services.EmployeeSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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

	@ApiOperation(value = "API to get Page of Employees", produces = "application/json", 
			httpMethod = "GET", notes = "API end point to fetch Page of Employees")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful retrival of Page"), 
			@ApiResponse(responseCode = "401", description = "UnAuthorized Access - You are not authorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden - Insufficient previlage to access the resource"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Some issue occurred"),
			@ApiResponse(responseCode = "504", description = "Gateway Timeout - Timeout occurred")})	
	@GetMapping(path = "/v1/employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<?>> getEmployeesDetails(@RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(name = "sortOrder", defaultValue = "ASC") String sortingOrder) {
		return new ResponseEntity<Page<?>>(empService.fetchEmployees(pageNum, pageSize, sortingOrder), HttpStatus.OK);
	}

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
			@RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(name = "sortOrder", defaultValue = "ASC") String sortingOrder,
			@RequestParam(name = "firstname", required = false) String firstName,
			@RequestParam(name = "lastname", required = false) String lastName) {

		if ((firstName != null && !firstName.isEmpty()) && (lastName != null && !lastName.isEmpty())) {
			return new ResponseEntity<Page<?>>(empService.fetchOnlyFirstAndLastNamesOfEmployees(firstName, lastName,
					pageNum, pageSize, sortingOrder), HttpStatus.OK);
		}

		return new ResponseEntity<Page<?>>(
				empService.fetchOnlyFirstAndLastNamesOfEmployees(pageNum, pageSize, sortingOrder), HttpStatus.OK);

	}

}
