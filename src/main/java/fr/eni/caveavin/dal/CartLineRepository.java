package fr.eni.caveavin.dal;

import fr.eni.caveavin.bo.client.CartLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartLineRepository extends JpaRepository<CartLine, Integer> {
}
