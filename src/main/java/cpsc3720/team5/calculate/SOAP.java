package cpsc3720.team5.calculate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

// SOAP communication must be abstracted into a separate class that returns the results in the form of a list of objects. Comprehensive unit tests are required for this component
public class SOAP {

	
	public static String getSoapMsg(String soapMsg, String URLInput) throws IOException
	{
		MediaType XML = MediaType.parse("text/xml; charset=utf-8");
		RequestBody body = RequestBody.create(XML, soapMsg);
		try
		{
			Request request = new Request.Builder()
//				.url("http://127.0.0.1:5001/upnp/control/content_directory")
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
	
	public static List<Object> parseSoapMessage(String resultStr)
	{
		ArrayList<Object> ret = new ArrayList<Object>();
		if(resultStr.length() == 0)
			return ret;
		
//			System.out.println("Sample Soap response \n" + resultStr);
			final String START_TAG = "<Result>";
			final String END_TAG = "</Result>";
			String duration = "";
			int startIndex = resultStr.indexOf(START_TAG) + START_TAG.length();
			int endIndex = resultStr.indexOf(END_TAG);
			String finalStr = resultStr.substring(startIndex, endIndex);
			
			finalStr = finalStr.replace("&lt;", "<").replace("&gt;",">");
			System.out.println("FinalStr \n" + finalStr);
			
//			System.out.println(finalStr);		
			//System.out.println(finalStr.replace(">", ">\n"));		
			
			// Overall index
			int index = finalStr.indexOf("<container", 0);
			
			String ID, title, clipURL;
			
			while(index != -1)
			{
				try
				{
					String sub = finalStr.substring(index, finalStr.indexOf("</container>", index));
					
					startIndex = sub.indexOf("<container id=\"") + "<container id=\"".length();
					endIndex = sub.indexOf("\"", startIndex);
					
					if(startIndex == -1 + "<container id=\"".length() || endIndex == -1)
					{
						throw new StringIndexOutOfBoundsException();
					}
					
					ID = sub.substring(startIndex, endIndex);
					System.out.println("C: Object ID = " + ID);
					
					startIndex = sub.indexOf("<dc:title>") + "<dc:title>".length();
					endIndex = sub.indexOf("</dc:title>");
					
					if(startIndex == -1 + "<dc:title>".length() || endIndex == -1)
					{
						throw new StringIndexOutOfBoundsException();
					}

					title = sub.substring(startIndex, endIndex);
					System.out.println("C: Title     = " + title);
					
					
					
	//				startIndex = sub.lastIndexOf("\">",  sub.indexOf("</res>")) + "\">".length();
	//				endIndex = sub.indexOf("</res>");
	//				System.out.println("C: ClipURL   = " + sub.substring(startIndex, endIndex));
					
	
	//				System.out.println("Title     = " + sub.substring(sub.indexOf("<dc:title>") + "<dc:title>".length(), sub.indexOf("</dc:title>")));
	//				System.out.println("ClipURL   = " + sub.substring(sub.lastIndexOf("\">", sub.indexOf("</res>")) + "\">".length(), sub.indexOf("</res>")));
					

				} catch (StringIndexOutOfBoundsException ex)
				{
					// When an exception is thrown, continue searching without adding anythings
					index = finalStr.indexOf("<container", index+1);
					continue;
				}
				
				clipURL = "";
				duration = "";
				
				ret.add( new String[] { title, ID, clipURL, duration } );
				
				index = finalStr.indexOf("<container", index+1);
			}
			
			
			
			index = finalStr.indexOf("<item", 0);
			
			while(index != -1)
			{
				try
				{
					String sub = finalStr.substring(index, finalStr.indexOf("</item>", index));
					
					startIndex = sub.indexOf("<item id=\"") + "<item id=\"".length();
					endIndex = sub.indexOf("\"", startIndex);
					
					if(startIndex == -1 + "<item id=\"".length() || endIndex == -1)
					{
						throw new StringIndexOutOfBoundsException();
					}

					ID = sub.substring(startIndex, endIndex);
					System.out.println("Object ID = " + ID);
					
					startIndex = sub.indexOf("<dc:title>") + "<dc:title>".length();
					endIndex = sub.indexOf("</dc:title>");
					
					if(startIndex == -1 + "<dc:title>".length() || endIndex == -1)
					{
						throw new StringIndexOutOfBoundsException();
					}

					title = sub.substring(startIndex, endIndex);
					System.out.println("Title     = " + title);
					
					startIndex = sub.lastIndexOf("\">",  sub.indexOf("</res>")) + "\">".length();
					endIndex = sub.indexOf("</res>");
					
					if(startIndex == -1 + "\">".length() || endIndex == -1)
					{
						throw new StringIndexOutOfBoundsException();
					}

					clipURL = sub.substring(startIndex, endIndex);
					System.out.println("ClipURL   = " + clipURL);
					
					System.out.println("SUB = " + sub);
					
					
					startIndex = sub.indexOf("duration=\"") + "duration=\"".length();
					endIndex = sub.indexOf("\"", startIndex);
					
					if(startIndex == -1 + "duration=\"".length() || endIndex == -1)
					{
						throw new StringIndexOutOfBoundsException();
					}
					
					duration = sub.substring(startIndex, endIndex);
					System.out.println("DURATION = " + duration);
					
					
	
	//				System.out.println("Title     = " + sub.substring(sub.indexOf("<dc:title>") + "<dc:title>".length(), sub.indexOf("</dc:title>")));
	//				System.out.println("ClipURL   = " + sub.substring(sub.lastIndexOf("\">", sub.indexOf("</res>")) + "\">".length(), sub.indexOf("</res>")));
				} catch (StringIndexOutOfBoundsException ex)
				{
					index = finalStr.indexOf("<item", index+1);
					continue;
				}
				
				ret.add( new String[] { title, ID, clipURL, duration } );
				
				index = finalStr.indexOf("<item", index+1);
			}
			
//			int bound = new java.util.Random().nextInt(20) + 2;
			
//			for(int i = 1; i < bound; i++)
//			{
//				String number = Integer.toString(i);
//			//	ret.add( new String[] { "Element", "Object ID", "URL" } );
//				ret.add( new String[] { "Element " + number , number, "URL " + number } );
//			}
			
//			processResults(resultStr);
	
	
		System.out.println("Return List\n");
		for(int i = 0; i < ret.size(); i++)
		{
			//System.out.println(((String[])(ret.get(i)))[0] + " " + ((String[])(ret.get(i)))[1] + " " + ((String[])(ret.get(i)))[2]);
		}
		return ret;
	}
	
	public static List<Object> getItems(String soapMsg, String URLInput) throws IOException
	{

//		String resultStr = getSoapMsg(String soapMsg, StringURLInput);
		String resultStr = getSoapMsg(soapMsg, URLInput);
		System.out.println("---\n" + resultStr + "\n---");
			if(resultStr == null)
			{
				// ERROR
				ArrayList<Object> ret = new ArrayList<Object>();
				
				ret.add( new String[] { "ERROR", "0", "http://", "" }) ;
				return new ArrayList<Object>();
			}
			else
			{
				return parseSoapMessage(resultStr);
			}
	}
}