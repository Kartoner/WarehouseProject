package BOT.warehouseProject.Database.Repository;

import BOT.warehouseProject.Authentication.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r.name FROM Role r")
    List<String> getRoleNamesList();

    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    Optional<Role> findByRoleName(String roleName);
}
