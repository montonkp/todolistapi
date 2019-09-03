package todolistapi.todolisttest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import todolistapi.entity.ToDoList;
import todolistapi.service.ToDoListService;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ToDoListServiceTests {
    @InjectMocks
    private ToDoListService toDoListService;

    @Mock
    private ToDoList toDoList;

    @Test
    public void toDoListDueDateIsFalse(){
        toDoList = new ToDoList();
        toDoList.setDueDate(LocalDate.now().plusMonths(1));

        Boolean isDue = toDoListService.isToDoListDueDate(toDoList);
        System.out.println("ToDoList Due is false");
        assertEquals(false, isDue);
    }

    @Test
    public void toDoListDueDateIsTrue(){
        toDoList = new ToDoList();
        toDoList.setIssuedDate(LocalDate.now());
        toDoList.setDueDate(LocalDate.now().minusMonths(1));

        Boolean isDue = toDoListService.isToDoListDueDate(toDoList);
        System.out.println("ToDoList Due is True");
        assertEquals(true, isDue);
    }
}
