package io.nightfrost.mytubeapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MyTubeApplication extends SpringBootServletInitializer{
	
	@Bean
	ModelMapper modelMapper() {
	    return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(MyTubeApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("api/v1/video/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
				registry.addMapping("api/v1/user/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
				registry.addMapping("api/v1/comment/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
				registry.addMapping("api/v1/playlist/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
			}
		};
	}
	
}