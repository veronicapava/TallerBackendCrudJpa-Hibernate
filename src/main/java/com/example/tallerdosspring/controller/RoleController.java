package com.example.tallerdosspring.controller;

import com.example.tallerdosspring.model.Employee;
import com.example.tallerdosspring.model.Role;
import com.example.tallerdosspring.repository.IRoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    IRoleJpaRepository roleRepository;

    @GetMapping()
    public ResponseEntity<List<Role>> getRoles(){
        try {
            List<Role> role = new ArrayList<Role>();

            return new ResponseEntity<>(role, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}