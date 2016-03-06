package cpsc3720.team5.controller;

import cpsc3720.team5.view.MainWindow;

import java.io.File;

import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.PlugInManager;
import javax.media.Time;
import javax.media.format.AudioFormat;
import javax.swing.SwingWorker;

import cpsc3720.team5.model.*;

public class PlayerController {
	
	private static Player player;
	public static void playSong() {
		// TODO Auto-generated method stub
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				File file = new File(MainWindow.selectedSong.getFileName());

				Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
				Format input2 = new AudioFormat(AudioFormat.MPEG);
				Format output = new AudioFormat(AudioFormat.LINEAR);
				PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder", new Format[] { input1, input2 },
						new Format[] { output }, PlugInManager.CODEC);

				try {
					String filename = file.getName();

					player = Manager
							.createRealizedPlayer(new MediaLocator(new File("./temp/" + filename).toURI().toURL()));
					player.setMediaTime(MainWindow.mediaTime);

					player.start();

					//System.out.println("Clip length: " + player.getDuration().getSeconds() + " seconds");
					//System.out.print("Press enter to stop playing...");

				} catch (Exception ex) {
					ex.printStackTrace();
				}

				return null;
			}
		};
		worker.execute();
	}

	public static void pauseSong() {
		MainWindow.mediaTime = player.getMediaTime();
		player.stop();
		
	}

	public static void stopSong() {
		MainWindow.mediaTime = new Time(0.0);
		player.stop();
	}


}
