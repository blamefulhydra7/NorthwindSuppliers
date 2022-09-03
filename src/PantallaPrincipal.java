import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaPrincipal extends JFrame implements ActionListener {

    JTextField txtServ, txtBD, txtUser, txtPass;
    JLabel lbServ, lbBD, lbUser, lbPass;
    JButton btnConectar, btnSig;
    Conexion bd;

    public PantallaPrincipal()
    {
        super("Proyecto de BD");
        hasInterfaz();
        hasEscuchas();
    }

    private void hasInterfaz()
    {
        setSize(275, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        lbServ = new JLabel("Servidor");
        lbServ.setBounds(10, 10, 100, 15);
        add(lbServ);

        txtServ = new JTextField();
        txtServ.setBounds(100, 5, 150, 30);
        add(txtServ);

        lbBD = new JLabel("BD");
        lbBD.setBounds(10, 50, 50, 15);
        add(lbBD);

        txtBD = new JTextField();
        txtBD.setBounds(100, 45, 150, 30);
        add(txtBD);

        lbUser = new JLabel("Usuario");
        lbUser.setBounds(10, 100, 100, 15);
        add(lbUser);

        txtUser = new JTextField();
        txtUser.setBounds(100, 95, 150, 30);
        add(txtUser);

        lbPass = new JLabel("Contraseña");
        lbPass.setBounds(10, 150, 100, 15);
        add(lbPass);

        txtPass = new JTextField();
        txtPass.setBounds(100, 145, 150, 30);
        add(txtPass);

        btnConectar = new JButton("Conectar");
        btnConectar.setBounds(20, 200, 100, 20);
        add(btnConectar);

        btnSig = new JButton("Crud Suppliers");
        btnSig.setBounds(130, 200, 130, 20);
        btnSig.setVisible(false);
        add(btnSig);

        setVisible(true);
    }

    private void hasEscuchas()
    {
        btnConectar.addActionListener(this);
        btnSig.addActionListener(this);

        txtServ.addActionListener(this);
        txtBD.addActionListener(this);
        txtUser.addActionListener(this);
        txtPass.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            if (e.getSource() == btnConectar) {
                if (txtServ.getText().equals("") || txtBD.getText().equals("") || txtUser.getText().equals("") || txtPass.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "No se permiten campos vacíos", "Error de usuario", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                bd = new Conexion(txtServ.getText(), txtBD.getText(), txtUser.getText(), txtPass.getText(), btnSig);
                return;
            }

            if (e.getSource() == btnSig) {
                new PantallaSuppliers(bd);
                dispose();
                return;
            }
        }

        if (e.getSource() instanceof JTextField)
        {
            if (e.getSource() == txtServ)
            {
                txtBD.requestFocus();
                return;
            }

            if (e.getSource() == txtBD)
            {
                txtUser.requestFocus();
                return;
            }

            if (e.getSource() == txtUser)
            {
                txtPass.requestFocus();
                return;
            }

            if (e.getSource() == txtPass)
            {
                btnConectar.requestFocus();
            }
        }
    }

    public static void main(String[] args) {
        new PantallaPrincipal();
    }
}
