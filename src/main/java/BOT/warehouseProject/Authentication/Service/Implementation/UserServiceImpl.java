package BOT.warehouseProject.Authentication.Service.Implementation;

import BOT.warehouseProject.Authentication.Entity.User;
import BOT.warehouseProject.Authentication.Service.IUserService;
import BOT.warehouseProject.Database.Repository.RoleRepository;
import BOT.warehouseProject.Database.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<User> authenticate(String username, String password) {
        return userRepository.findForAuthentication(username, password);
    }

    @Override
    public Boolean createUser(User user) {
        try{
            userRepository.save(user);
        } catch (Exception ex){
            log.info("Failed creating user");
            return Boolean.FALSE;
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
            return Boolean.FALSE;
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
            return Boolean.FALSE;
        }
        log.info("Deleted user");

        return Boolean.TRUE;
    }

    @Override
    public Optional<User> getUser(Long id) { return userRepository.findById(id);}

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByRole(String roleName) {
        return userRepository.findByUserRole(roleName);
    }

    @Override
    public Optional<User> getUserByUsername(String username) { return userRepository.findByUsername(username); }

    @Override
    public List<String> getRolesList() { return roleRepository.getRoleNamesList(); }
}
