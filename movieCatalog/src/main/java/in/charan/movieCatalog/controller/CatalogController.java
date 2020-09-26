package in.charan.movieCatalog.controller;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import in.charan.movieCatalog.model.response.MovieRating;
import in.charan.movieCatalog.service.MovieCatalogService;
import in.charan.movieCatalog.service.MovieInfoService;
import in.charan.movieCatalog.service.RatingsDataService;
import in.charan.movieCatalog.model.request.ratingsdataservice.UserMovieRating;
import in.charan.movieCatalog.model.response.MovieCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private MovieCatalogService movieCatalogService;

    @Autowired
    public EurekaClient eurekaClient;

    @RequestMapping("/{userId}")
    public ResponseEntity<Object> getMovieCatalog(@PathVariable("userId") String userId) {
        //UserMovieRating userRatedMovies = restTemplate.getForObject("http://localhost:9003/ratings/users/" + userId, UserMovieRating.class);
        //UserMovieRating userRatedMovies = restTemplate.getForObject("http://ratingsdataservice/ratings/users/" + userId, UserMovieRating.class);
        /*UserMovieRating userRatedMovies = builder
                .baseUrl("http://ratingsdataservice/ratings")
                .build()
                .get()
                .uri("/users/" + userId)
                .retrieve()
                .bodyToMono(UserMovieRating.class)
                .block();*/

        return movieCatalogService.getMovieCatalogForUser(userId);
    }
}