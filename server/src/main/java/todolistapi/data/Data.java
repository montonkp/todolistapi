package todolistapi.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import todolistapi.entity.Member;
import todolistapi.entity.Task;
import todolistapi.entity.TaskStatus;
import todolistapi.entity.ToDoList;
import todolistapi.repository.MemberRepository;
import todolistapi.repository.TaskRepository;
import todolistapi.repository.TaskStatusRepository;
import todolistapi.repository.ToDoListRepository;

import java.time.LocalDate;
import java.util.stream.Stream;

@Component
public class Data implements CommandLineRunner {

    @Autowired
    TaskStatusRepository taskStatusRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ToDoListRepository toDoListRepository;

    @Override
    public void run(String... arg0) throws Exception {
        createMemberData();
        createToDoListData();
        createTaskStatusData();
        createTaskData();
    }
    private void createTaskStatusData(){
        Stream.of("Finished", "Not Finished").forEach(status -> {
            TaskStatus taskStatus = new TaskStatus();
            taskStatus.setTaskStatus(status);
            taskStatusRepository.save(taskStatus);
        });
    }
    private void createTaskData(){
        ToDoList toDoList = toDoListRepository.findById(1l).get();

        Task task = new Task();

        task.setTaskStatus(taskStatusRepository.findById(1l).get());
        task.setToDoList(toDoList);
        task.setTitle("10KM Running");
        task.setDescription("Monday,Wednesday and Friday");
        taskRepository.save(task);

        Task task2 = new Task();

        task2.setToDoList(toDoList);
        task2.setTaskStatus(taskStatusRepository.findById(2l).get());
        task2.setTitle("100 Push-Ups");
        task2.setDescription("Wednesday and Friday");
        taskRepository.save(task2);

        Task task3 = new Task();

        task3.setToDoList(toDoList);
        task3.setTaskStatus(taskStatusRepository.findById(1l).get());
        task3.setTitle("100 Sit-Ups");
        task3.setDescription("-");
        taskRepository.save(task3);
    }
    private void createMemberData() {
        Member member = new Member();

        member.setUsername("admin");
        member.setPassword("admin");
        member.setFirstName("Monthon");
        member.setLastName("Kanpoh");
        member.setBirthday(LocalDate.now().minusYears(18));
        member.setPhone("0814463573");
        member.setEmail("monton.kp@gmail.com");
        memberRepository.save(member);

        Member member2 = new Member();

        member2.setUsername("admin2");
        member2.setPassword("admin2");
        member2.setFirstName("Abcd");
        member2.setLastName("Efgh");
        member2.setBirthday(LocalDate.now().minusYears(18));
        member2.setPhone("0987654321");
        member2.setEmail("abcdefgh@gmail.com");
        memberRepository.save(member2);
    }
    private void createToDoListData() {
        ToDoList toDoList = new ToDoList();

        toDoList.setMember(memberRepository.findById(1l).get());
        toDoList.setIssuedDate(LocalDate.now());
        toDoList.setDueDate(LocalDate.now().plusMonths(1));
        toDoListRepository.save(toDoList);

        ToDoList toDoList2 = new ToDoList();

        toDoList2.setMember(memberRepository.findById(2l).get());
        toDoList2.setIssuedDate(LocalDate.now());
        toDoList2.setDueDate(LocalDate.now().plusMonths(2));
        toDoListRepository.save(toDoList2);
    }
}
