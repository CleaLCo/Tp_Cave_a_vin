package fr.eni.caveavin.dal;

import fr.eni.caveavin.bo.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, String> {
}
