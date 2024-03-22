package com.gl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gl.bean.User;
import com.gl.dao.UserDAO;

@Service // Ensure that it is annotated with @Service to make it a Spring bean
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO udao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = udao.findByUsername(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        
        return new MyUserDetails(user);
    }
}
