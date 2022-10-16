package com.mycompany.empms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


/**
 * equivalent to these annotations
 * @Configuration
 * @ComponentScan
 * @EnableAutoConfiguration
 */
@SpringBootApplication
public class EmpmsApplication {
	private static final Logger Log= LoggerFactory.getLogger(EmpmsApplication.class);


	public static void main(String[] args) {
		ConfigurableApplicationContext context=SpringApplication.run(EmpmsApplication.class, args);
	   // FrontEndClient frontend=context.getBean(FrontEndClient.class);
	   // frontend.render();
		Log.debug("*****i am written for debug level");
		Log.info("*****i am written for info level");
		Log.error("****i am written for error level");
	}

	/**
	 *
	 * for handling cross origin requests
	 */
	@Bean
	public CorsFilter corsFilter(){
		UrlBasedCorsConfigurationSource src=new UrlBasedCorsConfigurationSource();
		CorsConfiguration configuration=new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.addAllowedHeader("*");
		configuration.addAllowedOrigin("http://localhost:4200");
		configuration.addAllowedMethod("*");
		src.registerCorsConfiguration("/**",configuration);
		return new CorsFilter(src);
	}

}
