package cpsc3720.team5.model;


public class main {

	public static void main(String[] args) {
		Settings set = Settings.getInstance();
		Album a = new Album();
//		set.addApprovedAlbum(a.approvalLevel, a);
		set.writeSettings();
		set.readSettings();
	}

}
