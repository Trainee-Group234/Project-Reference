package com.yash.ppm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.ppm.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	public Project findByProjectIdentifier(String projectIdentifier);

	public void deleteByProjectIdentifier(String projectIdentifier);
	
	Iterable<Project> findAllByProjectLeader(String username);
}
