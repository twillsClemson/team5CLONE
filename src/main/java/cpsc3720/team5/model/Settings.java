package cpsc3720.team5.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Settings {
	private static Settings set = null;
	private ArrayList<UserProfiles> profiles = null;
	private int approvalLevels = 3;
	private ArrayList<Album> approved = null;
	private String url = null;
	public static final String tempFilePath = "./temp/Settings.json";
	public static final String defaultURL = "http://127.0.0.1:5001/upnp/control/content_directory";
	private Map<String, Album> libraryAlbums = new HashMap<String, Album>();
	private UserProfiles currentUser = null;


	private Settings() {
		profiles = new ArrayList<UserProfiles>();

		approved = new ArrayList<Album>();
		url = defaultURL;
	}
	
	public Map<String, Album> getLibraryAlbums()
	{
		return libraryAlbums;
	}

	public UserProfiles getProfileByName(String name)
	{
		UserProfiles user = null;
		for(int i = 0; i < profiles.size(); i++)
		{
			if(profiles.get(i).getName().compareTo(name) == 0)
			{
				user = profiles.get(i);
			}
		}
		return user;
	}
	
	public void setCurrentUser(UserProfiles user)
	{
		currentUser = user;
	}
	
	public UserProfiles getCurrentUser()
	{
		return currentUser;
	}
	
	public ArrayList<Album> getApproved()
	{
		return approved;
	}
	
	public ArrayList<UserProfiles> getProfiles()
	{
		return profiles;
	}
	
	public void addUserProfile(UserProfiles user)
	{
		profiles.add(user);
	}

	public static Settings getInstance() {
		if (new File("./temp/Settings.json").exists())
		{
			set = new Settings();
			set.readSettings();
		}
		else if(set == null && !new File("./temp/Settings.json").exists())
		{
			set = new Settings();
		}
		
		return set;
	}
	
	public static Settings resetSettings()
	{
		set = null;
		set = Settings.getInstance();
		return set;
	}

//	public void addApprovedAlbum(Album album)
//	{
//		approved.put(album, Math.max(album.getApprovalLevel(), approvalLevels));
//	}
	public void setApprovalLevels(int levels)
	{
		approvalLevels = levels;
	}
	public void addApprovedAlbum(Album album, int approvalLevel) {
		album.setApprovalLevel(approvalLevel);
		approved.add(album);
	}
	
	public boolean isAlbumApproved(Album album, int currentApprovalLevel)
	{
		if(album.getApprovalLevel() > currentApprovalLevel)
		{
			System.out.println("Album approval > current level = FALSE " + album.getApprovalLevel() + " > " + currentApprovalLevel + " | " + album.getName());
			return false;
		}
		else
		{
			System.out.println("Album approval < current level = TRUE " + album.getApprovalLevel() + " <= " + currentApprovalLevel + " | " + album.getName());
			return true;
		}
	}
	
	public boolean isAlbumApproved(String albumName, int currentApprovalLevel)
	{
		Album target = getAlbum(albumName);
		
		if(target != null)
		{
			return isAlbumApproved(target, currentApprovalLevel);
		}
		else
		{
			System.out.println("Album not found - false [" + albumName + "]");
			return false;
		}
	}
	
	public Album getAlbum(String albumName)
	{
		for(int i = 0; i < approved.size(); i++)
		{
			if(approved.get(i).getName().equals(albumName))
			{
				return approved.get(i);
			}
		}
		return null;
	}

	public void setServerURL(String ServerURL) {
		url = ServerURL;
	}
	
	public String getServerURL()
	{
		return url;
	}

	public void writeSettings() {
		Gson gson = new Gson();
		String json = gson.toJson(set);
		try {
			FileWriter writer = new FileWriter(tempFilePath);
			writer.write(json);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Settings readSettings() {
		Gson gson = new Gson();
		//String json = gson.toJson(set);
//		System.out.println("JSON = " + json);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(tempFilePath));
			set = gson.fromJson(reader, Settings.class);
//			System.out.println("JSON = " + gson.toJson(set));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return set;

	}
	public void applySettings(ArrayList<Album> approve, String newurl, ArrayList<UserProfiles> profs)
	{
		approved = approve;
		url = newurl;
		profiles = profs;
	}
	public String getURL()
	{
		return url;
	}

	@Override
	public String toString()
	{
		return "Settings = [profiles=" + profiles + ", approvalLevels="
				+ approvalLevels + ", approved=" + approved + ", url=" + url + "]";
	}
}
