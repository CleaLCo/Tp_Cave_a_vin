package fr.eni.caveavin.dal;

import fr.eni.caveavin.bo.vin.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Integer> {

}
