package fr.eni.caveavin.dal;

import fr.eni.caveavin.bo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
