package com.yash.ppm;

import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yash.ppm.repository.ProjectRepository;
import com.yash.ppm.service.ProjectService;
import com.yash.ppm.web.ProjectController;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DependencyTest {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	ProjectController projectController;
	
	@Test
	public void checkInjectedComponentAreNotNull() {
		assertNotNull(dataSource);
		assertNotNull(applicationContext);
		assertNotNull(projectRepository);
		assertNotNull(projectController);
		assertNotNull(projectService);
	}
}
