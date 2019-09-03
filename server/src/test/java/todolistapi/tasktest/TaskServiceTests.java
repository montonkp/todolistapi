package todolistapi.tasktest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import todolistapi.entity.Task;
import todolistapi.repository.TaskRepository;
import todolistapi.service.TaskService;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTests {
    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private Task task;

    @Test
    public void desBeNullFillNot(){
        task = new Task();
        task.setTitle("test");
        task.setDescription(null);

        String msgNot = taskService.fillNotMsg(task);
        System.out.println("Description is \""+msgNot+"\"");
        assertEquals(msgNot, "-");
    }
    @Test
    public void desBeEmptyFillNot(){
        task = new Task();
        task.setTitle("test");
        task.setDescription("");

        String msgNot = taskService.fillNotMsg(task);
        System.out.println("Description is \""+msgNot+"\"");
        assertEquals(msgNot, "-");
    }
    @Test
    public void desBeBlankFillNot(){
        task = new Task();
        task.setTitle("test");
        task.setDescription("  ");

        String msgNot = taskService.fillNotMsg(task);
        System.out.println("Description is \""+msgNot+"\"");
        assertEquals(msgNot, "-");
    }
    @Test
    public void desHasValueNothingToDo(){
        String msg = "ABCD";
        task = new Task();
        task.setTitle("test");
        task.setDescription(msg);

        String msgNot = taskService.fillNotMsg(task);
        System.out.println("Description is \""+msgNot+"\"");
        assertEquals(msgNot, msg);
    }
}
