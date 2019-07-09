package BOT.warehouseProject.Authentication.Service;

import BOT.warehouseProject.Authentication.Entity.Role;
import BOT.warehouseProject.Authentication.Entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends UserDetailsService {
    public Boolean createUser(User user);

    public Boolean updateUser(User user);

    public Boolean deleteUser(Long id);

    public Optional<User> getUser(Long id);

    public List<User> getAllUsers();

    public List<User> getUsersByRole(String roleName);

    public Optional<User> getUserByUsername(String username);

    public List<String> getRolesList();

    public List<Role> getAllRoles();

    public Optional<Role> getRoleByName(String roleName);

    public UserDetails loadUserByUsername(String username);
}
