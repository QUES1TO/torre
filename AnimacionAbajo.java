
/**
 * Write a description of class AnimacionAbajo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AnimacionAbajo implements Runnable
{
    private PanelContenedor panelContenedor;
    private int x,y;
    private boolean caer= true;
    private int limite;
    public AnimacionAbajo(PanelContenedor panelContenedor,int x, int y)
    {
        this.x = x;
        this.y = y;
        this.panelContenedor = panelContenedor;
    }
    public void setLimite(int limite)
    {
        this.limite = limite;
    }
    public void setCaer(boolean caer)
    {   
        this.caer=caer;
    }
    public void run()
    {
        if(limite<=y)
        {
            panelContenedor.setLocation(x, limite);
            this.caer=false;
        }
        while(this.caer)
        {
            panelContenedor.setLocation(x, y);
            try{
                Thread.sleep(2);
            }
            catch (Exception e){}
            
            y+=2;
            if(y >= limite)
            {
                this.caer=false;

            }
        }
    }
}
