package com.example.demo.userDetails;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.Login;

public class LoginDetailsImpl implements UserDetails {
	private Login login;
	
	public LoginDetailsImpl(Login login) {
		this.login = login;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("ROLE_" + this.login.getRolename());
	}
	
	@Override
	public String getUsername() {
		return login.getUsername();
	}

	@Override
	public String getPassword() {
		return login.getPassword();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	// 新しいメソッドを追加
    public Login getLogin() {
        return this.login;
    }
}
