package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilLoggerManager {
    public static Logger getLogger(Class<?> clase) {
        return LoggerFactory.getLogger(clase);
    }
}
