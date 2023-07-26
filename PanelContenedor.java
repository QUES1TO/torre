import javax.swing.*;
import java.awt.*;
public class PanelContenedor extends JPanel
{
    private Graphics2D g2d;
    private Disco disco,brillo;
    private int x, y;
    public PanelContenedor(double x, double y, double width, double height, double arcWidth, Color color)
    {
        setLayout(null);
        disco = new Disco(x,y,width,height,20,color);
        brillo = new Disco(x+5,y+4,width-15,height-17,20,Color.WHITE);
        setOpaque(false);
        //setBackground(Color.GREEN);
    }
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        iniciarDisco();     
    }
    public void iniciarDisco()
    {        
            
            g2d.setColor(disco.getColor());
            g2d.fill(disco);
            g2d.setColor(brillo.getColor());
            g2d.fill(brillo);

        
        
    }
    public void setPosX(int x)
    {
        this.x = x;
    }
    public void setPosY(int y)
    {
        this.y = y;
    }
    public int getPosX()
    {
        return this.x;
    }
    public int getPosY()
    {
        return this.y;
    }
}
