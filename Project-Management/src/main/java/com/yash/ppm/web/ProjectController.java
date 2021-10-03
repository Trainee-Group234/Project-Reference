package com.yash.ppm.web;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
public class ProjectController {
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	ProjectService projectService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addNewProject(@Valid @RequestBody Project project, BindingResult result){
		Project savedProject = projectService.addProject(project);
		return new ResponseEntity<Project>(savedProject,HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectIdentifier}")
	public ResponseEntity<?> getProjectByIdentifier(@PathVariable("projectIdentifier")String projectIdentifier){
		Project p = projectService.getByIdentifier(projectIdentifier);
		return new ResponseEntity<Project>(p,HttpStatus.FOUND);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllProjects(){
		return new ResponseEntity<>(projectService.getAllProjects(),HttpStatus.FOUND);
	}
	
	@DeleteMapping("/{projectIdentifier}")
	public ResponseEntity<?> deleteProjet(@PathVariable("projectIdentifier")String projectIdentifier){
		projectService.deleteByIdentifier(projectIdentifier);
		return new ResponseEntity<>(projectIdentifier+" Deleted Successfully!",HttpStatus.FOUND);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateProject(@Validated @RequestBody Project project, BindingResult result){
		return new ResponseEntity<>(projectService.updateProject(project),HttpStatus.OK);
	}
}
