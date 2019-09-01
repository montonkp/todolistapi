package todolistapi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Mart_Item")
public class Item {
    @Id
    @SequenceGenerator(name = "item_seq",sequenceName = "item_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "item_seq")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "TYPE_ID")
    private Type type;
}
