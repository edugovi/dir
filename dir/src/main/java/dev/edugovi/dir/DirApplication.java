package dev.edugovi.dir;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class DirApplication {

	public static void main(String[] args) {
		ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		SpringApplication.run(DirApplication.class, args);
	}
}
