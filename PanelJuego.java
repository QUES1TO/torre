import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class PanelJuego extends JPanel implements ActionListener
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
    private PanelContenedorEscenario pila1,pila2,pila3,pila4,pila5,pila6;
    private int anchoDeDisco = 300;
    private int altoDeDisco = 20;
    private PilaRecursiva<PanelContenedor> pilaRecursiva1, pilaRecursiva2,pilaRecursiva3,pilaRecursiva4,pilaRecursiva5,pilaRecursiva6;
    private Robot robot;
    private int startY;
    private ImageIcon fondo;
    private DiscoMouseListener discoMouseListener;
    private DiscoMouseMotionListener discoMouseMotionListener;
    
    private int dato = 0;
    private JLabel label, label2,label3,label4;
     private JPanel panel, panel2 ,panel3,panel4,panel5,panel6,panel7;
      private ImageIcon img;
       private BotonPersonalizado botonVolver,botonReiniciar;
        private ImageIcon img6;
        public BotonPersonalizado getBotonVolver()
        {
            return this.botonVolver;
        }
        public BotonPersonalizado getBotonReiniciar()
        {
            return this.botonReiniciar;
        }
        
    public int getWidthFromPilaRecursiva(PilaRecursiva<PanelContenedor> pilaRecursiva)
    {
        PanelContenedor panelContenedor = pilaRecursiva.pop();
        int width = 0;
        if(panelContenedor!=null)
        {
            width = panelContenedor.getWidth();
            pilaRecursiva.push(panelContenedor);
        }
        else
        {
            width =10000000 ;
        }
        return width;
    }
    public void comprobarVictoria()
    {
        if(pilaRecursiva1.size()==0 && pilaRecursiva3.size()==0)
        {
            label3.setVisible(true);
        }
    }
    
    public void removeListenerToPilaRecursiva1()
    {
        int lastPosition = pilaRecursiva1.size()-1;
        Container container = getParent().getParent().getParent().getParent();
        if(pilaRecursiva1.size()>0){
        pilaRecursiva1.get(lastPosition).removeMouseListener(discoMouseListener);
         pilaRecursiva1.get(lastPosition).removeMouseMotionListener(discoMouseMotionListener);
        }
    }
    public void removeListenerToPilaRecursiva2()
    {
        int lastPosition = pilaRecursiva2.size()-1;
        if(pilaRecursiva2.size()>0){
        Container container = getParent().getParent().getParent().getParent();
        pilaRecursiva2.get(pilaRecursiva2.size()-1).removeMouseListener(discoMouseListener);
         pilaRecursiva2.get(pilaRecursiva2.size()-1).removeMouseMotionListener(discoMouseMotionListener);
        }
    }
    public void removeListenerToPilaRecursiva3()
    {
        if(pilaRecursiva3.size()>0){
        Container container = getParent().getParent().getParent().getParent();
        pilaRecursiva3.get(pilaRecursiva3.size()-1).removeMouseListener(discoMouseListener);
         pilaRecursiva3.get(pilaRecursiva3.size()-1).removeMouseMotionListener(discoMouseMotionListener);
        }
    }
    public void addListenerToPilaRecursiva1()
    {
        if(pilaRecursiva1.size()>0){
        Container container = getParent().getParent().getParent().getParent();
        pilaRecursiva1.get(pilaRecursiva1.size()-1).addMouseListener(discoMouseListener);
         pilaRecursiva1.get(pilaRecursiva1.size()-1).addMouseMotionListener(discoMouseMotionListener);
        }
    }

    public void addListenerToPilaRecursiva2()
    {
        int lastPosition = pilaRecursiva2.size()-1;
        if(pilaRecursiva2.size()>0){
        Container container = getParent().getParent().getParent().getParent();
        pilaRecursiva2.get(pilaRecursiva2.size()-1).addMouseListener(discoMouseListener);
         pilaRecursiva2.get(pilaRecursiva2.size()-1).addMouseMotionListener(discoMouseMotionListener);
        }
    }
    
    public void addListenerToPilaRecursiva3()
    {
        if(pilaRecursiva3.size()>0){
        Container container = getParent().getParent().getParent().getParent();
        pilaRecursiva3.get(pilaRecursiva3.size()-1).addMouseListener(discoMouseListener);
         pilaRecursiva3.get(pilaRecursiva3.size()-1).addMouseMotionListener(discoMouseMotionListener);
        }
    }
   public PanelJuego(Ventana ventana)
    {
        setLayout(null);
        brillos = new ArrayList<Disco>();
        discos = new ArrayList<Disco>();
        JSlider slider = new JSlider(3, 8, 8);
        slider.setMajorTickSpacing(1);
        //slider.setMinorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(new ChangeListener() 
        {
          public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider) e.getSource();
            int value = source.getValue();
            System.out.println("Valor seleccionado: " + value);
            cantidadDiscos =source.getValue();
            System.out.println(pilaRecursiva1.size()+" antes de vacias");
            vaciarPilas(pilaRecursiva1);
            vaciarPilas(pilaRecursiva2);
            vaciarPilas(pilaRecursiva3);
            System.out.println(pilaRecursiva1.size()+" despues de vaciar");
            
            pila1.setLimiteCaida(520);
            pila2.setLimiteCaida(520);
            pila3.setLimiteCaida(520);
            
            switch (cantidadDiscos) { 
                case 3:
                 anchoDeDisco = 150;
                 break;
                case 4:
                 anchoDeDisco = 180;
                 break;
               
                case 5 :
                 anchoDeDisco = 210;
                 break;
                case 6 :
                 anchoDeDisco = 240;
                 break;
                 case 7 :
                 anchoDeDisco = 270;
                 break;
                 case 8 :
                 anchoDeDisco = 300;
                 break;
            }
            
            llenarListaDeDiscos();
            addListenerToPilaRecursiva1();
          }
       /*public void actionPerformed(ActionEvent e)
       {
           Object objeto = e.getSource();
           if(objeto==botonInicio)
        }*/
        });
         add(slider);
         slider.setBounds(200, 590, 700, 50);
         slider.setBackground(new Color(255, 155, 155, 150));
        slider.setVisible(true);
        
    }
    public void juego()
    {
        discoMouseListener = new DiscoMouseListener();
        Container container = getParent().getParent().getParent().getParent();
        discoMouseMotionListener = new DiscoMouseMotionListener((Ventana)container);
        prepararEscenario();
        llenarListaDeDiscos(); 
        
          
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        
         
         
        img= new ImageIcon(getClass().getResource("img4.jpg"));
         label = new JLabel("iso: 0 movimientos");
        label.setBounds(550,0,200,200);
        label.setVisible(true);
        label.setBackground(Color.WHITE);
        label.setForeground(Color.BLUE);
        Font fuenteGrande = new Font(label.getFont().getName(), Font.BOLD, 15);
        label.setFont(fuenteGrande);
        add(label);
        
        panel=new JPanel();
        panel.setBounds(540, 93, 155, 20);
        panel.setVisible(true);
        panel.setBackground(new Color(255, 255, 255, 150));
        add(panel);
        
        panel3=new JPanel();
        panel3.setBounds(174, 140, 265, 420);
        panel3.setVisible(true);
        panel3.setBackground(new Color(44, 117, 255, 50));
        add(panel3);
        
        panel4=new JPanel();
        panel4.setBounds(474, 140, 265, 420);
        panel4.setVisible(true);
        panel4.setBackground(new Color(44, 117, 255, 50));
        add(panel4);
        
        panel5=new JPanel();
        panel5.setBounds(774, 140, 265, 420);
        panel5.setVisible(true);
        panel5.setBackground(new Color(44, 117, 255, 50));
        add(panel5);
             
        label2 = new JLabel();
        label2.setBounds(0,0,1200,800);
        label2.setVisible(true);
        add(label2);
        
        panel2 = new JPanel();
        panel2.setBounds(0,0,1200,800);
        Icon i4= new ImageIcon(img.getImage().getScaledInstance(panel2.getWidth(),panel2.getHeight(),Image.SCALE_DEFAULT));
        label2.setIcon(i4);
        panel2.setVisible(true);
        add(panel2);
    }
  /*  public void preInit(){
       fondo= new ImageIcon(getClass().getResource("fondo.png"));
    }
    
     public void initComponents(){
         
    }
     public void paint(Graphics g){
        g.drawImage(fondo,0,0,getWidth(),getHeight(),this);
    }
 */
    public void prepararEscenario()
    {
        img6= new ImageIcon(getClass().getResource("salir.png"));
          botonVolver= new BotonPersonalizado("salir.png");
          Ventana v= (Ventana)getParent().getParent().getParent().getParent();
          botonVolver.addActionListener(v);
         botonVolver.setBounds(830,20,150,75);
         botonVolver.setVisible(true);
        add( botonVolver);
        
        botonReiniciar= new BotonPersonalizado("reiniciar.png");
          botonReiniciar.addActionListener(this);
         botonReiniciar.setBounds(240,20,150,90);
         botonReiniciar.setVisible(true);
        add( botonReiniciar);
        
        pilaRecursiva1 = new PilaRecursiva<PanelContenedor>(); 
        pilaRecursiva2 = new PilaRecursiva<PanelContenedor>(); 
        pilaRecursiva3 = new PilaRecursiva<PanelContenedor>();
        pilaRecursiva4 = new PilaRecursiva<PanelContenedor>();
        pilaRecursiva5 = new PilaRecursiva<PanelContenedor>();
        pilaRecursiva6 = new PilaRecursiva<PanelContenedor>();
        
        pila4 = new PanelContenedorEscenario(0,0,25,393,20, Color.white);
        pila5 = new PanelContenedorEscenario(0,0,25,393,20, Color.white);
        pila6 = new PanelContenedorEscenario(0,0,25,393,20, Color.white);
        
        pila1 = new PanelContenedorEscenario(0,0,20,400,20, Color.BLACK);
        pila2 = new PanelContenedorEscenario(0,0,20,400,20, Color.BLACK);
        pila3 = new PanelContenedorEscenario(0,0,20,400,20, Color.BLACK);
        suelo = new PanelContenedorEscenario(0,0,1200,500,20, Color.BLACK);
        
        pila4.setBounds(298,150,25,393);
        pila5.setBounds(598,150,25,393);
        pila6.setBounds(898,150,25,393);
        
        pila1.setBounds(300,150,20,400);
        pila2.setBounds(600,150,20,400);
        pila3.setBounds(900,150,20,400);
        suelo.setBounds(0,540,1200,20);
        
        label3 = new JLabel("¡¡GANASTE¡¡");
        label3.setBounds(500,30,540,540);
        label3.setVisible(false);
        label3.setForeground(Color.MAGENTA);
        add(label3);
        Font fuenteGrande = new Font(label3.getFont().getName(), Font.BOLD, 36);
        label3.setFont(fuenteGrande);
        
        
        add(pila1);
        add(pila2);
        add(pila3);
        add(pila4);
        add(pila5);
        add(pila6);
        add(suelo);
    
    }
    
      public void actionPerformed(ActionEvent e)
    {
        Object objeto = e.getSource();
        if(objeto==botonReiniciar)
        {
            System.out.println(pilaRecursiva1.size()+" antes de vacias");
            vaciarPilas(pilaRecursiva1);
            vaciarPilas(pilaRecursiva2);
            vaciarPilas(pilaRecursiva3);
            System.out.println(pilaRecursiva1.size()+" despues de vaciar");
            label3.setVisible(false);
            
            pila1.setLimiteCaida(520);
            pila2.setLimiteCaida(520);
            pila3.setLimiteCaida(520);
            llenarListaDeDiscos();
            addListenerToPilaRecursiva1();
             dato=-1;
             dato++;
             label.setText("Hizo: " + dato+" "+"movimientos");
              
        }
        
    }
    public void vaciarPilas(PilaRecursiva<PanelContenedor> pilaRecursiva)
    {
        int tamañoActual = pilaRecursiva.size();
        if(tamañoActual>0)
        {
            for(int i=0;i<tamañoActual;i++)
            {
                remove(pilaRecursiva.pop());
            }
        }
        repaint();
    }
    public void llenarListaDeDiscos()
    {        
        for(int i = 0;i<cantidadDiscos;i++)
        {
            PanelContenedor panelContenedor = new PanelContenedor(0, 0, anchoDeDisco, altoDeDisco, 20, Color.MAGENTA); 
            System.out.println(cantidadDiscos+"");
            int centoX=323-(anchoDeDisco/2)+(altoDeDisco/2);
            
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
    public void vaciarListaDeDiscos()
    {
        Ventana v= (Ventana)getParent().getParent().getParent().getParent();
          botonVolver.removeActionListener(v);
        removeAll();
        anchoDeDisco = 300;
        
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
            label.setText("Hizo: " + dato+" "+"movimientos");
        }

        public void mouseReleased(MouseEvent e) {
               
            int centroPanelContenedor = 0;
            int alturaPanelContenedor = yOnReleased;
            int limiteCaida = 0;
            discoSeleccionado = (PanelContenedor) e.getComponent();
            boolean mismoOrigen=false;
            if(discoSeleccionado.getX()<=350)
            {
                centroPanelContenedor=323-(discoSeleccionado.getWidth()/2)+(discoSeleccionado.getHeight()/2);
                //animacion.setLimite(pila1.gettLimiteCaida());
                limiteCaida = pila1.gettLimiteCaida();
               
                if(PanelJuego.this.xOnClicked<=350)
                {   
                    System.out.println(limiteCaida+"");
                    animarDiscos(limiteCaida+20,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);
                    mismoOrigen=true;
                   
                } 
                else{                    
                    //animarDiscos(limiteCaida,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);
                    //pila1.setLimiteCaida(pila1.gettLimiteCaida()-20);
                    if(PanelJuego.this.xOnClicked>350 && PanelJuego.this.xOnClicked<=650)                    
                    {
                        if(getWidthFromPilaRecursiva(pilaRecursiva1)<discoSeleccionado.getWidth())
                        {
                            centroPanelContenedor=623-(discoSeleccionado.getWidth()/2)+(discoSeleccionado.getHeight()/2);                            
                            limiteCaida = pila2.gettLimiteCaida();
                            animarDiscos(limiteCaida+20,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);                            
                        }
                        else{
                            animarDiscos(limiteCaida,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);
                            pila1.setLimiteCaida(pila1.gettLimiteCaida()-20);
                            removeListenerToPilaRecursiva2();
                            removeListenerToPilaRecursiva1();
                            pilaRecursiva1.push(pilaRecursiva2.pop());
                            addListenerToPilaRecursiva2();
                            addListenerToPilaRecursiva1();
                            pila2.setLimiteCaida(pila2.gettLimiteCaida()+20);
                        }
                        
                    }
                    else
                    {
                        //animarDiscos(limiteCaida,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);
                       // pila1.setLimiteCaida(pila1.gettLimiteCaida()-20);
                        
                        if(PanelJuego.this.xOnClicked>650)
                                {
                                    
                        if(getWidthFromPilaRecursiva(pilaRecursiva1)<discoSeleccionado.getWidth())
                        {
                            centroPanelContenedor=923-(discoSeleccionado.getWidth()/2)+(discoSeleccionado.getHeight()/2);                            
                            limiteCaida = pila3.gettLimiteCaida();
                            animarDiscos(limiteCaida+20,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);                            
                        }
                        else{
                            animarDiscos(limiteCaida,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);
                            pila1.setLimiteCaida(pila1.gettLimiteCaida()-20);
                            removeListenerToPilaRecursiva3();
                                    removeListenerToPilaRecursiva1();
                                    pilaRecursiva1.push(pilaRecursiva3.pop());
                                    addListenerToPilaRecursiva3();
                                    addListenerToPilaRecursiva1();
                                    pila3.setLimiteCaida(pila3.gettLimiteCaida()+20);
                        }
                                }
                    }
                }
            }
            
            //pila2
            
            if(discoSeleccionado.getX()>350 && discoSeleccionado.getX()<=650)
            {
                centroPanelContenedor=623-(discoSeleccionado.getWidth()/2)+(discoSeleccionado.getHeight()/2);
                //animacion.setLimite(pila2.gettLimiteCaida());
                limiteCaida = pila2.gettLimiteCaida();
                
                if(PanelJuego.this.xOnClicked>350 && PanelJuego.this.xOnClicked<=650)
                {   
                    System.out.println(limiteCaida+"");
                    animarDiscos(limiteCaida+20,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);
                    mismoOrigen=true;
                } 
                else{
                    //animarDiscos(limiteCaida,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);
                    //pila2.setLimiteCaida(pila2.gettLimiteCaida()-20);
                    if(PanelJuego.this.xOnClicked<=350)
                    {
                        if(getWidthFromPilaRecursiva(pilaRecursiva2)<discoSeleccionado.getWidth())
                        {
                            centroPanelContenedor=323-(discoSeleccionado.getWidth()/2)+(discoSeleccionado.getHeight()/2);                            
                            limiteCaida = pila1.gettLimiteCaida();
                            animarDiscos(limiteCaida+20,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);                            
                        }
                        else{
                            animarDiscos(limiteCaida,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);
                            pila2.setLimiteCaida(pila2.gettLimiteCaida()-20);
                            removeListenerToPilaRecursiva1();
                            removeListenerToPilaRecursiva2();
                            pilaRecursiva2.push(pilaRecursiva1.pop());
                            addListenerToPilaRecursiva1();
                            addListenerToPilaRecursiva2();                        
                            pila1.setLimiteCaida(pila1.gettLimiteCaida()+20); 
                        }
                    }
                    else
                    {
                       // pila3.setLimiteCaida(pila3.gettLimiteCaida()+20);
                        if(PanelJuego.this.xOnClicked>650)
                                {
                                    
                                    if(getWidthFromPilaRecursiva(pilaRecursiva2)<discoSeleccionado.getWidth())
                                     {
                                        centroPanelContenedor=923-(discoSeleccionado.getWidth()/2)+(discoSeleccionado.getHeight()/2);                            
                                        limiteCaida = pila3.gettLimiteCaida();
                                        animarDiscos(limiteCaida+20,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);                            
                                    }
                                    else{
                                                animarDiscos(limiteCaida,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);
                                                pila2.setLimiteCaida(pila2.gettLimiteCaida()-20);
                                                removeListenerToPilaRecursiva3();
                                                removeListenerToPilaRecursiva2();
                                                pilaRecursiva2.push(pilaRecursiva3.pop());
                                                addListenerToPilaRecursiva3();
                                                addListenerToPilaRecursiva2();
                                                pila3.setLimiteCaida(pila3.gettLimiteCaida()+20);
                                    }
                                }
                    }
                }
            }
            if(discoSeleccionado.getX()>650)
            {
                centroPanelContenedor=923-(discoSeleccionado.getWidth()/2)+(discoSeleccionado.getHeight()/2);
                //animacion.setLimite(pila3.gettLimiteCaida());
                limiteCaida = pila3.gettLimiteCaida();
                if(PanelJuego.this.xOnClicked>650)
                {   
                    System.out.println(limiteCaida+"");
                    animarDiscos(limiteCaida+20,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);
                    mismoOrigen=true;
                } 
                else{
                    //animarDiscos(limiteCaida,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);
                    //pila3.setLimiteCaida(pila3.gettLimiteCaida()-20);
                    if(PanelJuego.this.xOnClicked<=350) //disco1
                    {
                          if(getWidthFromPilaRecursiva(pilaRecursiva3)<discoSeleccionado.getWidth())
                        {
                               centroPanelContenedor=323-(discoSeleccionado.getWidth()/2)+(discoSeleccionado.getHeight()/2);                            
                                    limiteCaida = pila1.gettLimiteCaida();
                                    animarDiscos(limiteCaida+20,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);                                    
                        }
                       // animarDiscos(limiteCaida,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);
                        //pila3.setLimiteCaida(pila3.gettLimiteCaida()-1);
                               
                        else{
                                    animarDiscos(limiteCaida,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);
                                    pila3.setLimiteCaida(pila3.gettLimiteCaida()-20);
                                    removeListenerToPilaRecursiva1();
                                    removeListenerToPilaRecursiva3();
                                    pilaRecursiva3.push(pilaRecursiva1.pop());
                                    addListenerToPilaRecursiva1();
                                    addListenerToPilaRecursiva3();
                                    pila1.setLimiteCaida(pila1.gettLimiteCaida()+20);
                        }
                    }
                    else
                    {
                       //  animarDiscos(limiteCaida,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);
                        if(PanelJuego.this.xOnClicked>350 && PanelJuego.this.xOnClicked<=650)
                    {
                                    if(getWidthFromPilaRecursiva(pilaRecursiva3)<discoSeleccionado.getWidth())
                                {
                                    centroPanelContenedor=623-(discoSeleccionado.getWidth()/2)+(discoSeleccionado.getHeight()/2);                            
                                    limiteCaida = pila2.gettLimiteCaida();
                                    animarDiscos(limiteCaida+20,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);                            
                                }
                                 else{
                                                             animarDiscos(limiteCaida,discoSeleccionado,centroPanelContenedor,alturaPanelContenedor);
                                                            pila3.setLimiteCaida(pila3.gettLimiteCaida()-20);
                                                            removeListenerToPilaRecursiva2();
                                                            removeListenerToPilaRecursiva3();
                                                            pilaRecursiva3.push(pilaRecursiva2.pop());
                                                            addListenerToPilaRecursiva2();
                                                            addListenerToPilaRecursiva3();
                                                            pila2.setLimiteCaida(pila2.gettLimiteCaida()+20);
                                     }
                    }
                    }
                }
            }
            comprobarVictoria();
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

