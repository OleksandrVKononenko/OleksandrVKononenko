package ap.test.audio;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// java AudioClient "C:/Users/Public/Music/Sample Music/adios.wav"

public class AudioClient {
	
    public static void main(String[] args) throws Exception {
    	
        if (args.length > 0) {
        	
            // play a file passed via the command line
            File soundFile = AudioUtil.getSoundFile(args[0]);
            
            System.out.println("Client: " + soundFile);
            
            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(soundFile))) {
            	
                play(in);
            }
        }
        else {
            // play soundfile from server
            System.out.println("Client: reading from 127.0.0.1:7777");
            
            try (Socket socket = new Socket("127.0.0.1", 7777)) {
                if (socket.isConnected()) {
                    InputStream in = new BufferedInputStream(socket.getInputStream());
                    
                    while(true)
                    {
                    	play(in);
                    }
                    
                }
            }
        }

        System.out.println("Client: end");
    }


    private static synchronized void play(final InputStream in) throws Exception {
    	
        AudioInputStream ais = AudioSystem.getAudioInputStream(in);
        
        try (Clip clip = AudioSystem.getClip()) {
        	
            clip.open(ais);
            
            clip.start();
            
            Thread.sleep(100); // given clip.drain a chance to start
            
            clip.drain();
        }
    }
}