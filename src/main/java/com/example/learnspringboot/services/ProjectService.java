package com.example.learnspringboot.services;

import com.example.learnspringboot.model.Project;
import com.example.learnspringboot.repositories.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    public Project saveOrUpdateProject(Project project) {
        return projectRepo.save(project);
    }
}
