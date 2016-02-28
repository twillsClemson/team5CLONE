package cpsc3720.team5.data;

public class Song
{
	String name;
	String length;
	String URL;
	
	public Song()
	{
		name = "";
		length = "";
		URL = "";
	}
	
	public Song(String _name, String _length, String _URL)
	{
		name = _name;
		length = _length;
		URL = _URL;
	}
	
	public String getFileName()
	{
		int index = URL.lastIndexOf("/") + 1;
		return URL.substring(index);
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
