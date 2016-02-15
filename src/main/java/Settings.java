import java.util.ArrayList;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Settings {
	private static Settings set = null;
	private ArrayList<UserProfiles> profiles = null;
	private ArrayList<Album> approved1 = null;
	private ArrayList<Album> approved2 = null;
	private ArrayList<Album> approved3 = null;
	private ArrayList<Album> approved4 = null;
	private ArrayList<Album> approved5 = null;
	private String url = null;
	public Settings()
	{
		profiles = new ArrayList<UserProfiles>();
		approved1 = new ArrayList<Album>();
		approved2 = new ArrayList<Album>();
		approved3 = new ArrayList<Album>();
		approved4 = new ArrayList<Album>();
		approved5 = new ArrayList<Album>();
		url = "";
	}

	public static Settings getInstance(){
		if(set == null)
			set = new Settings();

		return set;
	}

	public void addApprovedAlbum(int approvalLevel, Album album)
	{
		switch(approvalLevel){
		case 1:
			approved1.add(album);
			break;
		case 2:
			approved2.add(album);
			break;
		case 3:
			approved3.add(album);
			break;
		case 4:
			approved4.add(album);
			break;
		case 5:
			approved5.add(album);
			break;
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
