import config.Conexion;
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

    @Before
    public void setUp() {
        conexion = new Conexion();
    }
    
    @Test
    public void testGetConnection() {
        try {
            Connection con = conexion.getConnection();
            Assert.assertNotNull("La conexión no debería ser null", con);
        } catch (Exception e) {
            Assert.fail("La conexión a la base de datos falló: " + e.getMessage());
        }
    }
}