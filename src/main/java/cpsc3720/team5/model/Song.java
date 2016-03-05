package cpsc3720.team5.model;

public class Song
{
	String name;
	String length;
	String artist;
	String URL;
	String albumName;
	
	public Song()
	{
		name = "";
		length = "";
		artist = "";
		URL = "";
		
	}
	
	public Song(String _name, String _length, String _URL, String _artist, String _albumName)
	{
		name = _name;
		length = _length;
		URL = _URL;
		artist = _artist;
		albumName = _albumName;
	}
	
	public String getAlbumName()
	{
		return albumName;
	}
	
	public void setAlbumName(String _albumName)
	{
		albumName = _albumName;
	}
	
	
	public String getFileName()
	{
		int index = URL.lastIndexOf("/") + 1;
		return URL.substring(index);
	}
	
	public String getArtist()
	{
		return artist;
	}
	
	public void setArtist(String _artist)
	{
		artist = _artist;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getLength()
	{
		return length;
	}

	public void setLength(String length)
	{
		this.length = length;
	}

	public String getURL()
	{
		return URL;
	}

	public void setURL(String uRL)
	{
		URL = uRL;
	}
}
