package fr.eni.caveavin;

import fr.eni.caveavin.bo.Owner;
import fr.eni.caveavin.bo.User;
import fr.eni.caveavin.bo.client.Client;
import fr.eni.caveavin.dal.ClientRepository;
import fr.eni.caveavin.dal.OwnerRepository;
import fr.eni.caveavin.dal.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;


@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestHeritage {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    public void initDB() {
        List<User> users = new ArrayList<>();
        users.add(User
                .builder()
                .username("harrisonford@email.fr")
                .password("IndianaJones3")
                .lastName("Ford")
                .firstName("Harrison")
                .build());

        users.add(Owner
                .builder()
                .username("georgelucas@email.fr")
                .password("Réalisateur&Producteur")
                .lastName("Lucas")
                .firstName("George")
                .siret("12345678901234")
                .build());

        users.add(Client
                .builder()
                .username("natalieportman@email.fr")
                .password("MarsAttacks!")
                .lastName("Portman")
                .firstName("Natalie")
                .build());

        // Contexte de la DB
        users.forEach(e -> {
            entityManager.persist(e);
        });
    }

    //TODO

    @Test
    public void testFindAllUsers(){
        System.out.println("******************testFindAllUsers******************");

        List<User> users = userRepository.findAll();
        Assertions.assertThat(users).hasSize(3);

        users.forEach(u ->{
            System.out.println("***********************************************");
            Assertions.assertThat(u.getUsername()).isNotNull();

            System.out.println(u);
        });


    }


    @Test
    public void testFindAllClients(){
        System.out.println("******************testFindAllClients******************");

        List<Client> clients = clientRepository.findAll();
        Assertions.assertThat(clients).isNotEmpty();
        Assertions.assertThat(clients).hasSize(1);

        System.out.println(clients);


    }


    @Test
    public void testFindAllOwners(){
        System.out.println("******************testFindAllOwners******************");

        List<Owner> owners = ownerRepository.findAll();
        Assertions.assertThat(owners).isNotEmpty();
        Assertions.assertThat(owners).hasSize(1);

        System.out.println(owners);

    }

}
