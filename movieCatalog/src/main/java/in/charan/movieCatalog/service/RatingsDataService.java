package in.charan.movieCatalog.service;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import in.charan.movieCatalog.model.request.ratingsdataservice.UserMovieRating;
import in.charan.movieCatalog.model.response.CustomException;
import in.charan.movieCatalog.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerErrorException;

import javax.management.ServiceNotFoundException;
import javax.naming.ServiceUnavailableException;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Service
public class RatingsDataService {

    @Autowired
    private EurekaClient eurekaClient;

    public UserMovieRating getUserRatedMovies(String userId) throws Exception {

        checkServiceAvailability();

        int isZero = new Random().nextInt(100) % 2;
        if (isZero == 0) {
            RestTemplate restTemplate = BeanUtil.getBeanUtil(RestTemplate.class);
            return restTemplate.getForObject("http://ratingsdataservice/ratings/users/" + userId, UserMovieRating.class);
            //return restTemplate.getForObject(baseUrl + "/ratings/users/" + userId, UserMovieRating.class);

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

    private void checkServiceAvailability() throws Exception {

        Application application = eurekaClient.getApplication("ratingsdataservice");
        if ((application == null) || application.getInstances() == null || application.getInstances().isEmpty()) {
            /*UserMovieRating userMovieRating = new UserMovieRating();
            userMovieRating.setApiStatusCode(HttpStatus.SERVICE_UNAVAILABLE.value());
            userMovieRating.setExceptionMessage("UNABLE TO FIND SERVICE!");
            return userMovieRating;*/
            throw new ServiceUnavailableException("SERVICE NOT AVAILABLE!");
        }
    }

}