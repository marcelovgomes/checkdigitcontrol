package com.example.checkdigitcontrol.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * AppConfig
 *
 * This Bean is responsible for scanning the Spring-managed components
 *
 * @author  Marcelo Gomes
 * @created 2022-10-01
 */

@Configuration
@ComponentScan("com.example.checkdigitcontrol.services")
public class AppConfig {
}
