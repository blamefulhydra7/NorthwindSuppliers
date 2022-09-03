import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PantallaSuppliers extends JFrame implements ActionListener, FocusListener {

    Conexion bd;
    ResultSet rs;
    JLabel lbTitle, lbSupplierID, lbCompName, lbContName, lbContTitle, lbAddress, lbCity, lbRegion, lbPostal, lbCountry, lbPhone,
            lbFax, lbHome;
    JTextField txtCompName, txtContName, txtContTitle, txtAddress, txtCity, txtRegion, txtPostal,
                txtCountry, txtPhone, txtFax, txtHome;
    JTextField[] txtCajas;
    LeeEntero txtID;
    JButton btnRecuperar, btnGrabar, btnBorrar, btnActualizar, btnConsultar, btnLimpiar;
    JRadioButton rbtnCrear, rbtnModificar;
    ButtonGroup rbtns;
    Color color;


    public PantallaSuppliers(Conexion c)
    {
        super("Proyecto de BD");
        hasInterfaz();
        hasEscuchas();
        bd = c;
    }

    private void hasInterfaz()
    {
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        lbTitle = new JLabel("Crud de Suppliers");
        lbTitle.setBounds(getWidth()/2 - 75, 10, 150, 15);
        add(lbTitle);

        lbSupplierID = new JLabel("ID");
        lbSupplierID.setBounds(10, 50, 50, 15);
        add(lbSupplierID);

        txtID = new LeeEntero(10);
        txtID.setBounds(200, 45, 250, 30);
        add(txtID);

        lbRegion = new JLabel("Region");
        lbRegion.setBounds(510, 50, 100, 15);
        add(lbRegion);

        txtRegion = new JTextField();
        txtRegion.setBounds(700, 45, 250, 30);
        add(txtRegion);

        lbCompName = new JLabel("Company Name");
        lbCompName.setBounds(10, 100, 100, 15);
        add(lbCompName);

        txtCompName = new JTextField();
        txtCompName.setBounds(200, 95, 250, 30);
        add(txtCompName);

        lbPostal = new JLabel("Postal Code");
        lbPostal.setBounds(510, 100, 100, 15);
        add(lbPostal);

        txtPostal = new JTextField();
        txtPostal.setBounds(700, 95, 250, 30);
        add(txtPostal);

        lbContName = new JLabel("Contact Name");
        lbContName.setBounds(10, 150, 100, 15);
        add(lbContName);

        txtContName = new JTextField();
        txtContName.setBounds(200, 145, 250, 30);
        add(txtContName);

        lbCountry = new JLabel("Country");
        lbCountry.setBounds(510, 150, 100, 15);
        add(lbCountry);

        txtCountry = new JTextField();
        txtCountry.setBounds(700, 145, 250, 30);
        add(txtCountry);

        lbContTitle = new JLabel("Contact Title");
        lbContTitle.setBounds(10, 200, 100, 15);
        add(lbContTitle);

        txtContTitle = new JTextField();
        txtContTitle.setBounds(200, 195, 250, 30);
        add(txtContTitle);

        lbPhone = new JLabel("Phone");
        lbPhone.setBounds(510, 200, 100, 15);
        add(lbPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(700, 195, 250, 30);
        add(txtPhone);

        lbAddress = new JLabel("Address");
        lbAddress.setBounds(10, 250, 100, 15);
        add(lbAddress);

        txtAddress = new JTextField();
        txtAddress.setBounds(200, 245, 250, 30);
        add(txtAddress);

        lbFax = new JLabel("Fax");
        lbFax.setBounds(510, 250, 100, 15);
        add(lbFax);

        txtFax = new JTextField();
        txtFax.setBounds(700, 245, 250, 30);
        add(txtFax);

        lbCity = new JLabel("City");
        lbCity.setBounds(10, 300, 100, 15);
        add(lbCity);

        txtCity = new JTextField();
        txtCity.setBounds(200, 295, 250, 30);
        add(txtCity);

        lbHome = new JLabel("Home Page");
        lbHome.setBounds(510, 300, 100, 15);
        add(lbHome);

        txtHome = new JTextField();
        txtHome.setBounds(700, 295, 250, 30);
        add(txtHome);

        btnRecuperar = new JButton("RECUPERAR");
        btnRecuperar.setBounds(225, 350, 250, 20);
        add(btnRecuperar);

        btnGrabar = new JButton("GRABAR");
        btnGrabar.setBounds(500, 350, 250, 20);
        add(btnGrabar);

        btnBorrar = new JButton("BORRAR");
        btnBorrar.setBounds(225, 375, 250, 20);
        add(btnBorrar);

        btnActualizar = new JButton("ACTUALIZAR");
        btnActualizar.setBounds(500, 375, 250, 20);
        add(btnActualizar);

        btnConsultar = new JButton("CONSULTAR");
        btnConsultar.setBounds(225, 400, 250, 20);
        add(btnConsultar);

        btnLimpiar = new JButton("LIMPIAR");
        btnLimpiar.setBounds(500, 400, 250, 20);
        add(btnLimpiar);

        txtCajas = new JTextField[12];
        txtCajas[0] = txtID;
        txtCajas[1] = txtCompName;
        txtCajas[2] = txtContName;
        txtCajas[3] = txtContTitle;
        txtCajas[4] = txtAddress;
        txtCajas[5] = txtCity;
        txtCajas[6] = txtRegion;
        txtCajas[7] = txtPostal;
        txtCajas[8] = txtCountry;
        txtCajas[9] = txtPhone;
        txtCajas[10] = txtFax;
        txtCajas[11] = txtHome;

        rbtnCrear = new JRadioButton("Insertar");
        rbtnCrear.setBounds(700, 10, 100, 25);
        rbtnCrear.setSelected(true);
        add(rbtnCrear);

        rbtnModificar = new JRadioButton("Modificar");
        rbtnModificar.setBounds(810, 10, 100, 25);
        add(rbtnModificar);

        rbtns = new ButtonGroup();
        rbtns.add(rbtnCrear);
        rbtns.add(rbtnModificar);

        txtID.setEnabled(false);
        btnRecuperar.setEnabled(false);
        btnBorrar.setEnabled(false);
        btnActualizar.setEnabled(false);

        color = txtID.getBackground();

        setVisible(true);
    }

    private void hasEscuchas()
    {
        for (int i = 0; i < txtCajas.length; i++)
        {
            txtCajas[i].addActionListener(this);
            txtCajas[i].addFocusListener(this);
        }

        btnRecuperar.addActionListener(this);
        btnGrabar.addActionListener(this);
        btnBorrar.addActionListener(this);
        btnActualizar.addActionListener(this);
        btnConsultar.addActionListener(this);
        btnLimpiar.addActionListener(this);

        rbtnCrear.addActionListener(this);
        rbtnModificar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JRadioButton)
        {
            if (e.getSource() == rbtnCrear)
            {
                txtID.setEnabled(false);
                btnGrabar.setEnabled(true);
                btnRecuperar.setEnabled(false);
                btnBorrar.setEnabled(false);
                btnActualizar.setEnabled(false);

                for (int i = 0; i < txtCajas.length; i++)
                {
                    txtCajas[i].setText("");
                    txtCajas[i].setBackground(color);
                }

                return;
            }

            if (e.getSource() == rbtnModificar)
            {
                txtID.setEnabled(true);
                btnRecuperar.setEnabled(true);
                btnBorrar.setEnabled(true);
                btnActualizar.setEnabled(true);
                btnGrabar.setEnabled(false);

                for (int i = 0; i < txtCajas.length; i++)
                {
                    txtCajas[i].setText("");
                    txtCajas[i].setBackground(color);
                }

                return;
            }
        }

        if (e.getSource() instanceof JTextField)
        {
            if (e.getSource() == txtID)
            {
                txtCompName.requestFocus();
                return;
            }

            if (e.getSource() == txtCompName)
            {
                txtContName.requestFocus();
                return;
            }

            if (e.getSource() == txtContName)
            {
                txtContTitle.requestFocus();
                return;
            }

            if (e.getSource() == txtContTitle)
            {
                txtAddress.requestFocus();
                return;
            }

            if (e.getSource() == txtAddress)
            {
                txtCity.requestFocus();
                return;
            }

            if (e.getSource() == txtCity)
            {
                txtRegion.requestFocus();
                return;
            }

            if (e.getSource() == txtRegion)
            {
                txtPostal.requestFocus();
                return;
            }

            if (e.getSource() == txtPostal)
            {
                txtCountry.requestFocus();
                return;
            }

            if (e.getSource() == txtCountry)
            {
                txtPhone.requestFocus();
                return;
            }

            if (e.getSource() == txtPhone)
            {
                txtFax.requestFocus();
                return;
            }

            if (e.getSource() == txtFax)
            {
                txtHome.requestFocus();
                return;
            }
        }

        if (e.getSource() instanceof JButton)
        {
            if (e.getSource() == btnRecuperar)
            {
                if (txtID.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Escriba en el campo ID", "Error de usuario", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String query = "Select * from suppliers where supplierid = " + txtID.getText();
                rs = bd.select(query);

                if (rs == null)
                    return;

                try {
                    rs.next();
                    txtCompName.setText(rs.getString("companyname"));
                    txtContName.setText(rs.getString("contactname"));
                    txtContTitle.setText(rs.getString("contacttitle"));
                    txtAddress.setText(rs.getString("address"));
                    txtCity.setText(rs.getString("city"));
                    txtRegion.setText(rs.getString("region"));
                    txtPostal.setText(rs.getString("postalcode"));
                    txtCountry.setText(rs.getString("country"));
                    txtPhone.setText(rs.getString("phone"));
                    txtFax.setText(rs.getString("fax"));
                    txtHome.setText(rs.getString("homepage"));
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Hubo un error al recuperar la información", "Error de consulta", JOptionPane.ERROR_MESSAGE);
                }
                return;
            }

            if (e.getSource() == btnGrabar)
            {
                boolean vacias = false;

                for (int i = 1; i < txtCajas.length; i++)
                {
                    if (txtCajas[i].getText().equals(""))
                    {
                        txtCajas[i].setBackground(Color.yellow);
                        vacias = true;
                    }
                    else
                        txtCajas[i].setBackground(color);
                }

                if (vacias)
                {
                    JOptionPane.showMessageDialog(null, "Llene los datos faltantes.", "Error de usuario", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String query = "exec sp_supplierjava @supplierid output, '" + txtCompName.getText() + "', '" + txtContName.getText() + "', '" +
                                txtContTitle.getText() + "', '" + txtAddress.getText() + "', '" + txtCity.getText() + "', '" + txtRegion.getText() + "', '" +
                                txtPostal.getText() + "', '" + txtCountry.getText() + "', '" + txtPhone.getText() + "', '" + txtFax.getText() + "', '" +
                                txtHome.getText() + "'";
                int output = bd.insert(query);

                if (output == 0)
                    return;

                txtID.setText(output + "");
                JOptionPane.showMessageDialog(null, "ID del nuevo supplier: " + output);

                return;
            }

            if (e.getSource() == btnBorrar)
            {
                if (txtID.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Por favor, introduzca un ID válido", "Error de usuario", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String query = "exec sp_supplierjavadelete " + txtID.getText();
                bd.delete(query);

                return;
            }

            if (e.getSource() == btnActualizar)
            {
                if (txtID.getText().equals("") || txtCompName.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Escriba un ID y Company Name válido.", "Error de usuario", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String query = "exec sp_supplierjava " + txtID.getText() + ", '" + txtCompName.getText() + "', '" + txtContName.getText() + "', '" +
                                txtContTitle.getText() + "', '" + txtAddress.getText() + "', '" + txtCity.getText() + "', '" + txtRegion.getText() + "', '" +
                                txtPostal.getText() + "', '" + txtCountry.getText() + "', '" + txtPhone.getText() + "', '" + txtFax.getText() + "', '" +
                                txtHome.getText() + "'";

                bd.update(query);

                return;
            }

            if (e.getSource() == btnConsultar)
            {
                String query = "select * from suppliers ", where = "";
                String[] campos = {"ID", "Company Name", "Contact Name", "Contact Title", "Address", "City", "Region", "Postal Code", "Country", "Phone", "Fax", "Home Page"};
                int c = 0;

                for (int i = 0; i < txtCajas.length; i++)
                {
                    if (c == 0 && !txtCajas[i].getText().equals(""))
                    {
                        switch (i)
                        {
                            case 0 -> {
                                where = "where supplierid = " + txtID.getText();
                                c++;
                            }
                            case 1 -> {
                                where = "where companyname like '%" + txtCompName.getText() + "%'";
                                c++;
                            }
                            case 2 -> {
                                where = "where contactname like '%" + txtContName.getText() + "%'";
                                c++;
                            }
                            case 3 -> {
                                where = "where contacttitle like '%" + txtContTitle.getText() + "%'";
                                c++;
                            }
                            case 4 -> {
                                where = "where address like '%" + txtAddress.getText() + "%'";
                                c++;
                            }
                            case 5 -> {
                                where = "where city like '%" + txtCity.getText() + "%'";
                                c++;
                            }
                            case 6 -> {
                                where = "where region like '%" + txtRegion.getText() + "%'";
                                c++;
                            }
                            case 7 -> {
                                where = "where postalcode like '%" + txtPostal.getText() + "%'";
                                c++;
                            }
                            case 8 -> {
                                where = "where country like '%" + txtCountry.getText() + "%'";
                                c++;
                            }
                            case 9 -> {
                                where = "where phone like '%" + txtPhone.getText() + "%'";
                                c++;
                            }
                            case 10 -> {
                                where = "where fax like '%" + txtFax.getText() + "%'";
                                c++;
                            }
                            case 11 -> {
                                where = "where homepage like '%" + txtHome.getText() + "%'";
                                c++;
                            }
                        }
                    }

                    else if (c != 0 && !txtCajas[i].getText().equals(""))
                    {
                        switch (i)
                        {
                            case 1 -> {
                                where = where + " and companyname like '%" + txtCompName.getText() + "%'";
                                c++;
                            }
                            case 2 -> {
                                where = where + " and contactname like '%" + txtContName.getText() + "%'";
                                c++;
                            }
                            case 3 -> {
                                where = where + " and contacttitle like '%" + txtContTitle.getText() + "%'";
                                c++;
                            }
                            case 4 -> {
                                where = where + " and address like '%" + txtAddress.getText() + "%'";
                                c++;
                            }
                            case 5 -> {
                                where = where + " and city like '%" + txtCity.getText() + "%'";
                                c++;
                            }
                            case 6 -> {
                                where = where + " and region like '%" + txtRegion.getText() + "%'";
                                c++;
                            }
                            case 7 -> {
                                where = where + " and postalcode like '%" + txtPostal.getText() + "%'";
                                c++;
                            }
                            case 8 -> {
                                where = where + " and country like '%" + txtCountry.getText() + "%'";
                                c++;
                            }
                            case 9 -> {
                                where = where + " and phone like '%" + txtPhone.getText() + "%'";
                                c++;
                            }
                            case 10 -> {
                                where = where + " and fax like '%" + txtFax.getText() + "%'";
                                c++;
                            }
                            case 11 -> {
                                where = where + " and homepage like '%" + txtHome.getText() + "%'";
                                c++;
                            }
                        }
                    }
                }

                query = query + where;
                new TablaConsulta(bd.select(query), campos);
                return;
            }

            if (e.getSource() == btnLimpiar)
            {
                txtID.setText("");
                txtCompName.setText("");
                txtContName.setText("");
                txtContTitle.setText("");
                txtAddress.setText("");
                txtCity.setText("");
                txtRegion.setText("");
                txtPostal.setText("");
                txtCountry.setText("");
                txtPhone.setText("");
                txtFax.setText("");
                txtHome.setText("");
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        ((JTextField)(e.getSource())).setBackground(color);
    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}
