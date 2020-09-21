package in.charan.movieCatalog.client;

import in.charan.movieCatalog.model.request.ratingsdataservice.UserMovieRating;
import in.charan.movieCatalog.util.BeanUtil;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Random;

public class RatingsDataService {

    public UserMovieRating getUserRatedMovies(String userId) {
        int isZero = new Random().nextInt(100) % 2;

        if (isZero == 0) {
            RestTemplate restTemplate = BeanUtil.getBeanUtil(RestTemplate.class);
            return restTemplate.getForObject("http://ratingsdataservice/ratings/users/" + userId, UserMovieRating.class);
        } else {
            WebClient.Builder builder = BeanUtil.getBeanUtil(WebClient.Builder.class);
            return builder
                    .baseUrl("http://ratingsdataservice")
                    .build()
                    .get()
                    .uri("/ratings/users/" + userId)
                    .retrieve()
                    .bodyToMono(UserMovieRating.class)
                    .block();
        }
    }

}