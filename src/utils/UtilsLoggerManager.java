package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Esta clase implementa una forma resumida de obtener un registrador de tipo Logger de forma sencilla.
 * Utiliza el método de LoggerFactory, getLogger.
 * 
 * @author Emmanuel
 */

public class UtilsLoggerManager {
    public static Logger getLogger(Class<?> clase) {
        return LoggerFactory.getLogger(clase);
    }
}
