package editortexto;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VentanaIng extends JFrame {

    public static void main(String[] args) {
         VentanaIng x=new VentanaIng("he");
    }

    public VentanaIng() {
        super("Mi Ventana");
    }

    public VentanaIng(String title) {
        super(title);
        Toolkit kt = Toolkit.getDefaultToolkit();
        Dimension tam = kt.getScreenSize();
        setSize((int) (tam.width / 2.5), (int) (tam.height / 2.5));
        setLocation((tam.width / 2) - (tam.width / 6), (tam.height / 2) - (tam.height / 6));

        Container c = this.getContentPane();
        c.setLayout((new BorderLayout(5, 5)));
        c.setName("Contenedor principal");
        JLabel l = new JLabel("Esto es una etiqueta");
        l.setName("Label");
        c.add(l, BorderLayout.NORTH);
        c.add(new MiPanel(), BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

}

class MiPanel extends JPanel implements ActionListener {

    JFileChooser fileChoose;

    public MiPanel() {
        JButton btn = new JButton("Abrir dialogo");
        setName("MiPanel");
        this.add(btn);
        btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        Container c = this.getParent();
        System.out.println("Contenedor padre: " + c.getName());

        System.out.println("Componetes adicionados");
        for (Component col : c.getComponents()) {
            System.out.println(col.getClass().getName() + "\t" + col.getName());
        }

    }
}
