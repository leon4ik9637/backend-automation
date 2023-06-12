package log4jAppender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jAppender {
    static Logger logger = LogManager.getLogger(Log4jAppender.class);

    public static void main(String[] args) {

        logger.info("This is my information message");
        logger.error("This is my error message");
        logger.debug("This is my debugging message");
        logger.warn("This is my warning message");
        logger.fatal("This is my fatal message");
        logger.trace("This is my tracing message");
    }
}
