public class PilaRecursiva<T>
{
   private Nodo<T> ini;
   private PilaRecursiva<T> sig;
   public void push(T dato)
   {
       if(ini==null)
       {
           ini = new Nodo<>(dato);
           sig = new PilaRecursiva<>();
       }
       else{
           sig.push(dato); 
       }
   }
   
   public int size()
   {     
     if(sig == null)
     {
         return 0;
     }
     else
     {         
         return sig.size()+1;
     }
   }
   public void remove(int index)
   {
       if(index==0)
       {
           if(sig!=null)
           {
               ini = sig.ini;
               sig = sig.sig;
           }
       }
       else{
           sig.remove(index - 1);
       }
   }
   public void mostrarDatos()
   {
       System.out.println("Tus datos hasta el momento son: "+listarDatos());
   }
   public String listarDatos()
   {
       if(sig.ini==null)
       {
           return (String)ini.getValor();
       }
       else
       {
           return (String)ini.getValor()+"," + sig.listarDatos();
       }
   }
   public T get(int index)
   {
       if(index==0)
       {
           return ini.getValor();
       }
       else{
           return sig.get(index - 1);
       }
   }
   public void marcarPosiciones()
   {
       int size = size();
       marcar(size);
   }
   public void marcar(int size)
   {     
     int currentSize = size();
     if(sig.sig == null)
     {
         ini.setPos(size - currentSize);
     }
     else
     {    
         ini.setPos(size - currentSize);
         sig.marcar(size);
     }
   }
    public T pop(){
     
      
            if (sig == null)
            {
                T dato = ini.getValor();
                ini=null;
                return dato;
            }
            else {
                return sig.pop();
            }
       
    }
}