package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Som {
    Clip clip;
    URL[] somURL = new URL[30];

    public Som(){
        somURL[0] = getClass().getResource("/sound/musicainicio.wav");
        somURL[1] = getClass().getResource("/sound/coin.wav");
        somURL[2] = getClass().getResource("/sound/powerup.wav");
        somURL[3] = getClass().getResource("/sound/unlock.wav");
        somURL[4] = getClass().getResource("/sound/fanfare.wav");
        somURL[5] = getClass().getResource("/sound/stairs.wav");
        somURL[6] = getClass().getResource("/sound/hitmonster.wav");
        somURL[8] = getClass().getResource("/sound/cursor.wav");
        somURL[9] = getClass().getResource("/sound/gameover.wav");
    }

    public void setFile(int i){
    try{
        AudioInputStream ais = AudioSystem.getAudioInputStream(somURL[i]);
        clip = AudioSystem.getClip();
        clip.open(ais);
    }catch(Exception e){
    }
    }

    public void toca(){
    clip.start();
    }

    public void loop(){
    clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
    clip.stop();
    }

}
