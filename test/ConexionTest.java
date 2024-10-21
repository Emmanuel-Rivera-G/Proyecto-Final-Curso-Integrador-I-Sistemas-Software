import config.Conexion;
import org.slf4j.Logger;
import utils.UtilLoggerManager;
import java.sql.Connection;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author USER
 */
public class ConexionTest {
    private Conexion conexion;
    private final Logger LOGGER = UtilLoggerManager.getLogger(ConexionTest.class);

    @Before
    public void setUp() {
        conexion = new Conexion();
    }
    
    @Test
    public void testGetConnection() {
        try {
            Connection con = conexion.getConnection();
            Assert.assertNotNull("La conexión no debería ser null", con);
            LOGGER.info("Test de conexión exitoso.");
        } catch (Exception e) {
            LOGGER.error("Test fallido: Error al conectar con la base de datos.", e);
            Assert.fail("La conexión a la base de datos falló: " + e.getMessage());
        }
    }
}
