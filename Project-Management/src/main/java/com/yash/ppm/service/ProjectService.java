package com.yash.ppm.service;

import java.util.List;

import com.yash.ppm.model.Project;

public interface ProjectService {
	
	Project addProject(Project project, String username);
	Project getByIdentifier(String identifier, String name);
	List<Project> getAllProjects(String name);
	void deleteByIdentifier(String identifier, String name);
	Project updateProject(Project project);
}
