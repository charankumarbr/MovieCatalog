package in.charan.ratingsData.model;

public class MovieRating {

    private Rating movieRating;

    public MovieRating() {
    }

    public MovieRating(Rating movieRating) {
        this.movieRating = movieRating;
    }

    private int maxRating = 10;

    public int getMaxRating() {
        return maxRating;
    }

    public Rating getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(Rating movieRating) {
        this.movieRating = movieRating;
    }
}
