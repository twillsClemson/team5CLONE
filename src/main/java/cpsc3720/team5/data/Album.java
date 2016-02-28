package cpsc3720.team5.data;

import java.util.ArrayList;

public class Album {
	public String name;
	public int approvalLevel;
	public ArrayList<Song> songs;

	public Album() {
		name = "";
		approvalLevel = 1; // Default to lowest restriction level
		songs = new ArrayList<Song>();
	}
	
	public Album(String albumName)
	{
		name = albumName;
		approvalLevel = 1;
		songs = new ArrayList<Song>();
	}
	
	public ArrayList<Song> getSongs()
	{
		return songs;
	}
	
	public void addSong(Song song)
	{
		songs.add(song);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getApprovalLevel()
	{
		return approvalLevel;
	}

	public void setApprovalLevel(int approvalLevel)
	{
		this.approvalLevel = approvalLevel;
	}
	
	
}