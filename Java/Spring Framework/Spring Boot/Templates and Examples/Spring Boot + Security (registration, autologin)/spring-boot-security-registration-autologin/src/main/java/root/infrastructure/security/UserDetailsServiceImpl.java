package root.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import root.application.UserGateway;
import root.domain.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService 
{
    @Autowired
    private UserGateway userGateway;
    
    @Override
    public UserDetails loadUserByUsername(String username) 
        throws UsernameNotFoundException 
    {
        UserDetails userDetails = null;        
        User user = userGateway.findByLogin(username);
        if (user != null)
        {
            userDetails = 
                org.springframework.security.core.userdetails.User.withUsername(username)
                    .password(user.getPassword()).roles(user.getRole().name()).build();
        }
        return userDetails;
    }
}
