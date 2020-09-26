package in.charan.movieCatalog.log;

import org.springframework.stereotype.Service;

@Service
public class LoggerUtils {

    public void logException(Exception exception) {
        //TODO: move to logger file.
        exception.printStackTrace();
        System.out.println("Exception thrown:" + exception);
    }

}
