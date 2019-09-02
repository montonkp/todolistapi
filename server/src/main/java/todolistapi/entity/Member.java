package todolistapi.entity;

import lombok.*;
import org.apache.tomcat.jni.Address;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Mart_Member")
public class Member {
    @Id
    @SequenceGenerator(name = "member_seq",sequenceName = "member_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "member_seq")
    private Long id;

    @NotNull(message="Please type username before save!")
    @Size(min = 2, max = 20,message = "Username length incorrect!(2-20)")
    @Pattern(regexp = "[A-Za-z]\\w*",message = "Username incorrect!")
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @NotNull(message = "Please type firstName before save!")
    @Size(min = 2, max = 30,message = "FirstName length incorrect!(2-30)")
    @Pattern(regexp = "[A-Za-zก-ฮ][A-Za-zก-์]*",message = "FirstName incorrect!")
    private String firstName;

    @NotNull(message = "Please type lastName before save!")
    @Size(min = 2, max = 30,message = "LastName length incorrect!(2-30)")
    @Pattern(regexp = "[A-Za-zก-ฮ][A-Za-zก-์]*",message = "LastName incorrect!")
    private String lastName;

    @NotNull(message = "Please type email before save!")
    @Email(message = "Email incorrect!")
    private String email ;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @NotNull(message = "Please type phone before save!")
    @Size(min = 10, max = 10,message = "Phone length incorrect!(10)")
    @Pattern(regexp = "[0]\\d*",message = "Phone incorrect!")
    private String phone;


    @Min(value = 18,message = "Please selected birthday before 18 years of current!")
    public int getMaxDateOfBirthday(){
        int maxDate = LocalDate.now().getYear()-this.getBirthday().getYear();
        return maxDate;
    }

    public Member(@NotNull(message = "Please type username before save!") @Size(min = 2, max = 20, message = "Username length incorrect!(2-20)") @Pattern(regexp = "[A-Za-z]\\w*", message = "Username incorrect!") String username, @NotNull String password, @NotNull(message = "Please type firstName before save!") @Size(min = 2, max = 30, message = "FirstName length incorrect!(2-30)") @Pattern(regexp = "[A-Za-zก-ฮ][A-Za-zก-์]*", message = "FirstName incorrect!") String firstName, @NotNull(message = "Please type lastName before save!") @Size(min = 2, max = 30, message = "LastName length incorrect!(2-30)") @Pattern(regexp = "[A-Za-zก-ฮ][A-Za-zก-์]*", message = "LastName incorrect!") String lastName, @NotNull(message = "Please type email before save!") @Email(message = "Email incorrect!") String email, LocalDate birthday, @NotNull(message = "Please type phone before save!") @Size(min = 10, max = 10, message = "Phone length incorrect!(10)") @Pattern(regexp = "[0]\\d*", message = "Phone incorrect!") String phone) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.phone = phone;
    }
}
