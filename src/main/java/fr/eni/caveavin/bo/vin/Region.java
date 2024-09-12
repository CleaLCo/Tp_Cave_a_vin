package fr.eni.caveavin.bo.vin;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString()

@Entity
@Table(name="CAV_REGION")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REGION_ID")
    private Integer id;

    @Column(name = "NAME", unique = true)
    private String name;

}
