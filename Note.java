
/**
 * Stores information about the note
 * 
 * @author Andrew Wang
 * @version Not too big of a #
 */
public class Note
{
    int keyNumber;  //on the piano
    int noteLength; //in beats
    String note;
    
    /**
     * Constructor for objects of class Note, called by measureButton and measureButtonPanel to make the image representing what note the user has selected to input
     */
    public Note(int kn, int nl)
    {
        keyNumber = kn;
        note = keyNumberToNote(kn);
        
        noteLength = nl;
    }
    
    /**
     * Constructor for objects of class Note, called by MainButtonPanel when opening files
     */
    public Note(int nl) //needs to externally add the keyNumber and convert to "note"
    {
        noteLength = nl;
    }

    /**
     * Converts the number on the piano to a String value representing which octave it's on and what note it is
     * 
     * @param - int kn - the keyNumber of the Note
     * @return - String note - the String value representing the Note
     */
    public String keyNumberToNote(int kn)
    {
        if(kn == 0)
        {
            return "Rest";
        }
        else
        {
            boolean sharp = false;
            //boolean flat = false;
            String accidental = "";
            int basicKeyNumber = kn % 12;
            int octaveNumber = kn / 12;
            if(basicKeyNumber == 0 || basicKeyNumber == 10 || basicKeyNumber == 7 || basicKeyNumber == 5 || basicKeyNumber == 2)
            {
                sharp = true;
                basicKeyNumber --;    //if it's sharp, put note down 1 and add "#" to the end
                accidental = "#";

                /*flat = true;
                basicKeyNumber ++; 
                accidental = "b";*/     //if it's flat, put note up 1 and add "b" to the end
            }
            if(basicKeyNumber < 1)      //if below octave
            {
                basicKeyNumber += 12;
                octaveNumber --;
            }
            if(basicKeyNumber > 11)     //the following adjusts for black keys
            {
                basicKeyNumber -= 5;
            }
            else if(basicKeyNumber > 9)
            {
                basicKeyNumber -= 4;
            }
            else if(basicKeyNumber > 6)
            {
                basicKeyNumber -= 3;
            }
            else if(basicKeyNumber > 4)
            {
                basicKeyNumber -= 2;
            }
            else if(basicKeyNumber > 1)
            {
                basicKeyNumber -= 1;
            }
            char basicKey = (char)(basicKeyNumber + 64);
            String note = basicKey + accidental + octaveNumber;
            return note;
        }
    }
}
