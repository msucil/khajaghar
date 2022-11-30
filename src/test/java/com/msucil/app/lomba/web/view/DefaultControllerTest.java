package com.msucil.app.lomba.web.view;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(value = DefaultController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class DefaultControllerTest {

	@Autowired
	MockMvc mvc;

	@Test
	void testIndexShouldReturnIndexPage() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/")).andDo(print()).andExpectAll(view().name("index"));
	}
}
