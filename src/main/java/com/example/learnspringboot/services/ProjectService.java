package com.example.learnspringboot.services;

import com.example.learnspringboot.exceptions.ProjectIdException;
import com.example.learnspringboot.model.Project;
import com.example.learnspringboot.repositories.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepo.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exist");
        }
    }

    public Project findProjectByIdentifier(String projectId) {
        Project project = projectRepo.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("Project Id Does not Exist");
        }
        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepo.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepo.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("Cannot Delete, Project Id Does Not Exist");
        }
        projectRepo.delete(project);
    }

    public Project updateProjectByIdentifier(Project updatedProject) {

        updatedProject.setProjectIdentifier(updatedProject.getProjectIdentifier().toUpperCase());

        Project oldProject = projectRepo.findByProjectIdentifier(updatedProject.getProjectIdentifier());
        if (oldProject == null) {
            throw new ProjectIdException(String.format("Cannot update project as Project ID: %s does not exist", updatedProject.getProjectIdentifier()));
        }

        updatedProject.setId(oldProject.getId());
        return projectRepo.save(updatedProject);
    }
}
