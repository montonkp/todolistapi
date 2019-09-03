package todolistapi.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "MART_TASK")
public class Task {
    @Id
    @SequenceGenerator(name = "task_seq",sequenceName = "task_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "task_seq")
    private Long id;

    @NotNull
    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "TASK_ID")
    private TaskStatus taskStatus;

    @ManyToOne
    @JoinColumn(name = "TODOLIST_ID")
    private ToDoList toDoList;

    public Task(@NotNull String title, String description, TaskStatus taskStatus, ToDoList toDoList) {
        this.title = title;
        this.description = description;
        this.taskStatus = taskStatus;
        this.toDoList = toDoList;
    }
}
