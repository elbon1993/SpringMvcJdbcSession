package com.imtac.service;

import java.util.List;

public interface EmployeeDAO
{
    public void saveEmployee(Employee employee);
    public Employee getEmployeeById(int id);
    public void updateEmployee(Employee employee);
    public void deleteEmployee(int id);
    public List<Employee> getAllEmployees();
	public String getUser(String username, String password);
}