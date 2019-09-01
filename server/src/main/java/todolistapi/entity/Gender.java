package todolistapi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Mart_Gender")
public class Gender {
    @Id
    @SequenceGenerator(name = "gender_seq",sequenceName = "gender_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "gender_seq")
    private Long id;

    private String genderType;

    public Gender(String genderType) {
        this.genderType = genderType;
    }
}
