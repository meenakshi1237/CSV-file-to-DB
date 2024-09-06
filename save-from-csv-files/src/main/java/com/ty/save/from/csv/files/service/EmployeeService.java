package com.ty.save.from.csv.files.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.ty.save.from.csv.files.dao.EmployeeDao;
import com.ty.save.from.csv.files.entity.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;

	public boolean hasCsvFormat(MultipartFile file) {
		String type="text/csv";
		System.err.println(file.getContentType());
		if(!type.equalsIgnoreCase(file.getContentType())) {
			return false;
		}
		return true;
	}

	public List<Employee> processAndSaveData(MultipartFile file) throws IOException, CsvException {
		List<String[]> csvData=csvToEmployees(file);
		List<Employee> savedEmployees=new ArrayList<Employee>();
		
		//getting headder i.e i st row from file(entity property names)
		String[] headder=csvData.get(0);
		for (int i=1;i<csvData.size();i++) {
			String[] row=csvData.get(i);
		
			 // Map CSV data to your Employee entity and save to database
			
			
				/*
				 * Employee employee =Employee.builder() .name(row[0]) .email(row[2])
				 * .phone(Long.parseLong(row[1])) .password(row[3])
				 * .salary(Double.parseDouble(row[4])) .build();
				 */
            
            
			/*
			 * employee.setName(row[0]); // first column contains name
			 * employee.setEmail(row[2]); // third column contains email
			 * employee.setPhone(Long.parseLong(row[1])); // second column contain phone
			 * contains phone employee.setPassword(row[3]); //fourth column contain password
			 * contains password employee.setSalary(Double.parseDouble(row[4])); // fifth coloum contain password
			 * Set other attributes as needed
			 * 
			 */    
			Employee employee=mapToEmployee(row, headder);
			
            Employee savedEmployee=employeeDao.saveEmployee(employee);
            savedEmployees.add(savedEmployee);
        }
		return savedEmployees;
		
		
	}

	private List<String[]> csvToEmployees(MultipartFile file) throws IOException, CsvException {
		 List<String[]> data = new ArrayList<>();
	        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
	            data = reader.readAll();
	        }
	        return data;
	}
	
	private Employee mapToEmployee(String[] row,String[] header) {
		Employee employee=new Employee();
		for(int i=0;i<header.length;i++) {
			switch(header[i].toLowerCase()) {
			case "name":{
				employee.setName(row[i]);
				break;
			}
			case "phone":{
				employee.setPhone(Long.parseLong(row[i]));
				break;
			}
			case "email":{
				employee.setEmail(row[i]);
				break;
			}
			case "password":{
				employee.setPassword(row[i]);
				break;
			}
			case "salary":{
				employee.setSalary(Double.parseDouble(row[i]));
				break;
			}
			}
		}
		return employee;
	}

}
