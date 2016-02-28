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
// Saves track to local machine for playing
	private void downloadTrack(String trackName) throws IOException {
		File file = new File("./temp/" + trackName);
		file.createNewFile();

		URL url = new java.net.URL(/*textField_ClipURL.getText()*/ "");

		URLConnection urlConnect = url.openConnection();
		byte[] buffer = new byte[8 * 1024];

		InputStream input = urlConnect.getInputStream();
		try {
			OutputStream output = new FileOutputStream("./temp/" + file.getName());
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

	// Use to see if track has already been downloaded
	public boolean trackExists() {
		File folder = new File(System.getProperty("user.dir") + "/temp/");
		folder.mkdir();

		return new File(folder, /*getTrackName()*/ "").exists();
	}
}
