package com.example.backend;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired
	private ComebackDateRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();

		repository.save(new ComebackDate(LocalDateTime.of(2020, 11, 4, 17, 0), "Filmlet"));
		// repository.save(new ComebackDate(LocalDateTime.of(2020, 10, 23, 12, 0), "Red Puberty"));

		System.out.println(repository.findFirstByOrderByIdDesc());


	}

}
