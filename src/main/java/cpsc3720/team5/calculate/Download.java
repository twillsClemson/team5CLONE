package cpsc3720.team5.calculate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class Download
{
	private static final String downloadsFolder = "./temp/";
	
	// Saves track to local machine for playing
	public static void downloadTrack(String trackURL) throws IOException
	{
	
		if(trackExists(getFileName(trackURL)))
		{
			System.out.println("NOT Downloading [" + trackURL + "]");
			return;
		}
		
		System.out.println("Downloading [" + trackURL + "]");
			
		File file = new File(downloadsFolder + getFileName(trackURL));
		file.createNewFile();

		URL url = new java.net.URL(trackURL);

		URLConnection urlConnect = url.openConnection();
		byte[] buffer = new byte[8 * 1024];

		InputStream input = urlConnect.getInputStream();
		try {
			OutputStream output = new FileOutputStream(downloadsFolder + file.getName());
			try {
				int bytesRead;
				while ((bytesRead = input.read(buffer)) != -1) {
					output.write(buffer, 0, bytesRead);
				}
			} finally {
				output.close();
			}
		} finally {
			input.close();
		}
	}
	
	private static String getFileName(String name)
	{
		int index = name.lastIndexOf("/") + 1;
		return name.substring(index);
	}

	// Use to see if track has already been downloaded
	private static boolean trackExists(String trackName) {
		File folder = new File(downloadsFolder);
		folder.mkdir();
		return new File(downloadsFolder+trackName).exists();
	}
	
//	public static String getTrackName()
//	{
//		
//	}
}
