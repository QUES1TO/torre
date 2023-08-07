import javax.swing.*;
import java.awt.*;
public class PanelContenedorEscenario extends JPanel
{
    private Graphics2D g2d;
    private Pila pila;
    private int x, y;
    private int limiteCaida = 720;
    public PanelContenedorEscenario(double x, double y, double width, double height, double arcWidth, Color color)
    {
        setLayout(null);
        pila = new Pila(x,y,width,height,20,color);
        setOpaque(false);
        //setBackground(Color.GREEN);
    }
    public void setLimiteCaida(int limiteCaida)
    {
        this.limiteCaida = limiteCaida;
    }
    public int gettLimiteCaida()
    {
        return this.limiteCaida;
    }
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        iniciarDisco();     
    }
    public void iniciarDisco()
    {        
            
            g2d.setColor(pila.getColor());
            g2d.fill(pila);
            

        
        
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
