import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import java.util.ArrayList;

import java.awt.Stroke;
/**
 * Sets up the Instrument's information as well as the panel to display said information
 * 
 * @author Andrew Wang
 * @version big number
 */
public class Instrument extends JPanel implements ActionListener, MouseListener, Runnable
{
    int dimensionX = 1400;
    int dimensionY = 600;
    int measureSize = 70;
    int gridWidth = dimensionX / measureSize;
    int gridHeight = dimensionY / measureSize;
    
    int mousePosX = -10;    //apparent initial starting position of mouse is offscreen
    int mousePosY = -10;
    
    Measure[][] measureGrid = new Measure[gridWidth][gridHeight];   //grid of measures
    
    int tempo;
    int measureLength;
    int beatLength;
    
    String clef; //Bass or Treble
    
    String keySig;
    boolean sharps;
    ArrayList<Integer> keySigAcc = new ArrayList<Integer>();    //dealing with keySignatures and which lines the sharps and flats fall on
    
    InstrumentFrame iFrame; //the frame it's connected to; the Instrument class actually makes its own frame, which then adds it to it
    MainButtonPanel main;   //just in case I want to access information from the MainButtonPanel outside of the constructor
    
    

    /**
     * Constructor for objects of class Instrument
     */
    public Instrument(MainButtonPanel main, String n) throws Exception       //where n is the number instrument it is
    {
        setPreferredSize(new Dimension(dimensionX, dimensionY));
        setBackground(Color.WHITE);
        
        for(int i = 0; i < measureGrid.length; i ++)
        {
            for(int j = 0; j < measureGrid[i].length; j ++)
            {
                measureGrid[i][j] = new Measure(dimensionX, dimensionY, i + 1 + j * dimensionX / measureSize, beatLength, measureLength, keySig);  //i + j * dimensionX / measureSize is the number of the measure
            }
        }
        
        addMouseListener(this);
        
        iFrame = new InstrumentFrame(this, n + "");
        this.main = main;
        tempo = main.tempo;
        measureLength = main.measureLength;
        beatLength = main.beatLength;
    }

    /**
     * Paints the stuffs
     * 
     * @param  Graphics g
     * @return    void
     */
    public void paintComponent(Graphics g)
    {
        for(int i = 0; i < measureGrid.length; i ++)        //Draws the grid
        {
            for(int j = 0; j < measureGrid[i].length; j ++)
            {
                Measure thisMeasure = measureGrid[i][j];
                
                if(thisMeasure.used)
                {
                    g.fillRect(i * measureSize, j * measureSize, measureSize, measureSize);
                }
                else
                {
                    g.drawRect(i * measureSize, j * measureSize, measureSize, measureSize);
                }
                
                if(mousePosX > i * measureSize && mousePosX < (i + 1) * measureSize)
                {
                    if(mousePosY > j * measureSize && mousePosY < (j + 1) * measureSize)        //if the mouse is clicked on top of a measure, go to the editing screen
                    {
                        
                        thisMeasure.edit();
                        
                        
                    }
                }
            }
        }
    }
    
    
    
    public void mousePressed(MouseEvent e)
    {
        mousePosX = e.getX();
        mousePosY = e.getY();
        repaint();
    }
    
    public void mouseReleased(MouseEvent e)
    {
        
    }
    
    public void mouseEntered(MouseEvent e)
    {
    
    }
    
    public void mouseExited(MouseEvent e)
    {
    
    }
    
    public void mouseClicked(MouseEvent e)
    {
        /**if(SwingUtilities.isRightMouseButton(e))
        {
            mousePosX = e.getX();
            mousePosY = e.getY();
        }*/
    }
    
    public void actionPerformed(ActionEvent e)  //20 times a second
    {
        
    }
    
    /**
     * Runs/ plays the Instrument 
     * 
     * @param     none
     * @return    void
     */
    public void run()       //might be changed to "play()" soon and the Runnable implementation deleted
    {
        for(int i = 0; i < measureGrid.length; i ++)    
        {
            for(int j = 0; j < measureGrid[i].length; j ++)
            {
                Measure thisMeasure = measureGrid[i][j];
                for(int counter2 = 0; counter2 < thisMeasure.chordList.size(); counter2 ++)
                {
                    Chord thisChord = thisMeasure.chordList.get(counter2);
                    thisChord.play(this.tempo);
                }
            }
        }
    }
}
