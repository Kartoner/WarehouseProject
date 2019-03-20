package BOT.warehouseProject.Configuration;

import BOT.warehouseProject.Authentication.Services.IUserService;
import BOT.warehouseProject.Authentication.Services.Implementations.UserServiceImplementation;
import BOT.warehouseProject.Domain.Services.IWarehouseService;
import BOT.warehouseProject.Domain.Services.Implementations.WarehouseServiceImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public IUserService userService(){
        return new UserServiceImplementation();
    }

    @Bean
    public IWarehouseService warehouseService(){
        return new WarehouseServiceImplementation();
    }
}
