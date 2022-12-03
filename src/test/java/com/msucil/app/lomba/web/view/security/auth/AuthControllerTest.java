package com.msucil.app.lomba.web.view.security.auth;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.msucil.app.lomba.web.view.AbstractMvcTest;

@WebMvcTest(AuthController.class)
class AuthControllerTest extends AbstractMvcTest {
	
	@Autowired
	private MockMvc mvc;

	@Test
	void testLoginShouldReturnLoginPage() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/security/auth/login"))
		.andExpect(MockMvcResultMatchers.view().name(equalTo("pages/security/auth/login")));
	}

}
