import config.Conexion;
import org.slf4j.Logger;
import utils.UtilsLoggerManager;
import java.sql.Connection;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Clase de prueba para la conexión a la base de datos.
 * Esta clase verifica que la conexión a la base de datos se establezca correctamente.
 * 
 * @author Emmanuel
 */
public class ConexionTest {
    private Conexion conexion;
    private final Logger LOGGER = UtilsLoggerManager.getLogger(ConexionTest.class);

    /**
     * Configuración inicial antes de cada prueba.
     * Se instancia la clase Conexion.
     */
    @Before
    public void setUp() {
        conexion = new Conexion();
    }
    
    /**
     * Test que verifica que se pueda obtener una conexión a la base de datos.
     * 
     * Se espera que la conexión no sea nula. 
     * Si ocurre un error al intentar conectarse, se registrará un mensaje de error
     * y la prueba fallará.
     */
    @Test
    public void testGetConnection() {
        try {
            Connection con = conexion.getConnection();
            assertNotNull("La conexión no debería ser null", con);
            LOGGER.info("Test de conexión exitoso.");
        } catch (Exception e) {
            LOGGER.error("Test fallido: Error al conectar con la base de datos.", e);
            fail("La conexión a la base de datos falló: " + e.getMessage());
        }
    }
}
