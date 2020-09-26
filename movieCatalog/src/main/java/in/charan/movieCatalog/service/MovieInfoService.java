package in.charan.movieCatalog.service;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.EurekaClientNames;
import com.netflix.discovery.shared.Application;
import in.charan.movieCatalog.model.request.movieinfoservice.MovieInfo;
import in.charan.movieCatalog.model.request.ratingsdataservice.UserMovieRating;
import in.charan.movieCatalog.model.response.MovieRating;
import in.charan.movieCatalog.model.request.ratingsdataservice.UserMovie;
import in.charan.movieCatalog.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerErrorException;

import javax.naming.ServiceUnavailableException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MovieInfoService {

    @Autowired
    private EurekaClient eurekaClient;

    public List<MovieRating> getMovieInfo(ArrayList<UserMovie> userRatedMovies) throws Exception {

        checkServiceAvailability();

        List<MovieRating> movieInfoWithRating = new ArrayList<>();

        for (UserMovie eachRatedMovie : userRatedMovies) {
            MovieInfo movieInfo = getMovieInfo(eachRatedMovie);
            if (movieInfo != null) {
                MovieRating movieRating = new MovieRating(eachRatedMovie.getMovieId(), movieInfo.getTitle(),
                        movieInfo.getDescription(), movieInfo.getReleaseDate(), eachRatedMovie.getRating());
                movieInfoWithRating.add(movieRating);
            }
        }

        return movieInfoWithRating;
    }

    private MovieInfo getMovieInfo(UserMovie eachRatedMovie) throws Exception {

        MovieInfo movieInfo;
        int isZero = new Random().nextInt(100) % 2;

        checkServiceAvailability();

        if (isZero == 0) {
            RestTemplate restTemplate = BeanUtil.getBeanUtil(RestTemplate.class);
            movieInfo = restTemplate.getForObject("http://movieinfoservice/movieInfo/" + eachRatedMovie.getMovieId(), MovieInfo.class);

        } else {
            WebClient.Builder builder = BeanUtil.getBeanUtil(WebClient.Builder.class);
            movieInfo = builder
                    .baseUrl("http://movieinfoservice")
                    .build()
                    .get()
                    .uri("/movieInfo/" + eachRatedMovie.getMovieId())
                    .retrieve()
                    .bodyToMono(MovieInfo.class)
                    .block();
        }

        return movieInfo;
    }

    private void checkServiceAvailability() throws Exception {
        Application application = eurekaClient.getApplication("movieinfoservice");
        if ((application == null) || application.getInstances() == null || application.getInstances().isEmpty()) {
            throw new ServiceUnavailableException("SERVICE NOT AVAILABLE!");
        }
    }

}