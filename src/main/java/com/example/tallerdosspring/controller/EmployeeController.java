package com.example.tallerdosspring.controller;

import com.example.tallerdosspring.model.Employee;
import com.example.tallerdosspring.repository.IEmployeeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    IEmployeeJpaRepository employeeRepository;

    //Metodo para obtener los empleados
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(){
        try {
            List<Employee> emplo = new ArrayList<Employee>();
            return new ResponseEntity<>(emplo, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Metodo para agregar un empleado
    @PostMapping("/employees/post")
    public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee employee){
        try{
            Employee emplo = employeeRepository.save(new Employee(employee.getFirstName(),
                    employee.getLastName(), employee.getEmployeeId(), employee.getRole()));
            return new ResponseEntity<>(emplo, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
