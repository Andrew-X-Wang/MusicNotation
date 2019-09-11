import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import java.util.ArrayList;

import java.awt.Stroke;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Screen for editing the measure, saves all changes back into the measure
 * 
 * @author Andrew Wang
 * @version Large numbers everywhere
 */
public class MeasureEditor extends JPanel implements MouseListener, MouseMotionListener, ActionListener
{
    int dimensionX;
    int dimensionY;
    int lineSpacing = 15;    //physical size of note on the page/ how far apart the lines are
    
    ArrayList<Chord> chordList = new ArrayList<Chord>();
    
    String clef = "Treble";
    int beatLength;
    int measureLength;
    int keySignature;
    
    int mouseX;
    int mouseY;
    int imageSizeX = 50;
    int imageSizeY= 75;
    
    BufferedImage inputImage;
    BufferedImage note16;
    BufferedImage note8;
    BufferedImage note4;
    BufferedImage note2;
    BufferedImage note1;
    BufferedImage d16Note;
    BufferedImage d8Note;
    BufferedImage d4Note;
    BufferedImage d2Note;
    BufferedImage d1Note;
    BufferedImage rest16;
    BufferedImage rest8;
    BufferedImage rest4;
    BufferedImage rest2;
    BufferedImage rest1;
    
    BufferedImage treble;
    BufferedImage bass;
    
    BufferedImage sharp;
    BufferedImage flat;
    
    Measure measure;

    /**
     * Constructor for objects of class MeasureEditor, basically initializes the images
     */
    public MeasureEditor(Measure m) throws IOException
    {
        measure = m;
        
        dimensionX = m.dimensionX;
        dimensionY = m.dimensionY;
        
        setPreferredSize(new Dimension(dimensionX, dimensionY));
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        
        chordList = m.chordList;
        
        try {note16 = ImageIO.read(new File("Images/SixteenthNote.png"));}  //for some reason when I tried to put everything into a single try catch statement half of the images stopped being drawn... I know it's inefficient but I'll leave it as is for the time being
        catch(IOException e) {}
        try {note8 = ImageIO.read(new File("Images/EighthNote.png"));}
        catch(IOException e) {}
        try {note4 = ImageIO.read(new File("Images/QuarterNote.png"));}
        catch(IOException e) {}
        try {note2 = ImageIO.read(new File("Images/HalfNote.png"));}
        catch(IOException e) {}
        try {note1 = ImageIO.read(new File("Images/WholeNote.png"));}
        catch(IOException e) {}
        try {d16Note = ImageIO.read(new File("Images/DottedSixteenthNote.png"));}
        catch(IOException e) {}
        try {d8Note = ImageIO.read(new File("Images/DottedEighthNote.png"));}
        catch(IOException e) {}
        try {d4Note = ImageIO.read(new File("Images/DottedQuarterNote.png"));}
        catch(IOException e) {}
        try {d2Note = ImageIO.read(new File("Images/DottedHalfNote.png"));}
        catch(IOException e) {}
        try {d1Note = ImageIO.read(new File("Images/DottedWholeNote.png"));}
        catch(IOException e) {}
        try {rest16 = ImageIO.read(new File("Images/SixteenthRest.png"));}
        catch(IOException e) {}
        try {rest8 = ImageIO.read(new File("Images/EighthRest.png"));}
        catch(IOException e) {}
        try {rest4 = ImageIO.read(new File("Images/QuarterRest.png"));}
        catch(IOException e) {}
        try {rest2 = ImageIO.read(new File("Images/HalfRest.png"));}
        catch(IOException e) {}
        try {rest1 = ImageIO.read(new File("Images/WholeRest.png"));}
        catch(IOException e) {}
        
        try {treble = ImageIO.read(new File("Images/TrebleClef.png"));}
        catch(IOException e) {}
        try {bass = ImageIO.read(new File("Images/BassClef.png"));}
        catch(IOException e) {}
        
        try {sharp = ImageIO.read(new File("Images/Sharp.png"));}
        catch(IOException e) {}
        try {flat = ImageIO.read(new File("Images/Flat.png"));}
        catch(IOException e) {}
        
        addMouseListener(this);
        addMouseMotionListener(this);
        
        repaint();
    }
    
    /**
     * Draws the stuff
     * 
     * @param Graphics g
     * @return  void 
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        int pad = 50;
        int rightPAD = 100;
        
        for(int line = 0; line < 5; line ++)
        {
            g.drawLine(0, pad + (dimensionY - pad) / lineSpacing * line, dimensionX, pad + (dimensionY - pad) / lineSpacing * line);
        }
        
        if(measure.tempNote.keyNumber != 0)     //changes the note image that appears on screen depending on which one you selected from the menu
        {
            if(measure.tempNote.noteLength == 1)    //noteLength is number of 16th notes
            {
                inputImage = note16;
            }
            else if(measure.tempNote.noteLength == 2)
            {
                inputImage = note8;
            }
            else if(measure.tempNote.noteLength == 4)
            {
                inputImage = note4;
            }
            else if(measure.tempNote.noteLength == 8)
            {
                inputImage = note2;
            }
            else if(measure.tempNote.noteLength == 16)
            {
                inputImage = note1;
            }
            else if(measure.tempNote.noteLength == 3)
            {
                inputImage = d8Note;
            }
            else if(measure.tempNote.noteLength == 6)
            {
                inputImage = d4Note;
            }
            else if(measure.tempNote.noteLength == 12)
            {
                inputImage = d2Note;
            }
            else if(measure.tempNote.noteLength == 24)
            {
                inputImage = d1Note;
            }
        }
        else
        {
            if(measure.tempNote.noteLength == 1)
            {
                inputImage = rest16;
            }
            else if(measure.tempNote.noteLength == 2)
            {
                inputImage = rest8;
            }
            else if(measure.tempNote.noteLength == 4)
            {
                inputImage = rest4;
            }
            else if(measure.tempNote.noteLength == 8)
            {
                inputImage = rest2;
            }
            else if(measure.tempNote.noteLength == 16)
            {
                inputImage = rest1;
            }
        }
        if(inputImage != null)
        {
            g2.drawImage(inputImage, mouseX - imageSizeX / 2, mouseY - imageSizeY * 3 / 4, imageSizeX, imageSizeY, this);
        }
        
        if(measure.clef.equals("Treble"))   //image, x, y, width, height, this          //this part draws the clef on the left
        {
            g2.drawImage(treble, 0, pad, 100, lineSpacing * 5 + 100, this);
            
            /**if(measure.keySig.equals("A"))       //wanted to try drawing the key Signature, but wasn't working out well
            {
                g2.drawImage(sharp, 100, pad, 50, 50, this);
                g2.drawImage(sharp, 100, pad + (int)((dimensionY - pad) / lineSpacing * 2.5), 50, 50, this);
                g2.drawImage(sharp, 100, pad + (int)((dimensionY - pad) / lineSpacing * -.5), 50, 50, this);
            }*/
        }
        else
        {
            g2.drawImage(bass, 0, pad, 100, lineSpacing * 5 + 55, this);
        }
        
        for(int counter = 0; counter < measure.chordList.size(); counter ++)        //draws the chords...?
        {
            Chord thisChord = measure.chordList.get(counter);
            for(int counter2 = 0; counter2 < thisChord.noteList.size(); counter2 ++)
            {
                
            }
        }
    }

    /**
     * Doesn't draw the chords
     * 
     * @param - Chord c - the Chord to be drawn
     * @return   void
     */
    public void drawChord(Chord c)
    {
        for(int counter = 0; counter < c.noteList.size(); counter ++)
        {
            if(clef.equals("Bass"))
            {
                
            }
            else if(clef.equals("Treble"))
            {
                
            }
        }
    }
    
    public void mouseMoved(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
        repaint();
    }
    
    public void mouseDragged(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
        //repaint();
    }
    
    public void mousePressed(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        
        if(dimensionY / lineSpacing > 0)    //to determine the notes
        {
        
        }
        
        //measure.chordList.set(dimensionX / x, new Note(, measure.tempoNote.length;
    }
    
    public void mouseReleased(MouseEvent e)
    {
                                                                                                       //to give an initial velocity to the ball. You can still choose to have a massive object
        repaint();                                                                                              //with a lower initial velocity because the velocity's only dependent on the distance dragged
    }
    
    public void mouseEntered(MouseEvent e)
    {
    
    }
    
    public void mouseExited(MouseEvent e)
    {
    
    }
    
    public void mouseClicked(MouseEvent e)
    {
        if(SwingUtilities.isRightMouseButton(e))            //if I right click the Mass object is deleted
        {
            
        }
    }
    
    public void actionPerformed(ActionEvent e)  //20 times a second
    {
        //if(e.getSource() ==
    }
}
