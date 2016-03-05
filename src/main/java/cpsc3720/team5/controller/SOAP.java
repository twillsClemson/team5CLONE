package cpsc3720.team5.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

// SOAP communication must be abstracted into a separate class that returns the results in the form of a list of objects.
public class SOAP {

	// Returns a List of Object Arrays of the following format:
	//		Object[0] = String containing title of item
	//		Object[1] = String containing server ID of item
	//		Object[2] = String containing server URL of item, if item is a song
	//		Object[3] = String containing duration of item, if item is a song
	//		Object[4] = String containing artist of item, if item is a song
	public static List<Object> getItems(String soapMsg, String URLInput) throws IOException
	{
		String resultStr = getSoapMsg(soapMsg, URLInput);

		if(resultStr == null)
		{
			return new ArrayList<Object>();
		}
		else
		{
			return parseSoapMessage(resultStr);
		}
	}
	
	// Returns a string that is formatted to be an acceptable SOAP server request
	private static String getSoapMsg(String soapMsg, String URLInput) throws IOException
	{
		MediaType XML = MediaType.parse("text/xml; charset=utf-8");
		RequestBody body = RequestBody.create(XML, soapMsg);
		try
		{
			Request request = new Request.Builder()
					.url(URLInput)
					.header("Soapaction", "\"urn:schemas-upnp-org:service:ContentDirectory:1#Browse\"")
					.post(body)
					.build();
		
			OkHttpClient client = new OkHttpClient();
			Response response = client.newCall(request).execute();
		
			if (response.code() == 200)
			{
				return response.body().string();
			}
			else
			{
			// This may need changed
				System.err.println("Response code: " + response.code());
				System.err.println("Response: " + response.message());
				return null;
			}
		} catch (IllegalArgumentException ex)
		{
			return null;
		}
	}
	
	// The low-level algorithm used by getItems to retrieve information about items on the server
	private static List<Object> parseSoapMessage(String resultStr)
	{
		ArrayList<Object> ret = new ArrayList<Object>();
		if(resultStr.length() == 0)
			return ret;
		
		final String START_TAG = "<Result>";
		final String END_TAG = "</Result>";
		int startIndex = resultStr.indexOf(START_TAG) + START_TAG.length();
		int endIndex = resultStr.indexOf(END_TAG);
		String finalStr = resultStr.substring(startIndex, endIndex);
		
		finalStr = finalStr.replace("&lt;", "<").replace("&gt;",">");
		System.out.println(finalStr);
		
		// Overall index
		int index = finalStr.indexOf("<container", 0);
		
		String ID, title, clipURL = "", artist = "", duration = "";
		
		// Handle folders on server
		while(index != -1)
		{
			try
			{
				String sub = finalStr.substring(index, finalStr.indexOf("</container>", index));
				
				// Retrieve Object ID
				startIndex = sub.indexOf("<container id=\"") + "<container id=\"".length();
				endIndex = sub.indexOf("\"", startIndex);
				
				if(startIndex == -1 + "<container id=\"".length() || endIndex == -1)
				{
					throw new StringIndexOutOfBoundsException();
				}
				
				ID = sub.substring(startIndex, endIndex);
				
				// Retrieve album title
				startIndex = sub.indexOf("<dc:title>") + "<dc:title>".length();
				endIndex = sub.indexOf("</dc:title>");
				
				if(startIndex == -1 + "<dc:title>".length() || endIndex == -1)
				{
					throw new StringIndexOutOfBoundsException();
				}

				title = sub.substring(startIndex, endIndex);
				

			} catch (StringIndexOutOfBoundsException ex)
			{
				// When an exception is thrown, continue searching without adding anythings
				index = finalStr.indexOf("<container", index+1);
				continue;
			}
			
			clipURL = "";
			duration = "";

			ret.add( new String[] { title, ID, clipURL, duration, artist } );
			
			index = finalStr.indexOf("<container", index+1);
		}
		
		
		
		index = finalStr.indexOf("<item", 0);
		
		// Handle media items on server
		while(index != -1)
		{
			try
			{
				String sub = finalStr.substring(index, finalStr.indexOf("</item>", index));
				
				// Retrieve object ID
				startIndex = sub.indexOf("<item id=\"") + "<item id=\"".length();
				endIndex = sub.indexOf("\"", startIndex);
				
				if(startIndex == -1 + "<item id=\"".length() || endIndex == -1)
				{
					throw new StringIndexOutOfBoundsException();
				}

				ID = sub.substring(startIndex, endIndex);
				System.out.println("Object ID = " + ID);
				
				// Retrieve song title
				startIndex = sub.indexOf("<dc:title>") + "<dc:title>".length();
				endIndex = sub.indexOf("</dc:title>");
				
				if(startIndex == -1 + "<dc:title>".length() || endIndex == -1)
				{
					throw new StringIndexOutOfBoundsException();
				}

				title = sub.substring(startIndex, endIndex);
				
				// Retrieve artist
				startIndex = sub.indexOf("<upnp:artist>") + "<upnp:artist>".length();
				endIndex = sub.indexOf("</upnp:artist>");
				
				if(startIndex == -1 + "<upnp:artist>".length() || endIndex == -1)
				{
					throw new StringIndexOutOfBoundsException();
				}

				artist = sub.substring(startIndex, endIndex);
				
				// Retrieve URL of song
				startIndex = sub.lastIndexOf("\">",  sub.indexOf("</res>")) + "\">".length();
				endIndex = sub.indexOf("</res>");
				
				if(startIndex == -1 + "\">".length() || endIndex == -1)
				{
					throw new StringIndexOutOfBoundsException();
				}

				clipURL = sub.substring(startIndex, endIndex);

				// Retrieve song length
				startIndex = sub.indexOf("duration=\"") + "duration=\"".length();
				endIndex = sub.indexOf("\"", startIndex);
				
				if(startIndex == -1 + "duration=\"".length() || endIndex == -1)
				{
					throw new StringIndexOutOfBoundsException();
				}
				
				duration = sub.substring(startIndex, endIndex);

			} catch (StringIndexOutOfBoundsException ex)
			{
				index = finalStr.indexOf("<item", index+1);
				continue;
			}
			
			ret.add( new String[] { title, ID, clipURL, duration, artist } );
			
			index = finalStr.indexOf("<item", index+1);
		}
		
		return ret;
	}
}
