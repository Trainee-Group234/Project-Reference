package com.yash.ppm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.ppm.exception.ProjectIdentifierException;
import com.yash.ppm.model.Project;
import com.yash.ppm.repository.ProjectRepository;
import com.yash.ppm.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectRepository projectRepository;
	
	@Override
	public Project getByIdentifier(String identifier) {
		// TODO Auto-generated method stub
		Project p = projectRepository.findByProjectIdentifier(identifier);
		if(p==null)
			throw new ProjectIdentifierException("No project found for \""+identifier+"\"!");
		return projectRepository.findByProjectIdentifier(identifier);
	}

	@Override
	public List<Project> getAllProjects() {
		// TODO Auto-generated method stub
		return projectRepository.findAll();
	}

	@Transactional
	@Override
	public void deleteByIdentifier(String identifier) {
		// TODO Auto-generated method stub
		Project p = projectRepository.findByProjectIdentifier(identifier);
		if(p==null)
			throw new ProjectIdentifierException("No project found for \""+identifier+"\"!");
		projectRepository.deleteByProjectIdentifier(identifier);
	}

	@Override
	public Project updateProject(Project project) {
		// TODO Auto-generated method stub
		Project p = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
		if(p==null)
			throw new ProjectIdentifierException("No project found for \""+project.getProjectIdentifier()+"\"!");
		return projectRepository.save(project);
	}

	@Override
	public Project addProject(Project project) {
		// TODO Auto-generated method stub
		return projectRepository.save(project);
	}

}
