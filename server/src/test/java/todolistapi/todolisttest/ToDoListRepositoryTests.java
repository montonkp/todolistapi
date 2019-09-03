package todolistapi.todolisttest;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import todolistapi.entity.ToDoList;
import todolistapi.repository.ToDoListRepository;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ToDoListRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ToDoListRepository toDoListRepository;

    @After
    public void dropAll() throws Exception {
        toDoListRepository.deleteAll();
    }

    @Test
    public void testFindByName() throws InterruptedException {
        //Arrange
        String name = "Bcde";
        ToDoList toDoList = new ToDoList();
        toDoList.setName(name);
        entityManager.persist(toDoList);

        //Act
        Optional<ToDoList> toDoListOptional = toDoListRepository.findByName(name);

        //Assert
        assertThat(toDoListOptional.get().getName()).isEqualTo(name);
    }

}
