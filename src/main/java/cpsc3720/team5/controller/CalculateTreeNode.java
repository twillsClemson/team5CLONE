package cpsc3720.team5.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;

import cpsc3720.team5.controller.*;
import cpsc3720.team5.model.Album;
import cpsc3720.team5.model.Settings;
import cpsc3720.team5.model.Song;

public class CalculateTreeNode
{
	
	static public final String soapString = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>"
			+ "<s:Envelope s:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\""
			+ "	xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\">" + "	<s:Body>"
			+ "		<u:Browse xmlns:u=\"urn:schemas-upnp-org:service:ContentDirectory:1\">"
			+ "			<ObjectID>{OBJECT_ID}</ObjectID>" + "			<BrowseFlag>BrowseDirectChildren</BrowseFlag>"
			+ "			<Filter></Filter>" + "			<StartingIndex>0</StartingIndex>"
			+ "			<RequestedCount>999</RequestedCount>" + "			<SortCriteria></SortCriteria>"
			+ "		</u:Browse>" + "	</s:Body>" + "</s:Envelope>";
	
	static public String serverURL;
	static public int currentApprovalLevel;
	static public Map<String, Album> albums;
	
	// Recursively generates the nodes to be used in the "Library" tab of the main window
	// Filters any results that the current user does not have permissions for
	static public DefaultMutableTreeNode calculateTreeNode(String name, String newServerURL, Map<String, Album> newAlbums, int newCurrentApprovalLevel)
		throws IOException
	{
		serverURL = newServerURL;
		currentApprovalLevel = newCurrentApprovalLevel;
		albums = newAlbums;
		DefaultMutableTreeNode node = calculateTreeNodeRecursive(name, "0");
		return node;
	}
	
	// Recursive algorithm used by calling function, "calculateTreeNode()"
	static private DefaultMutableTreeNode calculateTreeNodeRecursive(final String name, final String objectID) throws IOException
	{
		DefaultMutableTreeNode ret = new DefaultMutableTreeNode(name);
		
		String soapMsg = soapString.replace("{OBJECT_ID}", objectID);

		ArrayList<Object> items;

		items = (ArrayList<Object>) SOAP.getItems(soapMsg, serverURL);


		for (Iterator<Object> i = items.iterator(); i.hasNext();)
		{
			String[] next = (String[]) i.next();
			
			//	next[0] = String containing title of item
			//	next[1] = String containing server ID of item
			//	next[2] = String containing server URL of item, if item is a song
			//	next[3] = String containing duration of item, if item is a song
			//	next[4] = String containing artist of item, if item is a song
			DefaultMutableTreeNode node = calculateTreeNodeRecursive(next[0], next[1]);
			
			// If the current album is not approved, do not add it to the tree
			if(node == null ||(albums.containsKey(next[0]) && !Settings.getInstance().isAlbumApproved(next[0], currentApprovalLevel)))
			{
				continue;
			}
			
			// Handle albums and folders by adding them to the tree
			// (A node is an album or folder if it does not have a server URL)
			if(next[2].length() == 0)
			{
				ret.add(node);
			}
			else
			{
				Album album = albums.get(name);
				if(album == null)
				{
					album = new Album(name);
					albums.put(name, album);
				}
				
				Song song = new Song();
				song.setName(next[0]);
				song.setURL(next[2]);
				song.setLength(next[3]);
				song.setArtist(next[4]);
				song.setAlbumName(name);
				
				album.addSong(song);
			}
		}
		return ret;
	}
	
	// Recursive algorithm used by calling function, "calculateTreeNode()"
	static public DefaultMutableTreeNode calculateFavoritesTreeNode(ArrayList<Album> userFavorites)
	{
		DefaultMutableTreeNode base = new DefaultMutableTreeNode("Favorites");
		for(Iterator<Album> i = userFavorites.iterator(); i.hasNext();)
		{
			Album album = (Album) i.next();
			
			DefaultMutableTreeNode albumTree = new DefaultMutableTreeNode(album.getName());
			
			for(Iterator<Song> j = album.getSongs().iterator(); j.hasNext();)
			{
				Song song = (Song) j.next();
				
				albumTree.add(new DefaultMutableTreeNode(song.getName()));
			}
			
			base.add(albumTree);
		}
		
		return base;
	}
}