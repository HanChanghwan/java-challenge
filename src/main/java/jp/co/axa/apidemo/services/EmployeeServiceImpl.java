package jp.co.axa.apidemo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;

/*
 * service class which is implemented EmployeeService interface.
 * */
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

	/*
	 * gettingthe all data from EMPLOYEE-Table in h2 database.
	 **/
    public List<Employee> retrieveEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    /*
     * getting the employee data by id from EMPLOYEE-Table in h2 database.
     * @param Id
     * @return Employee
     **/
    public Employee getEmployee(Long employeeId) {
        Optional<Employee> optEmp = employeeRepository.findById(employeeId);
        return optEmp.get();
    }

    /*
     * saving the employee data by id from EMPLOYEE-Table in h2 database.
     * @param Employee entity
     * @return
     **/
    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    /*
     * deletingt the employee data by id from EMPLOYEE-Table in h2 database.
     * @param Employee entity
     * @return
     **/
    public void deleteEmployee(Long employeeId){
        employeeRepository.deleteById(employeeId);
    }

    /*
     * updating the employee data in EMPLOYEE-Table in h2 database.
     * @param Employee entity
     * @return
     **/
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}