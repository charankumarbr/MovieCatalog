package in.charan.ratingsData.util;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class RatingsUtil {

    public static int getMovieCount(Random random) {
        return random.nextInt((6 - 1) + 1) + 1;
    }

    public static int getMovieRating(Random random) {
        return random.nextInt((10 - 1) + 1) + 1;
    }
}
