package com.example.tallerdosspring.repository;

import com.example.tallerdosspring.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleJpaRepository extends JpaRepository<Role,Long> {
}
