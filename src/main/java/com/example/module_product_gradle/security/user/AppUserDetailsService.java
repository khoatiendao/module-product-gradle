package com.example.module_product_gradle.security.user;

import com.example.module_product_gradle.common.constant.MessageConstant;
import com.example.module_product_gradle.common.exception.AppException;
import com.example.module_product_gradle.common.exception.ErrorCode;
import com.example.module_product_gradle.module.user.entity.User;
import com.example.module_product_gradle.module.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         User user = userRepository.findByEmail(email.trim().toLowerCase())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND, MessageConstant.EMAIL_NOT_FOUND));
        return new AppUserDetails(user);
    }
}
