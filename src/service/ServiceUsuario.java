/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.implemetacion.DAOUsuarioImpl;
import dto.DTOUsuario;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.List;
import javax.swing.JTable;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * La clase {@code ServiceUsuario} proporciona servicios para la gestión de
 * usuarios, incluyendo operaciones de autenticación, registro, edición y
 * exportación de datos.
 *
 * @author Ralfph
 */
public class ServiceUsuario {

    private DAOUsuarioImpl daoUsuario;

    /**
     * Constructor que inicializa el servicio de usuario, estableciendo la
     * conexión con la base de datos a través de {@code DAOUsuarioImpl}.
     *
     * @throws SQLException Si ocurre un error al establecer la conexión con la
     * base de datos.
     */
    public ServiceUsuario() throws SQLException {
        daoUsuario = new DAOUsuarioImpl();
    }

    /**
     * Verifica el inicio de sesión de un usuario basado en su nombre de
     * usuario, contraseña y tipo de usuario.
     *
     * @param usuario El nombre de usuario.
     * @param contrasena La contraseña del usuario.
     * @param tipoUsuario El tipo de usuario (ej. 1=administrador, 2=empleado).
     * @return {@code true} si las credenciales son válidas, {@code false} en
     * caso contrario.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public boolean login(String usuario, String contrasena, int tipoUsuario) throws SQLException {
        return daoUsuario.login(usuario, contrasena, tipoUsuario);
    }

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param usuario El objeto {@code DTOUsuario} que contiene la información
     * del usuario a registrar.
     * @return {@code true} si el registro fue exitoso, {@code false} en caso
     * contrario.
     * @throws SQLException Si ocurre un error durante la inserción de datos en
     * la base de datos.
     */
    public boolean registrarUsuario(DTOUsuario usuario) throws SQLException {
        return daoUsuario.registrarUsuario(usuario);
    }

    public List<DTOUsuario> buscarUsuarioPorCriterios(String nombre, String apellido, String documento, String correo) throws SQLException {
        return daoUsuario.buscarUsuarioPorCriterios(nombre, apellido, documento, correo);
    }

    /**
     * Elimina un usuario de la base de datos según su documento de
     * identificación.
     *
     * @param documento El documento de identificación del usuario a eliminar.
     * @return {@code true} si el usuario fue eliminado correctamente,
     * {@code false} en caso contrario.
     * @throws SQLException Si ocurre un error al eliminar el usuario en la base
     * de datos.
     */
    public boolean eliminarUsuarioPorDocumento(String documento) throws SQLException {
        return daoUsuario.eliminarUsuarioPorDocumento(documento);
    }

    /**
     * Edita la información de un usuario existente.
     *
     * @param usuario El objeto {@code DTOUsuario} que contiene la información
     * actualizada del usuario.
     * @return {@code true} si la actualización fue exitosa, {@code false} en
     * caso contrario.
     * @throws SQLException Si ocurre un error al actualizar los datos en la
     * base de datos.
     */
    public boolean editarUsuario(DTOUsuario usuario) throws SQLException {
        return daoUsuario.editarUsuario(usuario);
    }

    /**
     * Obtiene la lista de todos los usuarios almacenados en la base de datos.
     *
     * @return Una lista de objetos {@code DTOUsuario} con la información de
     * todos los usuarios.
     * @throws SQLException Si ocurre un error al consultar la base de datos.
     */
    public List<DTOUsuario> obtenerUsuarios() throws SQLException {
        return daoUsuario.obtenerUsuarios();
    }

    /**
     * Exporta los datos de una tabla a un archivo de Excel.
     *
     * @param archivo El archivo de Excel donde se exportarán los datos.
     * @param table La tabla {@code JTable} cuyos datos se exportarán.
     * @return Un mensaje que indica si la exportación fue exitosa o fallida.
     */
    public String exportarExcel(File archivo, JTable table) {
        Workbook wb;
        String respuesta;
        respuesta = "No se puede exportar";
        int numfilas = table.getRowCount();
        int numcolumns = table.getColumnCount();
        if (archivo.getName().endsWith("xls")) {
            wb = new HSSFWorkbook();
        } else {
            wb = new XSSFWorkbook();
        }
        Sheet hoja = wb.createSheet("Usuario");
        try {
            for (int i = -1; i < numfilas; i++) {
                //aqui se crea la fila en el excel
                Row filaEx = hoja.createRow(i + 1);
                for (int j = 0; j < numcolumns; j++) {
                    //aqui se pinta la celda o escribe
                    Cell cellCont = filaEx.createCell(j);
                    if (i == -1) {
                        cellCont.setCellValue(String.valueOf(table.getColumnName(j)));

                    } else {
                        cellCont.setCellValue(String.valueOf(table.getValueAt(i, j)));
                    }
                    wb.write(new FileOutputStream(archivo));
                }
            }
            respuesta = "exportacion exitosa";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

}
