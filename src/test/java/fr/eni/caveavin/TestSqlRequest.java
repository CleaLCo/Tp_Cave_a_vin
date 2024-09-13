package fr.eni.caveavin;

import fr.eni.caveavin.bo.vin.Bottle;
import fr.eni.caveavin.bo.vin.Color;
import fr.eni.caveavin.bo.vin.Region;
import fr.eni.caveavin.dal.BottleRepository;
import fr.eni.caveavin.dal.ColorRepository;
import fr.eni.caveavin.dal.RegionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class TestSqlRequest {


    @Autowired
    private BottleRepository bottleRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private RegionRepository regionRepository;

    Color red;
    Color white;
    Color rose;

    Region grandEst;
    Region paysDeLaLoire;
    Region nouvelleAquitaine;

    @BeforeEach
    public void initDB() {
        red = Color
                .builder()
                .name("Rouge")
                .build();

        white = Color
                .builder()
                .name("Blanc")
                .build();

        rose = Color
                .builder()
                .name("Rosé")
                .build();

        colorRepository.save(red);
        colorRepository.save(white);
        colorRepository.save(rose);

        grandEst =
                Region
                        .builder()
                        .name("Grand Est")
                        .build();

        paysDeLaLoire =
                Region
                        .builder()
                        .name("Pays de la Loire")
                        .build();

        nouvelleAquitaine =
                Region
                        .builder()
                        .name("Nouvelle Aquitaine")
                        .build();

        regionRepository.save(grandEst);
        regionRepository.save(paysDeLaLoire);
        regionRepository.save(nouvelleAquitaine);
    }

    private List<Bottle> jeuDeDonnees() {
        List<Bottle> bottles = new ArrayList<>();
        bottles.add(Bottle
                .builder()
                .name("Blanc du DOMAINE ENI Ecole")
                .vintage("2022")
                .price(23.95f)
                .quantity(1298)
                .region(paysDeLaLoire)
                .color(white)
                .build());
        bottles.add(Bottle
                .builder()
                .name("Rouge du DOMAINE ENI Ecole")
                .vintage("2018")
                .price(11.45f)
                .quantity(987)
                .region(paysDeLaLoire)
                .color(red)
                .build());
        bottles.add(Bottle
                .builder()
                .name("Blanc du DOMAINE ENI Service")
                .vintage("2022")
                .price(34)
                .sparkling(true)
                .quantity(111)
                .region(grandEst)
                .color(white)
                .build());
        bottles.add(Bottle
                .builder()
                .name("Rouge du DOMAINE ENI Service")
                .vintage("2012")
                .price(8.15f)
                .quantity(344)
                .region(paysDeLaLoire)
                .color(red)
                .build());
        bottles.add(Bottle
                .builder()
                .name("Rosé du DOMAINE ENI")
                .vintage("2020")
                .price(33)
                .quantity(1987)
                .region(nouvelleAquitaine)
                .color(rose)
                .build());
        return bottles;
    }


    @Test
    public  void testSearchByColor() {
        //Arrange
        List<Bottle> savedBottles = jeuDeDonnees();
        bottleRepository.saveAll(savedBottles);
        String color = "Rouge";
        //Act
        List<Bottle> bottles = bottleRepository.searchByColor(color);
        //Assert
        Assertions.assertThat(bottles).isNotNull();
        Assertions.assertThat(bottles).hasSize(2);
        System.out.println(bottles);

    }

    @Test
    public  void testSearchByRegion() {
        //Arrange
        List<Bottle> savedBottles = jeuDeDonnees();
        bottleRepository.saveAll(savedBottles);
        String region = "Pays de la Loire";
        //Act
        List<Bottle> bottles = bottleRepository.searchByRegion(region);
        //Assert
        Assertions.assertThat(bottles).isNotNull();
        Assertions.assertThat(bottles).hasSize(3);
        System.out.println(bottles);

    }


}
