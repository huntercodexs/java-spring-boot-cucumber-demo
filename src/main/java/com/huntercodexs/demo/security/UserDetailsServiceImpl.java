package com.huntercodexs.demo.security;

import com.huntercodexs.demo.entity.UserEntity;
import com.huntercodexs.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            System.out.println("Username not found: " + username);
            throw new UsernameNotFoundException(username);
        }

        return new CustomUser(userEntity, Collections.emptyList());
    }

}
