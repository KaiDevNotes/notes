package root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import root.domain.User;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
        http.authorizeRequests()
                .antMatchers("/", "/login", "/registration", "/static/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole(User.Role.ADMIN.name())
                .antMatchers("/user/**").hasAnyRole(User.Role.USER.name())                
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .failureUrl("/login?error")
                .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
            .exceptionHandling().accessDeniedPage("/403");
    }
}
