package file.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import file.entity.Role;
import file.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final file.entity.User u = userService.getUser(username);
        
        if (u == null) {
            throw new UsernameNotFoundException("User not found");
        }
        
        final Collection<SimpleGrantedAuthority> sga = new ArrayList<>();
        
        if (u.getRoles() != null) {
            for (Role r : u.getRoles()) {
                sga.add(new SimpleGrantedAuthority(r.getName()));
            }
        }
        
        sga.add(new SimpleGrantedAuthority(username));
        
        return new User(username, u.getPassword(), true, true, true, true, sga);
    }
}
