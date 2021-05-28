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

@RestController
@RequestMapping("/api")
public class EmployeeSearchController {

	@Autowired
	private EmployeeSearchService empService;

	@GetMapping("/test")
	public String test() {
		return "Hello";
	}

	@GetMapping(path = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<?>> getEmployeesDetails(@RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(name = "sortOrder", defaultValue = "ASC") String sortingOrder) {
		return new ResponseEntity<Page<?>>(empService.fetchEmployees(pageNum, pageSize, sortingOrder), HttpStatus.OK);
	}

	@GetMapping(path = "/employees-first-last-names", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<?>> getOnlyEmployeeFirstAndLastNames(
			@RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(name = "sortOrder", defaultValue = "ASC") String sortingOrder) {

		return new ResponseEntity<Page<?>>(
				empService.fecthOnlyFirstAndLastNamesOfEmployees(pageNum, pageSize, sortingOrder), HttpStatus.OK);

	}

}
