package todolistapi.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "MART_TASKSTATUS")
public class TaskStatus {
    @Id
    @SequenceGenerator(name = "taskStatus_seq",sequenceName = "taskStatus_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "taskStatus_seq")
    private Long id;

    private String taskStatus;

    public TaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
}
