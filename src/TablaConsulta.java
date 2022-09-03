import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class TablaConsulta extends JDialog {

    JTable tbl;
    DefaultTableModel model;
    ResultSet rs;
    String [] campos;

    public TablaConsulta(ResultSet rs, String[] c)
    {
        if (rs == null)
        {
            JOptionPane.showMessageDialog(null, "Hubo un error al consultar", "Error de consulta", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }
        this.rs = rs;
        campos = c;
        hasInterfaz();
    }

    private void hasInterfaz()
    {
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);

        tbl = new JTable();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(campos);

        tbl.setModel(model);

        Vector<Object> fila;

        while(true)
        {
            try {
                if (!rs.next()) break;

                fila = new Vector<>();

                fila.addElement(rs.getInt("supplierID"));
                fila.addElement(rs.getString("companyname"));
                fila.addElement(rs.getString("contactname"));
                fila.addElement(rs.getString("contacttitle"));
                fila.addElement(rs.getString("address"));
                fila.addElement(rs.getString("city"));
                fila.addElement(rs.getString("region"));
                fila.addElement(rs.getString("postalcode"));
                fila.addElement(rs.getString("country"));
                fila.addElement(rs.getString("phone"));
                fila.addElement(rs.getString("fax"));
                fila.addElement(rs.getString("homepage"));

                model.addRow(fila);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Hubo un error al consultar", "Error de consulta", JOptionPane.ERROR_MESSAGE);
            }
        }

        JScrollPane sp = new JScrollPane(tbl);
        add(sp, BorderLayout.CENTER);

        setVisible(true);
    }
}
