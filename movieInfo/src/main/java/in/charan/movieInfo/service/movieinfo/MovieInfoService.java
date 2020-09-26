package in.charan.movieInfo.service.movieinfo;

import in.charan.movieInfo.model.MovieInfo;
import org.springframework.http.ResponseEntity;

public interface MovieInfoService {

    ResponseEntity<Object> getMovieInfo(String movieId);
}
