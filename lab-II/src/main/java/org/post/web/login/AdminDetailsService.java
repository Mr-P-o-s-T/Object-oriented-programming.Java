package org.post.web.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AdminDetailsService implements UserDetailsService {

	@Autowired
	private IAdminService IAdminService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Admin admin = IAdminService.getUser("admin");

		Set<GrantedAuthority> roles = new HashSet();
		roles.add(new SimpleGrantedAuthority(UserRoleEnum.ADMIN.name()));

		UserDetails userDetails =
				new org.springframework.security.core.userdetails.User(admin.getLogin(),
						admin.getPassword(),
						roles);

		return userDetails;
	}

}
