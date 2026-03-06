package com.salesianostriana.dam.precioustime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class PreciousTimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreciousTimeApplication.class, args);
	}

}
