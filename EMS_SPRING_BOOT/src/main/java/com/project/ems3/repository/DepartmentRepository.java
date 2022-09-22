package com.project.ems3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; 
import com.project.ems3.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
