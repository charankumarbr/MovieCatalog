package in.charan.movieCatalog.model.request.ratingsdataservice;

public class UserMovie {

    private String movieId;

    private int rating;

    public UserMovie(String movieId, int rating) {
        this.movieId = movieId;
        this.rating = rating;
    }

    public UserMovie() {

    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
