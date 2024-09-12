package fr.eni.caveavin.bo.vin;


import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString()
@EqualsAndHashCode(exclude = {"region", "color"})

@Entity
@Table(name="CAV_BOTTLE")
public class Bottle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOTTLE_ID")
    private Integer id;

    @Column(name = "NAME", length = 250, unique = true)
    private String name;

    @Column(name = "SPARKLING")
    private boolean sparkling;

    @Column(name = "VINTAGE", length = 100)
    private String vintage;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "PRICE", precision = 2)
    private float price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COLOR_ID")
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REGION_ID")
    private Region region;

}
