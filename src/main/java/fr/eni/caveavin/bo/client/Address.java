package fr.eni.caveavin.bo.client;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CAV_ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Integer id;
    @NotNull
    @Column(length = 250, nullable = false, name = "STREET")
    private String street;
    @NotNull
    @Column(length = 5, nullable = false, name = "POSTAL_CODE")
    private String zipCode;
    @NotNull
    @Column(length = 150, nullable = false, name = "CITY")
    private String city;

}
