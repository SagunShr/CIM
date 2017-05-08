package com.cim.adcampaign.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author sagun
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.cim.adcampaign")
public class AppConfig {

}