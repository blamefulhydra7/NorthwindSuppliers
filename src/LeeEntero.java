import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LeeEntero extends JTextField implements KeyListener, FocusListener {
    protected int tamaño;

    public LeeEntero()
    {
        this(5);
    }

    public LeeEntero(int tamaño)
    {
        setTamaño(tamaño);
        hasEscuchas();
    }

    public int getTamaño() {
        return tamaño;
    }

    protected void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    private void hasEscuchas()
    {
        addKeyListener(this);
        addFocusListener(this);
    }

    public long getCantidad()
    {
        return Long.parseLong(getText());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (getText().length() >= tamaño)
        {
            e.consume();
            return;
        }

        if ("0123456789".indexOf(e.getKeyChar()) == -1)
        {
            e.consume();
            return;
        }

        /*if ("-".indexOf(e.getKeyChar()) != -1 && getText().length() != 0)
        {
            e.consume();
            return;
        }*/
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown() || e.isMetaDown())
        {
            e.consume();
            return;
        }

        if (e.getKeyCode() == 36 || e.getKeyCode() == 37 || e.getKeyCode() == 39)
        {
            e.consume();
            return;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}
