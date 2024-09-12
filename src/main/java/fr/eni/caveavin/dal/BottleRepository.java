package fr.eni.caveavin.dal;

import fr.eni.caveavin.bo.vin.Bottle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BottleRepository extends JpaRepository<Bottle, Integer> {
}
