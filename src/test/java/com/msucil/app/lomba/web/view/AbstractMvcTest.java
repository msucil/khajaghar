package com.msucil.app.lomba.web.view;

import org.springframework.context.annotation.Import;

import com.msucil.app.lomba.config.SecurityConfig;

@Import(value = SecurityConfig.class)
public abstract class AbstractMvcTest {

}
