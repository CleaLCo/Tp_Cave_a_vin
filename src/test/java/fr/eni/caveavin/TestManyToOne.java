package fr.eni.caveavin;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;


@DataJpaTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestManyToOne {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    BottleRepository bottleRepository;

    @Autowired
    ColorRepository colorRepository;

    @Autowired
    RegionRepository regionRepository;

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

    //TODO


    @Test
    public void testSaveBottle() {
        System.out.println("***************Test save bottle******************");

        Bottle bottle = Bottle.builder()
                .name("Jurançon")
                .vintage("2021")
                .quantity(53)
                .price(52.99f)
                .region(nouvelleAquitaine)
                .color(white)
                .build();

        Bottle savedBottle = bottleRepository.save(bottle);
        Assertions.assertThat(savedBottle.getId()).isGreaterThan(0);
        Assertions.assertThat(savedBottle.getColor()).isNotNull();
        Assertions.assertThat(savedBottle.getRegion()).isNotNull();

        System.out.println(savedBottle);

    }


    @Test
    public void TestSaveBottles() {
        System.out.println("***************Test save bottles******************");
        List<Bottle> bottles = jeuDeDonnees();
        List<Bottle> savedBottles = bottleRepository.saveAll(bottles);

        savedBottles.forEach(b ->{
            System.out.println("***********************************************");
            Assertions.assertThat(b.getId()).isGreaterThan(0);
            Assertions.assertThat(b.getColor()).isNotNull();
            Assertions.assertThat(b.getRegion()).isNotNull();

            System.out.println(b);
        });

    }


    @Test
    public void TestDeleteBottle() {
        System.out.println("***************Test delete bottle******************");

        Bottle bottle = Bottle.builder()
                .name("Jurançon")
                .vintage("2021")
                .quantity(53)
                .price(52.99f)
                .region(nouvelleAquitaine)
                .color(white)
                .build();

        Bottle savedBottle = bottleRepository.saveAndFlush(bottle);

        Assertions.assertThat(savedBottle.getId()).isGreaterThan(0);
        bottleRepository.delete(savedBottle);

       Optional<Color> colorOpt = colorRepository.findById(savedBottle.getColor().getId());
       Assertions.assertThat(colorOpt).isPresent();
       System.out.println(colorOpt.get());

       Optional<Region> region = regionRepository.findById(savedBottle.getRegion().getId());
       Assertions.assertThat(region).isPresent();
       System.out.println(region.get());

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
}
