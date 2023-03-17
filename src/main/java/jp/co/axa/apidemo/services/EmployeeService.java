package jp.co.axa.apidemo.services;

import java.util.List;

import jp.co.axa.apidemo.entities.Employee;

/*
 * service interface for CRUD from h2 database
 * */
public interface EmployeeService {

    public List<Employee> retrieveEmployees();

    public Employee getEmployee(Long employeeId);

    public void saveEmployee(Employee employee);

    public void deleteEmployee(Long employeeId);

    public void updateEmployee(Employee employee);
}