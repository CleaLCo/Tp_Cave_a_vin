package fr.eni.caveavin.dal;

import fr.eni.caveavin.bo.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
