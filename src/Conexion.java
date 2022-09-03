import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;

import javax.swing.*;

public class Conexion {
    static Statement statement = null;
    static Connection con;
    String usuario, password, servidor, bd;
    JButton btnSig;

    public Conexion(String serv, String bd, String user, String pass, JButton sig)
    {
        usuario = user;
        password = pass;
        servidor = serv;
        this.bd = bd;
        btnSig = sig;
        abrirBD();
    }

    private void abrirBD()
    {
        String url = "jdbc:sqlserver://" + servidor + ":1433;databaseName=" + bd + ";user=" + usuario + ";password=" + password;
        //String url = "jdbc:sqlserver://localhost:1433;databasename=northwind;user=java;password=123";

        try {
            con = DriverManager.getConnection(url);
            statement = con.createStatement();
        } catch (SQLException e) {
            if (e.getErrorCode() == 4060)
            {
                JOptionPane.showMessageDialog(null, "El servidor rechazó la conexión con BD y Usuario especificados", "Error de conexión", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(null, "Se presentó un error en la conexión\nCódigo de error: " + e.getErrorCode(), "Error de BD", JOptionPane.ERROR_MESSAGE);
            btnSig.setVisible(false);
            return;
        }
        JOptionPane.showMessageDialog(null, "La conexión fue exitosa");
        btnSig.setVisible(true);
    }

    public void cerrarBD()
    {
        try {
            con.close();
            statement.close();
        }catch (SQLException e)
        {
            System.out.println("Hubo un error al cerrar la BD");
        }
    }

    public ResultSet select(String query)
    {
        try {
            return statement.executeQuery(query);
        }catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al recuperar datos\nCódigo de error: " + e.getErrorCode(), "Error en consulta", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void update(String query)
    {
        try {
            statement.executeQuery(query);
        } catch (SQLException e) {
            if (e.getErrorCode() != 0) {
                JOptionPane.showMessageDialog(null, "Hubo un error al actualizar los datos\nCódigo de error: " + e.getErrorCode(), "Error en update", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Datos actualizados con éxito.");
    }

    public void delete(String query)
    {
        try {
            statement.executeQuery(query);
        } catch (SQLException e) {
            if (e.getErrorCode() != 0) {
                if (e.getErrorCode() == 547)
                {
                    JOptionPane.showMessageDialog(null, "Imposible eliminar registro por regla de llave foránea", "Error de integridad", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(null, "Hubo un error al borrar el registro\nCódigo de error: " + e.getErrorCode(), "Error en delete", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Registro eliminado con éxito");
    }

    public int insert(String query)
    {
        String query3;
        int output = 0;

        query3 = "declare @supplierid int \n" + query + "\n select @supplierid";

        try {
            ResultSet rs = statement.executeQuery(query3);
            rs.next();
            output = rs.getInt(1);
        } catch (SQLException e) {
            if (e.getErrorCode() != 0) {
                JOptionPane.showMessageDialog(null, "Hubo un error al insertar el registro\nCódigo de error: " + e.getErrorCode(), "Error en insert", JOptionPane.ERROR_MESSAGE);
                return 0;
            }
        }
        JOptionPane.showMessageDialog(null, "Datos insertados con éxito.");
        return output;
    }
}
