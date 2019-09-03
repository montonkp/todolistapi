package todolistapi.tasktest;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import todolistapi.entity.Task;
import todolistapi.repository.TaskRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TaskRepository taskRepository;

    @After
    public void dropAll() throws Exception {
        taskRepository.deleteAll();
    }

    @Test
    public void testFindByName() throws InterruptedException {
        //Arrange
        String title = "Test";
        Task task = new Task();
        task.setTitle(title);
        entityManager.persist(task);

        //Act
        Optional<Task> toDoListOptional = taskRepository.findByTitle(title);

        //Assert
        assertThat(toDoListOptional.get().getTitle()).isEqualTo(title);
    }
}
