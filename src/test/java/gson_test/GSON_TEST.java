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
	public void testWrite()
	{
		Settings settings = Settings.getInstance();
		Album a = new Album();
		a.setName("This album");
		a.setApprovalLevel(3);
		settings.addApprovedAlbum(a.approvalLevel, a);
		settings.writeSettings();

		Gson gson = new Gson();
		String json = gson.toJson(Settings.getInstance());
//		System.out.println("JSON = " + json);
		try
		{
			// Read entire file into string
			String contents = new String(Files.readAllBytes(Paths
					.get(Settings.tempFilePath)));
//			System.out.println("    JSON: " + json + "\nContents: " + contents);

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
		
		
		// There should be two albums in the first approval level,
		// one album in levels 2-4, and no albums in level 5
		assertEquals(1, settings.getProfiles().size());
		assertEquals(2, settings.getApproved().get(0).size());
		assertEquals(1, settings.getApproved().get(1).size());
		assertEquals(0, settings.getApproved().get(4).size());
		
		// Assert that the albums are the ones we expect
		assertTrue(settings.getApproved().get(0).get(0).getName().equals("Album 1"));
		assertTrue(settings.getApproved().get(0).get(0).getApprovalLevel() == 1);
		
		assertTrue(settings.getApproved().get(0).get(1).getName().equals("Second Album"));
		assertTrue(settings.getApproved().get(0).get(1).getApprovalLevel() == 4);
		
		for(int i = 1; i < 4; i++)
		{
			assertTrue(settings.getApproved().get(i).get(0).getName().equals("Second Album"));
			assertTrue(settings.getApproved().get(i).get(0).getApprovalLevel() == 4);
		}
		
		assertTrue(settings.getProfiles().get(0).getName().equals("John"));
	}
	
	

	/*
	 * Settings settings = Settings.getInstance(); Album a = new Album();
	 * settings.addApprovedAlbum(a.approvalLevel, a); settings.writeSettings();
	 * settings.readSettings();
	 */

}

