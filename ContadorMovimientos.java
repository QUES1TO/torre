import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContadorMovimientos extends JFrame {
    private int contador;
    private JButton btnContador;
    private JLabel lblContador;

    public ContadorMovimientos() {
        contador = 0;
        btnContador = new JButton("Registrar movimiento");
        lblContador = new JLabel("Movimientos registrados: " + contador);

        btnContador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contador++;
                lblContador.setText("Movimientos registrados: " + contador);
            }
        });

        setLayout(new FlowLayout());
        add(btnContador);
        add(lblContador);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Contador de Movimientos");
        setSize(300, 100);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ContadorMovimientos();
    }
}

