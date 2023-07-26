import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;
public class PanelJuego extends JPanel
{
    private Graphics2D g2d;
    private ArrayList<Disco> brillos;
    private ArrayList<Disco> discos;
    private ArrayList<PanelContenedor> contenedor;
    private int cantidadDiscos = 8;
    private PanelContenedor discoSeleccionado;
    private int offsetX, offsetY;
    private int xOnReleased,yOnReleased;
    private int xOnClicked,yOnCLicked;
    private Timer smoothMoveTimer;
    private final int MOVE_AMOUNT = 1; // Cantidad de movimiento en píxeles en cada iteración
    private final int DELAY = 10; // Retraso en milisegundos entre cada iteración del temporizador
    private PanelContenedorEscenario suelo;
    private PanelContenedorEscenario pila1,pila2,pila3;
    private int anchoDeDisco = 300;
    private int altoDeDisco = 20;
    private PilaRecursiva<PanelContenedor> pilaRecursiva1, pilaRecursiva2,pilaRecursiva3;
    private Robot robot;
    private int startY;
    
    private int dato = 0;
    private JLabel label;
     private JPanel panel;
    public void addListenerToPilaRecursiva1()
    {
        Container container = getParent().getParent().getParent().getParent();
        pilaRecursiva1.get(pilaRecursiva1.size()-1).addMouseListener(new DiscoMouseListener());
        pilaRecursiva1.get(pilaRecursiva1.size()-1).addMouseMotionListener(new DiscoMouseMotionListener((Ventana)container));
    }
     /*public PilaRecursiva<PanelContenedor> getPilaRecursiva2()
    {
        //panelContenedor.addMouseMotionListener(new DiscoMouseMotionListener((Ventana)this.getParent()));
    }
     public PilaRecursiva<PanelContenedor> getPilaRecursiva2()
    {
        //panelContenedor.addMouseMotionListener(new DiscoMouseMotionListener((Ventana)this.getParent()));
    }*/
    public PanelJuego()
    {
        setLayout(null);
        brillos = new ArrayList<Disco>();
        discos = new ArrayList<Disco>();
         
        prepararEscenario();
        llenarListaDeDiscos();  
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        
        label = new JLabel("iso: 0 movimientos");
        label.setBounds(60,0,200,200);
        label.setVisible(true);
        label.setBackground(Color.WHITE);
        add(label);
        
        panel=new JPanel();
        panel.setBounds(54, 93, 150, 20);
        panel.setVisible(true);
        panel.setBackground(Color.white);
        add(panel);
    }
    public void prepararEscenario()
    {
        pilaRecursiva1 = new PilaRecursiva<PanelContenedor>(); 
        pilaRecursiva2 = new PilaRecursiva<PanelContenedor>(); 
        pilaRecursiva3 = new PilaRecursiva<PanelContenedor>();
        
        suelo = new PanelContenedorEscenario(0,0,1200,20,20, Color.GREEN);
        pila1 = new PanelContenedorEscenario(0,0,20,400,20, Color.GREEN);
        pila2 = new PanelContenedorEscenario(0,0,20,400,20, Color.GREEN);
        pila3 = new PanelContenedorEscenario(0,0,20,400,20, Color.GREEN);
        suelo.setBounds(0,540,1200,20);
        pila1.setBounds(300,150,20,400);
        pila2.setBounds(600,150,20,400);
        pila3.setBounds(900,150,20,400);
       
        add(pila1);
        add(pila2);
        add(pila3);
        add(suelo);
        
    }
    
    public void llenarListaDeDiscos()
    {        
        for(int i = 0;i<cantidadDiscos;i++)
        {
            PanelContenedor panelContenedor = new PanelContenedor(0, 0, anchoDeDisco, altoDeDisco, 20, Color.RED); 
            
            int centoX=300-(anchoDeDisco/2)+(altoDeDisco/2);
            
            panelContenedor.setBounds(centoX,pila1.gettLimiteCaida(),anchoDeDisco,altoDeDisco);
            //panelContenedor.addMouseListener(new DiscoMouseListener());
            //panelContenedor.addMouseMotionListener(new DiscoMouseMotionListener((Ventana)this.getParent()));
            pilaRecursiva1.push(panelContenedor);
            add(panelContenedor);
            setComponentZOrder(panelContenedor,0);
            anchoDeDisco-=30;
            int limiteCaida = pila1.gettLimiteCaida();
            pila1.setLimiteCaida(limiteCaida-20);
           
        }
    }
    
     private class DiscoMouseListener extends MouseAdapter {
        
        public void mousePressed(MouseEvent e) {
            startY = e.getX();
            discoSeleccionado = (PanelContenedor) e.getComponent();
            offsetX = e.getX();
            offsetY = e.getY();
            PanelJuego.this.xOnClicked = discoSeleccionado.getX();
            PanelJuego.this.yOnCLicked = discoSeleccionado.getY();
            
            dato++;
            label.setText("iso: " + dato+" "+"movimientos");
        
            
        }
        public void mouseReleased(MouseEvent e) {
               
            int centroPanelContenedor = 0;
            int alturaPanelContenedor = yOnReleased;
            int limiteCaida = 0;
            boolean mismoOrigen=false;
            
            
            if(discoSeleccionado.getX()<=350)
            {
                centroPanelContenedor=300-(discoSeleccionado.getWidth()/2)+(discoSeleccionado.getHeight()/2);
                //animacion.setLimite(pila1.gettLimiteCaida());
                limiteCaida = pila1.gettLimiteCaida();
                if(PanelJuego.this.xOnClicked<=350)
                {   
                    System.out.println(limiteCaida+"");
                    animarDiscos(limiteCaida+20,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);
                    mismoOrigen=true;
                } 
                else{
                    animarDiscos(limiteCaida,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);
                    pila1.setLimiteCaida(pila1.gettLimiteCaida()-20);
                    if(PanelJuego.this.xOnClicked>350 && PanelJuego.this.xOnClicked<=650)
                    {
                        pila2.setLimiteCaida(pila2.gettLimiteCaida()+20);
                    }
                    else
                    {
                        pila3.setLimiteCaida(pila3.gettLimiteCaida()+20);
                    }
                }
            }
            if(discoSeleccionado.getX()>350 && discoSeleccionado.getX()<=650)
            {
                centroPanelContenedor=600-(discoSeleccionado.getWidth()/2)+(discoSeleccionado.getHeight()/2);
                //animacion.setLimite(pila2.gettLimiteCaida());
                limiteCaida = pila2.gettLimiteCaida();
                
                if(PanelJuego.this.xOnClicked>350 && PanelJuego.this.xOnClicked<=650)
                {   
                    System.out.println(limiteCaida+"");
                    animarDiscos(limiteCaida+20,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);
                    mismoOrigen=true;
                } 
                else{
                    animarDiscos(limiteCaida,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);
                    pila2.setLimiteCaida(pila2.gettLimiteCaida()-20);
                    if(PanelJuego.this.xOnClicked<=350)
                    {
                        pila1.setLimiteCaida(pila1.gettLimiteCaida()+20);
                    }
                    else
                    {
                        pila3.setLimiteCaida(pila3.gettLimiteCaida()+20);
                    }
                }
            }
            if(discoSeleccionado.getX()>650)
            {
                centroPanelContenedor=900-(discoSeleccionado.getWidth()/2)+(discoSeleccionado.getHeight()/2);
                //animacion.setLimite(pila3.gettLimiteCaida());
                limiteCaida = pila3.gettLimiteCaida();
                if(PanelJuego.this.xOnClicked>650)
                {   
                    System.out.println(limiteCaida+"");
                    animarDiscos(limiteCaida+20,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);
                    mismoOrigen=true;
                } 
                else{
                    animarDiscos(limiteCaida,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);
                    pila3.setLimiteCaida(pila3.gettLimiteCaida()-20);
                    if(PanelJuego.this.xOnClicked<=350)
                    {
                        pila1.setLimiteCaida(pila1.gettLimiteCaida()+20);
                    }
                    else
                    {
                        pila2.setLimiteCaida(pila2.gettLimiteCaida()+20);
                    }
                }
            }
            discoSeleccionado = null;
             

        }
    }
    public void animarDiscos(int limiteCaida,PanelContenedor discoSeleccionado,int centroPanelContenedor,int alturaPanelContenedor)
    {
        AnimacionAbajo animacion = new AnimacionAbajo(discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);            
        System.out.println("x"+discoSeleccionado.getX()+"y"+discoSeleccionado.getY());
        Thread thread = new Thread(animacion);
        thread.start();
        animacion.setLimite(limiteCaida);
    }
    public Component content()
    {
        return ((JFrame)getParent()).getContentPane();
    }
    private class DiscoMouseMotionListener extends MouseMotionAdapter {
        private Ventana frame;
        public DiscoMouseMotionListener(Ventana frame)
        {
            this.frame = frame;
        }
        public void mouseDragged(MouseEvent e) {
            if (discoSeleccionado != null) {
                int test =discoSeleccionado.getX();
                
                /*Point framePosition = this.frame.getLocationOnScreen();
                Point targetPosition = new Point(framePosition.x + 300, framePosition.y + 520);

                // Convertir las coordenadas globales de la pantalla a coordenadas relativas dentro del JFrame
                SwingUtilities.convertPointFromScreen(targetPosition, this.frame);*/

                
                Point panelPosition = getLocationOnScreen();
                int xx = panelPosition.x + test;
                int yy = panelPosition.y + 520;
                
                int deltaY = startY;
                int x = e.getXOnScreen() - offsetX - PanelJuego.this.getLocationOnScreen().x;
                int y = e.getYOnScreen() - offsetY - PanelJuego.this.getLocationOnScreen().y;
                xOnReleased = x;
                yOnReleased = y;
                
                if(y>150 && y<=520)
                {
                  if(xx<=350)
                  {
                      xx=300-(discoSeleccionado.getWidth()/2)+(discoSeleccionado.getHeight()/2);
                  }
                  if(xx>350 && xx<=650)
                  {
                      xx=600-(discoSeleccionado.getWidth()/2)+(discoSeleccionado.getHeight()/2);
                  }
                  if(xx>650)
                  {
                      xx=900-(discoSeleccionado.getWidth()/2)+(discoSeleccionado.getHeight()/2);
                  }
                  robot.mouseMove(xx,e.getYOnScreen());
                  discoSeleccionado.setLocation(xx, y);  
                }
                else
                {
                   if(y>520)
                   {
                      robot.mouseMove(xx,yy);
                      discoSeleccionado.setLocation(xx, yy); 
                   }
                   else
                   {
                       discoSeleccionado.setLocation(x, y); 
                   }
                   
                }
                repaint();
                
            }
        }
        
    }
   
}
