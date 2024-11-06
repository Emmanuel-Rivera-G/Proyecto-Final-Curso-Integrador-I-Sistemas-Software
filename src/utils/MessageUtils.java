package utils;

import javax.swing.JOptionPane;

/**
 *
 * @author Emmanuel
 */
public class MessageUtils {
    
    /**
    * Muestra un mensaje de error en un cuadro de diálogo con un título especificado.
    *
    * @param mensaje Texto del mensaje de error a mostrar.
    * @param title Título del cuadro de diálogo.
    */
    public static void mostrarMensajeError(String mensaje, String title) {
        JOptionPane.showMessageDialog(null, mensaje, title, JOptionPane.ERROR_MESSAGE);
    }
    
    /**
    * Muestra un mensaje de error en un cuadro de diálogo con el título predeterminado "ERROR".
    *
    * @param mensaje Texto del mensaje de error a mostrar.
    */
    public static void mostrarMensajeError(String mensaje) {
        mostrarMensajeError(mensaje, "ERROR");
    }
    
    /**
    * Muestra un mensaje de éxito en un cuadro de diálogo con un título especificado.
    *
    * @param mensaje Texto del mensaje de éxito a mostrar.
    * @param title Título del cuadro de diálogo.
    */
    public static void mostrarMensajeExitoso(String mensaje, String title) {
        JOptionPane.showMessageDialog(null, mensaje, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
    * Muestra un mensaje de éxito en un cuadro de diálogo con el título predeterminado "INFORMACION".
    *
    * @param mensaje Texto del mensaje de éxito a mostrar.
    */
    public static void mostrarMensajeExitoso(String mensaje) {
        mostrarMensajeExitoso(mensaje, "INFORMACION");
    }
}
