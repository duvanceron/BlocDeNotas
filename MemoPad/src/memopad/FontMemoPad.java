package memopad;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;

public class FontMemoPad extends JDialog implements ActionListener {
    
    private JComboBox font, styleFont, size;
    private final String[] fontNames;
    private final String[] nameStyle;
    private final Object fontSize[];
    private JPanel north, south;
    private JButton accept, cancel;
    private final WindowPad window;
    
    public FontMemoPad(WindowPad ventana) {
        super();
        this.window = ventana;
        this.setTitle("Fuente");
        fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontSize = new Object[]{8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72};
        nameStyle = new String[]{"Normal", "Cursiva", "Negrita", "cursiva Negrita"};
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setModal(true);
        initComponents();
        addComponents();
        
    }
    
    public void initComponents() {
        font = new JComboBox(fontNames);
        styleFont = new JComboBox(nameStyle);
        size = new JComboBox(fontSize);
        north = new JPanel(new FlowLayout(1, 10, 10));
        south = new JPanel(new FlowLayout(2, 10, 10));
        accept = new JButton("aceptar");
        accept.addActionListener(this);
        cancel = new JButton("cancelar");
        cancel.addActionListener(this);
        
    }
    
    public void addComponents() {
        north.add(font);
        north.add(styleFont);
        north.add(size);
        south.add(accept);
        south.add(cancel);
        this.add(north, BorderLayout.NORTH);
        this.add(south, BorderLayout.SOUTH);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "aceptar":
                String family_font = font.getSelectedItem().toString();
                int sizeFinal = Integer.parseInt(size.getSelectedItem().toString());
                
                Font fuente = new Font(family_font, Font.BOLD, sizeFinal);
                window.getNote().setFont(fuente);
                this.dispose();
                break;
            
        }
        
    }
    
}
