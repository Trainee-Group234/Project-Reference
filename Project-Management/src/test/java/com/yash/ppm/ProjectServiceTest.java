package com.yash.ppm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yash.ppm.exception.ProjectIdentifierException;
import com.yash.ppm.model.Project;
import com.yash.ppm.repository.ProjectRepository;
import com.yash.ppm.service.ProjectService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectServiceTest {

	@Autowired
	ProjectService projectService;
	
	@MockBean
	ProjectRepository projectRepository;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void when_addProject_ItShouldReturn_project() {
		
		Project p = new Project(1L,"T011","Test","Test",new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()));
		when(projectRepository.save(p)).thenReturn(p);
		
		assertThat(projectService.addProject(p)).isEqualTo(p);
	}
	
	@Test
	public void whenGivenCorrectIdentifier_shouldReturnTheCorrectProject() {
		Project p = new Project(1L,"T011","Test","Test",new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()));
		when(projectRepository.findByProjectIdentifier("T011")).thenReturn(p);
		
		assertThat(projectService.getByIdentifier("T011")).isEqualTo(p);
	}
	
	@Test
	public void whenGivenInvalidIdentifier_shouldThrowException() {
		when(projectRepository.findByProjectIdentifier("T111")).thenThrow(ProjectIdentifierException.class);

		assertThrows(ProjectIdentifierException.class,()->projectService.getByIdentifier("T111"));
	}
	
	@Test
	public void shouldCallDeleteMethodOfProjectRepository_whenDeleteProjectByIdentifierIsCalled() {
		Project p = new Project(1L,"test11","Test","Test",new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()));
		when(projectRepository.findByProjectIdentifier("test11")).thenReturn(p);
		doNothing().when(projectRepository).deleteByProjectIdentifier("test11");

		projectService.deleteByIdentifier("test11");;
		verify(projectRepository,times(1)).deleteByProjectIdentifier("test11");
	}
}
