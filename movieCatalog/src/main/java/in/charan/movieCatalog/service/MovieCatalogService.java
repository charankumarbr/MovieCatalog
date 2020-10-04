package in.charan.movieCatalog.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import in.charan.movieCatalog.log.LoggerUtils;
import in.charan.movieCatalog.model.request.ratingsdataservice.UserMovie;
import in.charan.movieCatalog.model.request.ratingsdataservice.UserMovieRating;
import in.charan.movieCatalog.model.response.MovieCatalog;
import in.charan.movieCatalog.model.response.MovieRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieCatalogService {

    @Autowired
    private MovieInfoService movieInfoService;

    @Autowired
    private RatingsDataService ratingsDataService;

    @Autowired
    private LoggerUtils loggerUtils;

    public ResponseEntity<Object> getMovieCatalogForUser(String userId) {

        UserMovieRating userRatedMovies = getUserRatedMovies(userId);

        MovieCatalog movieCatalog;
        if (userRatedMovies.getApiStatusCode() == HttpStatus.OK.value() && userRatedMovies.getMoviesRated() != null
                && !userRatedMovies.getMoviesRated().isEmpty()) {
            movieCatalog = getMovieInfo(userRatedMovies);

        } else {
            movieCatalog = new MovieCatalog();
            movieCatalog.setExceptionMessage(userRatedMovies.getExceptionMessage());
            movieCatalog.setApiStatusCode(userRatedMovies.getApiStatusCode());
        }
        return new ResponseEntity<>(movieCatalog, HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "getFallbackUserRatedMovies")
    private UserMovieRating getUserRatedMovies(String userId) {
        UserMovieRating userMovieRating;
        try {
            userMovieRating = ratingsDataService.getUserRatedMovies(userId);

            if (userMovieRating == null) {
                userMovieRating = getFallbackUserRatedMovies();

            } else if (userMovieRating.getApiStatusCode() != 0 && userMovieRating.getApiStatusCode() != HttpStatus.OK.value()) {
                userMovieRating.setExceptionMessage(userMovieRating.getExceptionMessage());
                userMovieRating.setApiStatusCode(userMovieRating.getApiStatusCode());
            }

        } catch (Exception e) {
            loggerUtils.logException(e);
            return getFallbackUserRatedMovies();
        }

        return userMovieRating;
    }

    private UserMovieRating getFallbackUserRatedMovies() {
        UserMovieRating userMovieRating = new UserMovieRating();
        userMovieRating.setExceptionMessage("SERVICE NOT AVAILABLE - fallback");
        userMovieRating.setApiStatusCode(503);
        return userMovieRating;
    }

    @HystrixCommand(fallbackMethod = "getFallbackMovieInfo")
    private MovieCatalog getMovieInfo(UserMovieRating userMovieRating) {
        ArrayList<UserMovie> moviesRated = userMovieRating.getMoviesRated();
        MovieCatalog movieCatalog = new MovieCatalog();

        if (moviesRated != null && !moviesRated.isEmpty()) {
            try {
                List<MovieRating> movieInfoRated = movieInfoService.getMovieInfo(moviesRated);
                if (moviesRated != null && !moviesRated.isEmpty()) {
                    movieCatalog.setMoviesRated(movieInfoRated);
                }
            } catch (Exception e) {
                loggerUtils.logException(e);
                return getFallbackMovieInfo();
            }
        }

        movieCatalog.setUserId(userMovieRating.getUserId());
        movieCatalog.setMaxRating(userMovieRating.getMaxRating());
        movieCatalog.setApiStatusCode(userMovieRating.getApiStatusCode());
        return movieCatalog;
    }

    private MovieCatalog getFallbackMovieInfo() {
        MovieCatalog movieCatalog = new MovieCatalog();
        movieCatalog.setExceptionMessage("SERVICE NOT AVAILABLE - fallback");
        movieCatalog.setApiStatusCode(503);
        return movieCatalog;
    }
}
