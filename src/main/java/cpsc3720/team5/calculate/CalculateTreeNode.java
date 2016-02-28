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
	
	static public DefaultMutableTreeNode calculateTreeNode(String name, String newServerURL, Map<String, Album> newAlbums, int newCurrentApprovalLevel)
	{
		serverURL = newServerURL;
		currentApprovalLevel = newCurrentApprovalLevel;
		albums = newAlbums;
		DefaultMutableTreeNode node = calculateTreeNodeRecursive(name, "0");
		return node;
//		return new DefaultMutableTreeNode(name)
//		{
//			{
//				DefaultMutableTreeNode node_1;
//				
//				node_1 = new DefaultMutableTreeNode("Bach");
//				add(node_1);
//				node_1 = new DefaultMutableTreeNode("Beethoven");
//				add(node_1);
//				node_1 = new DefaultMutableTreeNode("Motzart");
//				add(node_1);
//			}
//		};
	}
	
	static private DefaultMutableTreeNode calculateTreeNodeRecursive(final String name, final String objectID)
	{
		try
		{
			return new DefaultMutableTreeNode(name)
			{
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				{
					String soapMsg = soapString.replace("{OBJECT_ID}", objectID);

					ArrayList<Object> items = (ArrayList<Object>) SOAP.getItems(soapMsg, serverURL);

					for (Iterator<Object> i = items.iterator(); i.hasNext();)
					{
						String[] next = (String[]) i.next();
						System.out.println("[[" +next[0] + " | " + next[1] + " | " + next[2] + " | " + next[3]);
						
						DefaultMutableTreeNode node = calculateTreeNodeRecursive(next[0], next[1]);
						
						if(albums.containsKey(next[0]) && !Settings.getInstance().isAlbumApproved(next[0], currentApprovalLevel))
						{
							// If the current album is not approved, do not add it to the tree
							continue;
						}
						
						add(node);
						
						// Handle nodes that are not songs
						if(next[2].length() != 0)
						{
							Album album = albums.get( name);
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
//							// If folder is an album
//							if(node.getChildCount() != 0 && node.getChildAt(0).getChildCount() == 0)
//							{
//								Album album = albums.get(next[0]);
//								if(album == null)
//								{
//									album = new Album(next[0]);
//									album.setName(next[0]);
//									albums.put(next[0], album);
//								}
//								
//								
//	
//							}
//							add(node);
////							Object[] objInfo = new Object[3];
////							objInfo[0] = node;
////							objInfo[1] = next[0];
////							objInfo[2] = next[1];
////						objInfo[3] = 
//						URLs.put(node, next[2]);
						}
						
						else
						{
						
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
