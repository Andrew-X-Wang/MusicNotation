import java.awt.*;
import javax.swing.*;

/**
 * Frame for the MainButtonPanel
 * 
 * @author Andrew Wang
 * @version depends on whether you look at the name of the class or its function
 */
public class MainFrame extends JFrame
{
    public MainFrame() throws Exception
    {
        JFrame frame = new JFrame("Moooooosic!");
        frame.setLayout(new BorderLayout());
        MainButtonPanel b = new MainButtonPanel();
        frame.add(b, BorderLayout.NORTH);
        frame.pack(); // prepares frame for display
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
