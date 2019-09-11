import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import java.util.ArrayList;

/**
 * Just stores the length of the chord, nothing bit
 * 
 * @author Andrew Wang
 * @version 1.5
 */
public class Chord
{
    // instance variables - replace the example below with your own
    int lengthInBeats; //in beats
    ArrayList<Note> noteList = new ArrayList<Note>();
    

    /**
     * Constructor for objects of class Chord
     */
    public Chord(int l)
    {
        lengthInBeats = l;
    }
    
    /**
     * Play; connects with the PlayMidiNote class to play the correct tones
     * 
     * @param - int tempo - so it can be passed onto PlayMidiNote
     * @return   void
     */
    public void play(int tempo)
    {
        PlayMidiNote pmn = new PlayMidiNote();
        for(int counter = 0; counter < noteList.size(); counter ++)
        {
            pmn.playSong(noteList.get(counter).keyNumber, noteList.get(counter).noteLength, tempo);
        }
        
    }
}
