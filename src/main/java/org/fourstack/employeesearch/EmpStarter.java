package org.fourstack.employeesearch;

import java.util.Arrays;
import java.util.List;

import org.fourstack.employeesearch.dao.EmployeeRepository;
import org.fourstack.employeesearch.models.Employee;
import org.fourstack.employeesearch.utils.CommonUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EmpStarter implements CommandLineRunner {

	private final EmployeeRepository empRepository;

	public EmpStarter(EmployeeRepository empRepository) {
		this.empRepository = empRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		empRepository.saveAll(getEmpList());
	}

	private List<Employee> getEmpList() {

		String content = CommonUtils.getFileContentUsingResourceStream("json-files/emp-test-data.json",
				this.getClass().getClassLoader());

		Employee[] empList = CommonUtils.deserializeJsonStringToResource(content, Employee[].class);
		return Arrays.asList(empList);
	}

}
