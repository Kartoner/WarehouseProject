package BOT.warehouseProject.Authentication.Services;

import BOT.warehouseProject.Authentication.Entities.User;
import BOT.warehouseProject.Authentication.Enums.UserStatus;

import java.util.List;

public interface IUserService {
    public User authenticate(String username, String password);

    public Boolean createUser(String username,
                              String password,
                              UserStatus userStatus,
                              String firstName,
                              String lastName,
                              String address,
                              String email,
                              String phoneNumber);

    public Boolean updateUser(Long id,
                              String username,
                              String password,
                              UserStatus userStatus,
                              String firstName,
                              String lastName,
                              String address,
                              String email,
                              String phoneNumber);

    public User getUser(Long id);

    public List<User> getAllUsers();

    public List<User> getUsersByStatus(UserStatus userStatus);
}
