package todolistapi.todolisttest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ToDoListControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testShouldGetToDoListByName() throws Exception	{
		String content = "Workout Program";
		mockMvc.perform( MockMvcRequestBuilders
				.get("/todolists/search?name="+content)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(content));
	}

	@Test
	public void testShouldGetAllToDoList() throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/todolists")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testShouldGetOneToDoList() throws Exception	{
		int content = 1;
		mockMvc.perform( MockMvcRequestBuilders
				.get("/todolists/{id}", content)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(content));
	}

	@Test
	public void testShouldSaveToDoList() throws Exception {
		String content = "{\n" +
				"        \"name\": \"Prepare for a toeic test Program\",\n" +
				"        \"member\": {\n" +
				"            \"id\": 1\n" +
				"        },\n" +
				"        \"dueDate\": \"2019-10-03\",\n" +
				"        \"issuedDate\": \"2019-09-03\"\n" +
				"}";
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
				.post("/todolists")
				.content(content)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().is(201))
				.andReturn();
		String request = mvcResult.getRequest().getContentAsString();
		Assert.assertEquals(request,content);
	}

	@Test
	public void testShouldUpdateToDoList() throws Exception {
		int id = 1;
		String content = "{\n" +
				"    \"id\": "+id+",\n" +
				"    \"name\": \"Workout!\",\n" +
				"    \"member\": {\n" +
				"        \"id\": 2,\n" +
				"        \"username\": \"admin2\",\n" +
				"        \"password\": \"admin2\",\n" +
				"        \"firstName\": \"Abcdefg\",\n" +
				"        \"lastName\": \"Hijkl\",\n" +
				"        \"email\": \"monton.kp@gmail.com\",\n" +
				"        \"birthday\": \"2001-09-02\",\n" +
				"        \"phone\": \"0987654321\",\n" +
				"        \"maxDateOfBirthday\": 18\n" +
				"    },\n" +
				"    \"dueDate\": \"2019-10-02\",\n" +
				"    \"issuedDate\": \"2019-09-02\"\n" +
				"}";
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
				.put("/todolists/{id}",id)
				.content(content)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().is(204))
				.andReturn();
		String request = mvcResult.getRequest().getContentAsString();
		Assert.assertEquals(request,content);
	}

	@Test
	public void testShouldDeleteOneToDoList() throws Exception	{
		this.mockMvc.perform( MockMvcRequestBuilders
				.delete("/todolists/{id}", 2)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
}
