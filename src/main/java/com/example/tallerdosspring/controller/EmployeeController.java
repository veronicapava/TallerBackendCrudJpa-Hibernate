package com.example.tallerdosspring.controller;

import com.example.tallerdosspring.model.Employee;
import com.example.tallerdosspring.model.Project;
import com.example.tallerdosspring.model.Role;
import com.example.tallerdosspring.repository.IEmployeeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    IEmployeeJpaRepository employeeRepository;

    //Metodo para obtener los empleados
    @GetMapping()
    public ResponseEntity<List<Employee>> getEmployees(){
        try {
            List<Employee> emplo = new ArrayList<Employee>();
            employeeRepository.findAll().forEach(emplo::add);
            if (emplo.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(emplo, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Metodo para obtener empleado por id
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id){
        Optional<Employee> employeeData = employeeRepository.findById(id);
        if (employeeData.isPresent()) {
            return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Metodo para agregar un empleado
    @PostMapping("/post")
    public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee employee){
        try{

            Employee emplo = employeeRepository.save(new Employee(employee.getFirstName(),
                    employee.getLastName(), employee.getEmployeeId(), employee.getRole(), employee.getProjects()));
            return new ResponseEntity<>(emplo, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    //Metodo para actualizar empleado
    @PutMapping("/upd/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
        Optional<Employee> upEmployee = employeeRepository.findById(id);

        if (upEmployee.isPresent()) {
            Employee employee_ = upEmployee.get();
            employee_.setFirstName(employee.getFirstName());
            employee_.setLastName(employee.getLastName());
            employee_.setEmployeeId(employee.getEmployeeId());
            employee_.setRole(employee.getRole());
            employee_.setProjects(employee.getProjects());
            return new ResponseEntity<>(employeeRepository.save(employee_), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Metodo para eliminar empleado
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
        try {
            employeeRepository.deleteById(id);
            return new ResponseEntity<>("Employee delete",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
