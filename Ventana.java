import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Icon;
public class Ventana extends JFrame implements ActionListener
{
    private PanelJuego panelJuego;
     private ImageIcon img,img3;
     private JLabel label;
     private BotonPersonalizado botonInicio;
    public Ventana()
    {
        setSize(1200, 800);
    
        img = new ImageIcon(getClass().getResource("inicioo.png"));
        img3= new ImageIcon(getClass().getResource("img.png"));
        
        botonInicio = new BotonPersonalizado("/inicioo.png");
        botonInicio.setBounds(500,330,240,140);
        botonInicio.addActionListener(this);
        add(botonInicio);
        botonInicio.setVisible(true);
    
        label = new JLabel();
        label.setBounds(0,0,1200,800);
        label.setVisible(true);
        Icon i3= new ImageIcon(img3.getImage().getScaledInstance(label.getWidth(),label.getHeight(),Image.SCALE_DEFAULT));
        label.setIcon(i3);
        add(label);   
        
        panelJuego = new PanelJuego(this);
        panelJuego.setBounds(0,0,1200,1200);
        panelJuego.setVisible(false);
        add(panelJuego);
    }
  
    public static void main(String[] args)
    {
        Ventana ventana=new Ventana();
        ventana.setVisible(true);
    }
    
     public void actionPerformed(ActionEvent e)
    {
        Object objeto = e.getSource();
        if(objeto==botonInicio)
        {
            panelJuego.setVisible(true);
            botonInicio.setVisible(false);
            label.setVisible(false);
            panelJuego.juego();
            panelJuego.addListenerToPilaRecursiva1();
            repaint();
        }
        if (objeto==panelJuego.getBotonVolver())
        {
            panelJuego.setVisible(false);
            botonInicio.setVisible(true);
            panelJuego.vaciarListaDeDiscos();
            label.setVisible(true);
        }
    }
}
