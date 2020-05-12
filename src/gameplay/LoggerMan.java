package gameplay;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * class with static functions for general logging
 */
public class LoggerMan {
    private static Logger logger;

    private LoggerMan(){}

    public static void initialize(){
        logger = Logger.getLogger("GameLogger");

        try {
            FileHandler fileHandler = new FileHandler(logger.getName());
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);

            logger.setUseParentHandlers(false);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(Level level, String msg){
        try{
            logger.log(level, msg);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


}
