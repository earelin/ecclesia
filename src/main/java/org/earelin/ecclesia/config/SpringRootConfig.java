package org.earelin.ecclesia.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "org.earelin.ecclesia.service" })
public class SpringRootConfig {
}