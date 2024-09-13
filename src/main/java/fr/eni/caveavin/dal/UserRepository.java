package fr.eni.caveavin.dal;

import fr.eni.caveavin.bo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User searchUserByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    User searchUserByUsernameAndPassword(@Param("username") String keyword, @Param("password") String password);

}
