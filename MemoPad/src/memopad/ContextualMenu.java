package memopad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

/**
 *
 * @author Dceron
 */
public class ContextualMenu extends JDialog implements ActionListener {

    private JPopupMenu popuMenu;
    private JMenu insertCharacter;
    private JMenuItem undo, cut, copy, paste, delete, selectAll, readerRightLeft, showCharacter, openIme, reconversion, redo;
    private WindowPad windowPadHere;

    public ContextualMenu(WindowPad window) {
        super();
        this.windowPadHere = window;
        initComponents();
        addComponents();
        setModal(true);

    }

    public final void initComponents() {
        popuMenu = new JPopupMenu();
        undo = new JMenuItem("Deshacer",KeyEvent.VK_Z);
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK));
        undo.setEnabled(false);
        undo.addActionListener(this);
        redo = new JMenuItem("Rehacer");
        redo.setEnabled(false);
        redo.addActionListener(this);
        cut = new JMenuItem("Cortar");
        cut.setEnabled(false);
        copy = new JMenuItem("Copiar");
        copy.setEnabled(false);
        paste = new JMenuItem("Pegar");
        delete = new JMenuItem("Eliminar");
        delete.setEnabled(false);
        selectAll = new JMenuItem("Seleccionar todo");
        readerRightLeft = new JMenuItem("Lectura de derecha a izquierda");
        showCharacter = new JMenuItem("Mostrar caracteres de control Unicode");
        insertCharacter = new JMenu("Insertar carácter");
        openIme = new JMenuItem("Abrir IME ");
        reconversion = new JMenuItem("Reconversion");
        //reconversion=new JRadioButtonMenuItem("Reconversión");

    }

    public final void addComponents() {
        popuMenu.add(undo);
        popuMenu.add(redo);
        popuMenu.addSeparator();
        popuMenu.add(cut);
        popuMenu.add(copy);
        popuMenu.add(paste);
        popuMenu.add(delete);
        popuMenu.addSeparator();
        popuMenu.add(selectAll);
        popuMenu.addSeparator();
        popuMenu.add(readerRightLeft);
        popuMenu.add(showCharacter);
        popuMenu.add(insertCharacter);
        popuMenu.addSeparator();
        popuMenu.add(openIme);
        popuMenu.add(reconversion);

    }

    public JMenuItem getUndo() {
        return undo;
    }

    public void setUndo(JMenuItem undo) {
        this.undo = undo;
    }

    public JPopupMenu getPopuMenu() {
        return popuMenu;
    }

    public void setPopuMenu(JPopupMenu popuMenu) {
        this.popuMenu = popuMenu;
    }

    public JMenuItem getRedo() {
        return redo;
    }

    public void setRedo(JMenuItem redo) {
        this.redo = redo;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Deshacer":
                windowPadHere.methodUndo();
                break;

            case "Rehacer":
                windowPadHere.methodRedo();
                break;
        }
    }

}
