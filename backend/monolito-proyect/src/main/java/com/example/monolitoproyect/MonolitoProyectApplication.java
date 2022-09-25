package com.example.monolitoproyect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = "*")
@SpringBootApplication
public class MonolitoProyectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonolitoProyectApplication.class, args);
	}

}
