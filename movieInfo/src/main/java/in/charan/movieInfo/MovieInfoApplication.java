package in.charan.movieInfo;

import in.charan.movieInfo.repo.MovieInfoRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
@EnableEurekaClient
public class MovieInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieInfoApplication.class, args);
	}

	@Bean
	public MovieInfoRepo providesMovieInfoRepo() {
		return new MovieInfoRepo();
	}

	@Bean
	public RestTemplate providesRestTemplate() {
		return new RestTemplateBuilder()
				.setConnectTimeout(Duration.ofSeconds(4))
				.build();
	}

}
