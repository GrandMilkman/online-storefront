package file.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import file.entity.Role;
import file.service.UserService;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final file.entity.User u = userService.getUser(username);

        if (u == null) {
            throw new UsernameNotFoundException("User not found");
        }

        final Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if (u.getRoles() != null) {
            for (Role r : u.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(r.getName()));
            }
        }

        authorities.add(new SimpleGrantedAuthority(username));

        return new User(username, u.getPassword(), true, true, true, true, authorities);
    }
}
