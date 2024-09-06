package com.ty.save.from.csv.files.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.save.from.csv.files.entity.Employee;
import com.ty.save.from.csv.files.repository.EmployeeRepository;
@Repository
public class EmployeeDao {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> saveEmployees(List<Employee> employees){
		return employeeRepository.saveAll(employees);
	}
	
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
}
