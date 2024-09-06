package com.ty.save.from.csv.files.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.save.from.csv.files.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
