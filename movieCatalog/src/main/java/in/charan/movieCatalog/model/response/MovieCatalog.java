package in.charan.movieCatalog.model.response;

import java.util.List;

public class MovieCatalog extends CustomException {

    private String userId;

    private List<MovieRating> moviesRated;

    private int maxRating;

    public MovieCatalog() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<MovieRating> getMoviesRated() {
        return moviesRated;
    }

    public void setMoviesRated(List<MovieRating> moviesRated) {
        this.moviesRated = moviesRated;
    }

    public int getMaxRating() {
        return maxRating;
    }

    public void setMaxRating(int maxRating) {
        this.maxRating = maxRating;
    }
}
