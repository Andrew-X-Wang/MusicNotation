import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import java.util.ArrayList;

import java.awt.Stroke;

import java.io.IOException;

/**
 * Class for storing Measure information
 * 
 * @author Andrew Wang
 * @version I always forget to count
 */
public class Measure
{ 
    int dimensionX;
    int dimensionY;
    boolean used;
    
    int measureNumber;  //the # measure it is on the grid
    
    ArrayList<Chord> chordList = new ArrayList<Chord>();
    
    int beatLength; 
    int measureLength; //length in beats
    int tempo;
    
    String clef = "Treble"; //Bass or Treble, default treble
    
    String keySig;
    boolean sharps;
    ArrayList<Integer> keySigAcc = new ArrayList<Integer>();    //starting from the lowest D on the staff for treble, lowest F on staff for bass (both first note above lines)
                                                                        //starting with 0 = D/F
    Note tempNote = new Note(0, 4);
    
    
    /**
     * Constructor for objects of class Measure, called when a default Instrument is created    @TODO: not sure if this is actually useful or if it can be subbed for the other one everywhere
     */
    public Measure(int xDim, int yDim, int n, int bl, int ml, String ks)
    {
        dimensionX = xDim;
        dimensionY = yDim;
        measureNumber = n;
        
        beatLength = bl;
        measureLength = ml;
        
        keySig = ks;
        
        Chord c = new Chord(measureLength);
        c.noteList.add(new Note(0, measureLength)); //that's a problem...
        chordList.add(c);
    }
    
    /**
     * Alternate constructor for objects of class Measure, called by the openFile method in MainButtonPanel
     */
    public Measure(String keySig, String clef, int ml, int bl)
    {
        this.keySig = keySig;
        this.clef = clef;
        measureLength = ml;
        beatLength = bl;
    }
    
    /**
     * Sets the current keySicAcc(which accidenatals are to be painted in the measureEditor)
     * 
     * @param  String c - the keySig of this Measure
     * @return keySigAcc - this Measure's keySig accidental information
     * @TODO: has yet to be tested
     */
    public ArrayList<Integer> keySigScore(String c) //both returns the arraylist itself and sets the current keySigAcc, @param is the current clef (helps with bass)
    {
        if(clef.equals("treble"))
        {
            if(keySig.equals("A"))
            {
                sharps = true;
                keySigAcc.add(9); keySigAcc.add(6); keySigAcc.add(10);
            }
            else if(keySig.equals("Bb"))
            {
                sharps = false;
                keySigAcc.add(5); keySigAcc.add(8);
            }
            else if(keySig.equals("B"))
            {
                sharps = true;
                keySigAcc.add(9); keySigAcc.add(6); keySigAcc.add(10); keySigAcc.add(7); keySigAcc.add(4);
            }
            else if(keySig.equals("C")){}
            else if(keySig.equals("Cs"))
            {
                sharps = true;
                keySigAcc.add(9); keySigAcc.add(6); keySigAcc.add(10); keySigAcc.add(7); keySigAcc.add(4); keySigAcc.add(8); keySigAcc.add(5);
            }
            else if(keySig.equals("D"))
            {
                sharps = true;
                keySigAcc.add(9); keySigAcc.add(6); 
            }
            else if(keySig.equals("Eb"))
            {
                sharps = false;
                keySigAcc.add(5); keySigAcc.add(8); keySigAcc.add(4);
            }
            else if(keySig.equals("E"))
            {
                sharps = true;
                keySigAcc.add(9); keySigAcc.add(6); keySigAcc.add(10); keySigAcc.add(7);
            }
            else if(keySig.equals("F"))
            {
                sharps = false;
                keySigAcc.add(5);
            }
            else if(keySig.equals("Fs"))
            {
                sharps = true;
                keySigAcc.add(9); keySigAcc.add(6); keySigAcc.add(10); keySigAcc.add(7); keySigAcc.add(4); keySigAcc.add(8);
            }
            else if(keySig.equals("G"))
            {
                sharps = true;
                keySigAcc.add(9);
            }
            else if(keySig.equals("Ab"))
            {
                sharps = false;
                keySigAcc.add(5); keySigAcc.add(8); keySigAcc.add(4); keySigAcc.add(7);
            }
        }
        else    //bass clef
        {
            ArrayList<Integer> tempTreble = keySigScore("treble");
            for(int counter = 0; counter < tempTreble.size(); counter ++)
            {
                keySigAcc.add(tempTreble.get(counter) - 2); //because in bass clef everything shifted down 2
            }
        }
        return keySigAcc;
    }

    /**
     * Calls on the measureFrame, which then opens up the measureEditor
     * 
     * @param   none
     * @return  none
     */
    public void edit()
    {
        try
        {
            MeasureFrame mf = new MeasureFrame(this);
        }
        catch(IOException e){}
    }
}
