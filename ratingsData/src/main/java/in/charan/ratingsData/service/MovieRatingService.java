package in.charan.ratingsData.service;

import in.charan.ratingsData.log.LoggerUtils;
import in.charan.ratingsData.model.CustomException;
import in.charan.ratingsData.model.MovieRating;
import in.charan.ratingsData.model.Rating;
import in.charan.ratingsData.model.UserMovieRating;
import in.charan.ratingsData.util.RatingsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MovieRatingService {

    @Autowired
    private Random random;

    @Autowired
    private LoggerUtils loggerUtils;

    public ResponseEntity<Object> getMovieRating(String movieId) {
        try {
            Rating rating = new Rating(movieId, RatingsUtil.getMovieRating(random));
            MovieRating movieRating = new MovieRating(rating);
            return new ResponseEntity<>(movieRating, HttpStatus.OK);

        } catch (Exception e) {
            loggerUtils.logException(e);
            return new ResponseEntity<>(new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
