package com.example.module_product_gradle.module.auth.service;

import com.example.module_product_gradle.common.CommonEnum.UserRoleEnum;
import com.example.module_product_gradle.common.constant.MessageConstant;
import com.example.module_product_gradle.common.exception.AppException;
import com.example.module_product_gradle.common.exception.ErrorCode;
import com.example.module_product_gradle.module.auth.dto.req.AuthReqDto;
import com.example.module_product_gradle.module.auth.dto.res.AuthResDto;
import com.example.module_product_gradle.module.user.entity.User;
import com.example.module_product_gradle.module.user.repository.UserFunctionRepository;
import com.example.module_product_gradle.module.user.repository.UserRepository;
import com.example.module_product_gradle.security.jwt.JwtProperties;
import com.example.module_product_gradle.security.jwt.JwtService;
import com.example.module_product_gradle.security.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserFunctionRepository userFunctionRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final JwtProperties jwtProperties;

    @Transactional
    public void register(AuthReqDto.register registerDto) {
        String email = registerDto.email().trim().toLowerCase();
        if(userRepository.existsByEmail(email)) {
            throw new AppException(ErrorCode.CONFLICT, MessageConstant.EMAIL_ALREADY_EXISTS);
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(registerDto.password()));
        userRepository.save(user);
    }

    @Transactional
    public void createAdmin(AuthReqDto.createAdminDto adminDto) {
        String email = adminDto.email().trim().toLowerCase();
        if(userRepository.existsByEmail(email)) {
            throw new AppException(ErrorCode.CONFLICT, MessageConstant.EMAIL_ALREADY_EXISTS);
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(adminDto.password()));
        user.setRole(UserRoleEnum.ADMIN);
        userRepository.save(user);
    }

    @Transactional
    public AuthResDto login(AuthReqDto.login loginDto) {
        User user = userRepository.findByEmail(loginDto.email().trim().toLowerCase())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND, MessageConstant.EMAIL_NOT_FOUND));

        if(!passwordEncoder.matches(loginDto.password(), user.getPassword())) {
            throw new AppException(ErrorCode.BAD_REQUEST, MessageConstant.EMAIL_OR_PASS_WRONG);
        }

        if(user.getActive() == false) {
            throw new AppException(ErrorCode.UNAUTHORIZED, MessageConstant.ACCOUNT_IS_DELETED);
        }

        String refreshToken = TokenUtil.generateRefreshToken();
        user.setRefreshToken(TokenUtil.sha256Hex(refreshToken));
        userRepository.save(user);

        return new AuthResDto(jwtService.generateAccessToken(user), refreshToken);
    }

    @Transactional
    public AuthResDto refreshToken(String refreshToken) {
        User user = userRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND, MessageConstant.TOKEN_NOT_FOUND));

        String newRefreshToken = TokenUtil.generateRefreshToken();
        user.setRefreshToken(TokenUtil.sha256Hex(newRefreshToken));
        userRepository.save(user);

        return new AuthResDto(jwtService.generateAccessToken(user), newRefreshToken);
    }

    @Transactional
    public void logout(Long userId) {
        userFunctionRepository.removeRefreshToken(userId);
    }
}
