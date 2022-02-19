package com.example.tallerdosspring.repository;

import com.example.tallerdosspring.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeJpaRepository extends JpaRepository<Employee, Long> {
    //Select fields from employee where employeeid='[param]'
    Employee findByEmployeeId(String employeeid);
    List<Employee> findByLastName(String lastName);
}
