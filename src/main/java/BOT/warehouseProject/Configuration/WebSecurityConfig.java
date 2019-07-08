package BOT.warehouseProject.Configuration;

import BOT.warehouseProject.Authentication.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IUserService userService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/user", "/user*", "/user/*/*", "/user/role*").hasAuthority("ADMIN")
                    .antMatchers("/delivery", "/delivery*", "/delivery/*/*", "/delivery/status*", "/item*", "/item/*/*").hasAnyAuthority("ADMIN", "EMPLOYEE")
                    .antMatchers("/cart", "/cartAdd/*", "/cart/**", "/item", "/item/type*", "/user/details", "/user/delivery", "/delivery/*").hasAnyAuthority("ADMIN", "EMPLOYEE", "CUSTOMER")
                    .antMatchers("/resources/**", "/css/**", "/webjars/**", "/", "/index").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/index", true)
                    .permitAll()
                .and()
                .logout()
                    .logoutSuccessUrl("/index")
                    .permitAll()
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");
    }


}
