package com.example.learnspringboot.repositories;

import com.example.learnspringboot.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project, Long> {
}
