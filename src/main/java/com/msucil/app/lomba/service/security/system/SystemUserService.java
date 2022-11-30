package com.msucil.app.lomba.service.security.system;

import com.msucil.app.lomba.persistance.security.user.User;

public interface SystemUserService {

	User registerSystemUser(User user);
	
	boolean canRegisterSystemUser();
}
