import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import java.util.*;

import java.awt.Stroke;

import java.io.IOException;

import java.io.*;
/**
 * Controls the highest level; hopefully eventually gets to the point where it can play back the score, add Instruments, open scores from text files, and write scores to text files
 * 
 * @author Andrew Wang
 * @version who's counting?
 */
public class MainButtonPanel extends JPanel implements ActionListener
{
    int panelWidth = 1400;
    int panelHeight = 100;
    
    JLabel tempoLabel = new JLabel("Tempo: ");
    int tempo;
    int measureLength;
    int beatLength;
    
    JButton play = new JButton("Play");
    JButton addInstrument = new JButton("Add Instrument");
    JButton openFile = new JButton("Open File");
    JButton writeToFile = new JButton("Write to File");
    
    ArrayList<Instrument> iList = new ArrayList<Instrument>();
        
    /**
     * Constructor for objects of class MainButtonPanel
     */
    public MainButtonPanel() throws IOException
    {
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(Color.WHITE);
        
        this.add(play);
        this.add(addInstrument);
        this.add(openFile);
        this.add(writeToFile);
        this.add(tempoLabel);
        
        play.addActionListener(this);
        addInstrument.addActionListener(this);
        openFile.addActionListener(this);
        writeToFile.addActionListener(this);
        
    }

    /**
     * What pressing each button does
     * 
     * @param  ActionEvent e    Some button being pushed
     * @return     void
     */
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == play)   //if the play button is hit, go through the Instruments and run? then... Might change to regular play method
        {
            for(int counter = 0; counter < iList.size(); counter ++)
            {
                iList.get(counter).run();
            }
        }
        else if(e.getSource() == addInstrument) //adds an instrument in a few instrumentFrame with the # instrument it is
        {
            try
            {
                Instrument i = new Instrument(this, "Instrument Number " + (iList.size() + 1));   //where iList.size() + 1 is the number instrument it is
                iList.add(i);
            }
            catch(Exception except){}
        }
        else if(e.getSource() == openFile)      //reads a ext file (currently somehow unable to get input from the keyboard for file names?) and converts to Score
        {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter the file you would like to read from (Format: filename.txt)");
            String input = keyboard.nextLine();
            try     //Ideal format is planned out in "Scores.txt" text file in the project folder
            {               //First tempo, measureLength, and beatLength printed, then each Instrument is separated by "Instrument:" and "End". Each line is a measure, starts off with leySig and clef, then prints each chord separated by "/" with notes separated by ","

                FileReader fr = new FileReader(input);
                //FileReader fr = new FileReader("Score.txt");
                Scanner scan = new Scanner(fr);
                
                tempo = Integer.parseInt(scan.nextLine()); //tempo, beats per measure, beat length
                measureLength = Integer.parseInt(scan.nextLine());
                beatLength = Integer.parseInt(scan.nextLine());
                while(scan.hasNext())
                {
                    if(scan.nextLine().equals("Instrument:"))
                    {
                        Instrument newInstrument = new Instrument(this, "Instrument Number " + (iList.size() + 1));
                        ArrayList<Measure> tempMList = new ArrayList<Measure>();
                        System.out.println("Got Here");
                        while(!(scan.nextLine().equals("End")))
                        {
                            String measure = scan.nextLine();
                            String[] chords = measure.split("/"); 
                            Measure newMeasure = new Measure(chords[0], chords[1], measureLength, beatLength);  //keySignature, clef
                            for(int counter = 3; counter < chords.length; counter ++)
                            {
                                String[] notes = chords[counter].split(",");
                                Chord newChord = new Chord(Integer.parseInt(notes[0]));    //note/chordLength
                                for(int counter2 = 1; counter2 < notes.length; counter2 ++)
                                {
                                    Note newNote = new Note(Integer.parseInt(notes[counter]));  //keyNote
                                    newChord.noteList.add(newNote);
                                    System.out.println("Note Added");
                                }
                                newMeasure.chordList.add(newChord);
                                System.out.println("Chord Added");
                            }
                            //newInstrument.measureList.add(newMeasure);
                            tempMList.add(newMeasure);
                            System.out.println("Measure added");
                        }
                        for(int counter = 0; counter < tempMList.size(); counter ++)
                        {
                            newInstrument.measureGrid[counter / newInstrument.gridHeight][counter % newInstrument.gridHeight - 1] = tempMList.get(counter);
                        }
                        
                        iList.add(newInstrument);
                        System.out.println("\f");
                        System.out.println("Instrument Added");
                    }
                }
            }
            catch(Exception except){}
        }
        else if(e.getSource() == writeToFile)       //ideally writes to file in the same manner as shown in "Scores.txt" in the project folder
        {
            Scanner keyboard = new Scanner(System.in);
            String input = keyboard.next();
            try
            {
                //PrintWriter pw = new PrintWriter(input);
                PrintWriter pw = new PrintWriter("PrintScore.txt");
                pw.println(tempo);
                System.out.println(tempo);
                pw.println(measureLength);
                pw.println(beatLength);
                for(int instr = 0; instr < iList.size(); instr ++)
                {
                    Instrument thisInstrument = iList.get(instr);
                    pw.println("Instrument:");
                    for(int i = 0; i < thisInstrument.measureGrid.length; i ++)
                    {
                        for(int j = 0; j < thisInstrument.measureGrid[i].length; j ++)
                        {
                            Measure thisMeasure = thisInstrument.measureGrid[i][j];
                            pw.print(thisMeasure.keySig + "/" + thisMeasure.clef + "/");
                            for(int chord = 0; chord < thisMeasure.chordList.size(); chord ++)
                            {
                                Chord thisChord = thisMeasure.chordList.get(chord);
                                pw.print(thisChord.lengthInBeats + ",");
                                for(int note = 0; note < thisChord.noteList.size(); note ++)
                                {
                                    Note thisNote = thisChord.noteList.get(note);
                                    pw.print(thisNote.keyNumber + ",");
                                    
                                }
                                pw.println("/");
                            }
                        }
                        pw.println("End");
                    }
                }
            }
            catch(Exception except){}
        }
            
    }
}
