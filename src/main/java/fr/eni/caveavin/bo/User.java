package fr.eni.caveavin.bo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(of = {"username"})

@Entity
@Table(name = "CAV_USER")
@Inheritance(strategy=InheritanceType.JOINED)
public class User {

    @Id
    @Column(length = 255, name="LOGIN", nullable=false)
    private String username;

    @Column(length=68, nullable=false, name = "PASSWORD")
    @NonNull
    @ToString.Exclude
    private String password;

    @NonNull
    @Column(length=90, nullable=false, name = "LAST_NAME")
    private String lastName;

    @NonNull
    @Column(length=150, nullable = false , name = "FIRST_NAME")
    private String firstName;

}
