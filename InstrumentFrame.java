import java.awt.*;
import javax.swing.*;

/**
 * Frame for the Instruments.
 *
 * @author Andrew Wang
 * @version again, depends on whether you look at the class name or its function. At some point was the same thing as MainButtonPanel
 */
public class InstrumentFrame extends JFrame
{
    Instrument i;
    String name;
    
    /**
     * Constructor for objects of class InstrumentFrame
     */
    public InstrumentFrame(Instrument i, String n) throws Exception
    {
        JFrame frame = new JFrame(n);
        frame.setLayout(new BorderLayout());
        this.i = i;
        frame.add(i, BorderLayout.NORTH);
        frame.pack(); // prepares frame for display
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
