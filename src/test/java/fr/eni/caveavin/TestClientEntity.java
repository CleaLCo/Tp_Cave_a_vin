package fr.eni.caveavin;

import fr.eni.caveavin.bo.client.Client;
import fr.eni.caveavin.dal.ClientRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestClientEntity {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @Commit
    public void testSavePost(){

        Client client = Client.builder()
                .username("keke29")
                .firstName("keke")
                .lastName("kenau")
                .password("mdp")
                .build();

        Client clientDb = clientRepository.save(client);

        Assertions.assertThat(clientDb).isNotNull();
        Assertions.assertThat(clientDb).isEqualTo(client);
        System.out.println(clientDb);

        Assertions.assertThat(clientDb.toString().contains("mdp")).isFalse();
    }


    @Test
    public void testDeleteClient(){

        final String username = "keke29";

        Client client = Client.builder()
                .username("keke29")
                .firstName("keke")
                .lastName("kenau")
                .password("mdp")
                .build();

        Client clientDb = clientRepository.save(client);
        System.out.println(clientDb);
        clientRepository.delete(clientDb);

        Optional<Client> clientOpt = clientRepository.findById(username);

        Assertions.assertThat(clientOpt.isPresent()).isFalse();

    }

}
