package com.project.ems3.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.ems3.exception.ResourceNotFoundException;
import com.project.ems3.model.Department;
import com.project.ems3.model.Employee;
import com.project.ems3.repository.EmployeeRepository;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@PersistenceContext
    private EntityManager entityManager;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping("/employees/search/{name}")
	public List<Employee> getEmployeeByName(@PathVariable(value = "name") String employeeName) {
			return employeeRepository.serchUserByName(employeeName);
	}

	//http://localhost:8080/employees
	
	@GetMapping("/employees/all-employees")
	public ArrayList getAllEmployeeFields() {
		 Query q = entityManager.createQuery("SELECT emp, dn from employee emp, department dn  WHERE employee_department = department_id");
		 List<Object[]> employee = q.getResultList();
		 ArrayList<HashMap<String, String>> resultArray = new ArrayList();
		 //////////////
		 for ( Object[] row : employee ) {
			 //table and entity name
			  Employee employee_details = (Employee)row[ 0 ];
			  Department department_details = (Department)row[ 1 ];  
			    HashMap<String, String> results = new HashMap();  
			    //display employee data
			    results.put("department_name",department_details.getDepartment_name());
			    results.put("employee_id",String.valueOf(employee_details.getEmployee_id()));
				results.put("employee_first_name",employee_details.getEmployee_first_name());
				results.put("employee_last_name",employee_details.getEmployee_last_name());
				results.put("employee_mobile",employee_details.getEmployee_mobile());
				results.put("employee_email",employee_details.getEmployee_email());
			    resultArray.add(results);
			 
		 }	 

        return resultArray;
	}
	
	//retrieving the data
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	//create
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

//update
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		final Employee updatedEmployee = employeeRepository.save(employeeDetails);
		return ResponseEntity.ok(updatedEmployee);
	}
//delete

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
