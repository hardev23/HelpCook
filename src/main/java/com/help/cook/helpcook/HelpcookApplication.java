package com.help.cook.helpcook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * @author Jennifer
 * @version 1.0, 2022/11/05
 */
@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.help.cook.helpcook.repository")
public class HelpcookApplication{
	
	/**
	 * Clase Main
	 * @param args. Lista de argumentos para la ejecución
	 */
	public static void main(String[] args) {
		SpringApplication.run(HelpcookApplication.class, args);
	}
	
	/**
	 * Método para conceder los permisos de acceso del Front
	 * @return devuelve la nueva configuración
	 */
@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("*").allowedHeaders("*");
			}
		};
	}

}