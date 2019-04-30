package memopad;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

/**
 *
 * @author Dceron
 */
public class windowSearch extends JDialog implements ActionListener, UndoableEditListener {

    private JPanel layoutCenter, layoutEAST;
    private JTextField inputSearch;
    private JLabel searchLabel, direction;
    private JButton cancel, search, searchNext;
    private JFrame window;
    private JCheckBox CapitalTiny;
    private GridBagConstraints key;
    private JRadioButton up, down;
    private WindowPad windowPad;
    private String lastSearch;

    public windowSearch(WindowPad newWindow) {
        this.windowPad = newWindow;
        this.setLayout(new BorderLayout(5, 5));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600, 170);
        this.setTitle("Buscar");
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
        searchLabel = new JLabel("Buscar");
        addComponent(searchLabel, layoutCenter, 1, 1, 5, 1);

        inputSearch = new JTextField(15);
        addComponent(inputSearch, layoutCenter, 6, 1, 20, 1);
        inputSearch.getDocument().addUndoableEditListener(this);
        direction = new JLabel("Direccion");
        addComponent(direction, layoutCenter, 20, 3, 1, 1);

        CapitalTiny = new JCheckBox("Coincidir mayúsculas y minúsculas");
        addComponent(CapitalTiny, layoutCenter, 1, 5, 10, 1);

        up = new JRadioButton("subir");
        addComponent(up, layoutCenter, 15, 5, 5, 1);
        down = new JRadioButton("bajar");
        addComponent(down, layoutCenter, 21, 5, 10, 1);

    }

    public final void createLayoutWest() {
        layoutEAST.setLayout(new BoxLayout(layoutEAST, BoxLayout.Y_AXIS));
        layoutEAST.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        search = new JButton("         Buscar         ");
        search.setActionCommand("Search");
        search.addActionListener(this);
        search.setEnabled(false);
        search.setSize(30, 10);
        searchNext = new JButton("Buscar siguiente");
        searchNext.setEnabled(false);
        searchNext.addActionListener(this);
        cancel = new JButton("       Cancelar        ");
        cancel.setActionCommand("exit");
        cancel.addActionListener(this);
        layoutEAST.add(search);
        layoutEAST.add(Box.createVerticalGlue());
        layoutEAST.add(searchNext);
        layoutEAST.add(Box.createVerticalGlue());
        layoutEAST.add(cancel);
        layoutEAST.add(Box.createRigidArea(new Dimension(20, 20)));
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
    public void actionPerformed(ActionEvent a) {
        switch (a.getActionCommand()) {
            case "Search":
                if (inputSearch.getText() != null) {
                    searchMethod(inputSearch);
                }
                break;
            case "Buscar siguiente":
                searchNext(inputSearch);
                break;
            case "exit":
                dispose();
                break;
        }
    }

    public void searchMethodStatic() {
        String contentText = windowPad.getNote().getText();
        int pos = contentText.indexOf(inputSearch.getText());//Se debe aplicar el metodo a la cadena de texto pasando como parametro
        //el texto a buscar    
        if (pos >= 0) { //si la posicion es mayor o igual 0 significa que la palabra se encuentra
            windowPad.getNote().select(pos, pos + inputSearch.getText().length());

        } else {

            JOptionPane.showMessageDialog(null, "No se encontro " + inputSearch.getText(), "Bloc de notas", JOptionPane.INFORMATION_MESSAGE);
        }
        lastSearch = inputSearch.getText();

    }

    public void searchNext(JTextField input) {

        if (lastSearch == null) {
            searchMethod(input);
        } else {
            String contentTextNext = windowPad.getNote().getText();
            int pos = windowPad.getNote().getCaretPosition(); //posicion del cursor.
            pos = contentTextNext.indexOf(lastSearch, pos);// se busca la palabra apartir de la posición del cursor

            if (pos >= 0) {
                windowPad.getNote().select(pos, pos + lastSearch.length());// Resalta la palabra

            } else {
                JOptionPane.showMessageDialog(null, "No se encontro " + inputSearch.getText(), "Bloc de notas", JOptionPane.INFORMATION_MESSAGE);

            }

        }

    }

    public void searchMethod(JTextField inputS) {
        System.out.println("aqui");
        try {
            String contentText = windowPad.getNote().getText();
            int pos = contentText.indexOf(inputS.getText());//Se debe aplicar el metodo a la cadena de texto pasando como parametro
            //el texto a buscar    
            if (pos >= 0) { //si la posicion es mayor o igual 0 significa que la palabra se encuentra
                windowPad.getNote().select(pos, pos + inputS.getText().length());
            } else {

                JOptionPane.showMessageDialog(null, "No se encontro " + inputS.getText(), "Bloc de notas", JOptionPane.INFORMATION_MESSAGE);
            }
            lastSearch = inputS.getText();

        } catch (HeadlessException e) {
            System.out.println("Error" + e);
        }
    }
    
    
    public void searchMethodReplace(JTextField inputS, JTextField replace) {
        System.out.println("aqui");
        try {
            String contentText = windowPad.getNote().getText();
            int pos = contentText.indexOf(inputS.getText());//Se debe aplicar el metodo a la cadena de texto pasando como parametro
            //el texto a buscar    
            if (pos >= 0) { //si la posicion es mayor o igual 0 significa que la palabra se encuentra
                windowPad.getNote().select(pos, pos + inputS.getText().length());
                contentText=contentText.replaceFirst(inputS.getText(), replace.getText());
                windowPad.getNote().setText(contentText);
            } else {

                JOptionPane.showMessageDialog(null, "No se encontro " + inputS.getText(), "Bloc de notas", JOptionPane.INFORMATION_MESSAGE);
            }
            lastSearch = inputS.getText();

        } catch (HeadlessException e) {
            System.out.println("Error" + e);
        }
    }
    
    public JButton getSearch() {
        return search;
    }

    public void setSearch(JButton search) {
        this.search = search;
    }

    public JButton getSearchNext() {
        return searchNext;
    }

    public void setSearchNext(JButton searchNext) {
        this.searchNext = searchNext;
    }

    public JTextField getInputSearch() {
        return inputSearch;
    }

    public void setInputSearch(JTextField inputSearch) {
        this.inputSearch = inputSearch;
    }

    @Override
    public void undoableEditHappened(UndoableEditEvent uee) {
        if (inputSearch.getText().length() < 1) {
            search.setEnabled(false);
            searchNext.setEnabled(false);
        } else {
            search.setEnabled(true);
            searchNext.setEnabled(true);
        }
    }

}
