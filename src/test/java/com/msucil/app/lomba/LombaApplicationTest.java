package com.msucil.app.lomba;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.msucil.app.lomba.setup.TestContainerDatabseConfig;

@SpringBootTest
@Import(TestContainerDatabseConfig.class)
class LombaApplicationTest {

	@Test
	void contextLoads() {
		
	}

}
