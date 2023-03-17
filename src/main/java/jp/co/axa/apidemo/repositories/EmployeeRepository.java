package jp.co.axa.apidemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.axa.apidemo.entities.Employee;

/*
 * connet to the h2 database through JpaRespository interface
 * */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
