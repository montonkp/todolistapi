package todolistapi.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
@Table(name = "MART_TODOLIST")
public class ToDoList {
    @Id
    @SequenceGenerator(name = "toDoList_seq",sequenceName = "toDoList_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "toDoList_seq")
    private Long id;

    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issuedDate;

    public ToDoList(@NotNull String name, Member member, LocalDate dueDate, LocalDate issuedDate) {
        this.name = name;
        this.member = member;
        this.dueDate = dueDate;
        this.issuedDate = issuedDate;
    }
}