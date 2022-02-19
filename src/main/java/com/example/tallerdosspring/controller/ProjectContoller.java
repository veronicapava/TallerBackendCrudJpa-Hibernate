package com.example.tallerdosspring.controller;

import com.example.tallerdosspring.model.Project;
import com.example.tallerdosspring.repository.IProjectJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/project")
public class ProjectContoller {
    @Autowired
    IProjectJpaRepository projectRepository;

    //Metodo para obtener todos los proyectos
    @GetMapping()
    public ResponseEntity<List<Project>> getProjects(){
        try {
            List<Project> projects = new ArrayList<Project>();
            projectRepository.findAll().forEach(projects::add);
            if (projects.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(projects, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Metodo para obtener proyectos por id
    @GetMapping("{id}")
    public ResponseEntity<Project> getProjectsById(@PathVariable("id") long id){
        Optional<Project> projectData = projectRepository.findById(id);
        if (projectData.isPresent()) {
            return new ResponseEntity<>(projectData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //Metodo para agregar un proyecto
    @PostMapping("/post")
    public ResponseEntity<Project> addNewProject(@RequestBody Project project){
        try{
            Project projectito = projectRepository.save(new Project(project.getName()));
            return new ResponseEntity<>(projectito, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
