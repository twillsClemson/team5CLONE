import java.util.ArrayList;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Settings {
	private static Settings set = null;
	private ArrayList<UserProfiles> profiles = null;
	private ArrayList<ArrayList<Album> > approved = null;
	private String url = null;
	private Settings()
	{
		profiles = new ArrayList<UserProfiles>();
		for(int i = 0; i < 5; i++)
		{
			approved.add(new ArrayList<Album>());
		}
		url = "";
	}

	public static Settings getInstance(){
		if(set == null)
			set = new Settings();

		return set;
	}

	public void addApprovedAlbum(int approvalLevel, Album album)
	{
		for(int i = 0; i < approvalLevel; i++)
		{
			approved.get(i).add(album);
		}
	}

	public void setServerURL(String ServerURL)
	{
		url = ServerURL;
	}

	public void writeSettings()
	{
	Gson gson = new Gson();
	String json = gson.toJson(Settings.getInstance());
	try{
		FileWriter writer = new FileWriter("./temp/Settings.json");
		   writer.write(json);
		   writer.close();

		  } catch (IOException e) {
		   e.printStackTrace();
		  }

	}

	public void readSettings()
	{
	Gson gson = new Gson();
	String json = gson.toJson(Settings.getInstance());
	try{
		BufferedReader reader = new BufferedReader(new FileReader("./temp/Settings.json"));
		   set =  gson.fromJson(reader, Settings.class);

		  } catch (IOException e) {
		   e.printStackTrace();
		  }

	}
}
