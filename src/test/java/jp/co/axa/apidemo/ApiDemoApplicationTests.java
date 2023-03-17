package jp.co.axa.apidemo;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.axa.apidemo.controllers.EmployeeController;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import jp.co.axa.apidemo.services.EmployeeServiceImpl;
import jp.co.axa.apidemo.util.cacheUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiDemoApplicationTests {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeServiceImpl empImpl;
	
	@Autowired
	EmployeeController target;

	/*
	 * testing for data caching as LRU(Least Recently Used) 
	 * */
	@Test
    public void testCase01() {
        Map<Long, Object> cache = new cacheUtil<>(true);
        
        List<Employee> employees = empImpl.retrieveEmployees();
        
        for(Employee employee: employees) {

	        cache.put(employee.getId(),employee);
        
        }

        assertThatThrownBy(() -> {
            cache.keySet().forEach(k -> cache.get(k));
        })
                .isInstanceOf(ConcurrentModificationException.class)
                .hasMessage(null);

        cache.entrySet().forEach(e -> System.out.println(e));

        cache.get(1L);
        cache.get(3L);

        Map.Entry<?, ?>[] entries = cache.entrySet().toArray(new Map.Entry<?, ?>[cache.size()]);

        assertEquals(((Employee)entries[0].getValue()).getId(),empImpl.getEmployee(2L).getId());
        assertEquals(((Employee)entries[1].getValue()).getId(),empImpl.getEmployee(4L).getId());
        assertEquals(((Employee)entries[2].getValue()).getId(),empImpl.getEmployee(5L).getId());
        assertEquals(((Employee)entries[3].getValue()).getId(),empImpl.getEmployee(1L).getId());
        assertEquals(((Employee)entries[4].getValue()).getId(),empImpl.getEmployee(3L).getId());
        
    }
	
	/*
	 * testing for data cacheing as FIFO(first in first out) 
	 * */
	 @Test
    public void testCase02() {
        Map<Long, Object> cache = new cacheUtil<>(false);

        List<Employee> employees = empImpl.retrieveEmployees();
        
        for(Employee employee: employees) {

	        cache.put(employee.getId(),employee);
        
        }

        cache.keySet().forEach(k -> cache.get(k));
        cache.entrySet().forEach(e -> System.out.println(e));

        cache.get(1L);
        cache.get(3L);

        Map.Entry<?, ?>[] entries = cache.entrySet().toArray(new Map.Entry<?, ?>[cache.size()]);
     
        assertEquals(((Employee)entries[0].getValue()).getId(),empImpl.getEmployee(1L).getId());
        assertEquals(((Employee)entries[1].getValue()).getId(),empImpl.getEmployee(2L).getId());
        assertEquals(((Employee)entries[2].getValue()).getId(),empImpl.getEmployee(3L).getId());
        assertEquals(((Employee)entries[3].getValue()).getId(),empImpl.getEmployee(4L).getId());
        assertEquals(((Employee)entries[4].getValue()).getId(),empImpl.getEmployee(5L).getId());

    }

    /**
     * Test for EmployeeServiceImpl.getEmployee()
     * getting a employee data by id from h2 database
     */
	@Test
	public void testCase03() {
		
		Employee employee = new Employee();
    	employee.setId(1L);
	    employee.setName("tom");
	    employee.setSalary(3000000);
	    employee.setDepartment("dept1");
	    employee.setCreated_user("admin");
	    employee.setCreated_time("2023-03-14 20:22:23");
	    employee.setUpdated_user(null);
	    employee.setUpdated_time(null);
       
	    Employee searchedEmployee = empImpl.getEmployee(1L);
	    
		assertThat(searchedEmployee).hasSameClassAs(employee);
	}
    
    /**
     * Test for EmployeeServiceImpl.retrieveEmployee()
     * getting all data from h2 database
     * 5 employees data in h2 database 
     */ 
    @Test
 	public void testCase04() {

		List<Employee> employees = empImpl.retrieveEmployees();

		assertThat(employees).hasSize(5).isNotEmpty();
	}

	
    /**
     * Test for EmployeeServiceImpl.saveEmployee()
     * saving a employee data to h2 database
     */
    @Test
	public void testCase05() {
    	
    	Employee newEmployee = new Employee();

    	newEmployee.setName("nakayama");
    	newEmployee.setSalary(777777);
    	newEmployee.setDepartment("dept6");
    	newEmployee.setCreated_user("admin");
    	newEmployee.setCreated_time("2023-03-20 12:12:12");
    	newEmployee.setUpdated_user(null);
    	newEmployee.setUpdated_time(null);

    	empImpl.saveEmployee(newEmployee);
    	
    	Optional<Employee> searchEmployee = employeeRepository.findById(6L);

		assertThat(searchEmployee.get()).hasFieldOrPropertyWithValue("salary", 777777);	
	}  
	
    /**
     * Test for EmployeeServiceImpl.updateEmployee()
     * updating a employee data to h2 database
     */
    @Test
 	public void testCase06() {
		
    	Employee updateEmployee = new Employee();
    	updateEmployee.setId(4L);
    	updateEmployee.setName("nakamura");
    	updateEmployee.setSalary(999999);
    	updateEmployee.setDepartment("dept99");
    	updateEmployee.setUpdated_user("admin");
    	updateEmployee.setUpdated_time("2023-03-21 15:25:55");    	
    	
    	empImpl.updateEmployee(updateEmployee);
    	
    	Optional<Employee> searchEmployee = employeeRepository.findById(4L);
    	
		assertThat(searchEmployee.get()).hasFieldOrPropertyWithValue("department", "dept99");	
	}

  

    /**
     * Test for EmployeeServiceImpl.deleteEmployee()
     * deleting of employee data by id to h2 database
	*/     
    @Test
 	public void testCase07() {
    	
		empImpl.deleteEmployee(6L);

		assertThat(employeeRepository.findById(6L)).isEmpty();
	}	
}
