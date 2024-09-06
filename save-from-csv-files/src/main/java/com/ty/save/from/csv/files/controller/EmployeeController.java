package com.ty.save.from.csv.files.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.exceptions.CsvException;
import com.ty.save.from.csv.files.entity.Employee;
import com.ty.save.from.csv.files.service.EmployeeService;

import lombok.Builder;

@RestController
@Builder
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/upload")
	public List<Employee> uploadFile(@RequestParam("file") MultipartFile file) throws IOException, CsvException{
		if(employeeService.hasCsvFormat(file)) {
			return employeeService.processAndSaveData(file);
		}
		return null;
	}
}
