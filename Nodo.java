import java.util.ArrayList;
public class Nodo<T>
{
    public T valor;
    public int pos;
    public Nodo(T valor)
    {
        this.valor = valor;
    }
    public T getValor()
    {
        return valor;
    }
    public void setPos(int pos)
    {
        this.pos = pos;
    }
}