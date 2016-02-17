package data;

import java.util.ArrayList;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Settings {
	private static Settings set = null;
	private ArrayList<UserProfiles> profiles = null;
	private final int approvalLevels = 5;
	private ArrayList<ArrayList<Album>> approved = null;
	private String url = null;
	public static final String tempFilePath = "./temp/Settings.json";
	public static final String defaultURL = "http://127.0.0.1:5001/upnp/control/content_directory";


	private Settings() {
		profiles = new ArrayList<UserProfiles>();

		approved = new ArrayList<ArrayList<Album>>();
		for (int i = 0; i < approvalLevels; i++) {
			approved.add(new ArrayList<Album>());
		}
		url = defaultURL;
	}
	
	public ArrayList<ArrayList<Album>> getApproved()
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
		if (set == null)
			set = new Settings();

		return set;
	}
	
	public static Settings resetSettings()
	{
		set = null;
		set = Settings.getInstance();
		return set;
	}

	public void addApprovedAlbum(int approvalLevel, Album album) {
		for (int i = Math.min(approvalLevel - 1, approvalLevels - 1); i >= 0 ; i--) {
			approved.get(i).add(album);
		}
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
		String json = gson.toJson(Settings.getInstance());
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
		String json = gson.toJson(Settings.getInstance());
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

	@Override
	public String toString()
	{
		return "Settings = [profiles=" + profiles + ", approvalLevels="
				+ approvalLevels + ", approved=" + approved + ", url=" + url + "]";
	}
}
