import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import java.util.ArrayList;

import java.awt.Stroke;

import java.io.IOException;

/**
 * Displays the measure editing screen, where eventually users will be able to input notes by clicking on the screen with the desired selected notes/rests
 * 
 * @author Andrew Wang
 * @version limit as x approaches infinity of y = x^(1/2)   (you can take that as a large value, or that it doesn't exist b/c I didn't keep count)
 */
public class MeasureButtonPanel extends JPanel implements ActionListener
{
    int panelWidth = 1400;
    int panelHeight = 100;
    
    JMenuBar menuBar = new JMenuBar();
    
    JMenu noteMenu = new JMenu("Note Input:");  //this menu allows users to select which note or rest they'd like to input
    JMenuItem note16 = new JMenuItem("note16"); 
        JMenuItem note8 = new JMenuItem("note8"); JMenuItem note4 = new JMenuItem("note4"); JMenuItem note2 = new JMenuItem("note2"); JMenuItem note1 = new JMenuItem("note1"); /**JMenuItem dNote16 = new JMenuItem("dNote16");*/ JMenuItem dNote8 = new JMenuItem("dNote8"); JMenuItem dNote4 = new JMenuItem("dNote4"); JMenuItem dNote2 = new JMenuItem("dNote2"); JMenuItem dNote1 = new JMenuItem("dNote1"); JMenuItem rest16 = new JMenuItem("rest16"); JMenuItem rest8 = new JMenuItem("rest8"); JMenuItem rest4 = new JMenuItem("rest4"); JMenuItem rest2 = new JMenuItem("rest2"); JMenuItem rest1 = new JMenuItem("rest1");

    JMenu keySigMenu = new JMenu("Key Signature:");     //this menu allows users to alter the keySignature of each particular measure
        JMenuItem A = new JMenuItem("A"); JMenuItem Bb = new JMenuItem("Bb"); JMenuItem B = new JMenuItem("B"); JMenuItem C = new JMenuItem("C"); JMenuItem Cs = new JMenuItem("Cs"); JMenuItem D = new JMenuItem("D"); JMenuItem Eb = new JMenuItem("Eb"); JMenuItem E = new JMenuItem("E"); JMenuItem F = new JMenuItem("F"); JMenuItem Fs = new JMenuItem("Fs"); JMenuItem G = new JMenuItem("G"); JMenuItem Ab = new JMenuItem("Ab");
        
    JLabel clef = new JLabel("Clef");
    JButton Treble = new JButton("Treble");     //these two buttons allow the user to alter the clef the measure will be displayed in
    JButton Bass = new JButton("Bass");
    
    Measure measure;
    MeasureEditor me;
        
    /**
     * Constructor for objects of class MeasureButtonPanel, initializes all of the menu items/ buttons
     */
    public MeasureButtonPanel(Measure m) throws IOException
    {
        measure = m;
        me = new MeasureEditor(measure);
        
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(Color.BLACK);
        
        this.add(menuBar);
        
        menuBar.add(noteMenu);
        menuBar.add(keySigMenu);
        
        noteMenu.add(note16);
        noteMenu.add(note8);
        noteMenu.add(note4);
        noteMenu.add(note2);
        noteMenu.add(note1);
        //noteMenu.add(dNote16);
        noteMenu.add(dNote8);
        noteMenu.add(dNote4);
        noteMenu.add(dNote2);
        noteMenu.add(dNote1);
        noteMenu.add(rest16);
        noteMenu.add(rest8);
        noteMenu.add(rest4);
        noteMenu.add(rest2);
        noteMenu.add(rest1);
        
        keySigMenu.add(A);
        keySigMenu.add(Bb);
        keySigMenu.add(B);
        keySigMenu.add(C);
        keySigMenu.add(Cs);
        keySigMenu.add(D);
        keySigMenu.add(Eb);
        keySigMenu.add(E);
        keySigMenu.add(F);
        keySigMenu.add(Fs);
        keySigMenu.add(G);
        keySigMenu.add(Ab);
        
        note16.addActionListener(this); 
        note8.addActionListener(this); 
        note4.addActionListener(this); 
        note2.addActionListener(this); 
        note1.addActionListener(this); 
        //dNote16.addActionListener(this); 
        dNote8.addActionListener(this); 
        dNote4.addActionListener(this); 
        dNote2.addActionListener(this); 
        dNote1.addActionListener(this); 
        rest16.addActionListener(this); 
        rest8.addActionListener(this); 
        rest4.addActionListener(this); 
        rest2.addActionListener(this); 
        rest1.addActionListener(this); 
        
        Bass.addActionListener(this); 
        Treble.addActionListener(this); 
        this.add(Bass);
        this.add(Treble);
        
        A.addActionListener(this); 
        Bb.addActionListener(this); 
        B.addActionListener(this); 
        C.addActionListener(this); 
        Cs.addActionListener(this); 
        D.addActionListener(this); 
        Eb.addActionListener(this); 
        E.addActionListener(this); 
        F.addActionListener(this); 
        Fs.addActionListener(this); 
        G.addActionListener(this); 
        Ab.addActionListener(this); 
        
    }

    /**
     * ActionPerformed just deals with what happens when you select the menu items and such
     * 
     * @param  ActionEvent e: some button/ menuItem activity
     * @return    void
     */
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == note16)               //first part is for the note/rest selection
        {
            measure.tempNote = new Note(1, 1);
        }
        else if(e.getSource() == note8)
        {
            measure.tempNote = new Note(1, 2);
        }
        else if(e.getSource() == note4)
        {
            measure.tempNote = new Note(1, 4);
        }
        else if(e.getSource() == note2)
        {
            measure.tempNote = new Note(1, 8);
        }
        else if(e.getSource() == note1)
        {
            measure.tempNote = new Note(1, 16);
        }
        /**else if(e.getSource() == dNote16)
        {
            measure.tempNote = new Note();
        }*/
        else if(e.getSource() == dNote8)
        {
            measure.tempNote = new Note(1, 3);
        }
        else if(e.getSource() == dNote4)
        {
            measure.tempNote = new Note(1, 6);
        }
        else if(e.getSource() == dNote2)
        {
            measure.tempNote = new Note(1, 12);
        }
        else if(e.getSource() == dNote1)
        {
            measure.tempNote = new Note(1, 24);
        }
        else if(e.getSource() == rest16)
        {
            measure.tempNote = new Note(0, 1);
        }
        else if(e.getSource() == rest8)
        {
            measure.tempNote = new Note(0, 2);
        }
        else if(e.getSource() == rest4)
        {
            measure.tempNote = new Note(0, 4);
        }
        else if(e.getSource() == rest2)
        {
            measure.tempNote = new Note(0, 8);
        }
        else if(e.getSource() == rest1)
        {
            measure.tempNote = new Note(0, 16);
        }
        
        if(e.getSource() == Treble)                //this part is for clef selection
        {
            measure.clef = "Treble";
        }
        else if(e.getSource() == Bass)
        {
            measure.clef = "Bass";
        }
        
        if(e.getSource() == A)                     //this part is for key signature selection
        {
            measure.keySig = "A";
        }
        else if(e.getSource() == Bb)
        {
            measure.keySig = "Bb";
        }
        else if(e.getSource() == B)
        {
            measure.keySig = "C";
        }
        else if(e.getSource() == C)
        {
            measure.keySig = "Cs";
        }
        else if(e.getSource() == Cs)
        {
            measure.keySig = "D";
        }
        else if(e.getSource() == D)
        {
            measure.keySig = "Eb";
        }
        else if(e.getSource() == Eb)
        {
            measure.keySig = "E";
        }
        else if(e.getSource() == E)
        {
            measure.keySig = "F";
        }
        else if(e.getSource() == F)
        {
            measure.keySig = "Fs";
        }
        else if(e.getSource() == Fs)
        {
            measure.keySig = "G";
        }
        else if(e.getSource() == G)
        {
            measure.keySig = "Ab";
        }
        else if(e.getSource() == Ab)
        {
            measure.keySig = "Ab";
        }
    }
}
