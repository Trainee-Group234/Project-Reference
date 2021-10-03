package com.yash.ppm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yash.ppm.exception.ProjectIdentifierException;
import com.yash.ppm.model.Project;
import com.yash.ppm.repository.ProjectRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectRepositoryTest {
	
	//@Autowired

	@MockBean
	ProjectRepository projectRepository;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void when_saveProject_ItShouldReturn_project() {
		
		Project p = new Project(1L,"T011","Test","Test",new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()));
		when(projectRepository.save(p)).thenReturn(p);
		
		assertThat(projectRepository.save(p)).isEqualTo(p);
	}
	
	@Test
	public void whenGivenCorrectIdentifier_shouldReturnTheCorrectProject() {
		Project p = new Project(1L,"T011","Test","Test",new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()));
		when(projectRepository.findByProjectIdentifier("T011")).thenReturn(p);
		
		assertThat(projectRepository.findByProjectIdentifier("T011")).isEqualTo(p);
	}
	
	@Test
	public void whenGivenInvalidIdentifier_shouldThrowException() {
		when(projectRepository.findByProjectIdentifier("T111")).thenThrow(ProjectIdentifierException.class);

		assertThrows(ProjectIdentifierException.class,()->projectRepository.findByProjectIdentifier("T111"));
	}
	
	@Test
	public void shouldCallDeleteMethodOfProjectRepository_whenDeleteProjectByIdentifierIsCalled() {
		doNothing().when(projectRepository).deleteByProjectIdentifier("test11");;

		projectRepository.deleteByProjectIdentifier("test11");
		verify(projectRepository,times(1)).deleteByProjectIdentifier("test11");
	}
	
}
