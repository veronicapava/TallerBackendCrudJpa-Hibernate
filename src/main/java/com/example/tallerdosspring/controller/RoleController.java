package com.example.tallerdosspring.controller;

import com.example.tallerdosspring.model.Project;
import com.example.tallerdosspring.model.Role;
import com.example.tallerdosspring.repository.IRoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    IRoleJpaRepository roleRepository;

    //Metodo get para obtener los roles
    @GetMapping()
    public ResponseEntity<List<Role>> getRoles(){
        try {
            List<Role> role = new ArrayList<Role>();
            roleRepository.findAll().forEach(role::add);
            if (role.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(role, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Metodo para obtener roles por id
    @GetMapping("{id}")
    public ResponseEntity<Role> getRolesById(@PathVariable("id") long id){
        Optional<Role> roleData = roleRepository.findById(id);
        if (roleData.isPresent()) {
            return new ResponseEntity<>(roleData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Metodo para agregar un rol
    @PostMapping("/post")
    public ResponseEntity<Role> addNewProject(@RequestBody Role role){
        try{
            Role rolecito = roleRepository.save(new Role(role.getName()));
            return new ResponseEntity<>(rolecito, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    //Metodo para actualizar role
    @PutMapping("/upd/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable("id") long id, @RequestBody Role role) {
        Optional<Role> upRole = roleRepository.findById(id);

        if (upRole.isPresent()) {
            Role role_ = upRole.get();
            role_.setName(role.getName());
            return new ResponseEntity<>(roleRepository.save(role_), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Metodo para eliminar role
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") long id) {
        try {
            roleRepository.deleteById(id);
            return new ResponseEntity<>("Role delete",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
