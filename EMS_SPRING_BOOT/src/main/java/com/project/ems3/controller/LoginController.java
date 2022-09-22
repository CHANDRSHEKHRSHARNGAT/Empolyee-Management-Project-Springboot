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
import com.project.ems3.model.Login;
import com.project.ems3.repository.LoginRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class LoginController {
	@Autowired
	private LoginRepository loginRepository;
	@PersistenceContext
    private EntityManager entityManager;
	
	public ArrayList getLoginData(long login_id) {
		 Query q = entityManager.createQuery("SELECT emp, log from employee emp, login log WHERE employee_id = login_employee_id AND login_id = :login_id");
		 List<Object[]> login = q.setParameter("login_id", login_id).getResultList();
				 ArrayList<HashMap<String, String>> resultArray = new ArrayList();
		 
		 for ( Object[] row : login ) {
			  
			 HashMap<String, String> results = new HashMap();
			  Login login_details = (Login)row[ 1 ];
			  Employee employee_details = (Employee)row[ 0 ];
			System.out.print("Employee ID"+login_details.getLogin_employee_id());
			    results.put("login_id",String.valueOf(login_details.getLogin_id()));
				results.put("login_employee_id",login_details.getLogin_employee_id());
				results.put("login_email",login_details.getLogin_email());
			
			  
			    resultArray.add(results);
		 }	 
        return resultArray;
	}
	
	@PostMapping("/login")
	public ArrayList checkLogin(@Valid @RequestBody Login login) {
		Login loginObj = loginRepository.checkLogin(login.getLogin_email(), login.getLogin_password());
		
		return this.getLoginData(loginObj.getLogin_id());

	}
}
