package ru.otus.hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
public class FilmServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmServerApplication.class, args);
		System.out.printf("Library Server Application address: %n%s%n", "http://localhost:8100/film/list");
	}

}
