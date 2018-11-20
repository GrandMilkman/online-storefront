package file.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import file.entity.Role;
import file.entity.User;
import file.service.UserService;

public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User u = userService.getUser(username);
        
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
        
        //TODO FIX RETURN VALUE
        return null;//new User();
    }

}
