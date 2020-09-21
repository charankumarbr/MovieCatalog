package in.charan.movieCatalog.controller;

import in.charan.movieCatalog.client.MovieInfoService;
import in.charan.movieCatalog.client.RatingsDataService;
import in.charan.movieCatalog.model.response.MovieCatalog;
import in.charan.movieCatalog.model.response.MovieRating;
import in.charan.movieCatalog.model.request.ratingsdataservice.UserMovieRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @RequestMapping("/{userId}")
    public MovieCatalog getMovieCatalog(@PathVariable("userId") String userId) {
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

        UserMovieRating userRatedMovies = new RatingsDataService().getUserRatedMovies(userId);

        MovieCatalog movieCatalog = new MovieCatalog();
        movieCatalog.setUserId(userId);
        movieCatalog.setMaxRating(userRatedMovies.getMaxRating());

        ArrayList<MovieRating> moviesRated = new MovieInfoService().getMovieInfo(userRatedMovies.getMoviesRated());
        movieCatalog.setMoviesRated(moviesRated);

        return movieCatalog;
    }
}