package main.java.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

	@Configuration
	@EnableWebMvc
	@ComponentScan(basePackages = "main.java.controller, main.java.test")
	public class MessageAppConfiguration { {
	     
	 
	}
}
