package com.fabiocompany.supermercadosdeltaplus.app.config.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.fabiocompany.supermercadosdeltaplus.app.config.auth.UserDetailService;
import com.fabiocompany.supermercadosdeltaplus.exception.NotFoundException;
import com.fabiocompany.supermercadosdeltaplus.model.User;
import com.fabiocompany.supermercadosdeltaplus.model.service.IUserService;
import com.fabiocompany.supermercadosdeltaplus.service.exception.ServiceException;

@Component
public class UserDetailService implements UserDetailsService {
	private static Logger LOG = LoggerFactory.getLogger(UserDetailService.class);
	@Autowired
	private IUserService userService;
	
	public static String AUTOLOGIN="**autoLogin**";
	public static String AUTOLOGIN_BYTOKEN="**autoLogin byToken**";

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		boolean autoLogin = false;
		boolean byToken = false;
		if (username.startsWith(AUTOLOGIN)) {
			autoLogin = true;
			username = username.substring(AUTOLOGIN.length(), username.length());
		}
		if (username.startsWith(AUTOLOGIN_BYTOKEN)) {
			autoLogin = true;
			byToken = true;
			username = username.substring(AUTOLOGIN_BYTOKEN.length(), username.length());
		}
		if (!autoLogin)
			LOG.debug("Try login: {}", username);
		User r = null;
		try {
			r = userService.load(username);
			LOG.debug("User logged: {} - {}", r.getUsername(), r.getAuthorities());
		} catch (ServiceException e) {
		} catch (NotFoundException e) {
			throw new UsernameNotFoundException(username + " no encontrado");
		}

		if (!autoLogin)
			LOG.debug("{} logged OK", username);
		LOG.debug("User logged: {} - {} [{}]", r.getUsername(), r.getAuthorities(),
				autoLogin ? (byToken ? "autologin byToken" : "autologin") : "login inicial");
		return r;
	}

}