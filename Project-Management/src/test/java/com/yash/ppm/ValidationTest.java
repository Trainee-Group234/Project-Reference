package com.yash.ppm;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.yash.ppm.repository.ProjectRepository;
import com.yash.ppm.web.ProjectController;

import lombok.SneakyThrows;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class ValidationTest {

	@InjectMocks
	ProjectController projectController;

	@MockBean
	ProjectRepository projectRepository;

	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testContextLoads() {
		assertNotNull(projectController);
		assertNotNull(mockMvc);
	}
	
	@SneakyThrows
	@Test
	public void whenPostRequestIsValid_thenCorrectResponse() throws Exception{
		
		String project = "{\"id\": \""+1+"\", \"projectIdentifier\" : \"test\",\"projectName\":\"test\",\"description\":\"test\",\"createdAt\" : \"2021-02-05\",\"updatedAt\":\"2021-02-05\",\"startDate\":\"2021-02-05\",\"endDate\":\"2021-02-05\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8082/project/add")
				.content(project)
				.contentType(org.springframework.http.MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@SneakyThrows
	@Test
	public void whenPostRequestInvalid_thenGiveExpectedErrorMessage() {
		String project = "{\"id\": \""+1+"\", \"projectIdentifier\" : \"test\",\"projectName\":,\"description\":\"test\",\"createdAt\" : \"2021-02-05\",\"updatedAt\":\"2021-02-05\",\"startDate\":\"2021-02-05\",\"endDate\":\"2021-02-05\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8082/project/add")
				.content(project)
				.contentType(org.springframework.http.MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
//		.andExpect(MockMvcResultMatchers.content().contentType(org.springframework.http.MediaType.APPLICATION_JSON));
//		.andExpect(MockMvcResultMatchers.jsonPath("$.projectName", Is.is("Project Name cant be blank")));
	}
}
