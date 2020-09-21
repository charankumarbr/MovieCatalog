package in.charan.movieCatalog.model.response;

import java.util.ArrayList;

public class MovieCatalog {

    private String userId;

    private ArrayList<MovieRating> moviesRated;

    private int maxRating;

    public MovieCatalog() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<MovieRating> getMoviesRated() {
        return moviesRated;
    }

    public void setMoviesRated(ArrayList<MovieRating> moviesRated) {
        this.moviesRated = moviesRated;
    }

    public int getMaxRating() {
        return maxRating;
    }

    public void setMaxRating(int maxRating) {
        this.maxRating = maxRating;
    }
}
