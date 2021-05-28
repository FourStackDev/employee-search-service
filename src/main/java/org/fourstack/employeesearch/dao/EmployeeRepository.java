package org.fourstack.employeesearch.dao;

import java.util.List;
import java.util.Optional;

import org.fourstack.employeesearch.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Repository class defined to support the MongoDB CRUD Operations related to
 * Employee Search.
 * <p>
 * Below are the extra functions defined for the EmployeeRepository.
 * <ol>
 * <li><i>Search By Mobile Number</i></li>
 * <li><i>Search By Mail Id</i></li>
 * <li><i>Search By FirstName</i></li>
 * <li><i>Search By LastName</i></li>
 * <li><i>Search By MiddleName</i></li>
 * <li><i>Search By First And Last Name</i></li>
 * <li><i>Search By First, Last And Middle Name</i></li>
 * <li><i>Search By Role</i></li>
 * <li><i>Search By Project Name </i></li>
 * <li><i>Search By Department Name</i></li>
 * <li><i>Search By Account Name </i></li>
 * <li><i>Search By Location</i></li>
 * <li><i>Search By FirstName and Role</i></li>
 * <li><i>Search By FirstName and Project Name</i></li>
 * <li><i>Search By FirstName and Department Name</i></li>
 * <li><i>Search By FirstName and Account Name</i></li>
 * <li><i>Search By FirstName and Location</i></li>
 * <li><i>Search By LastName and Role</i></li>
 * <li><i>Search By LastName and Project Name</i></li>
 * <li><i>Search By LastName and Department Name</i></li>
 * <li><i>Search By LastName and Account Name</i></li>
 * <li><i>Search By LastName and Location</i></li>
 * <li><i>Search By Role and ProjectName</i></li>
 * <li><i>Search By Project Name and Location</i></li>
 * <li><i>Search by Project Name and Account Name</i></li>
 * <li><i>Search By Role, Project Name and Account Name</i></li>
 * <li><i>Search By FirstName, LastName and Role</i></li>
 * <li><i>Search By FirstName, LastName and Account Name</i></li>
 * <li><i>Search By FirstName, LastName, MiddleName and Role</i></li>
 * <li><i>Search By FirstName, LastName, MiddleName and ProjectName</i></li>
 * <li><i>Search By FirstName, LastName, MiddleName, Role and
 * ProjectName</i></li>
 * <li><i>Search By FirstName and LastName, and returns only FirstName and
 * LastName</i></li>
 * <li><i>Return Page of Employees with only FirstName and LastName</i></li>
 * <ol>
 * </p>
 * 
 * @author Manjunath HM
 * 
 */
public interface EmployeeRepository extends MongoRepository<Employee, String> {

	// * Search By Mobile Number
	/**
	 * Search Employee based on Mobile number provided.
	 * 
	 * @param mobileNum Mobile Number of Employee
	 * @return Employee Object in Optional container. Returns Empty container if No
	 *         Employee found.
	 */
	Optional<Employee> findByMobileNum(String mobileNum);

	// * Search By Mail Id
	/**
	 * Search Employee based on Mail Id provided.
	 * 
	 * @param email Email Id of Employee
	 * @return Employee Object in Optional container. Returns Empty container if No
	 *         Employee found.
	 */
	Optional<Employee> findByMail(String email);

	// * Search By FirstName (List and Page)
	/**
	 * Search Employee List based on First Name.
	 * 
	 * @param firstName Search Parameter FirstName
	 * @return List of Employees (Filtered based on FirstName)
	 */
	List<Employee> findByFirstNameIgnoreCase(String firstName);

	// using JSON query method
	/**
	 * Search Employees by First Name and fetch a page of Employee Objects.
	 * 
	 * @param firstName Search parameter FirstName
	 * @param page      PageRequest Object
	 * @return Page of Employees (Filtered based on FirstName)
	 */
	@Query(value = "{'firstName' : {$regex: ?0, $options: 'i'}}")
	Page<Employee> findEmployeePageByFirstnameIgnoreCase(String firstName, Pageable page);

	// * Search By LastName (List and Page)
	/**
	 * Search Employees based on LastName and returns List of Employees.
	 * 
	 * @param lastName Search Parameter LastName
	 * @return List of Employees(Filtered based on LastName)
	 */
	List<Employee> findByLastNameIgnoreCase(String lastName);

	/**
	 * Search Employees based on LastName and returns Page of Employees.
	 * 
	 * @param lastName Search Parameter LastName
	 * @param page     PageRequest Object
	 * @return Page of Employees (Filtered based on LastName)
	 */
	@Query(value = "{'lastName' : {$regex: ?0, $options: 'i'}}")
	Page<Employee> findEmployeesByLastnameignoreCase(String lastName, Pageable page);

	// * Search By MiddleName (List and Page)
	/**
	 * Search Employees based on MiddleName and returns List of Employees.
	 * 
	 * @param middleName Search Parameter MiddleName.
	 * @return List of Employees (Filtered based on MiddleName)
	 */
	List<Employee> findByMiddleNameIgnoreCase(String middleName);

	/**
	 * Search Employees based on MiddleName and returns Page of Employees.
	 * 
	 * @param middleName Search Parameter MiddleName
	 * @param page       PageRequest Object
	 * @return Page of Employees (Filtered based on MiddleName)
	 */
	// @Query("{'middleName' : ?0}") >> This can be used as case sensitive query.
	@Query("{'middleName' : {$regex: ?0, $options: 'i'}}")
	Page<Employee> findEmployeesByMiddlenameIgnoreCase(String middleName, Pageable page);

	// * Search By First And Last Name (List and Page)
	/**
	 * Search Employees based on FirstName and LastName and return List of
	 * Employees.
	 * 
	 * @param firstName Search Parameter FirstName
	 * @param lastName  Search Parameter LastName
	 * @return List of Employees (Filtered based on FirstName and LastName)
	 */
	List<Employee> findByFirstNameAndLastNameAllIgnoreCase(String firstName, String lastName);

	/**
	 * Search Employees based on FirstName and LastName and return Page of
	 * Employees.
	 * 
	 * @param firstName Search Parameter FirstName
	 * @param lastName  Search Parameter LastName
	 * @param page      PageRequest Object
	 * @return Page of Employees (Filtered based on FirstName and LastName)
	 */
	@Query(value = "{'firstName' : {$regex: ?0, $options: 'i'}, 'lastName' : {$regex: ?1, $options: 'i'} }")
	Page<Employee> findEmployeesByFirstnameAndLastnameIgnoreCase(String firstName, String lastName, Pageable page);

	// * Search By First, Last And Middle Name (List and Page)
	/**
	 * Search Employees based on FirstName, LastName and MiddleName. Returns List of
	 * Employees.
	 * 
	 * @param firstName  Search Parameter FirstName
	 * @param lastName   Search Parameter LastName
	 * @param middleName Search Parameter MiddleName
	 * @return List of Employees (Filtered based on FirstName, LastName and
	 *         MiddleName)
	 */
	List<Employee> findByFirstNameAndLastNameAndMiddleNameAllIgnoreCase(String firstName, String lastName,
			String middleName);

	/**
	 * Search Employees based on FirstName, LastName and MiddleName. Returns Page of
	 * Employees.
	 * 
	 * @param firstName  Search Parameter FirstName
	 * @param lastName   Search Parameter LastName
	 * @param middleName Search Parameter MiddleName
	 * @param page       PageRequest object.
	 * @return Page of Employees (Filtered based on FirstName, LastName and
	 *         MiddleName)
	 */
	@Query(value = "{'firstName': {$regex: ?0, $options: 'i'}, 'lastName': {$regex: ?1, $options: 'i'} , middleName: {$regex: ?2, $options: 'i'}}")
	Page<Employee> findEmployeePageByFirstLastAndMiddleName(String firstName, String lastName, String middleName,
			Pageable page);

	// * Search By Role
	/**
	 * Search Employees based on Role. Returns Page of Employees.
	 * 
	 * @param roleName Search Parameter Role Name.
	 * @param page     PageRequest object.
	 * @return Page of Employees (Filtered based on Role)
	 */
	@Query("{'role.roleName' : {$regex: ?0, $options: 'i'}}")
	Page<Employee> findEmployeesByRoleIgnoreCase(String roleName, Pageable page);

	// * Search By Project Name
	/**
	 * Search Employees based on Project Name. Returns Page of Employees.
	 * 
	 * @param projectName Search Parameter Project Name
	 * @param page        PageRequest Object
	 * @return Page of Employees (Filtered based on Project Name)
	 */
	@Query("{'project.projectName' : {$regex: ?0, $options: 'i'}}")
	Page<Employee> findEmployeesByProjectName(String projectName, Pageable page);

	// * Search By Department Name
	/**
	 * Search Employees based on Department Name. Returns Page of Employees.
	 * 
	 * @param deptName Search Parameter Department Name
	 * @param page     PageRequest Object
	 * @return Page of Employees (Filtered based on Department Name)
	 */
	@Query("{'department.name' : {$regex: ?0, $options: 'i'}}")
	Page<Employee> findEmployeesByDepartmentName(String deptName, Pageable page);

	// * Search By Account Name
	/**
	 * Search Employees based on Account Name. Returns Page of Employees.
	 * 
	 * @param accountName Search Parameter Account Name
	 * @param page        PageRequest Object
	 * @return Page of Employees (Filtered based on Account Name)
	 */
	@Query("{'account.accountName': {$regex: ?0, $options: 'i'}}")
	Page<Employee> findEmployeesByAccountName(String accountName, Pageable page);

	// * Search By Location
	/**
	 * Search Employees based on Location . Returns List of Employees.
	 * 
	 * @param location Search Parameter Location
	 * @return List of Employees (Filtered based on Location)
	 */
	List<Employee> findByLocationIgnoreCase(String location);

	/**
	 * Search Employees based on Location. Returns Page of Employees.
	 * 
	 * @param location Search Parameter Location
	 * @param page     PageRequest Object
	 * @return Page of Employees (Filtered based on Location)
	 */
	@Query("{'location': {$regex: ?0, $options: 'i'}}")
	Page<Employee> findEmployeesByLocation(String location, Pageable page);

	// * Search By FirstName and Role
	/**
	 * Search Employees based on FirstName and Role. Returns Page of Employees.
	 * 
	 * @param firstName Search Parameter FirstName
	 * @param role      Search Parameter Role
	 * @param page      PageRequest Object
	 * @return Page of Employees (Filtered based on FirstName and Role)
	 */
	@Query("{'firstName': {$regex: ?0, $options: 'i'}, 'role.roleName':{$regex: ?1, $options: 'i'}}")
	Page<Employee> findEmployeesByFirstNameAndRole(String firstName, String role, Pageable page);

	// * Search By FirstName and Project Name
	/**
	 * Search Employees based on FirstName and Project Name. Returns Page of
	 * Employees.
	 * 
	 * @param firstName   Search Parameter FirstName
	 * @param projectName Search Parameter Project Name
	 * @param page        PageRequest Object
	 * @return Page of Employees (Filtered based on FirstName and Project Name)
	 */
	@Query("{'firstName': {$regex: ?0, $options: 'i'}, 'project.projectName': {$regex: ?1, $options: 'i'}}")
	Page<Employee> findEmployeesByFirstNameAndProject(String firstName, String projectName, Pageable page);

	// * Search By FirstName and Department Name
	/**
	 * Search Employees based on FirstName and Department Name. Returns Page of
	 * Employees.
	 * 
	 * @param firstName Search Parameter FirstName
	 * @param deptName  Search Parameter Department Name
	 * @param page      PageRequest Object
	 * @return Page of Employees (Filtered based on FirstName and Department Name)
	 */
	@Query("{'firstName': {$regex: ?0, $options: 'i'}, 'department.name': {$regex: ?1, $options: 'i'}}")
	Page<Employee> findEmployeesByFirstNameAndDepartment(String firstName, String deptName, Pageable page);

	// * Search By FirstName and Account Name
	/**
	 * Search Employees based on FirstName and Account Name. Returns Page of
	 * Employees.
	 * 
	 * @param firstName   Search Parameter FirstName
	 * @param accountName Search Parameter FirstName
	 * @param page        PageRequest Object
	 * @return Page of Employees (Filtered based on FirstName and Account Name)
	 */
	@Query("{'firstName':{$regex: ?0, $options: 'i'}, 'account.accountName':{$regex: ?1, $options: 'i'}}")
	Page<Employee> findEmployeesByFirstNameAndAccount(String firstName, String accountName, Pageable page);

	// * Search By FirstName and Location
	/**
	 * Search Employees based on FirstName and Location. Returns Page of Employees.
	 * 
	 * @param firstName Search Parameter FirstName
	 * @param location  Search Parameter Location
	 * @param page      PageRequest Object
	 * @return Page of Employees (Filtered based on FirstName and Location)
	 */
	@Query("{'firstName': {$regex: ?0, $options: 'i'}, 'location': {$regex: ?1, $options: 'i'}}")
	Page<Employee> findEmployeesByFirstNameAndLocation(String firstName, String location, Pageable page);

	// * Search By LastName and Role
	/**
	 * Search Employees based on LastName and Role. Returns Page of Employees.
	 * 
	 * @param lastName Search Parameter LastName
	 * @param role     Search Parameter Role
	 * @param page     PageRequest Object
	 * @return Page of Employees (Filtered based on LastName and Role)
	 */
	@Query("{'lastName': {$regex: ?0, $options: 'i'}, 'role.roleName': {$regex: ?1, $options: 'i'}}")
	Page<Employee> findEmployeesBylastNameAndRole(String lastName, String role, Pageable page);

	// * Search By LastName and Project Name
	/**
	 * Search Employees based on LastName and ProjectName. Returns Page of
	 * Employees.
	 * 
	 * @param lastName    Search Parameter LastName
	 * @param projectName Search Parameter Project Name
	 * @param page        PageRequest Object
	 * @return Page of Employees (Filtered based on LastName and Project Name)
	 */
	@Query("{'lastName': {$regex: ?0, $options: 'i'}, 'project.projectName': {$regex: ?1, $options: 'i'}}")
	Page<Employee> findEmployeesByLastNameAndProject(String lastName, String projectName, Pageable page);

	// * Search By LastName and Department Name
	/**
	 * Search Employees based on LastName and Department Name. Returns Page of
	 * Employees.
	 * 
	 * @param lastName Search Parameter LastName
	 * @param deptName Search Parameter Department Name
	 * @param page     PageRequest Object
	 * @return Page of Employees (Filtered based on LastName and Department Name)
	 */
	@Query("{'lastName': {$regex: ?0, $options: 'i'}, 'department.name':{$regex: ?1, $options: 'i'}}")
	Page<Employee> findEmployeesByLastNameAndDepartment(String lastName, String deptName, Pageable page);

	// * Search By LastName and Account Name
	/**
	 * Search Employees based on LastName and Account Name. Returns Page of
	 * Employees.
	 * 
	 * @param lastName    Search Parameter LastName
	 * @param accountName Search Parameter Account Name
	 * @param page        PageRequest Object
	 * @return Page of Employees (Filtered based on LastName and Account Name)
	 */
	@Query("{'lastName':{$regex: ?0, $options: 'i'}, 'account.accountName':{$regex: ?1, $options: 'i'}}")
	Page<Employee> findEmployeesByLastNameAndAccountName(String lastName, String accountName, Pageable page);

	// * Search By LastName and Location
	/**
	 * Search Employees based on LastName and Location. Returns Page of Employees.
	 * 
	 * @param lastName Search Parameter LastName
	 * @param location Search Parameter Location
	 * @param page     PageRequest Object
	 * @return Page of Employees (Filtered based on LastName and Location)
	 */
	@Query("{'lastName':{$regex: ?0, $options: 'i'}, 'location': {$regex: ?1, $options: 'i'}}")
	Page<Employee> findEmployeesByLastNameAndLocation(String lastName, String location, Pageable page);

	// * Search By Role and ProjectName
	/**
	 * Search Employees based on Role and Project Name. Returns Page of Employees.
	 * 
	 * @param role        Search Parameter Role
	 * @param projectName Search Parameter Project Name
	 * @param page        PageRequest Object
	 * @return Page of Employees (Filtered based on Role and Project Name)
	 */
	@Query("{'role.roleName':{$regex: ?0, $options: 'i'}, 'project.projectName':{$regex: ?1, $options: 'i'}}")
	Page<Employee> findEmployeesByRoleAndProjectName(String role, String projectName, Pageable page);

	// * Search By Project Name and Location
	/**
	 * Search Employees based on Project Name and Location. Returns Page of
	 * Employees.
	 * 
	 * @param projectName Search Parameter Project Name
	 * @param location    Search Parameter Location
	 * @param page        PageRequest Object
	 * @return Page of Employees (Filtered based on Project Name and Location)
	 */
	@Query("{'project.projectName':{$regex: ?0, $options: 'i'}, 'location': {$regex: ?1, $options: 'i'}}")
	Page<Employee> findEmployeesByProjectAndLocation(String projectName, String location, Pageable page);

	// * Search by Project Name and Account Name
	/**
	 * Search Employees based on Project Name and Account Name. Returns Page of
	 * Employees.
	 * 
	 * @param projectName Search Parameter Project Name
	 * @param accountName Search Parameter Account Name
	 * @param page        PageRequest Object
	 * @return Page of Employees (Filtered based on Project Name and Account Name)
	 */
	@Query("{'project.projectName':{$regex: ?0, $options: 'i'}, 'account.accountName': {$regex: ?1, $options: 'i'}}")
	Page<Employee> findEmployeesByProjectAndAccountName(String projectName, String accountName, Pageable page);

	// * Search By FirstName, LastName and Role
	/**
	 * Search Employees based on FirstName, LastName and Role. Returns Page of
	 * Employees.
	 * 
	 * @param firstName Search Parameter FirstName
	 * @param lastName  Search Parameter LastName
	 * @param role      Search Parameter Role
	 * @param page      PageRequest Object
	 * @return Page of Employees (Filtered based on FirstName, LastName and Role)
	 */
	@Query("{'firstName': {$regex: ?0, $options: 'i'}, 'lastName': {$regex: ?1, $options: 'i'}, 'role.roleName': {$regex: ?2, $options: 'i'}}")
	Page<Employee> findEmployeesByFirstNameLastNameAndRole(String firstName, String lastName, String role,
			Pageable page);

	// * Search By FirstName, LastName and Account Name
	/**
	 * Search Employees based on FirstName, LastName and Account Name. Returns Page
	 * of Employees.
	 * 
	 * @param firstName   Search Parameter FirstName
	 * @param lastName    Search Parameter LastName
	 * @param accountName Search Parameter Account Name
	 * @param page        PageRequest Object
	 * @return Page of Employees (Filtered based on FirstName, LastName and Account
	 *         Name)
	 */
	@Query("{'firstName': {$regex: ?0, $options: 'i'}, 'lastName': {$regex: ?1, $options: 'i'}, 'account.accountName': {$regex: ?2, $options: 'i'}}")
	Page<Employee> findEmployeesByFirstNameLastNameAndAccountName(String firstName, String lastName, String accountName,
			Pageable page);

	// * Search By Role, Project Name and Account Name
	/**
	 * Search Employees based on Role, Project Name and Account Name. Returns Page
	 * of Employees.
	 * 
	 * @param role        Search Parameter Role
	 * @param project     Search Parameter Project Name
	 * @param accountName Search Parameter Account Name
	 * @param page        PageRequest Object
	 * @return Page of Employees (Filtered based on Role, Project Name and Account
	 *         Name)
	 */
	@Query("{'role.roleName': {$regex: ?0, $options: 'i'}, 'project.projectName':{$regex: ?1, $options: 'i'}, 'account.accountName': {$regex: ?2, $options: 'i'}}")
	Page<Employee> findEmployeesByRoleAndProjectNameAndAccountName(String role, String project, String accountName,
			Pageable page);

	// * Search By FirstName, LastName, MiddleName and Role
	/**
	 * Search Employees based on FirstName, LastName, MiddleName and Role. Returns
	 * Page of Employees.
	 * 
	 * @param firstName  Search Parameter FirstName
	 * @param lastName   Search Parameter LastName
	 * @param middleName Search Parameter MiddleName
	 * @param role       Search Parameter Role
	 * @param page       PageRequest Object
	 * @return Page of Employees (Filtered based on FirstName, LastName, MiddleName
	 *         and Role)
	 */
	@Query("{'firstName': {$regex: ?0, $options: 'i'}, 'lastName': {$regex: ?1, $options: 'i'}, 'middleName': {$regex: ?2, $options: 'i'}, 'role.roleName': {$regex: ?3, $options: 'i'}}")
	Page<Employee> findEmployeesByFirstLastMiddleNameAndRole(String firstName, String lastName, String middleName,
			String role, Pageable page);

	// * Search By FirstName, LastName, MiddleName and ProjectName
	/**
	 * Search Employees based on FirstName, LastName, MiddleName and Project Name.
	 * Returns Page of Employees.
	 * 
	 * @param firstName   Search Parameter FirstName
	 * @param lastName    Search Parameter LastName
	 * @param middleName  Search Parameter MiddleName
	 * @param projectName Search Parameter Project Name
	 * @param page        PageRequest Object
	 * @return Page of Employees (Filtered based on FirstName, LastName, MiddleName
	 *         and Project Name)
	 */
	@Query("{'firstName': {$regex: ?0, $options: 'i'}, 'lastName': {$regex: ?1, $options: 'i'}, 'middleName': {$regex: ?2, $options: 'i'}, 'project.projectName': {$regex: ?3, $options: 'i'}}")
	Page<Employee> findEmployeesByFirstLastMiddleNameAndProjectName(String firstName, String lastName,
			String middleName, String projectName, Pageable page);

	// * Search By FirstName, LastName, MiddleName, Role and ProjectName
	/**
	 * Search Employees based on FirstName, LastName, MiddleName, Role and Project
	 * Name. Returns Page of Employees.
	 * 
	 * @param firstName   Search Parameter FirstName
	 * @param lastName    Search Parameter LastName
	 * @param middleName  Search Parameter MiddleName
	 * @param role        Search Parameter Role
	 * @param projectName Search Parameter Project Name
	 * @param page        PageRequest Object
	 * @return Page of Employees (Filtered based on FirstName, LastName, MiddleName,
	 *         Role and Project Name)
	 */
	@Query("{'firstName': {$regex: ?0, $options: 'i'}, 'lastName': {$regex: ?1, $options:'i'}, 'middleName':{$regex: ?2, $options:'i'}, 'role.roleName':{$regex:?3, $options:'i'}, 'project.projectName':{$regex: ?4, $options: 'i'} }")
	Page<Employee> findEmployeesByFirstLastMiddleAndRoleAndProjectName(String firstName, String lastName,
			String middleName, String role, String projectName, Pageable page);

	/**
	 * The below Query fetch the page of Employees, but it only returns the
	 * FirstName and LastName in the Object Structure.
	 * 
	 * @param firstName FirstName parameter
	 * @param lastName  LastName parameter
	 * @param page      PageRequest Object
	 * @return Page of Employees
	 */
	@Query(value = "{'firstName' : {$regex: ?0, $options: 'i'}, 'lastName' : {$regex: ?1, $options: 'i'} }", fields = "{'firstName' : 1, 'lastName': 1}")
	Page<Employee> findEmployeesWithOnlyFirstAndLastnameAsObject(String firstName, String lastName, Pageable page);

	/**
	 * Below Query returns a page of Employees. But it returns only the FirstName
	 * and LastName of the Employee Objects.
	 * 
	 * @param page PageRequest Object
	 * @return Page of Employees.
	 */
	@Query(value = "{}", fields = "{'firstName' : 1, 'lastName': 1}")
	Page<Employee> findEmployeeFirstAndLastNameObjects(Pageable page);

}
