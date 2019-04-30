package memopad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.DefaultEditorKit;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

/**
 *
 * @author Dceron
 */
public class WindowPad extends JFrame implements MouseListener, UndoableEditListener, WindowListener {

    private final JMenuBarPad menu;
    private final ContextualMenu ContextMenu;
    private Container containerPad;
    private JTextArea Note;
    private JScrollPane scrollNotes;
    private final statusBar BarStatusFirst;
    private ActionMap actionsEdit;
    private Action actionCopy, actionPaste, actionCut;
    private UndoManager EditionManager;
    private Boolean CanUndo, CanRemake, changeFile;
    private windowSearch windowGoogle;

    public WindowPad(String title) {
        super(title);
        this.addWindowListener(this);

        EditionManager = new UndoManager();//Lista de ediciones que pueden dehacero rehacer
        Note = new JTextArea();
        Note.addMouseListener(this);
        Note.getDocument().addUndoableEditListener(this);
        Note.setBackground(new Color(219, 239, 231));
        changeFile = false;
        menu = new JMenuBarPad(this);
        ContextMenu = new ContextualMenu(this);
        BarStatusFirst = new statusBar(this);
        windowGoogle = new windowSearch(this);

        ContextMenu.getPopuMenu().addMouseListener(this);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(300, 200, 700, 500);
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/ImagenesMemoPad/padicon2.jpg")));
        this.setJMenuBar(menu);

        container();

        //Edit();
    }

    public final void container() {

        scrollNotes = new JScrollPane(Note);
        containerPad = getContentPane();
        containerPad.setLayout(new BorderLayout());
        containerPad.add(scrollNotes, BorderLayout.CENTER);
        containerPad.add(BarStatusFirst, BorderLayout.SOUTH);

    }

    public void Edit() {
        actionsEdit = getNote().getActionMap();
        actionCopy = actionsEdit.get(DefaultEditorKit.copyAction);
        actionPaste = actionsEdit.get(DefaultEditorKit.pasteAction);
        actionCut = actionsEdit.get(DefaultEditorKit.cutAction);

        actionCopy.putValue(Action.NAME, "copy");
        actionCopy.putValue(Action.ACCELERATOR_KEY, KeyStroke.getAWTKeyStroke('c', Event.CTRL_MASK));
        actionPaste.putValue(Action.NAME, "paste");
        actionPaste.putValue(Action.ACCELERATOR_KEY, KeyStroke.getAWTKeyStroke('v', Event.CTRL_MASK));
        actionCut.putValue(Action.NAME, "cut");
        actionCut.putValue(Action.ACCELERATOR_KEY, KeyStroke.getAWTKeyStroke('x', Event.CTRL_MASK));

    }

    public void enableopcions() {

        CanUndo = EditionManager.canUndo();
        CanRemake = EditionManager.canRedo();

        menu.getUndo().setEnabled(CanUndo);
        menu.getRedo().setEnabled(CanRemake);

        ContextMenu.getUndo().setEnabled(CanUndo);
        ContextMenu.getRedo().setEnabled(CanRemake);

        if (Note.getText().length() < 1) {
            menu.getSearch().setEnabled(false);
            menu.getSearchNext().setEnabled(false);

        } else {
            menu.getSearch().setEnabled(true);
            menu.getSearchNext().setEnabled(false);
        }

    }

    public void methodUndo() {
        try {
            EditionManager.undo();
        } catch (CannotUndoException e) {
            System.out.println("error en deshacer" + e);
        }
        enableopcions();

    }

    public void methodRedo() {

        try {
            EditionManager.redo();
        } catch (CannotUndoException e) {
            System.out.println("error en Rehacer" + e);
        }
        enableopcions();

    }

    public void actionChangeName(String title) {
        this.setTitle(title);
    }

    public JTextArea getNote() {
        return Note;
    }

    public void setNote(JTextArea Note) {
        this.Note = Note;
    }

    public UndoManager getEditionManager() {
        return EditionManager;
    }

    public void setEditionManager(UndoManager EditionManager) {
        this.EditionManager = EditionManager;
    }

    public Boolean getChangeFile() {
        return changeFile;
    }

    public void setChangeFile(Boolean changeFile) {
        this.changeFile = changeFile;
    }

    public Container getContainerPad() {
        return containerPad;
    }

    public void setContainerPad(Container containerPad) {
        this.containerPad = containerPad;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        // ContextMenu.popuMenu.setVisible(true);
        // ContextMenu.popuMenu.setLocation(me.getLocationOnScreen());
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if (me.isPopupTrigger()) {
            ContextMenu.getPopuMenu().show(me.getComponent(), me.getX(), me.getY());

        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void undoableEditHappened(UndoableEditEvent uee) {//Se llama cuando se produce un evento deshacer en el componente escuchado.
        EditionManager.addEdit(uee.getEdit());/* el cambio realizado en el área de edición se guarda en el buffer del administrador de edición */
        enableopcions();
        changeFile = true;

    }

    @Override
    public void windowOpened(WindowEvent we) {

    }

    @Override
    public void windowClosing(WindowEvent we) {
        menu.saveFileAuthomatic();
    }

    @Override
    public void windowClosed(WindowEvent we) {
    }

    @Override
    public void windowIconified(WindowEvent we) {
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    @Override
    public void windowActivated(WindowEvent we) {
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }

}
