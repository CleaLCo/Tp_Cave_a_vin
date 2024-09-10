package fr.eni.caveavin.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of={"username"})
@Entity
@Table(name="CAV_CLIENT")
public class Client {

    @Id
    @Column(length=50, name="LOGIN", nullable=false)
    private String username;

    @Column(length=255, nullable=false, name = "PASSWORD")
    @NonNull
    @ToString.Exclude
    private String password;

    @NonNull
    @Column(length=50, nullable=false, name = "LAST_NAME")
    private String lastName;

    @NonNull
    @Column(length=50, nullable = false , name = "FIRST_NAME")
    private String firstName;

}
