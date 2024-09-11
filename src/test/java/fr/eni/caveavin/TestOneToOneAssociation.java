package fr.eni.caveavin;

import fr.eni.caveavin.bo.Address;
import fr.eni.caveavin.bo.Client;
import fr.eni.caveavin.dal.AddressRepository;
import fr.eni.caveavin.dal.ClientRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class TestOneToOneAssociation {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

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
    @Commit
    public void testSavedClientAdress() {

        final String username = "keke29";
        Optional<Client> clientOpt = clientRepository.findById(username);

        Assertions.assertThat(clientOpt).isPresent();
        Assertions.assertThat(clientOpt.get().getAddress()).isNotNull();

        System.out.println(clientOpt.get());
        System.out.println(clientOpt.get().getAddress());

    }


    @Test
    public void testDeleteCascade() {

        final String username = "keke29";

        Optional<Client> clientOpt = clientRepository.findById(username);

        Assertions.assertThat(clientOpt).isPresent();
        clientRepository.delete(clientOpt.get());
        Optional<Client> clientOpt2 = clientRepository.findById(username);
        Assertions.assertThat(clientOpt2).isEmpty();

        Optional<Address> addressOpt = addressRepository.findById(1);
        Assertions.assertThat(addressOpt).isEmpty();

    }

    @Test
    public void testDetachedAddress() {

        final String username = "keke29";
        Optional<Client> clientOpt = clientRepository.findById(username);
        Assertions.assertThat(clientOpt).isPresent();

        clientOpt.get().setAddress(null);
        clientRepository.save(clientOpt.get());

        Optional<Address> addressOpt = addressRepository.findById(1);
        Assertions.assertThat(addressOpt).isNotPresent();

    }

}
