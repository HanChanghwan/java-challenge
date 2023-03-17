package jp.co.axa.apidemo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/*
 * Mapping with EMPLOYEE table in h2 database
 * */
@Entity
@Table(name="EMPLOYEE")
public class Employee {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name="EMPLOYEE_NAME")
    private String name;

    @Getter
    @Setter
    @Column(name="EMPLOYEE_SALARY")
    private Integer salary;

    @Getter
    @Setter
    @Column(name="DEPARTMENT")
    private String department;
    
    @Getter
    @Setter
    @Column(name="CREATED_USER")
    private String created_user;
    
    @Getter
    @Setter
    @Column(name="CREATED_TIME")
    private String created_time;
    
    @Getter
    @Setter
    @Column(name="UPDATED_USER")
    private String updated_user;
    
    @Getter
    @Setter
    @Column(name="UPDATED_TIME")
    private String updated_time;

}
