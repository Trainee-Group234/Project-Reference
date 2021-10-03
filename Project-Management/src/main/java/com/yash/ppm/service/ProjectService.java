package com.yash.ppm.service;

import java.util.List;

import com.yash.ppm.model.Project;

public interface ProjectService {
	
	Project addProject(Project project);
	Project getByIdentifier(String identifier);
	List<Project> getAllProjects();
	void deleteByIdentifier(String identifier);
	Project updateProject(Project project);
}
