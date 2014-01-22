package com.ah3nong.wd.security.authentication;

import java.io.Serializable;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ah3nong.wd.bean.User;
import com.ah3nong.wd.dao.UserDao;

public class LoginServiceImpl implements UserDetailsService, Serializable {
	private static final long serialVersionUID = -202471996774477982L;

	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		User user = userDao.findUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username + "不存在");
		}
		user.addAuthority(new GrantedAuthority() {
			private static final long serialVersionUID = 7602171887164551343L;

			@Override
			public String getAuthority() {
				return "ROLE_USER";
			}
		});
		return user;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}