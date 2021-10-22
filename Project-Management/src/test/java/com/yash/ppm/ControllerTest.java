//package com.yash.ppm;
//
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import com.yash.ppm.model.Project;
//import com.yash.ppm.repository.ProjectRepository;
//import com.yash.ppm.web.ProjectController;
//
//import lombok.SneakyThrows;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ControllerTest {
//
//	@InjectMocks
//	ProjectController projectController;
//	
//	@MockBean
//	ProjectRepository projectRepository;
//	
//	@Autowired
//	MockMvc mockMvc;
//	
//	@SneakyThrows
//	@Test
//	public void whenPostRequestIsValid_thenCorrectResponse() throws Exception{
//		
//		String project = "{\"id\": \""+1+"\", \"projectIdentifier\" : \"test\",\"projectName\":\"test\",\"description\":\"test\",\"createdAt\" : \"2021-02-05\",\"updatedAt\":\"2021-02-05\",\"startDate\":\"2021-02-05\",\"endDate\":\"2021-02-05\"}";
//		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8082/project/add")
//				.content(project)
//				.contentType(org.springframework.http.MediaType.APPLICATION_JSON))
//		.andExpect(MockMvcResultMatchers.status().isCreated());
//	}
//	
//	@SneakyThrows
//	@Test
//	public void whenGetRequestForAll_thenReturnListOfProjects() {
//		List<Project> projects = new ArrayList<>();
//		when(projectRepository.findAll()).thenReturn(projects);
//		
//		mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8082/project/all"))
//		.andExpect(MockMvcResultMatchers.status().isFound());
//	}
//	
//	@SneakyThrows
//	@Test
//	public void whenGetRequestForAProject_thenReturnTheProject() {
//		Project p = new Project(1L,"T011","Test","Test",new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()));
//		when(projectRepository.findByProjectIdentifier("T011")).thenReturn(p);
//		
//		mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8082/project/T011"))
//		.andExpect(MockMvcResultMatchers.status().isFound());
//	}
//	
//	@SneakyThrows
//	@Test
//	public void whenDeleteRequestForAProject_thenResponseFound() {
//		Project p = new Project(1L,"T011","Test","Test",new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()));
//		when(projectRepository.findByProjectIdentifier("T011")).thenReturn(p);
//		doNothing().when(projectRepository).deleteByProjectIdentifier("T011");
//		
//		mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8082/project/T011"))
//		.andExpect(MockMvcResultMatchers.status().isFound());
//	}
//}
