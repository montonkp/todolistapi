package todolistapi.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
@Table(name = "Mart_Reservation")
public class Reservation {
    @Id
    @SequenceGenerator(name = "reservation_seq",sequenceName = "reservation_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "reservation_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issuedDate;

    @Min(value = 0)
    @Max(value = 1)
    public int getMonthOfPeriodDate(){
        int monthOfPeriodStartDate = this.issuedDate.getMonthValue() - LocalDate.now().getMonthValue();
        return monthOfPeriodStartDate;
    }

    public Reservation(Item item, Employee employee, LocalDate expiryDate, LocalDate issuedDate) {
        this.item = item;
        this.employee = employee;
        this.expiryDate = expiryDate;
        this.issuedDate = issuedDate;
    }
}