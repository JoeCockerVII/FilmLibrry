package ru.otus.hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class FilmLibraryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmLibraryServerApplication.class, args);
		System.out.printf("Library Server Application address: %n%s%n", "http://localhost:8090/");
		System.out.printf("Library Server Application address: %n%s%n", "http://localhost:8090/film/list");
		System.out.printf("Library Server Application address: %n%s%n", "http://localhost:8090/watch/create");
	}

}
