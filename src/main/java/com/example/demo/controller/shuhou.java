package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.userDetails.LoginDetailsImpl;

@Controller
public class shuhou {
    
    @GetMapping("/shuhou")
	public String showTop(@AuthenticationPrincipal LoginDetailsImpl loginDetails, Model model) {
		// ログインユーザーの情報を取得
		String username = loginDetails.getUsername();
		Long userId = loginDetails.getLogin().getId();
		
		// モデルに情報を追加
		model.addAttribute("username", username);
		model.addAttribute("userId", userId);
		return "shuhou";
	}
}
