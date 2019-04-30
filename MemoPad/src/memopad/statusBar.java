/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memopad;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import javax.swing.text.BadLocationException;

public class statusBar extends JPanel implements CaretListener {

    private JLabel fileSize, cursorPosition;
    private final Dimension minSize, prefSize, maxSize;
    private int caretPosition, posX, posY;
    private final WindowPad window;

    public statusBar(WindowPad newWindow) {
        this.window = newWindow;
        window.getNote().addCaretListener(this);
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(5), BorderFactory.createEmptyBorder(6, 6, 6, 6)));
        fileSize = new JLabel("");
        cursorPosition = new JLabel("Linea 0, Columna 0");
        //cursorPosition.setFont(new Font(Font.SANS_SERIF, 40 , Font.BOLD));
        minSize = new Dimension(5, 10);
        prefSize = new Dimension(5, 10);
        maxSize = new Dimension(Short.MAX_VALUE, 10);

        //this.add(Box.createHorizontalStrut(200));
        //this.add(Box.createRigidArea(new Dimension(200, 0)));
        this.add(new Box.Filler(minSize, prefSize, maxSize));
        this.add(fileSize);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(cursorPosition);
        /*Point punto = MouseInfo.getPointerInfo().getLocation();
        int x = punto.x;
        System.out.println("punto "+x);
        int y = punto.y;
        System.out.println("punto "+y);*/
    }

    public void caretPositionMethod(CaretEvent e) throws BadLocationException {

        caretPosition = window.getNote().getCaretPosition();//obtiene la posicion del cursor con respecto al inicio del JTextArea
        posY = window.getNote().getLineOfOffset(caretPosition);//Determina el desplazamiento del final de la línea dada. este caso carePosition, se obtiene el valor de la línea actual (se cuenta desde 0)
        posX = caretPosition - window.getNote().getLineStartOffset(posY);  /** a la posición del cursor se le resta la posición del inicio de la línea para  determinar el valor de la columna actual */
        cursorPosition.setText("Linea " + posX + ", Columna " + posY);
           
    }

    @Override
    public void caretUpdate(CaretEvent ce) {
        try {
           
            caretPositionMethod(ce);
        } catch (BadLocationException ex) {
            Logger.getLogger(WindowPad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
