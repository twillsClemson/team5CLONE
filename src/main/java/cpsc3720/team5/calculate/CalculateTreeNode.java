package cpsc3720.team5.calculate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;

import cpsc3720.team5.calculate.*;

public class CalculateTreeNode
{
	
	static String soapString = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>"
			+ "<s:Envelope s:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\""
			+ "	xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\">" + "	<s:Body>"
			+ "		<u:Browse xmlns:u=\"urn:schemas-upnp-org:service:ContentDirectory:1\">"
			+ "			<ObjectID>{OBJECT_ID}</ObjectID>" + "			<BrowseFlag>BrowseDirectChildren</BrowseFlag>"
			+ "			<Filter></Filter>" + "			<StartingIndex>0</StartingIndex>"
			+ "			<RequestedCount>999</RequestedCount>" + "			<SortCriteria></SortCriteria>"
			+ "		</u:Browse>" + "	</s:Body>" + "</s:Envelope>";
	
	static public DefaultMutableTreeNode calculateTreeNode(String name, String serverURL, Map<DefaultMutableTreeNode, String> URLs)
	{
		DefaultMutableTreeNode node = calculateTreeNodeRecursive(name, "0", serverURL, URLs);
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
	
	static private DefaultMutableTreeNode calculateTreeNodeRecursive(String name, final String objectID, final String serverURL, final Map<DefaultMutableTreeNode, String> URLs)
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
						System.out.println(next[0] + " | " + next[1] + " | " + next[2]);
						DefaultMutableTreeNode node = calculateTreeNodeRecursive(next[0], next[1], serverURL, URLs);
						add(node);
						
						if(next[2].length() != 0)
						{
							URLs.put(node, next[2]);
						}
					}
				};
			};
		} catch (final IOException e)
		{
			System.err.println(e);
			return new DefaultMutableTreeNode(name);
		}	
	}


}
