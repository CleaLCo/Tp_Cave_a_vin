package fr.eni.caveavin.dal;

import fr.eni.caveavin.bo.client.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
