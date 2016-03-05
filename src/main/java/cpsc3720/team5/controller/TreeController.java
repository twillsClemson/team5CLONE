//package cpsc3720.team5.controller;
//
//import java.util.Iterator;
//
//import cpsc3720.team5.model.Album;
//import cpsc3720.team5.model.Song;
//
//public class TreeController {
//	
//	public static boolean handleLibraryTreeChange(String[] names, Album album)
//	{
//		if(album != null)
//		{
//			
//			names[0] = album.getName();
//			
//			if(album.getSongs().size() != 0)
//			{
//				names[1] = album.getSongs().get(0).getArtist();
//			}
//
//			if(!currentUser.hasFavorite(album))
//			{
//				btnFavoriteAlbum.setEnabled(true);
//			}
//			else
//			{
//				btnFavoriteAlbum.setEnabled(false);
//			}
//			
//			int counter = 1;
//			for(Iterator<Song> j = album.getSongs().iterator(); j.hasNext();)
//			{
//				Song song = j.next();
//				Object[] row = new Object[]{ new Integer(counter++), song.getName(), song.getArtist(), song.getLength() };
//				addToAlbumTable(row);
////				System.out.println("  " + nextJ.getName() + " | " + nextJ.getLength() + " | " + nextJ.getURL());
//			}
//			
//		}
//		else
//		{
//			btnFavoriteAlbum.setEnabled(false);
//		}
//		
//	}
//
//}
