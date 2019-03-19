package BOT.warehouseProject.Database.Repositories;

import BOT.warehouseProject.Authentication.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
