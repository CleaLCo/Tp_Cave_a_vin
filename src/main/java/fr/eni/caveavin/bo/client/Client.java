package fr.eni.caveavin.bo.client;

import fr.eni.caveavin.bo.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
@SuperBuilder
@EqualsAndHashCode(callSuper=true)

@Entity
@Table(name = "CAV_CLIENT")
public class Client extends User {


    @OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

}
