package in.charan.movieInfo.repo;

import in.charan.movieInfo.model.MovieInfo;

public class MovieInfoRepo {

    public MovieInfo getMovieInfo(String movieId) {
        return new MovieInfo(movieId, "MovieInfo Title goes here", "MovieInfo desc goes here",
                "August 2016");
    }

}
