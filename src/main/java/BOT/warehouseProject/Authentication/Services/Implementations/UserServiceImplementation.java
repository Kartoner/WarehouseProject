package BOT.warehouseProject.Authentication.Services.Implementations;

import BOT.warehouseProject.Authentication.Entities.User;
import BOT.warehouseProject.Authentication.Enums.UserStatus;
import BOT.warehouseProject.Authentication.Services.IUserService;
import BOT.warehouseProject.Database.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImplementation.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User authenticate(String username, String password) {
        return null;
    }

    @Override
    public Boolean createUser(String username,
                              String password,
                              UserStatus userStatus,
                              String firstName,
                              String lastName,
                              String address,
                              String email,
                              String phoneNumber) {
        return null;
    }

    @Override
    public Boolean updateUser(Long id,
                              String username,
                              String password,
                              UserStatus userStatus,
                              String firstName,
                              String lastName,
                              String address,
                              String email,
                              String phoneNumber) {
        return null;
    }

    @Override
    public User getUser(Long id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public List<User> getUsersByStatus(UserStatus userStatus) {
        return null;
    }
}
