/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editortexto;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author r.vergara
 */
class VentanaBuscar extends JDialog implements ActionListener {

    private JTextField txtBuscar, txtReemplazar;
    private JLabel Resultado;
    private JFrame window;

    public VentanaBuscar(JFrame window, boolean reemplaza) {
        super(window, true);
        setTitle("Buscar y Reemplazar");
        setModal(true);
        initComponent(reemplaza);
        setResizable(false);
        pack();
        setLocationRelativeTo(this.window);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initComponent(boolean reemplaza) {
        JPanel c = (JPanel) this.getContentPane();
        c.setLayout(new GridBagLayout());
        JButton btn = null;
        addComponent(new JLabel("Buscar"), c, 0, 0, 1, 1);
        txtBuscar = new JTextField(15);
        addComponent(txtBuscar, c, 1, 0, 5, 1);

        btn = new JButton("Buscar");
        btn.addActionListener(this);
        addComponent(btn, c, 0, 3, 2, 1);

        Resultado = new JLabel(" ");
        addComponent(Resultado, c, 0, 4, 5, 1);

        btn = new JButton("Cerrar");
        btn.addActionListener(this);
        addComponent(btn, c, 4, 3, 1, 1);

        if (reemplaza) {
            addComponent(new JLabel("Reemplazar"), c, 0, 1, 1, 1);
            txtReemplazar = new JTextField(15);
            addComponent(txtReemplazar, c, 1, 1, 5, 1);

            btn = new JButton("Reemplazar");
            btn.addActionListener(this);
            addComponent(btn, c, 2, 3, 2, 1);

        } else {

            btn = new JButton("Buscar siguiente");
            btn.addActionListener(this);
            addComponent(btn, c, 2, 3, 2, 1);
        }

    }

    private void addComponent(Component c, JPanel p, int x, int y, int w, int h) {

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = x;
        g.gridy = y;
        g.gridwidth = w;
        g.gridheight = h;
        g.fill = GridBagConstraints.BOTH;
        g.insets = new Insets(1, 1, 1, 1);
        p.add(c, g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = null;
        switch (e.getActionCommand()) {
            case "Buscar":
                msg = "Yo debo buscar en el texto lo siguiente: " + txtBuscar.getText();
                break;
            case "Buscar siguiente":
                msg = "Yo debo buscar la siguiente aparición de: " + txtBuscar.getText() + " en el texto";
                break;
            case "Reemplazar":
                msg = "Yo debo reemplazar todas las apariciones de: " + txtBuscar.getText() + " por: " + txtReemplazar.getText() + " en el texto";
                break;
            case "Cerrar":
                msg = "Yo cierro la ventana";
                this.dispose();
                break;
        }

        JOptionPane.showMessageDialog(window, msg, e.getActionCommand(), JOptionPane.INFORMATION_MESSAGE);

    }

}
