package fr.eni.caveavin.dal;

import fr.eni.caveavin.bo.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {


}
