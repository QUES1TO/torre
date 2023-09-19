import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.awt.event.*;
public class BotonPersonalizado extends JButton
{
    private BufferedImage image;
    private boolean apuntando = false;
    public BotonPersonalizado(String rutaImagen)
    {
        URL resource = getClass().getResource(rutaImagen);
        try {
            image = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setOpaque(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        addMouseListener(new MouseListener());
        
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);   
        if(apuntando==true)
        {
            g.drawImage(image, 0, 0, getWidth()-20, getHeight()-20, this);
        }
        else
        {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
        
    }
    private class MouseListener extends MouseAdapter{
        public void mouseEntered(MouseEvent e) {
            apuntando=true;
            repaint();
        }
        public void mouseExited(MouseEvent e)
        {
            apuntando=false;
            repaint();
        }
    }
}
