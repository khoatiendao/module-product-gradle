package com.example.module_product_gradle.module.auth.controller;

import com.example.module_product_gradle.common.dto.res.ApiResponse;
import com.example.module_product_gradle.module.auth.dto.req.AuthReqDto;
import com.example.module_product_gradle.module.auth.dto.res.AuthResDto;
import com.example.module_product_gradle.module.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Void> registerController(@Valid @RequestBody AuthReqDto.register registerDto) {
        authService.register(registerDto);
        return ApiResponse.ok("Register Success", null);
    }

    @PostMapping("/create-admin")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Void> createAdminController(@Valid @RequestBody AuthReqDto.createAdminDto adminDto) {
        authService.createAdmin(adminDto);
        return ApiResponse.ok("Created", null);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<AuthResDto> loginController(@Valid @RequestBody AuthReqDto.login loginDto) {
        return ApiResponse.ok("Login Success", authService.login(loginDto));
    }

    @PostMapping("/refresh-token")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<AuthResDto> refreshTokenController(@Valid @RequestBody AuthReqDto.refreshTokenDto rftokenDto) {
        return ApiResponse.ok(authService.refreshToken(rftokenDto.refreshToken()));
    }

    @PatchMapping("/{id}/logout")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Void> logoutController(@PathVariable Long id) {
        authService.logout(id);
        return ApiResponse.ok("Logout", null);
    }
}
