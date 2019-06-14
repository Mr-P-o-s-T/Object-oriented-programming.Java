package org.post.web.login;

import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService {

	@Override
	public Admin getUser(String login) {
		Admin admin = new Admin();
		admin.setLogin(login);
		admin.setPassword("admin");

		return admin;
	}

}

