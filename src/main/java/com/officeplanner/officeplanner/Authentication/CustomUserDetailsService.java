package com.officeplanner.officeplanner.Authentication;

import com.officeplanner.officeplanner.Dao.UserRepository;
import com.officeplanner.officeplanner.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);
        if(user == null){

            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
}
