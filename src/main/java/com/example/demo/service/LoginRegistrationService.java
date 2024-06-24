package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Login;
import com.example.demo.repository.loginRepository;

@Service
public class LoginRegistrationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private loginRepository loginRepository;

    public void loginRegistration(String username, String password) {
        // ユーザー名の重複を確認
        if (loginRepository.existsByUsername(username)) {
            throw new DataIntegrityViolationException("ユーザー名がすでに存在します。別のユーザー名を選択してください。");
        }

        String hashedPassword = passwordEncoder.encode(password);
        loginRepository.saveAndFlush(new Login(username, hashedPassword, "GENERAL"));
    }
}
