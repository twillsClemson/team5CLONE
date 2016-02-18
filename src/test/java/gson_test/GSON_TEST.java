package gson_test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import data.*;

import org.junit.Test;

import com.google.gson.Gson;

public class GSON_TEST
{

	@Test
	public void testSettingsSave()
	{
		// Initialize Settings class
		Settings settings = Settings.getInstance();
		
		// Add arbitrary album
		Album album = new Album();
		album.setName("This album");
		album.setApprovalLevel(3);
		settings.addApprovedAlbum(album.approvalLevel, album);
		
		// Add arbitrary user
		UserProfiles user = new UserProfiles();
		user.setName("Jack");
		settings.addUserProfile(user);
		
		// Write settings
		settings.writeSettings();

		// Use Gson to compare contents of written file to expected json String
		Gson gson = new Gson();
		String json = gson.toJson(Settings.getInstance());
		try
		{
			// Read entire file into string
			String contents = new String(Files.readAllBytes(Paths
					.get(Settings.tempFilePath)));

			assertEquals(json, contents);

		} catch (IOException e)
		{
			fail("Exception in json file reading");
		}
	}
	
	// Test that a settings instance can be saved, reset, and loaded without changing any values
	@Test
	public void testSettingsLoad()
	{
		Settings settings = Settings.resetSettings();
		
		// Add arbitrary albums and users
		Album a1 = new Album();
		a1.setName("Album 1");
		a1.setApprovalLevel(1);
		settings.addApprovedAlbum(a1.getApprovalLevel(), a1);
		
		Album a2 = new Album();
		a2.setName("Second Album");
		a2.setApprovalLevel(4);
		settings.addApprovedAlbum(a2.getApprovalLevel(), a2);
		
		UserProfiles john = new UserProfiles();
		john.setName("John");
		settings.addUserProfile(john);
		
		// Write settings
		settings.writeSettings();
		
		// Populate String to compare this settings instance to a later instance
		Gson gson = new Gson();
		String json1 = gson.toJson(Settings.getInstance());
		
		// Reset settings (ie, now, settings == null)
		settings = Settings.resetSettings();
		
		// Read back in settings
		settings = Settings.getInstance();
		
		// Ensure that the new Settings is not the same as it was before, since nothing has been added to it
		String json2 = gson.toJson(Settings.getInstance());
		assertNotEquals(json1, json2);
		
		// Restore old Settings, and ensure that the new Settings is the same as it was before
		settings = settings.readSettings();
		json2 = gson.toJson(Settings.getInstance());
		assertEquals(json1, json2);
		
		// Assert that the URL was restored
		assertEquals(Settings.defaultURL, settings.getServerURL());
		
		// There should be two albums in the first approval level,
		// one album in levels 2-4, and no albums in level 5
		assertEquals(1, settings.getProfiles().size());
		assertEquals(2, settings.getApproved().get(0).size());
		assertEquals(1, settings.getApproved().get(1).size());
		assertEquals(0, settings.getApproved().get(4).size());
		
		// Assert that the albums available are the ones we expect
		assertEquals("Album 1", settings.getApproved().get(0).get(0).getName());
		assertEquals(1, settings.getApproved().get(0).get(0).getApprovalLevel());
	
		assertEquals("Second Album", settings.getApproved().get(0).get(1).getName());
		assertEquals(4, settings.getApproved().get(0).get(1).getApprovalLevel());
		
		for(int i = 1; i < 4; i++)
		{
			assertEquals("Second Album", settings.getApproved().get(i).get(0).getName());
			assertEquals(4, settings.getApproved().get(i).get(0).getApprovalLevel());
		}
		
		assertEquals("John", settings.getProfiles().get(0).getName());
	}
}