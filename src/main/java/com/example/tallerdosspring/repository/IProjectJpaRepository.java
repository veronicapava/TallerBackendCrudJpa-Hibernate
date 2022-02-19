package com.example.tallerdosspring.repository;

import com.example.tallerdosspring.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjectJpaRepository extends JpaRepository<Project, Long> {
}
