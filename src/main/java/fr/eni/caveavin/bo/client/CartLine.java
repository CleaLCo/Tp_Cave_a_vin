package fr.eni.caveavin.bo.client;


import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString()
@EqualsAndHashCode(of = "id")

@Entity
@Table(name="CAV_LINE")
public class CartLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LINE_ID")
    private Integer id;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name = "PRICE", nullable = false, precision = 2)
    private float price;

}
