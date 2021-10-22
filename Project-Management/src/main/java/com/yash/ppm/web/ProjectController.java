package com.yash.ppm.web;


import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ppm.model.Project;
import com.yash.ppm.repository.ProjectRepository;
import com.yash.ppm.service.ProjectService;

//@Log4j
@RestController
@RequestMapping("/project")
@CrossOrigin
public class ProjectController {
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	ProjectService projectService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addNewProject(@Valid @RequestBody Project project, BindingResult result, Principal principal){
		Project savedProject = projectService.addProject(project, principal.getName());
		return new ResponseEntity<Project>(savedProject,HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectIdentifier}")
	public ResponseEntity<?> getProjectByIdentifier(@PathVariable("projectIdentifier")String projectIdentifier, Principal principal){
		Project p = projectService.getByIdentifier(projectIdentifier,principal.getName());
		return new ResponseEntity<Project>(p,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public List<Project> getAllProjects(Principal principal){
		return projectService.getAllProjects(principal.getName());
	}
	
	@DeleteMapping("/{projectIdentifier}")
	public ResponseEntity<?> deleteProjet(@PathVariable("projectIdentifier")String projectIdentifier, Principal principal){
		projectService.deleteByIdentifier(projectIdentifier, principal.getName());
		return new ResponseEntity<>(projectIdentifier+" Deleted Successfully!",HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateProject(@Validated @RequestBody Project project, BindingResult result){
		return new ResponseEntity<>(projectService.updateProject(project),HttpStatus.OK);
	}
}
