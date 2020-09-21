package in.charan.movieCatalog.model.response;

public class MovieRating {

    private String movieId;

    private String title;

    private String desc;

    private String releaseDate;

    private int userRating;

    public MovieRating() {
    }

    public MovieRating(String movieId, String title, String desc, String releaseDate, int userRating) {
        this.movieId = movieId;
        this.title = title;
        this.desc = desc;
        this.releaseDate = releaseDate;
        this.userRating = userRating;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getUserRating() {
        return userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }
}
