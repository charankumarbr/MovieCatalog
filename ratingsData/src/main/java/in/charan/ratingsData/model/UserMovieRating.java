package in.charan.ratingsData.model;

import in.charan.ratingsData.util.RatingsUtil;

import java.util.ArrayList;
import java.util.Random;

public class UserMovieRating extends CustomException {

    private String userId;

    private ArrayList<Rating> moviesRated;

    private int maxRating = 10;

    public UserMovieRating(String userId, ArrayList<Rating> moviesRated) {
        super();
        this.userId = userId;
        this.moviesRated = moviesRated;
    }

    public UserMovieRating() {
        super();
    }

    public ArrayList<Rating> getMoviesRated() {
        return moviesRated;
    }

    public void setMoviesRated(ArrayList<Rating> moviesRated) {
        this.moviesRated = moviesRated;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void prepareUserRatedMovieList(Random random) throws Exception {
        ArrayList<Rating> moviesRated = new ArrayList<>();
        int moviesCount = RatingsUtil.getMovieCount(random);
        for (int index = 0; index < moviesCount; index++) {
            Rating movieRating = new Rating();
            movieRating.setMovieId(String.valueOf((index + 1) * 100));
            movieRating.setRating(RatingsUtil.getMovieRating(random));
            moviesRated.add(movieRating);
        }

        if (!moviesRated.isEmpty()) {
            this.moviesRated = moviesRated;
        } else {
            this.moviesRated = null;
        }
    }

    public int getMaxRating() {
        return maxRating;
    }
}
