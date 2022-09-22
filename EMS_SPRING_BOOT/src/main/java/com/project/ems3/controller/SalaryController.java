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
import com.project.ems3.model.Employee;
import com.project.ems3.model.Month;
import com.project.ems3.model.Salary;
import com.project.ems3.repository.SalaryRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class SalaryController {
	@Autowired
	private SalaryRepository salaryRepository;
	
	@PersistenceContext
    private EntityManager entityManager;

	@GetMapping("/salaries")
	public List<Salary> getAllSalarys() {
		return salaryRepository.findAll();
	}
	
	@GetMapping("/salaries/all-salaries")
	public ArrayList getAllSalaryFields() {
		 Query q = entityManager.createQuery("SELECT sal, emp, mon from salary sal, employee emp, month mon WHERE month_id = salary_month AND salary_employee_id = employee_id");
		 List<Object[]> salary = q.getResultList();
		 ArrayList<HashMap<String, String>> resultArray = new ArrayList();
		 
		 for ( Object[] row : salary ) {
			  Employee employee_details = (Employee)row[ 1 ];
			  Salary salary_details = (Salary)row[ 0 ];
			  Month month_details = (Month)row[ 2 ];
			
			  //display employee salary 
			  
			  HashMap<String, String> results = new HashMap();

			    results.put("month_name",String.valueOf(month_details.getMonth_name()));
			    results.put("employee_id",String.valueOf(employee_details.getEmployee_id()));
				results.put("employee_first_name",employee_details.getEmployee_first_name());
				results.put("salary_employee_id",String.valueOf(salary_details.getSalary_employee_id()));
				results.put("salary_month",String.valueOf(salary_details.getSalary_month()));
				results.put("salary_working_days",String.valueOf(salary_details.getSalary_working_days()));
				results.put("salary_total",String.valueOf(salary_details.getSalary_total()));
				results.put("salary_year",String.valueOf(salary_details.getSalary_year()));
			    resultArray.add(results);
		 }	 

        return resultArray;
	}
	
	@GetMapping("/salaries/{id}")
	public ResponseEntity<Salary> getSalaryById(@PathVariable(value = "id") Long salaryId)
			throws ResourceNotFoundException {
		Salary salary = salaryRepository.findById(salaryId)
				.orElseThrow(() -> new ResourceNotFoundException("Salary not found for this id :: " + salaryId));
		return ResponseEntity.ok().body(salary);
	}

	@PostMapping("/salaries")
	public Salary createSalary( @RequestBody Salary salary) {
		
		return salaryRepository.save(salary);
	}

	@PutMapping("/salaries/{id}")
	public ResponseEntity<Salary> updateSalary(@PathVariable(value = "id") Long salaryId,
			@Valid @RequestBody Salary salaryDetails) throws ResourceNotFoundException {
		final Salary updatedSalary = salaryRepository.save(salaryDetails);
		return ResponseEntity.ok(updatedSalary);
	}
	
	@DeleteMapping("/salaries/{id}")
	public Map<String, Boolean> deleteSalary(@PathVariable(value = "id") Long salaryId)
			throws ResourceNotFoundException {
		Salary salary = salaryRepository.findById(salaryId)
				.orElseThrow(() -> new ResourceNotFoundException("Salary not found for this id :: " + salaryId));

		salaryRepository.delete(salary);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
