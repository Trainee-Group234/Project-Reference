package com.yash.ppm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.ppm.exception.ProjectIdentifierException;
import com.yash.ppm.exception.ProjectNotFoundException;
import com.yash.ppm.model.Project;
import com.yash.ppm.model.User;
import com.yash.ppm.repository.ProjectRepository;
import com.yash.ppm.repository.UserRepository;
import com.yash.ppm.service.ProjectService;

import lombok.SneakyThrows;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@SneakyThrows
	@Override
	public Project getByIdentifier(String identifier, String name) {
		// TODO Auto-generated method stub
		Project p = projectRepository.findByProjectIdentifier(identifier);
		if(p==null)
			throw new ProjectIdentifierException("No project found for \""+identifier+"\"!");
		if(!p.getProjectLeader().equals(name))
			throw new ProjectNotFoundException("Project Not found in your Account!");
		return projectRepository.findByProjectIdentifier(identifier);
	}

	@Override
	public List<Project> getAllProjects(String name) {
		// TODO Auto-generated method stub
		return (List<Project>) projectRepository.findAllByProjectLeader(name);
	}

	@Transactional
	@Override
	public void deleteByIdentifier(String identifier, String name) {
		// TODO Auto-generated method stub
		projectRepository.delete(getByIdentifier(identifier,name));
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
	public Project addProject(Project project, String username) {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username);
		project.setUser(user);
		project.setProjectLeader(username);
		
		return projectRepository.save(project);
	}

}
