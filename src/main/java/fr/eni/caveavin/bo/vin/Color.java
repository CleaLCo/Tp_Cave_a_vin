package fr.eni.caveavin.bo.vin;

import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString()

@Entity
@Table(name="CAV_COLOR")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COLOR_ID")
    private Integer id;

    @Column(length = 250, name = "NAME", unique = true)
    private String name;

}
