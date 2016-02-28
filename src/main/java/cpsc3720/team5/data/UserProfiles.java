package cpsc3720.team5.data;

import java.util.ArrayList;


public class UserProfiles {
	
	private String name;
	private int restrictionLevel;
	private ArrayList<Album> favorites;
	
	
	public UserProfiles()
	{
		name = "";
		restrictionLevel = 0;
		favorites = new ArrayList<Album>();
	}
	
	public void addFavorite(Album album)
	{
		favorites.add(album);
	}
	
	public void removeFavorite(Album album)
	{
		int find = favorites.indexOf(album);
		if(find != -1)
		{
			favorites.remove(find);
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	
	public void setName(String name)
	{
		this.name = name;
	}

	public int getRestrictionLevel()
	{
		return restrictionLevel;
	}

	public void setRestrictionLevel(int restrictionLevel)
	{
		this.restrictionLevel = restrictionLevel;
	}

}
