package BOT.warehouseProject.Authentication.Service;

import BOT.warehouseProject.Authentication.Entity.User;
import BOT.warehouseProject.Authentication.Enum.UserStatus;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    public Optional<User> authenticate(String username, String password);

    public Boolean createUser(User user);

    public Boolean updateUser(User user);

    public Boolean deleteUser(Long id);

    public Optional<User> getUser(Long id);

    public List<User> getAllUsers();

    public List<User> getUsersByStatus(UserStatus userStatus);

    public Optional<User> getUserByUsername(String username);
}
