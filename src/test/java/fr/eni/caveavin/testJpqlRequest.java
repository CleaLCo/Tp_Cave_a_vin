package fr.eni.caveavin;


import fr.eni.caveavin.bo.User;
import fr.eni.caveavin.bo.client.Address;
import fr.eni.caveavin.bo.client.Client;
import fr.eni.caveavin.dal.ClientRepository;
import fr.eni.caveavin.dal.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class testJpqlRequest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        System.out.println("Début d'un test....");


        Address address = Address.builder()
                .street("4 rue Georges Perros")
                .zipCode("29000")
                .city("Quimper")
                .build();

        Client client = Client.builder()
                .username("keke29")
                .firstName("keke")
                .lastName("kenau")
                .password("mdp")
                .address(address)
                .build();

        Client clientBdd = clientRepository.save(client);
    }


    @Test
    public void testSearchByUsername() {
        //Arrange
        String username = "keke29";
        //Act
        User user = userRepository.searchUserByUsername(username);
        //Assert
        Assertions.assertThat(user).isNotNull();
        System.out.println(user);

    }


    @Test
    public void testSearchByUsernameAndPassword() {
        //Arrange
        String username = "keke29";
        String password = "mdp";
        //Act
        User user = userRepository.searchUserByUsernameAndPassword(username, password);
        //Assert
        Assertions.assertThat(user).isNotNull();
        System.out.println(user);

    }


}
