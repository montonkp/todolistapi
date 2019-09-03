package todolistapi.tasktest;

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
public class TaskControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShouldGetTaskByTitle() throws Exception {
        String content = "100 Push-Ups";
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/tasks/search?title="+content)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(content));
    }

    @Test
    public void testShouldGetAllTask() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/tasks")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testShouldGetOneTask() throws Exception	{
        int id = 1;
        mockMvc.perform( MockMvcRequestBuilders
                .get("/tasks/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
    }

    @Test
    public void testShouldSaveTask() throws Exception {
        String content = "{\n" +
                "    \"title\": \"100 squats\",\n" +
                "    \"description\": \"Monday and Friday\",\n" +
                "    \"taskStatus\": {\n" +
                "        \"id\": 1\n" +
                "    },\n" +
                "    \"toDoList\": {\n" +
                "        \"id\": 1\n" +
                "    }\n" +
                "}";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/tasks")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(201))
                .andReturn();
        String request = mvcResult.getRequest().getContentAsString();
        Assert.assertEquals(request,content);
    }

    @Test
    public void testShouldUpdateTask() throws Exception {
        int id = 1;
        String content = "{\n" +
                "        \"id\": "+id+",\n" +
                "        \"title\": \"100 Push-Ups\",\n" +
                "        \"description\": \"Wednesday and Friday\",\n" +
                "        \"taskStatus\": {\n" +
                "            \"id\": 1,\n" +
                "            \"taskStatus\": \"Finished\"\n" +
                "        },\n" +
                "        \"toDoList\": {\n" +
                "            \"id\": 1,\n" +
                "            \"name\": \"Workout Program\",\n" +
                "            \"member\": {\n" +
                "                \"id\": 1,\n" +
                "                \"username\": \"admin\",\n" +
                "                \"password\": \"admin\",\n" +
                "                \"firstName\": \"Monthon\",\n" +
                "                \"lastName\": \"Kanpoh\",\n" +
                "                \"email\": \"monton.kp@gmail.com\",\n" +
                "                \"birthday\": \"2001-09-03\"\n" +
                "            },\n" +
                "            \"dueDate\": \"2019-10-03\",\n" +
                "            \"issuedDate\": \"2019-09-03\"\n" +
                "        }\n" +
                "}";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                .put("/tasks/{id}",id)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(204))
                .andReturn();
        String request = mvcResult.getRequest().getContentAsString();
        Assert.assertEquals(request,content);
    }

    @Test
    public void testShouldDeleteOneTask() throws Exception	{
        this.mockMvc.perform( MockMvcRequestBuilders
                .delete("/tasks/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
