package BOT.warehouseProject.Database.Repository;

import BOT.warehouseProject.Authentication.Entity.User;
import BOT.warehouseProject.Authentication.Enum.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.userStatus = ?1")
    List<User> findByUserStatus(UserStatus userStatus);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2")
    Optional<User> findForAuthentication(String username, String password);
}
