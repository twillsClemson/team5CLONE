package cpsc3720.team5.calculate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;

import cpsc3720.team5.calculate.*;
import cpsc3720.team5.data.Album;
import cpsc3720.team5.data.Settings;
import cpsc3720.team5.data.Song;

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
	{
		serverURL = newServerURL;
		currentApprovalLevel = newCurrentApprovalLevel;
		albums = newAlbums;
		DefaultMutableTreeNode node = calculateTreeNodeRecursive(name, "0");
		return node;
	}
	
	// Recursive algorithm used by calling function, "calculateTreeNode()"
	static private DefaultMutableTreeNode calculateTreeNodeRecursive(final String name, final String objectID)
	{
		try
		{
			return new DefaultMutableTreeNode(name)
			{
				private static final long serialVersionUID = 1L;
				{
					String soapMsg = soapString.replace("{OBJECT_ID}", objectID);

					ArrayList<Object> items = (ArrayList<Object>) SOAP.getItems(soapMsg, serverURL);

					for (Iterator<Object> i = items.iterator(); i.hasNext();)
					{
						String[] next = (String[]) i.next();
						
						//	next[0] = String containing title of item
						//	next[1] = String containing server ID of item
						//	next[2] = String containing server URL of item, if item is a song
						//	next[3] = String containing duration of item, if item is a song
//						System.out.println("[[" +next[0] + " | " + next[1] + " | " + next[2] + " | " + next[3]);
						
						DefaultMutableTreeNode node = calculateTreeNodeRecursive(next[0], next[1]);
						
						// If the current album is not approved, do not add it to the tree
						if(albums.containsKey(next[0]) && !Settings.getInstance().isAlbumApproved(next[0], currentApprovalLevel))
						{
						
							continue;
						}
						
						add(node);
						
						// Handle nodes that are songs, designated by the item having a server URL
						if(next[2].length() != 0)
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
							
							album.addSong(song);
						}
					}
				};
			};
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}


}
