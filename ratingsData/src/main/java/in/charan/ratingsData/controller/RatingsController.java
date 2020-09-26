package in.charan.ratingsData.controller;

import in.charan.ratingsData.service.MovieRatingService;
import in.charan.ratingsData.service.UserRatedMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingsController {

    @Autowired
    private UserRatedMoviesService userRatedMoviesService;

    @Autowired
    private MovieRatingService movieRatingService;

    @RequestMapping(value = {"", "/"})
    public String getHomeRating() {
        return "Welcome to Movie Ratings System";
    }

    @RequestMapping(value = "/movies/{movieId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getMovieRating(@PathVariable("movieId") String movieId) {
        return movieRatingService.getMovieRating(movieId);
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserMovieRating(@PathVariable("userId") String userId) {
        return userRatedMoviesService.getUserRatedMovies(userId);
    }
}
