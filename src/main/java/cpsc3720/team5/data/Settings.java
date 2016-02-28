package cpsc3720.team5.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Settings {
	private static Settings set = null;
	private ArrayList<UserProfiles> profiles = null;
	private final int approvalLevels = 5;
	private Map<Album, Integer> approved = null;
	private String url = null;
	public static final String tempFilePath = "./temp/Settings.json";
	public static final String defaultURL = "http://127.0.0.1:5001/upnp/control/content_directory";


	private Settings() {
		profiles = new ArrayList<UserProfiles>();

		approved = new HashMap<Album, Integer>();
		url = defaultURL;
	}
	
	public Map<Album, Integer> getApproved()
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

	public void addApprovedAlbum(Album album)
	{
		approved.put(album, Math.max(album.getApprovalLevel(), approvalLevels));
	}
	
	public void addApprovedAlbum(Album album, int approvalLevel) {
		approved.put(album, Math.max(approvalLevel, approvalLevels));
	}
	
	public boolean isAlbumApproved(Album album, int approvalLevelNeeded)
	{
		if(album.getApprovalLevel() >= approvalLevelNeeded)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean isAlbumApproved(String albumName, int approvalLevelNeeded)
	{
		Iterator it = approved.entrySet().iterator();
		Album target;
		while(it.hasNext())
		{
			Map.Entry<Album, Integer> pair = (Map.Entry<Album, Integer>)it.next();
			target = pair.getKey();
			if(target.getName().equals(albumName))
			{
				if(target.getApprovalLevel() >= approvalLevelNeeded)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			it.remove();
		}
		
		return false;
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