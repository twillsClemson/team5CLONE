package gson_test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import data.*;

import org.junit.Test;

import com.google.gson.Gson;

public class GSON_TEST {

	@Test
	public void testWrite() {
		Settings settings = Settings.getInstance();
		Album a = new Album();
		settings.addApprovedAlbum(a.approvalLevel, a);
		settings.writeSettings();
		
		Gson gson = new Gson();
		String json = gson.toJson(Settings.getInstance());
		System.out.println("JSON = " + json);
		try {
//			BufferedReader reader = new BufferedReader(new FileReader("./temp/Settings.json"));
			
			
			// Read entire file into string
			String contents = new String(Files.readAllBytes(Paths.get("./temp/Settings.json")));
///			reader.close();
			System.out.println("    JSON: " + json + "\nContents: " + contents);
			
			assertEquals(json, contents);
			

		} catch (IOException e) {
			fail("Exception in json file reading");
		}
		
		
		
		
	}

	
	/*
		Settings settings = Settings.getInstance();
		Album a = new Album();
		settings.addApprovedAlbum(a.approvalLevel, a);
		settings.writeSettings();
		settings.readSettings();
		
	 */
	
}
