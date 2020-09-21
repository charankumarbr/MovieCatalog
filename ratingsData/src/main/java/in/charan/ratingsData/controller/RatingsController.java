package in.charan.ratingsData.controller;

import in.charan.ratingsData.model.MovieRating;
import in.charan.ratingsData.model.Rating;
import in.charan.ratingsData.model.UserMovieRating;
import in.charan.ratingsData.util.RatingsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/ratings")
public class RatingsController {

    @Autowired
    private Random random;

    @RequestMapping(value = {"", "/"})
    public String getHomeRating() {
        return "Welcome to Movie Ratings System";
    }

    /*@RequestMapping(value = "/movies", method = RequestMethod.GET)
    public MovieRating getMovieRating() {
        return new MovieRating("-1", random.nextInt(11));
    }*/

    @RequestMapping(value = "/movies/{movieId}", method = RequestMethod.GET)
    public MovieRating getMovieRating(@PathVariable("movieId") String movieId) {
        return new MovieRating(new Rating(movieId, RatingsUtil.getMovieRating(random)));
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public UserMovieRating getUserMovieRating(@PathVariable("userId") String userId) {
        UserMovieRating userMovieRating = new UserMovieRating();
        userMovieRating.setUserId(userId);
        userMovieRating.prepareUserRatedMovieList(random);
        return userMovieRating;
    }
}
