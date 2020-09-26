package in.charan.movieCatalog.service;

import in.charan.movieCatalog.model.request.movieinfoservice.MovieInfo;
import in.charan.movieCatalog.model.response.MovieRating;
import in.charan.movieCatalog.model.request.ratingsdataservice.Rating;
import in.charan.movieCatalog.util.BeanUtil;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovieInfoService {

    public List<MovieRating> getMovieInfo(ArrayList<Rating> userRatedMovies) {
        int isZero = new Random().nextInt(100) % 2;

        List<MovieRating> movieInfoWithRating = new ArrayList<>();
        if (isZero == 0) {
            RestTemplate restTemplate = BeanUtil.getBeanUtil(RestTemplate.class);
            for (Rating eachRatedMovie : userRatedMovies) {
                MovieInfo movieInfo = restTemplate.getForObject("http://movieinfoservice/movieInfo/" + eachRatedMovie.getMovieId(), MovieInfo.class);
                MovieRating movieRating = new MovieRating(eachRatedMovie.getMovieId(), movieInfo.getTitle(),
                        movieInfo.getDescription(), movieInfo.getReleaseDate(), eachRatedMovie.getRating());
                movieInfoWithRating.add(movieRating);
            }

        } else {
            WebClient.Builder builder = BeanUtil.getBeanUtil(WebClient.Builder.class);
            for (Rating eachRatedMovie : userRatedMovies) {
                MovieInfo movieInfo = builder
                        .baseUrl("http://movieinfoservice")
                        .build()
                        .get()
                        .uri("/movieInfo/" + eachRatedMovie.getMovieId())
                        .retrieve()
                        .bodyToMono(MovieInfo.class)
                        .block();
                MovieRating movieRating = new MovieRating(eachRatedMovie.getMovieId(), movieInfo.getTitle(),
                        movieInfo.getDescription(), movieInfo.getReleaseDate(), eachRatedMovie.getRating());
                movieInfoWithRating.add(movieRating);
            }
        }

        return movieInfoWithRating;
    }

}