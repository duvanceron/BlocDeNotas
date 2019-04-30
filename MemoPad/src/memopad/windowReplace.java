package memopad;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

public class windowReplace extends JDialog implements ActionListener, UndoableEditListener {
    
    private final JPanel layoutCenter, layoutEAST;
    private JLabel labelSearch, labelReplaceBy;
    private JFrame window;
    private JButton searchNext, replace, replaceAll, cancel;
    private JCheckBox capitalTiny;
    private GridBagConstraints key;
    private JTextField txtReplace, txtSearch;
    private WindowPad memopadWindow;
    private JMenuBarPad windowMenuBar;
    private windowSearch windowSearchPad;
    
    public windowReplace(WindowPad newWindow) {
        this.memopadWindow = newWindow;
        windowMenuBar = new JMenuBarPad(newWindow);
        windowSearchPad = new windowSearch(newWindow);
        this.setLayout(new BorderLayout(5, 5));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(450, 200);
        this.setTitle("Reemplazar");
        layoutCenter = new JPanel();
        layoutEAST = new JPanel();
        this.add(layoutCenter, BorderLayout.CENTER);
        this.add(layoutEAST, BorderLayout.EAST);
        setLocationRelativeTo(this.window);
        createLayoutCenter();
        createLayoutWest();
        setModal(true);
        
    }
    
    public final void createLayoutCenter() {
        layoutCenter.setLayout(new GridBagLayout());
        key = new GridBagConstraints();
        
        labelSearch = new JLabel("Buscar");
        addComponent(labelSearch, layoutCenter, 1, 1, 3, 1);
        
        txtSearch = new JTextField(13);
        addComponent(txtSearch, layoutCenter, 4, 1, 8, 1);
        txtSearch.getDocument().addUndoableEditListener(this);
        labelReplaceBy = new JLabel("Reemplazar por:");
        addComponent(labelReplaceBy, layoutCenter, 1, 3, 2, 2);
        
        txtReplace = new JTextField(13);
        addComponent(txtReplace, layoutCenter, 4, 3, 8, 1);
        
        capitalTiny = new JCheckBox("Coincidir mayúsculas y minúsculas");
        addComponent(capitalTiny, layoutCenter, 1, 5, 10, 1);
        
    }
    
    public final void createLayoutWest() {
        layoutEAST.setLayout(new BoxLayout(layoutEAST, BoxLayout.Y_AXIS));
        layoutEAST.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        searchNext = new JButton("Buscar siguiente");
        searchNext.setEnabled(false);
        searchNext.addActionListener(this);
        
        replace = new JButton("     Reemplazar    ");
        replace.setEnabled(false);
        replace.addActionListener(this);
        
        replaceAll = new JButton("Reemplazar todo");
        replaceAll.setEnabled(false);
        replaceAll.addActionListener(this);
        
        cancel = new JButton("        Cancelar       ");
        cancel.addActionListener(this);
        
        layoutEAST.add(searchNext);
        layoutEAST.add(Box.createVerticalGlue());
        layoutEAST.add(replace);
        layoutEAST.add(Box.createVerticalGlue());
        layoutEAST.add(replaceAll);
        layoutEAST.add(Box.createVerticalGlue());
        layoutEAST.add(cancel);
        layoutEAST.add(Box.createRigidArea(new Dimension(15, 15)));
        
    }
    
    private void addComponent(Component c, JPanel p, int x, int y, int w, int h) {
        
        key.gridx = x;
        key.gridy = y;
        key.gridwidth = w;
        key.gridheight = h;
        key.fill = GridBagConstraints.BOTH;
        key.insets = new Insets(4, 4, 4, 4);
        p.add(c, key);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Buscar siguiente":
                windowSearchPad.searchNext(txtSearch);
                break;
            case "     Reemplazar    ":
                System.out.println("he");
                windowSearchPad.searchMethodReplace(txtSearch, txtReplace);
                
                break;
            case "Reemplazar todo":
                replaceAll(txtSearch, txtReplace);
                break;
            case "        Cancelar       ":
                dispose();
                break;
            case "":
                break;
        }
    }
    
    @Override
    public void undoableEditHappened(UndoableEditEvent uee) {
        try {
            if (txtSearch.getText().length() < 1) {
                searchNext.setEnabled(false);
                replaceAll.setEnabled(false);
                replace.setEnabled(false);
            } else {
                searchNext.setEnabled(true);
                replaceAll.setEnabled(true);
                replace.setEnabled(true);
            }
        } catch (Exception e) {
            System.out.println("Error :" + e);
        }
    }
    
    public void replaceAll(JTextField word, JTextField wordReplaced) {
        String contentText = memopadWindow.getNote().getText();
        
        contentText = contentText.replaceAll(word.getText(), wordReplaced.getText());
        memopadWindow.getNote().setText(contentText);
        
    }
    
}
