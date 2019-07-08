package BOT.warehouseProject.Authentication.Service.Implementation;

import BOT.warehouseProject.Authentication.Entity.Role;
import BOT.warehouseProject.Authentication.Entity.User;
import BOT.warehouseProject.Authentication.Service.IUserService;
import BOT.warehouseProject.Database.Repository.RoleRepository;
import BOT.warehouseProject.Database.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service(value = "userService")
public class UserServiceImpl implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public Boolean createUser(User user) {
        try{
            String tempPassword = user.getPassword();
            user.setPassword(bCryptPasswordEncoder.encode(tempPassword));
            userRepository.save(user);
        } catch (Exception ex){
            log.info("Failed creating user");
            return Boolean.FALSE;
        }
        log.info("Created new user");

        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean updateUser(User user) {
        try{
            String tempPassword = user.getPassword();
            user.setPassword(bCryptPasswordEncoder.encode(tempPassword));
            userRepository.save(user);
        } catch (Exception ex){
            log.info("Failed updating user");
            return Boolean.FALSE;
        }
        log.info("Updated user");

        return Boolean.TRUE;
    }

    @Override
    @Transactional
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
    @Transactional
    public Optional<User> getUser(Long id) { return userRepository.findById(id);}

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public List<User> getUsersByRole(String roleName) {
        return userRepository.findByUserRole(roleName);
    }

    @Override
    @Transactional
    public Optional<User> getUserByUsername(String username) { return userRepository.findByUsername(username); }

    @Override
    @Transactional
    public List<String> getRolesList() { return roleRepository.getRoleNamesList(); }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Role role = user.get().getUserRole();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));

        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), grantedAuthorities);
    }
}
