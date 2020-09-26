package in.charan.ratingsData.repo;

import in.charan.ratingsData.model.UserMovieRating;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class UserRatedMoviesRepo {

    private Random random;

    public UserRatedMoviesRepo(Random random) {
        this.random = random;
    }

    public UserMovieRating getUserRatedMovies(String userId) throws Exception {
        UserMovieRating userMovieRating = new UserMovieRating();
        userMovieRating.setUserId(userId);
        userMovieRating.prepareUserRatedMovieList(random);
        return userMovieRating;
    }
}
