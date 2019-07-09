package BOT.warehouseProject.Authentication.Service.Implementation;

import BOT.warehouseProject.Authentication.Service.ISecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service(value = "securityService")
public class SecurityServiceImpl implements ISecurityService {

    private static final Logger log = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails instanceof UserDetails) {
            log.info("Logged in user: " + ((UserDetails) userDetails).getUsername());
            return ((UserDetails)userDetails).getUsername();
        }

        log.info("Nobody logged in!");
        return null;
    }
}
