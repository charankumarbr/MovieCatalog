package in.charan.movieInfo.service.movieinfo;

import in.charan.movieInfo.model.CustomException;
import in.charan.movieInfo.model.MovieInfo;
import in.charan.movieInfo.repo.MovieInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MovieInfoServiceImpl implements MovieInfoService {

    @Autowired
    private MovieInfoRepo movieInfoRepo;

    @Override
    public ResponseEntity<Object> getMovieInfo(String movieId) {
        try {
            MovieInfo movieInfo = movieInfoRepo.getMovieInfo(movieId);
            movieInfo.setApiStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(movieInfo, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
