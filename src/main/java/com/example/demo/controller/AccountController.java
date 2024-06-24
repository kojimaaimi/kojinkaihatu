package com.example.demo.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.UserRegistrationForm;
import com.example.demo.service.LoginRegistrationService;
import com.example.demo.userDetails.LoginDetailsImpl;

@Controller
public class AccountController {
	// 追記部分
	@Autowired
	private LoginRegistrationService loginRegistrationService;
	
	@GetMapping("/")
	public String showTop(@AuthenticationPrincipal LoginDetailsImpl loginDetails, Model model) {
		// ログインユーザーの情報を取得
		String username = loginDetails.getUsername();
		Long userId = loginDetails.getLogin().getId();
		
		// モデルに情報を追加
		model.addAttribute("username", username);
		model.addAttribute("userId", userId);
		return "index";
	}
	

	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	@GetMapping("/user/registration")
	public String showUserRegistration(@ModelAttribute("form") UserRegistrationForm form) {
		return "user-registration";
	}
	
	
    @PostMapping("/user/registration")
    public String loginRegistration(@Valid @ModelAttribute("form") UserRegistrationForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user-registration";
        }

        try {
            loginRegistrationService.loginRegistration(form.getUsername(), form.getPassword());
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "ユーザー名がすでに存在します。別のユーザー名を選択してください。");
            return "user-registration";
        }

        return "redirect:/login";
    }
}



