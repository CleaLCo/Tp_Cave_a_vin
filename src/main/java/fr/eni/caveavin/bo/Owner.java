package fr.eni.caveavin.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
@SuperBuilder
@EqualsAndHashCode(callSuper=true)
@Data

@Entity
@Table(name = "CAV_OWNER")
public class Owner extends User {

    @Column(name = "CLIENT_NUMBER", length = 14)
    private String siret;

}
