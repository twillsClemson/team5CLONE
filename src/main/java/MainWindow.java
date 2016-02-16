import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import javax.swing.border.EtchedBorder;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class MainWindow {

	JFrame frame = new JFrame("Media Player Login");
	JPanel panelControl = new JPanel();
	JPanel loginPanel = new JPanel();
	JPanel mediaPlayerPanel = new JPanel();
	JButton userOneButton = new JButton("User One");
	JButton userTwoButton = new JButton("User Two");
	JButton userThreeButton = new JButton("User Three");
	JButton logoutButton = new JButton("Logout");
	CardLayout cl = new CardLayout();
	private final JPanel playPanel = new JPanel();
	private final JButton btnSettings = new JButton("Settings");
	private final JPanel albumInfoPanel = new JPanel();
	private final JPanel playControlPanel = new JPanel();
	private final JPanel albumnPlaylistPanel = new JPanel();
	private final JPanel hierarchyBrowsePanel = new JPanel();
	private JPasswordField passwordField;
	private final JSlider slider = new JSlider();
	private final JLabel elapsedLabel = new JLabel("0:00");
	private final JLabel totalTimeLabel = new JLabel("1:00");
	private final JButton btnPlay = new JButton("Play");
	private final JButton btnStop = new JButton("Stop");
	private final JButton btnPause = new JButton("Pause");
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainWindow();
			}
		});
	}

	public MainWindow() {
		totalTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		elapsedLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		elapsedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		slider.setValue(0);
		frame.setResizable(false);
		frame.setSize(1000, 750);

		panelControl.setLayout(cl);

		panelControl.add(loginPanel, "1");
		
		JLabel lblEnterPin = new JLabel("Enter PIN:");
		lblEnterPin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		passwordField = new JPasswordField(4);
		
		PlainDocument document = (PlainDocument) passwordField.getDocument();
        document.setDocumentFilter(new DocumentFilter() {

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

                if (string.length() <= 4) {
                    super.replace(fb, offset, length, text, attrs); //To change body of generated methods, choose Tools | Templates.
                }
            }

        });
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(passwordField.getText().length() == 4 && !passwordField.getText().equals("0000"))
				{
					passwordField.setText("");
					frame.setTitle("Media Player");
					cl.show(panelControl, "2");
				}
				else
				{
					passwordField.setText("");
					JOptionPane.showMessageDialog(null, "Incorrect PIN entered! Please try again.", "Error Message", JOptionPane.INFORMATION_MESSAGE);				    
				}
			}
		});
		
		GroupLayout gl_loginPanel = new GroupLayout(loginPanel);
		gl_loginPanel.setHorizontalGroup(
			gl_loginPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addGap(116)
					.addComponent(userOneButton, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_loginPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_loginPanel.createSequentialGroup()
							.addComponent(lblEnterPin)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_loginPanel.createSequentialGroup()
							.addComponent(userTwoButton, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(userThreeButton, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
							.addGap(145))))
		);
		gl_loginPanel.setVerticalGroup(
			gl_loginPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addGap(158)
					.addGroup(gl_loginPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(userTwoButton, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
						.addComponent(userThreeButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addComponent(userOneButton, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
					.addGap(95)
					.addGroup(gl_loginPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterPin)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(loginButton))
					.addGap(333))
		);
		loginPanel.setLayout(gl_loginPanel);

		userOneButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		userTwoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		userThreeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		panelControl.add(mediaPlayerPanel, "2");
		
		JPanel browsePanel = new JPanel();
		browsePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout gl_mediaPlayerPanel = new GroupLayout(mediaPlayerPanel);
		gl_mediaPlayerPanel.setHorizontalGroup(
			gl_mediaPlayerPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_mediaPlayerPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(browsePanel, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(playPanel, GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
					.addContainerGap())
		);
		playPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		gl_mediaPlayerPanel.setVerticalGroup(
			gl_mediaPlayerPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_mediaPlayerPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_mediaPlayerPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(playPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
						.addComponent(browsePanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE))
					.addContainerGap())
		);
		GroupLayout gl_playPanel = new GroupLayout(playPanel);
		gl_playPanel.setHorizontalGroup(
			gl_playPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_playPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_playPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(playControlPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
						.addComponent(albumnPlaylistPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
						.addComponent(albumInfoPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE))
					.addContainerGap())
		);
		albumInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		playControlPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		albumnPlaylistPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		gl_playPanel.setVerticalGroup(
			gl_playPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_playPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(albumInfoPanel, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(albumnPlaylistPanel, GroupLayout.PREFERRED_SIZE, 446, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(playControlPanel, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblAlbumName = new JLabel("Album Name");
		lblAlbumName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel lblArtistName = new JLabel("Artist Name");
		lblArtistName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnFavoriteAlbum = new JButton("Favorite Album");
		GroupLayout gl_albumInfoPanel = new GroupLayout(albumInfoPanel);
		gl_albumInfoPanel.setHorizontalGroup(
			gl_albumInfoPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_albumInfoPanel.createSequentialGroup()
					.addGroup(gl_albumInfoPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_albumInfoPanel.createSequentialGroup()
							.addGap(271)
							.addComponent(lblAlbumName))
						.addGroup(gl_albumInfoPanel.createSequentialGroup()
							.addGap(297)
							.addComponent(lblArtistName)
							.addPreferredGap(ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
							.addComponent(btnFavoriteAlbum)))
					.addContainerGap())
		);
		gl_albumInfoPanel.setVerticalGroup(
			gl_albumInfoPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_albumInfoPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_albumInfoPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnFavoriteAlbum)
						.addGroup(gl_albumInfoPanel.createSequentialGroup()
							.addComponent(lblAlbumName)
							.addGap(18)
							.addComponent(lblArtistName)))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		albumInfoPanel.setLayout(gl_albumInfoPanel);
		GroupLayout gl_playControlPanel = new GroupLayout(playControlPanel);
		gl_playControlPanel.setHorizontalGroup(
			gl_playControlPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_playControlPanel.createSequentialGroup()
					.addGroup(gl_playControlPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_playControlPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(elapsedLabel, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(slider, GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(totalTimeLabel, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_playControlPanel.createSequentialGroup()
							.addGap(217)
							.addComponent(btnStop, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnPlay, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnPause, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_playControlPanel.setVerticalGroup(
			gl_playControlPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_playControlPanel.createSequentialGroup()
					.addGroup(gl_playControlPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_playControlPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_playControlPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnPlay)
								.addComponent(btnStop)
								.addComponent(btnPause))
							.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
							.addComponent(slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_playControlPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(totalTimeLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_playControlPanel.createSequentialGroup()
							.addGap(78)
							.addComponent(elapsedLabel, GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)))
					.addGap(19))
		);
		playControlPanel.setLayout(gl_playControlPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_albumnPlaylistPanel = new GroupLayout(albumnPlaylistPanel);
		gl_albumnPlaylistPanel.setHorizontalGroup(
			gl_albumnPlaylistPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_albumnPlaylistPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_albumnPlaylistPanel.setVerticalGroup(
			gl_albumnPlaylistPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_albumnPlaylistPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Track Number", "Song Name", "Song Duration"
			}
		));
		scrollPane.setViewportView(table);
		albumnPlaylistPanel.setLayout(gl_albumnPlaylistPanel);
		playPanel.setLayout(gl_playPanel);
		
				logoutButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						frame.setTitle("Media Player Login");
						cl.show(panelControl, "1");
					}
				});
		GroupLayout gl_browsePanel = new GroupLayout(browsePanel);
		gl_browsePanel.setHorizontalGroup(
			gl_browsePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_browsePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_browsePanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(hierarchyBrowsePanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
						.addComponent(logoutButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
						.addComponent(btnSettings, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_browsePanel.setVerticalGroup(
			gl_browsePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_browsePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(hierarchyBrowsePanel, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnSettings)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(logoutButton)
					.addContainerGap())
		);
		
		JTree libraryTree = new JTree();
		libraryTree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Library") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("Beethoven");
						node_1.add(new DefaultMutableTreeNode("Song 1"));
						node_1.add(new DefaultMutableTreeNode("Song 2"));
						node_1.add(new DefaultMutableTreeNode("Song 3"));
						node_1.add(new DefaultMutableTreeNode("Song 4"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Bach");
						node_1.add(new DefaultMutableTreeNode("Song 1"));
						node_1.add(new DefaultMutableTreeNode("Song 2"));
						node_1.add(new DefaultMutableTreeNode("Song 3"));
						node_1.add(new DefaultMutableTreeNode("Song 4"));
					add(node_1);
				}
			}
		));
		libraryTree.setBackground(new Color(240,240,240));
		libraryTree.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GroupLayout gl_hierarchyBrowsePanel = new GroupLayout(hierarchyBrowsePanel);
		gl_hierarchyBrowsePanel.setHorizontalGroup(
			gl_hierarchyBrowsePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_hierarchyBrowsePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(libraryTree, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_hierarchyBrowsePanel.setVerticalGroup(
			gl_hierarchyBrowsePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_hierarchyBrowsePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(libraryTree, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
					.addContainerGap())
		);
		hierarchyBrowsePanel.setLayout(gl_hierarchyBrowsePanel);
		browsePanel.setLayout(gl_browsePanel);
		mediaPlayerPanel.setLayout(gl_mediaPlayerPanel);
		cl.show(panelControl, "1");

		frame.getContentPane().add(panelControl);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

	}
}
