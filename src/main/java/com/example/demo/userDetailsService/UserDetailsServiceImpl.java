package com.example.demo.userDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Login;
import com.example.demo.repository.loginRepository;
import com.example.demo.userDetails.LoginDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private loginRepository loginRepository;
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Login login = loginRepository.findByUsername(username);
		if(login == null) {
			throw new UsernameNotFoundException("Not Found");
		}
		return new LoginDetailsImpl(login);

		// ユーザー情報をセッションに保存
        // SecurityContextHolder.getContext().setAuthentication(userDetails);
        // return userDetails;
	}
}

