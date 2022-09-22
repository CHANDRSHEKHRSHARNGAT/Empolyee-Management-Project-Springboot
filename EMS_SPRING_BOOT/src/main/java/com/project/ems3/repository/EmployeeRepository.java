package com.project.ems3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.project.ems3.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Query(value = "SELECT * FROM employee WHERE employee_first_name = ?1", nativeQuery = true)
	public List<Employee> serchUserByName(String employee_name);
	
}
