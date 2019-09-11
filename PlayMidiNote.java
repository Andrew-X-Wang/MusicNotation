import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;

import java.util.*;
import java.io.*;
/**
 * Plays the sounds!
 * 
 * @author Andrew Wang/ StackOverFLow
 * @version Relatively smol number
 */
public class PlayMidiNote
{
    int duration;
    int channel;
    int volume;

    /**
     * Plays the note, not actually the song, so a little misleading for now
     * 
     * @param - int keyNumber - dictates which note will be played
     * @param - int noteLength - dictates how long the note will be held for (how long Thread.sleep will be)
     * @param - int tempo - dictates how fast the music in general will be, so basically factors into how long the note is sustained for
     * @return    void
     */
    public void playSong(int keyNumber, int noteLength, int tempo)
    {
        int channel = 0; // 0 is a piano, 9 is percussion, other channels are for other instruments

        int volume = 80; // between 0 et 127
        int duration = 200; // in milliseconds (originally default 200)

        int editedDuration = (60000 * noteLength) / tempo;      //calculations for bpm to number of milliseconds for the appropriate noteLengths below

        /**
        beatLength(32 in a whole note) * tempo (beats / minute) / 32
        1 beat = 1000 ms for 60 bpm
        duration of a beat = 60000 / tempo
        duration of a note = duration of a beat * beatLength of note

        beatlength / (beat * minute) / (minute * 60 seconds) / seconds * 1000 ms

        60 bpm I want one beat / second

         **/
        try {
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            MidiChannel[] channels = synth.getChannels();

            // --------------------------------------
            // Play a few notes.
            // The two arguments to the noteOn() method are:
            // "MIDI note number" (pitch of the note),
            // and "velocity" (i.e., volume, or intensity).
            // Each of these arguments is between 0 and 127.
            boolean isRest = keyNumber == 0;
            if(!isRest)
            {
                channels[channel].noteOn( keyNumber + 8, volume ); // C4 note = 60 in MIDI
                Thread.sleep( editedDuration );         //origially Thread.sleep( duration ); @TODO: STILL NEEDS FIXING!!!!
                //channels[channel].noteOff( keyNumber + 8 );
            }
            else
            {
                Thread.sleep(editedDuration);   //doesn't play anything, just pauses for the duration of the rest
            }
            
            /**
            channels[channel].noteOn( keyNumber + 10, volume ); // D note = 62 in MIDI
            Thread.sleep( duration );
            channels[channel].noteOff( keyNumber + 10 );
            channels[channel].noteOn( keyNumber + 12, volume ); // E note = 64 in MIDI
            Thread.sleep( duration );
            channels[channel].noteOff( keyNumber + 12 );

            Thread.sleep( 500 );

            // --------------------------------------
            // Play a C major chord.
            channels[channel].noteOn( keyNumber + 8, volume ); // C
            channels[channel].noteOn( keyNumber + 12, volume ); // E
            channels[channel].noteOn( keyNumber + 15, volume ); // G
            Thread.sleep( 3000 );
            channels[channel].allNotesOff();
            Thread.sleep( 500 );
             */

            synth.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
    public void playFromFile() throws IOException
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the fileName you would like to play from: (format: fileName.txt)");
        String fileName = keyboard.next();
        Score fileScore = new Score(fileName);
        ArrayList<Note> savedSong = fileScore.readScoreFromFile(fileName);

        FileReader fr = new FileReader(fileName);
        Scanner scanner = new Scanner(fr);
        String unusedLine = scanner.nextLine();             //wait this works but why I forgot?????
        String line = scanner.nextLine();                       //nvm i think this part is just to get the measure info from the song which is on the next line

        String[] tokens = line.split("\\s+");  // split it on whitespace
        int beatLength = Integer.parseInt(tokens[0]); //beatLength //USELESS FOR THIS PART THIS IS THE LENGTH OF THE BEAT
        int beatNumber = Integer.parseInt(tokens[1]); //number of beats, NOT REALLY IMPORTANT FOR THE ARRAYLIST OF NOTES (only needed when separating measures out
        int tempo = Integer.parseInt(tokens[2]); //tempo

        int channel = 0; // 0 is a piano, 9 is percussion, other channels are for other instruments
        int volume = 80; // between 0 et 127
        int duration = 200; // in milliseconds (originally default 200)

        try {
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            MidiChannel[] channels = synth.getChannels();

            // --------------------------------------
            // Play a few notes.
            // The two arguments to the noteOn() method are:
            // "MIDI note number" (pitch of the note),
            // and "velocity" (i.e., volume, or intensity).
            // Each of these arguments is between 0 and 127.
            for(int noteCount = 0; noteCount < savedSong.size(); noteCount ++)
            {
                int keyNumber = savedSong.get(noteCount).keyNumber;
                channels[channel].noteOn( keyNumber + 8, volume ); // C4 note = 60 in MIDI
                Thread.sleep( 60000 / tempo * savedSong.get(noteCount).lengthInBeats );
                channels[channel].noteOff( keyNumber + 8 );
            }

            synth.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
