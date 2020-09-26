package in.charan.movieInfo.model;

public class MovieInfo {

    private String movieId;

    private String title;

    private String description;

    private String releaseDate;

    public MovieInfo() {
    }

    public MovieInfo(String movieId, String title, String description, String releaseDate) {
        this.movieId = movieId;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

}
