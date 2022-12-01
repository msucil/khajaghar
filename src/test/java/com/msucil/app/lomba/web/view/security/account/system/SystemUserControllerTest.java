package com.msucil.app.lomba.web.view.security.account.system;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.hamcrest.beans.HasPropertyWithValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.msucil.app.lomba.config.SecurityConfig;
import com.msucil.app.lomba.service.security.system.SystemUserService;

@WebMvcTest(value = SystemUserController.class)
@Import(value = SecurityConfig.class)
class SystemUserControllerTest {

	@MockBean
	private SystemUserService userService;

	@Autowired
	private MockMvc mvc;

	@Test
	void testRegisterUserShouldReturnRegistrationPage() throws Exception {

		when(userService.canRegisterSystemUser()).thenReturn(true);

		mvc.perform(MockMvcRequestBuilders.get("/security/account/system/register-user")).andExpect(status().isOk())
				.andExpect(view().name(equalTo("pages/security/account/system/system-user-form")));
	}

	@Test
	void testRegisterUserShouldReturn405ErrorOnSystemUserTrue() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get("/security/account/system/register-user")).andExpect(status().is(405));
	}

	@Test
	void testRegisterUserShouldRegisterSystemUser() throws Exception {
		when(userService.canRegisterSystemUser()).thenReturn(true);

		var form = new SystemUserForm();
		form.setFullName("full name");
		form.setUsername("username");
		form.setEmail("email@email.com");
		form.setPassword("password");
		form.setVerifyPassword("password");

		mvc.perform(MockMvcRequestBuilders.post("/security/account/system/register-user")
				.with(csrf())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED).param("fullName", form.getFullName())
				.param("username", form.getUsername()).param("email", form.getEmail())
				.param("password", form.getPassword()).param("verifyPassword", form.getVerifyPassword())
				

		).andDo(print()).andExpect(model().hasNoErrors()).andExpect(status().is3xxRedirection())
				.andExpect(flash().attribute("success", equalTo("System User Registration Successful")));
	}

	
	@Test
	void testRegisterUserShouldReturnValidationMessageWithFormPage() throws Exception {
		when(userService.canRegisterSystemUser()).thenReturn(true);

		var form = new SystemUserForm();
		form.setFullName("full name");
		form.setUsername("username");
		form.setEmail("email@email.com");
		form.setPassword("password");
		form.setVerifyPassword("password123");

		mvc.perform(MockMvcRequestBuilders.post("/security/account/system/register-user")
				.with(csrf())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED).param("fullName", form.getFullName())
				.param("username", form.getUsername()).param("email", form.getEmail())
				.param("password", form.getPassword()).param("verifyPassword", form.getVerifyPassword()))
				.andExpect(model().attributeHasFieldErrors("form", "password"))
				.andExpect(model().attribute("form",
						HasPropertyWithValue.hasProperty("fullName", equalTo(form.getFullName()))))
				.andExpect(model().attribute("form",
						HasPropertyWithValue.hasProperty("username", equalTo(form.getUsername()))))
				.andExpect(
						model().attribute("form", HasPropertyWithValue.hasProperty("email", equalTo(form.getEmail()))))
				.andExpect(model().attribute("form",
						HasPropertyWithValue.hasProperty("password", equalTo(form.getPassword()))))
				.andExpect(model().attribute("form",
						HasPropertyWithValue.hasProperty("verifyPassword", equalTo(form.getVerifyPassword()))));
	}

}
