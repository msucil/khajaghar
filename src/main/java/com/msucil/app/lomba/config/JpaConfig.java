package com.msucil.app.lomba.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.msucil.app.lomba.persistance")
@EnableTransactionManagement
public class JpaConfig {

}
