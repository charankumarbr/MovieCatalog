package in.charan.ratingsData.log;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class LoggerUtils {

    public void logException(Exception exception) {
        //TODO: move to logger file.
        exception.printStackTrace();
        System.out.println("Exception thrown:" + exception);
    }

}
