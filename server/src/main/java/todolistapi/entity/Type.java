package todolistapi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Mart_Type")
public class Type {
    @Id
    @SequenceGenerator(name = "type_seq",sequenceName = "type_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "type_seq")
    private Long id;

    private String itemType;

    public Type(String itemType) {
        this.itemType = itemType;
    }
}
