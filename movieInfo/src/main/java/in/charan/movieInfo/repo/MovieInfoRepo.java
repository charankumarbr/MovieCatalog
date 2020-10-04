package in.charan.movieInfo.repo;

import com.netflix.discovery.converters.Auto;
import in.charan.movieInfo.model.MovieInfo;
import in.charan.movieInfo.model.MovieInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class MovieInfoRepo {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${theMovieDB.apiKey}")
    private String apiKey;

    public MovieInfo getMovieInfo(String movieId) throws Exception {
        String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey + "&language=en-US";
        MovieInformation movieInformation = restTemplate.getForObject(url, MovieInformation.class);

        if (movieInformation != null && movieInformation.isSuccess()) {
            return new MovieInfo(movieId, movieInformation.getTitle(), movieInformation.getOverview(),
                    movieInformation.getRelease_date());
        } else {
            return null;
        }
    }

}
