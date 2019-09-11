import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import java.util.ArrayList;

import java.awt.Stroke;

import java.io.IOException;

/**
 * Provides the frame for the MeasureEditor
 * 
 * @author Andrew Wang
 * @version a smaller number
 */
public class MeasureFrame
{
    /**
     * Constructor for objects of class MeasureFrame
     */
    public MeasureFrame(Measure m) throws IOException
    {
        JFrame frame = new JFrame("Measure: " + m.measureNumber);
        MeasureButtonPanel panel = new MeasureButtonPanel(m);
        MeasureEditor me = panel.me;
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.NORTH);
        frame.add(me, BorderLayout.CENTER);
        frame.pack(); // prepares frame for display
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
