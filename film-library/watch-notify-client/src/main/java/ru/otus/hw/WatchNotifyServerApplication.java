package ru.otus.hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class WatchNotifyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatchNotifyServerApplication.class, args);
		System.out.printf("Watch Notufy Server Application address: %n%s%n", "http://localhost:8150/");
	}

}
