import javax.swing.*;
import java.awt.*;
public class Ventana extends JFrame
{
    private PanelJuego panelJuego;
    public Ventana()
    {
        
        setSize(1200, 1200);
        /*setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);*/
        panelJuego = new PanelJuego();
        panelJuego.setBounds(0,0,500,500);
        panelJuego.setBackground(Color.BLUE);
        panelJuego.setVisible(true);
        add(panelJuego);
        panelJuego.addListenerToPilaRecursiva1();
    }
    public static void main(String[] args)
    {
        Ventana ventana=new Ventana();
        
        ventana.setVisible(true);
    }
}
