package fr.eni.caveavin.dal;

import fr.eni.caveavin.bo.client.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
