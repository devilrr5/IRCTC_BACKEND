package com.irctc.config;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import com.irctc.repository.UserRepository;
import com.irctc.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.stream.Collectors;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository ur){this.userRepository=ur;}
    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
        var authorities = user.getRoles() == null ? java.util.List.of() : user.getRoles().stream().map(r->new SimpleGrantedAuthority("ROLE_"+r.getName())).collect(Collectors.toList());
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).authorities((GrantedAuthority) authorities).accountExpired(false).accountLocked(false).credentialsExpired(false).disabled(!user.isEnabled()).build();
    }
}
