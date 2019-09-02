package todolistapi.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
@Table(name = "Mart_ToDoList")
public class ToDoList {
    @Id
    @SequenceGenerator(name = "toDoList_seq",sequenceName = "toDoList_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "toDoList_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issuedDate;
/*
    @Min(value = 0)
    @Max(value = 1)
    public int getMonthOfPeriodDate(){
        int monthOfPeriodStartDate = this.issuedDate.getMonthValue() - LocalDate.now().getMonthValue();
        return monthOfPeriodStartDate;
    }
*/
    public ToDoList(Member member, LocalDate expiryDate, LocalDate issuedDate) {
        this.member = member;
        this.dueDate = expiryDate;
        this.issuedDate = issuedDate;
    }
}