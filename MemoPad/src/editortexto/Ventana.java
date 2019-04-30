/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editortexto;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import sun.awt.geom.AreaOp;

/**
 *
 * @author r.vergara
 */
public class Ventana extends JFrame implements ActionListener {

    JComboBox ComboFuentes, ComboTam;
    JRadioButton Negrita, Cursiva;
    JTextArea Texto;

    public static void main(String[] args) {
        new Ventana();
    }

    public Ventana() {
        super("Fuentes");
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container principal = this.getContentPane();
        principal.setLayout(new BorderLayout(5, 5));

        String[] fuente = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        ComboFuentes = new JComboBox(fuente);
        Negrita = new JRadioButton("Negrita");
        Cursiva = new JRadioButton("Cursiva");
      
        ButtonGroup g = new ButtonGroup();
        g.add(Negrita);
        g.add(Cursiva);

        ComboTam = new JComboBox(new Object[]{8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40});

        JPanel norte = new JPanel(new FlowLayout(FlowLayout.LEFT));
        norte.add(ComboFuentes);
        norte.add(Negrita);
        norte.add(Cursiva);
        norte.add(ComboTam);
        JButton btn = new JButton("Cambiar");
        btn.addActionListener(this);
        norte.add(btn);

        principal.add(norte, BorderLayout.NORTH);

        Texto = new JTextArea("hola mundo");
        Texto.setEditable(false);
        principal.add(Texto, BorderLayout.CENTER);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String familia_fuente = ComboFuentes.getSelectedItem().toString();
        int tamano = Integer.parseInt(ComboTam.getSelectedItem().toString());
        int estilo = Negrita.isSelected() ? Font.BOLD : Cursiva.isSelected() ? Font.ITALIC : Font.PLAIN;

        Font fuente = new Font(familia_fuente, estilo, tamano);
        Texto.setFont(fuente);
        this.dispose();
    }

}
