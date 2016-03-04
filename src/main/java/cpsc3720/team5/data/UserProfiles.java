package cpsc3720.team5.data;

import java.util.ArrayList;
import java.util.Iterator;


public class UserProfiles {
	
	private String name;
	private int restrictionLevel;
	private ArrayList<Album> favorites;
	private boolean admin;
	private String profilePic;
	private int PIN;
	public UserProfiles()
	{
		name = "";
		restrictionLevel = 0;
		admin = false;
		favorites = new ArrayList<Album>();
		profilePic = "";
		PIN = 0; 
	}
	
	@Deprecated
	public boolean hasFavorite(String albumName)
	{
		for(Iterator<Album> i = favorites.iterator(); i.hasNext();)
		{
			if(((Album) i.next()).getName().equals(albumName))
			{
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Album> getFavorites()
	{
		return favorites;
	}
	
	public boolean hasFavorite(Album album)
	{
		for(Iterator<Album> i = favorites.iterator(); i.hasNext();)
		{
			if(((Album) i.next()) == album)
			{
				return true;
			}
		}
		return false;
	}
	
	public void addFavorite(Album album)
	{
//		if(!hasFavorite(album.getName()))
//		{	
			favorites.add(album);
//		}
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
	
	public boolean getAdmin()
	{
		return this.admin;
	}
	
	public int getPIN()
	{
		return this.PIN;
	}
	
	public String getProfilePic()
	{
		return this.profilePic;
	}

}
