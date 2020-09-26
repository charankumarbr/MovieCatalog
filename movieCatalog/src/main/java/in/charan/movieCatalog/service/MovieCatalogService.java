package in.charan.movieCatalog.service;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import in.charan.movieCatalog.log.LoggerUtils;
import in.charan.movieCatalog.model.request.ratingsdataservice.UserMovieRating;
import in.charan.movieCatalog.model.response.CustomException;
import in.charan.movieCatalog.model.response.MovieCatalog;
import in.charan.movieCatalog.model.response.MovieRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

        try {
            UserMovieRating userRatedMovies = ratingsDataService.getUserRatedMovies(userId);

            if (userRatedMovies == null) {
                return new ResponseEntity<>(new CustomException("SERVICE NOT AVAILABLE", 503), HttpStatus.SERVICE_UNAVAILABLE);

            } else if (userRatedMovies.getApiStatusCode() != 0 && userRatedMovies.getApiStatusCode() != HttpStatus.OK.value()) {
                return new ResponseEntity<>(new CustomException(userRatedMovies.getExceptionMessage(), userRatedMovies.getApiStatusCode()),
                        HttpStatus.resolve(userRatedMovies.getApiStatusCode()));

            } else {
                MovieCatalog movieCatalog = new MovieCatalog();
                movieCatalog.setUserId(userId);
                movieCatalog.setMaxRating(userRatedMovies.getMaxRating());

                if (userRatedMovies.getMoviesRated() != null && !userRatedMovies.getMoviesRated().isEmpty()) {
                    List<MovieRating> moviesRated = movieInfoService.getMovieInfo(userRatedMovies.getMoviesRated());
                    if (moviesRated != null && !moviesRated.isEmpty()) {
                        movieCatalog.setMoviesRated(moviesRated);
                    }
                }

                movieCatalog.setApiStatusCode(userRatedMovies.getApiStatusCode());
                return new ResponseEntity<>(movieCatalog, HttpStatus.OK);
            }

        } catch (Exception e) {
            loggerUtils.logException(e);
            return new ResponseEntity<>(new CustomException("SOMETHING WENT WRONG!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
