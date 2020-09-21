package in.charan.movieDiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MovieDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieDiscoveryApplication.class, args);
	}

}
