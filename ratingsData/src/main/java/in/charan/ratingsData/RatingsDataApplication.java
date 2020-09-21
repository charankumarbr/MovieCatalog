package in.charan.ratingsData;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Random;

@SpringBootApplication
@EnableEurekaClient
public class RatingsDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingsDataApplication.class, args);
	}

	@Bean
	public Random getRandom() {
		return new Random();
	}

}
