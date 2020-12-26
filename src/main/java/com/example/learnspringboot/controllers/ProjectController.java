package com.example.learnspringboot.controllers;

import com.example.learnspringboot.model.Project;
import com.example.learnspringboot.services.MapValidationErrorService;
import com.example.learnspringboot.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> errorResult = mapValidationErrorService.MapValidationService(result);
        if (errorResult != null) {
            return errorResult;
        }

        projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    };

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
        // the above variable need to match
        Project project = projectService.findProjectByIdentifier(projectId);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("")
    public Iterable<Project> getAllProject() {
        return projectService.findAllProjects();
    }

    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<?> deleteProjectById(@PathVariable String projectId) {
        projectService.deleteProjectByIdentifier(projectId);
        return new ResponseEntity<String>("Project with Id: " + projectId + " is Deleted", HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> putProject(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> errorResult = mapValidationErrorService.MapValidationService(result);
        if (errorResult != null) {
            return errorResult;
        }

        projectService.updateProjectByIdentifier(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    };

}
