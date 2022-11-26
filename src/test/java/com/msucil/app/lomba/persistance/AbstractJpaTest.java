package com.msucil.app.lomba.persistance;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.msucil.app.lomba.setup.TestContainerDatabseConfig;

@DataJpaTest
@Import(TestContainerDatabseConfig.class)
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class
})
public abstract class AbstractJpaTest {

}
