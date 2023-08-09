import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Icon;
public class Ventana extends JFrame implements ActionListener
{
    private PanelJuego panelJuego;
     private Panelinicio panelinicio;
     private JButton boton1,boton2;
     private ImageIcon img, img1,img3,img4;
     private JLabel label ,label2;
    public Ventana()
    {
        
        setSize(1200, 1200);
        /*setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);*/
        
        boton1 = new JButton();
              
        img = new ImageIcon(getClass().getResource("inicioo.png"));
        img1= new ImageIcon(getClass().getResource("dificultad1.png"));
      
        img3= new ImageIcon(getClass().getResource("img1.jpg"));
        img4= new ImageIcon(getClass().getResource("img4.jpg")); 

      
        boton1.setBounds(500,250,220,120);
       
        Icon i= new ImageIcon(img.getImage().getScaledInstance(boton1.getWidth(),boton1.getHeight(),Image.SCALE_DEFAULT));
        boton1.setIcon(i);
        boton1.addActionListener(this);
        boton1.setVisible(true);
        add(boton1);
        
           
        boton2 = new JButton();
        boton2.setBounds(500,390,220,120);
        Icon i1= new ImageIcon(img1.getImage().getScaledInstance(boton2.getWidth(),boton2.getHeight(),Image.SCALE_DEFAULT));
        boton2.setIcon(i1);
        boton2.addActionListener(this);
        boton2.setVisible(true);
        add(boton2);
        
        
         label = new JLabel();
        label.setBounds(0,0,1200,800);
        label.setVisible(true);
        add(label);
        

        
        panelJuego = new PanelJuego();
        panelJuego.setBounds(0,0,1200,1200);
       
        
        //label.setIcon(i3);
        panelJuego.setVisible(false);
        add(panelJuego);
        panelJuego.addListenerToPilaRecursiva1();
        
        panelinicio = new Panelinicio();
        panelinicio.setBounds(0,0,1200,800);
        Icon i3= new ImageIcon(img3.getImage().getScaledInstance(panelinicio.getWidth(),panelinicio.getHeight(),Image.SCALE_DEFAULT));
        label.setIcon(i3);
        panelinicio.setVisible(true);
        add(panelinicio);
       
        
     
    }
  
    public static void main(String[] args)
    {
        Ventana ventana=new Ventana();
        
        ventana.setVisible(true);
    }
    
     public void actionPerformed(ActionEvent e)
    {
        Object objeto = e.getSource();
        if(objeto==boton1)
        {
            panelJuego.setVisible(true);
            panelinicio.setVisible(false);
            boton1.setVisible(false);
            boton2.setVisible(false);
              label.setVisible(false);
        }
     /*   if(objeto==boton2)
        {
         
        }
        if(objeto==boton3)
        {
           
        }*/
    }
}
