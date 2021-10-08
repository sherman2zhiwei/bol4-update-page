package com.example.backend;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;
import java.io.FileInputStream;
import org.bson.types.Binary;

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

		File filmletImage = new File("./src/main/resources/static/filmlet.jpg");
		FileInputStream f = new FileInputStream(filmletImage);
		byte b[] = new byte[f.available()];
		f.read(b);
		Binary data = new Binary(b);
		f.close();

		repository.save(new ComebackDate(LocalDateTime.of(2020, 11, 4, 17, 0), "Filmlet", data));
		// repository.save(new ComebackDate(LocalDateTime.of(2020, 10, 23, 12, 0), "Red Puberty"));

		System.out.println(repository.findFirstByOrderByIdDesc());
		System.out.println(repository.findFirstByDatetimeGreaterThanOrderByIdDesc(LocalDateTime.of(2020, 11, 5, 17, 0)));


	}

}
