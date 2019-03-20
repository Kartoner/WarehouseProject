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
        return userRepository.findForAuthentication(username, password);
    }

    @Override
    public Boolean createUser(User user) {
        try{
            userRepository.save(user);
        } catch (Exception ex){
            log.info("Failed creating user");
        }
        log.info("Created new user");

        return Boolean.TRUE;
    }

    @Override
    public Boolean updateUser(User user) {
        try{
            userRepository.save(user);
        } catch (Exception ex){
            log.info("Failed updating user");
        }
        log.info("Updated user");

        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteUser(Long id) {
        try{
            userRepository.deleteById(id);
        } catch (Exception ex){
            log.info("Failed deleting user");
        }
        log.info("Deleted user");

        return Boolean.TRUE;
    }

    @Override
    public User getUser(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByStatus(UserStatus userStatus) {
        return userRepository.findByUserStatus(userStatus);
    }
}
