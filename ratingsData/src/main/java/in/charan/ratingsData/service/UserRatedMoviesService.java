package in.charan.ratingsData.service;

import in.charan.ratingsData.log.LoggerUtils;
import in.charan.ratingsData.model.CustomException;
import in.charan.ratingsData.model.UserMovieRating;
import in.charan.ratingsData.repo.UserRatedMoviesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserRatedMoviesService {

    @Autowired
    private UserRatedMoviesRepo userRatedMoviesRepo;

    @Autowired
    private LoggerUtils loggerUtils;

    public ResponseEntity<Object> getUserRatedMovies(String userId) {

        try {
            UserMovieRating userMovieRating = userRatedMoviesRepo.getUserRatedMovies(userId);
            return new ResponseEntity<>(userMovieRating, HttpStatus.OK);

        } catch (Exception e) {
            loggerUtils.logException(e);
            return new ResponseEntity<>(new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
