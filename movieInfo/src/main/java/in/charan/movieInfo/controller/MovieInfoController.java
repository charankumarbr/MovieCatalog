package in.charan.movieInfo.controller;

import in.charan.movieInfo.model.MovieInfo;
import in.charan.movieInfo.service.movieinfo.MovieInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieInfo")
public class MovieInfoController {

    @Autowired
    private MovieInfoService movieInfoService;

    @RequestMapping("/{movieId}")
    public ResponseEntity<Object> getMovieInfo(@PathVariable("movieId") String movieId) {
        return movieInfoService.getMovieInfo(movieId);
    }
}
