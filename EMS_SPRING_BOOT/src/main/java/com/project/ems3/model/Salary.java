package com.project.ems3.model;

import javax.persistence.CascadeType;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "salary")
@Entity(name = "salary")

public class Salary {

	private long salary_id;
	private String salary_employee_id;
	private String salary_month;
	private String salary_working_days;
	private String salary_hra;
	private String salary_ta;
	private String salary_dpf;
	private String salary_dtax;
	private String salary_total;
	private String salary_dedc;
	private Integer salary_year;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getSalary_id() {
		return salary_id;
	}

	public void setSalary_id(long salary_id) {
		this.salary_id = salary_id;
	}

	public String getSalary_employee_id() {
		return salary_employee_id;
	}

	public void setSalary_employee_id(String salary_employee_id) {
		this.salary_employee_id = salary_employee_id;
	}

	public String getSalary_month() {
		return salary_month;
	}

	public void setSalary_month(String salary_month) {
		this.salary_month = salary_month;
	}

	public String getSalary_working_days() {
		return salary_working_days;
	}

	public void setSalary_working_days(String salary_working_days) {
		this.salary_working_days = salary_working_days;
	}

	public String getSalary_hra() {
		return salary_hra;
	}

	public void setSalary_hra(String salary_hra) {
		this.salary_hra = salary_hra;
	}

	public String getSalary_ta() {
		return salary_ta;
	}

	public void setSalary_ta(String salary_ta) {
		this.salary_ta = salary_ta;
	}

	public String getSalary_dpf() {
		return salary_dpf;
	}

	public void setSalary_dpf(String salary_dpf) {
		this.salary_dpf = salary_dpf;
	}

	public String getSalary_dtax() {
		return salary_dtax;
	}

	public void setSalary_dtax(String salary_dtax) {
		this.salary_dtax = salary_dtax;
	}

	public String getSalary_total() {
		return salary_total;
	}

	public void setSalary_total(String salary_total) {
		this.salary_total = salary_total;
	}

	public String getSalary_dedc() {
		return salary_dedc;
	}

	public void setSalary_dedc(String salary_dedc) {
		this.salary_dedc = salary_dedc;
	}

	public Salary() {
		
	}
	
	public Integer getSalary_year() {
		return salary_year;
	}

	public void setSalary_year(Integer salary_year) {
		this.salary_year = salary_year;
	}
	
	
}
