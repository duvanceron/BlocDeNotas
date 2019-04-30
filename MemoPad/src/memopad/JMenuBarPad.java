package memopad;

import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.text.Caret;

/**
 *
 * @author Dceron
 */
public final class JMenuBarPad extends JMenuBar implements ActionListener, MenuListener {

    private JMenu fileMenu, edition, format, see, help;
    private JMenuItem newFile, open, save, SaveAS, SetUp, print, exit, undo, redo, cut, copy, paste, delete, Search, searchNext, replace, goTo, selectAll, date;
    private JMenuItem setting, font, statusBar, seeHelp, about;
    private JFileChooser fileChoose;
    private final WindowPad window;
    FontMemoPad windowFont;
    Caret selection;
    windowSearch searchDialog;
    windowReplace replaceDialog;
    private File fileCurrent;
    private BufferedWriter bw;

    public JMenuBarPad(WindowPad ventana) {
        this.window = ventana;
        createJFileChooser();
        initComponents();
        addComponents();

    }

    public void initComponents() {
        fileMenu = new JMenu("Archivo");
        fileMenu.setMnemonic(KeyEvent.VK_1);
        edition = new JMenu("Edición");
        edition.setMnemonic(KeyEvent.VK_2);
        edition.addMenuListener(this);
        format = new JMenu("Formato");
        format.addActionListener(this);
        format.setMnemonic(KeyEvent.VK_3);
        see = new JMenu("Ver");
        see.setMnemonic(KeyEvent.VK_4);
        help = new JMenu("Ayuda");
        help.setMnemonic(KeyEvent.VK_5);

        newFile = new JMenuItem("Nuevo             Ctrl+N", KeyEvent.VK_N);
        newFile.setActionCommand("New");
        newFile.addActionListener(this);
        open = new JMenuItem("Abrir                Ctrl+A", KeyEvent.VK_A);
        open.addActionListener(this);
        open.setActionCommand("Open");
        save = new JMenuItem("Guardar          Ctrl+G", KeyEvent.VK_G);
        save.addActionListener(this);
        save.setActionCommand("Save");
        SaveAS = new JMenuItem("Guardar como..");
        SaveAS.addActionListener(this);
        SetUp = new JMenuItem("Configurar página");
        print = new JMenuItem("Imprimir          Ctrl+P", KeyEvent.VK_P);
        print.setActionCommand("Print");
        print.addActionListener(this);
        exit = new JMenuItem("Salir");
        exit.addActionListener(this);
        //-------------------------------------------------------------------//
        undo = new JMenuItem("Deshacer                   Ctrl+Z", KeyEvent.VK_Z);
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK));
        undo.setActionCommand("Undo");
        undo.setEnabled(false);
        undo.addActionListener(this);
        redo = new JMenuItem("Rehacer                   Ctrl+Z", KeyEvent.VK_Y);
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_MASK));
        redo.setActionCommand("Redo");
        redo.setEnabled(false);
        redo.addActionListener(this);
        cut = new JMenuItem("Cortar                       Ctrl+X", KeyEvent.VK_X);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cut.setActionCommand("Cut");
        cut.setEnabled(false);
        cut.addActionListener(this);
        copy = new JMenuItem("Copiar                        Ctrl+C", KeyEvent.VK_C);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copy.setActionCommand("Copy");
        copy.setEnabled(false);
        copy.addActionListener(this);
        paste = new JMenuItem("Pegar                        Ctrl+V", KeyEvent.VK_V);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK));
        paste.setActionCommand("Paste");
        paste.addActionListener(this);
        delete = new JMenuItem("Eliminar                       Supr", KeyEvent.VK_DELETE);
        delete.setActionCommand("Delete");
        delete.setEnabled(false);
        Search = new JMenuItem("Buscar                      Ctrl+B", KeyEvent.VK_B);
        Search.setActionCommand("Search");
        Search.addActionListener(this);
        Search.setEnabled(false);
        searchNext = new JMenuItem("Buscar siguiente           F3", KeyEvent.VK_F3);
        searchNext.setEnabled(false);
        searchNext.addActionListener(this);
        searchNext.setActionCommand("SearchNext");
        replace = new JMenuItem("Remplazar               Ctrl+R", KeyEvent.VK_R);
        replace.addActionListener(this);
        replace.setActionCommand("Replace");
        goTo = new JMenuItem("Ir a...                          Ctrl+T", KeyEvent.VK_T);
        selectAll = new JMenuItem("Selecionar todo      Ctrl+E", KeyEvent.VK_E);
        date = new JMenuItem("Hora y fecha                  F5", KeyEvent.VK_F5);
        //-------------------------------------------------------------------//
        setting = new JMenuItem("Ajuste de línea");
        font = new JMenuItem("Fuente...", KeyEvent.VK_F);
        font.addActionListener(this);

        statusBar = new JCheckBoxMenuItem("Barra de estado");
        statusBar.setSelected(false);
        statusBar.addActionListener(this);
        seeHelp = new JMenuItem("Ver la ayuda");
        about = new JMenuItem("Acerca del bloc de notas");
        about.setActionCommand("About");
        about.addActionListener(this);

    }

    public void addComponents() {
        this.add(fileMenu);
        // this.add(Box.createHorizontalStrut(6));    //añade espacios entre cada menú strunt=puntal
        this.add(edition);
        this.add(format);
        this.add(see);
        this.add(help);

        fileMenu.add(newFile);
        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.add(SaveAS);
        fileMenu.addSeparator();
        fileMenu.add(SetUp);
        fileMenu.add(print);
        fileMenu.addSeparator();
        fileMenu.add(exit);
        //--------------------------------------------------------------------//
        edition.add(undo);
        edition.add(redo);
        edition.addSeparator();
        edition.add(cut);
        edition.add(copy);
        edition.add(paste);
        edition.add(delete);
        edition.addSeparator();
        edition.add(Search);
        edition.add(searchNext);
        edition.add(replace);
        edition.add(goTo);
        edition.addSeparator();
        edition.add(selectAll);
        edition.add(date);

        format.add(setting);
        format.add(font);

        see.add(statusBar);

        help.add(seeHelp);
        help.add(about);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "New":
                newFile();
                break;
            case "Open":
                openFile();
//                String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
//                System.out.println(Arrays.toString(fontNames));

                break;
            case "Save":
                actionSaveFile();
                break;
            case "Guardar como..":
                actionSaveFileAs();
                break;
            case "Configurar página":
                break;
            case "Print":
                break;
            case "Salir":
                System.exit(0);
                break;
            case "Fuente...":
                windowFont = new FontMemoPad(window);
                windowFont.setVisible(true);
                break;
            case "Undo":
                window.methodUndo();
                break;
            case "Redo":
                window.methodRedo();
                break;
            case "Cut":
                window.getNote().cut();
                break;
            case "Copy":
                window.getNote().copy();
                break;
            case "Paste":
                window.getNote().paste();
                break;
            case "Delete":
                window.getNote();

                break;
            case "Search":
                searchDialog = new windowSearch(window);
                searchDialog.setVisible(true);
                break;
            case "SearchNext":
                searchDialog = new windowSearch(window);
                searchDialog.setVisible(true);
                break;
            case "Replace":
                replaceDialog = new windowReplace(window);
                replaceDialog.setVisible(true);
                break;
            case "About":
                JOptionPane.showMessageDialog(this,
                        "Creado por Duvan Ceron Muñoz",
                        "Acerca de",
                        JOptionPane.INFORMATION_MESSAGE);

                break;
        }
    }

    public void newFile() {
        if (window.getChangeFile() == true) {
            int option = JOptionPane.showConfirmDialog(window.getNote(), "¿Desea guardar los cambios?");

            switch (option) {
                case JOptionPane.YES_OPTION:
                    actionSaveFileAs();
                    break;
                case JOptionPane.CANCEL_OPTION:
                    return;// continue con la operación si le da cancelar. 

            }
        }
        window.getEditionManager().die();
        window.enableopcions();
        setFileCurrent(null);
        window.actionChangeName("Sin título: Bloc de notas");

    }

    public void openFile() {
        if (JFileChooser.APPROVE_OPTION == fileChoose.showOpenDialog(null)) { //fileChoose.showOpenDialog(null)=> abrimos la ventana y guardamos la opcion seleccionada por el usuario
            File file = fileChoose.getSelectedFile();//   Seleccionamos el fichero
            FileReader reader;//Nos permite leer archivos
            try {
                reader = new FileReader(file);
                BufferedReader bfReader = new BufferedReader(reader);//tienen la misma función que FileReader y FileWriter, leer y escribir en ficheros,
                String lineFile;//lineaFichero                                                    // pero BufferedReader y BufferedWriter optimizan estas funciones.
                StringBuilder contentFile = new StringBuilder();
                while ((lineFile = bfReader.readLine()) != null) {
                    contentFile.append(lineFile);
                    contentFile.append("\n");

                }
                window.getNote().setText(contentFile.toString());
                window.setTitle(file.getName() + " :Bloc de notas");
                setFileCurrent(file);
                window.enableopcions();
                window.setChangeFile(false);
            } catch (IOException e) {
            }

        }
    }

    public void actionSaveFile() {
        if (getFileCurrent() == null) {
            actionSaveFileAs();
        } else if (window.getChangeFile() == true) {
            try {//Abre un flujo de datos al documento actual
                bw = new BufferedWriter(new FileWriter(fileCurrent));//Construye un objeto fileWritter dado un objeto file
                bw.write(window.getNote().getText());//Escribe un string
                bw.close();
                window.setChangeFile(false);

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), e.toString(), JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void actionSaveFileAs() {

        JFileChooser fc = createJFileChooser();
        int option = fc.showSaveDialog(window);

        switch (option) {
            case JFileChooser.APPROVE_OPTION:
                File file = fc.getSelectedFile();
                try {
                    bw = new BufferedWriter(new FileWriter(file));//Construye un objeto fileWritter dado un objeto file
                    bw.write(window.getNote().getText());//Escribe un string
                    // window.getNote().write(bw);
                    bw.close();
                    window.setChangeFile(false);
                    setFileCurrent(file);
                    window.setTitle(file.getName() + " :Bloc de notas");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), e.toString(), JOptionPane.ERROR_MESSAGE);

                }
                break;
        }

    }

    public void saveFileAuthomatic() {
        try {

            File file = new File(System.getProperty("user.home") + "\\Documents\\" + searchWord() + ".txt");
            //File q = new File("C:\\Users\\Dceron\\Documents\\" + searchWord() + ".txt");

            bw = new BufferedWriter(new FileWriter(file));//Construye un objeto fileWritter dado un objeto file
            bw.write(window.getNote().getText());
            bw.close();
            setFileCurrent(file);
            window.setTitle(file.getName());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), e.toString(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public String searchWord() {
        String word;
        String textWithoutSpace;
        //textWithoutSpace=window.getNote().getText().replaceAll("^\\s*",""); 
        textWithoutSpace = window.getNote().getText().trim();
        System.out.println("" + textWithoutSpace);
        String[] arrayText = textWithoutSpace.split(" ");
        word = arrayText[0];
        return word;
    }

    public JFileChooser createJFileChooser() {
        fileChoose = new JFileChooser();//Creamos el objeto filechooser
        fileChoose.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChoose.setDialogTitle("Bloc de notas: Elige archivo");
        return fileChoose;
    }

    @Override
    public void menuSelected(MenuEvent me) {
        selection = window.getNote().getCaret();//Obtiene el cursor que permite la navegación orientada al texto sobre la vista.
        printEcentInfo("seleccciono", me);
        if (selection.getDot() == selection.getMark()) { //getDot Recupera posición actual del cursor
            cut.setEnabled(false);                       // getMark Obtiene la posición actual de la marca.                       
            copy.setEnabled(false);
            delete.setEnabled(false);
        } else {
            cut.setEnabled(true);
            copy.setEnabled(true);
            delete.setEnabled(true);
        }

    }

    @Override
    public void menuDeselected(MenuEvent me) {
    }

    @Override
    public void menuCanceled(MenuEvent me) {
    }

    private void printEcentInfo(String s, MenuEvent event) {
        JMenu menu = (JMenu) event.getSource();

        System.out.println(s + ": " + menu.getText());

    }

    public JMenuItem getUndo() {
        return undo;
    }

    public void setUndo(JMenuItem undo) {
        this.undo = undo;
    }

    public JMenuItem getRedo() {
        return redo;
    }

    public void setRedo(JMenuItem redo) {
        this.redo = redo;
    }

    public JMenuItem getSearch() {
        return Search;
    }

    public void setSearch(JMenuItem Search) {
        this.Search = Search;
    }

    public JMenuItem getSearchNext() {
        return searchNext;
    }

    public void setSearchNext(JMenuItem searchNext) {
        this.searchNext = searchNext;
    }

    public File getFileCurrent() {
        return fileCurrent;
    }

    public void setFileCurrent(File fileCurrent) {
        this.fileCurrent = fileCurrent;
    }

}
