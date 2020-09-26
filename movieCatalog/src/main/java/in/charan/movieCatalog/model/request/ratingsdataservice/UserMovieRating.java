package in.charan.movieCatalog.model.request.ratingsdataservice;

import in.charan.movieCatalog.model.response.CustomException;

import java.util.ArrayList;

public class UserMovieRating extends CustomException {

    private String userId;

    private ArrayList<UserMovie> moviesRated;

    private int maxRating = 10;

    public UserMovieRating(String userId, ArrayList<UserMovie> moviesRated) {
        super();
        this.userId = userId;
        this.moviesRated = moviesRated;
    }

    public UserMovieRating() {
        super();
    }

    public ArrayList<UserMovie> getMoviesRated() {
        return moviesRated;
    }

    public void setMoviesRated(ArrayList<UserMovie> moviesRated) {
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
