package fr.eni.caveavin.dal;

import fr.eni.caveavin.bo.vin.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color, Integer> {

}
