package fr.eni.caveavin.dal;

import fr.eni.caveavin.bo.vin.Bottle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BottleRepository extends JpaRepository<Bottle, Integer> {

    @Query(value = "SELECT b.* " +
                    "FROM CAV_BOTTLE b " +
                    "INNER JOIN CAV_COLOR c ON b.COLOR_ID = c.COLOR_ID " +
                    "WHERE c.name = :color", nativeQuery = true)
    List<Bottle> searchByColor(@Param("color") String color);


    @Query(value = "SELECT b.* " +
            "FROM CAV_BOTTLE b " +
            "INNER JOIN CAV_REGION r ON b.REGION_ID = r.REGION_ID " +
            "WHERE r.name = :region", nativeQuery = true)
    List<Bottle> searchByRegion(@Param("region") String region);

}
