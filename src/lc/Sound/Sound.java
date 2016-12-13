package Sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    public static File buy = new File("Sound/Buy.wav");
    public static File closeDoor = new File("Sound/DoorClose.wav");
    public static File lvUp = new File("Sound/LevelUp.wav");

    //resource from url: https://www.youtube.com/watch?v=QVrxiJyLTqU

    public static void play(File file) {
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
        } catch (Exception e) {
            System.out.println("non");
        }
    }
}
