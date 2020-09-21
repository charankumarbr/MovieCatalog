package in.charan.movieCatalog.model.request.ratingsdataservice;

import java.util.ArrayList;

public class UserMovieRating {

    private String userId;

    private ArrayList<Rating> moviesRated;

    private int maxRating = 10;

    public UserMovieRating(String userId, ArrayList<Rating> moviesRated) {
        this.userId = userId;
        this.moviesRated = moviesRated;
    }

    public UserMovieRating() {
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

    public int getMaxRating() {
        return maxRating;
    }

}
