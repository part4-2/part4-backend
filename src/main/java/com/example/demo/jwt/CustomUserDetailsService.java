package com.example.demo.jwt;

import com.example.demo.user.domain.entity.Users;
import com.example.demo.user.exception.UserException;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String oauthId) throws UsernameNotFoundException {
        Users users = userRepository.findByOauthId(oauthId)
                .orElseThrow(() -> new UserException.UserNotFoundException("회원을 찾을수 없습니다."));

        return new CustomUserDetails(users);
    }
}

