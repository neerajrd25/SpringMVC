package com.example.neeraj.service;

import java.util.List;

import com.example.neeraj.model.Employee;

public interface IEmployeeService {
	public int saveEmployee(Employee empObj);
	
	public void updateEmployee(Employee empObj) throws Exception;

	public List<Employee> getEmployees();

	Employee getEmployee(int id);

	void deleteEmployee(int id);
	
	void cachingDemo(int id) throws Exception;
}
