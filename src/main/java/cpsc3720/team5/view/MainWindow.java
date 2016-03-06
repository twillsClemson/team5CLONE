package cpsc3720.team5.view;

import javax.imageio.ImageIO;
import javax.media.Time;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import cpsc3720.team5.controller.*;
import cpsc3720.team5.model.Album;
import cpsc3720.team5.model.Settings;
import cpsc3720.team5.model.Song;
import cpsc3720.team5.model.UserProfiles;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MainWindow {
	
	static public Time mediaTime = new Time(0.0);
	static public Song selectedSong = null;
	private Album selectedAlbum = null;
	private ArrayList<UserProfiles> profiles = null;
	private ButtonGroup userRadioButtons;

	
	boolean playPauseSelected = false;
	boolean stopSelected = false;
	JFrame frameMain = new JFrame("Media Player Login");
	JPanel panelControl = new JPanel();
	JPanel loginPanel = new JPanel();
	JPanel pnlMediaPlayer = new JPanel();
	CardLayout cl = new CardLayout();
	private JPasswordField txtfldPIN;
	private JTable albumTable;
	private SettingsWindow settingsWindow = null;
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	
	private JLabel lblAlbumTitle;
	private JLabel lblAlbumArtist;
	
	JTabbedPane tabbedPane;
//	private Map<DefaultMutableTreeNode, String> songURLs;
//	private Map<DefaultMutableTreeNode, ArrayList<DefaultMutableTreeNode>> albumContents;
	
	JButton favoriteAlbumButton = new JButton("Favorite Album");
	private JButton btnFavoriteAlbum;
	
	

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
	
	public void getUsersInformation()
	{
		profiles = Settings.getInstance().getProfiles();
		
	}

	public MainWindow() {
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		
		getUsersInformation();
		
		frameMain.setResizable(false);
		frameMain.setSize(1000, 750);

		panelControl.setLayout(cl);

		panelControl.add(loginPanel, "1");
		
		JLabel lblEnterPin = new JLabel("Enter PIN:");
		lblEnterPin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtfldPIN = new JPasswordField(4);
		txtfldPIN.setAction(
				new AbstractAction()
				{
				    @Override
				    public void actionPerformed(ActionEvent e)
				    {
				    	checkPassword();
				    }
				}
		);
		
		PlainDocument document = (PlainDocument) txtfldPIN.getDocument();
        document.setDocumentFilter(new DocumentFilter() {

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

                if (string.length() <= 4) {
                    super.replace(fb, offset, length, text, attrs); //To change body of generated methods, choose Tools | Templates.
                }
            }

        });
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkPassword();
			}
		});
		
		JScrollPane scrollPaneUserProfiles = new JScrollPane();
		scrollPaneUserProfiles.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		
		JLabel lblMediaPlayer = new JLabel("Media Player");
		lblMediaPlayer.setFont(new Font("Tahoma", Font.BOLD, 46));
		int count = 0;
		for(int i = 0; i < profiles.size(); i++)
		{
			count += 1;
		}
		
		GroupLayout gl_loginPanel = new GroupLayout(loginPanel);
		gl_loginPanel.setHorizontalGroup(
			gl_loginPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addContainerGap(48, Short.MAX_VALUE)
					.addComponent(scrollPaneUserProfiles, GroupLayout.PREFERRED_SIZE, 902, GroupLayout.PREFERRED_SIZE)
					.addGap(44))
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addGap(358)
					.addComponent(lblMediaPlayer)
					.addContainerGap(339, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_loginPanel.createSequentialGroup()
					.addGap(350)
					.addComponent(lblEnterPin)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtfldPIN, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(352, Short.MAX_VALUE))
		);
		gl_loginPanel.setVerticalGroup(
			gl_loginPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addContainerGap(51, Short.MAX_VALUE)
					.addComponent(lblMediaPlayer)
					.addGap(32)
					.addComponent(scrollPaneUserProfiles, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
					.addGap(94)
					.addGroup(gl_loginPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterPin)
						.addComponent(txtfldPIN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogin))
					.addGap(124))
		);
		
		JPanel pnlUsers = new JPanel();
		scrollPaneUserProfiles.setViewportView(pnlUsers);
		
		populateUserImages(pnlUsers);
		pnlUsers.setLayout(new GridLayout(1, 0, 0, 0));
		
		loginPanel.setLayout(gl_loginPanel);
		
		panelControl.add(pnlMediaPlayer, "2");
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				resetAlbumTable();
				
				JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
				int index = sourceTabbedPane.getSelectedIndex();
				System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
			}
		};
		tabbedPane.addChangeListener(changeListener);
		
//		tabbedPane.addTab("Favorites", createFavoritesTemplate("Favorites"));
//		tabbedPane.addTab("Library", createLibraryTemplate("Library"));
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtfldPIN.setText("");
				frameMain.setTitle("Media Player Login");
				cl.show(panelControl, "1");
				
				// Remove the library tab (to be recreated when a new user logs in)
				tabbedPane.remove(1);
				tabbedPane.remove(0);
				
				// Clear library albums
				Settings.getInstance().getLibraryAlbums().clear();
			}
		});
		JButton btnSettings = new JButton("Settings");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				settingsWindow = new SettingsWindow();
				settingsWindow.main(null);
			}
		});
		
		JPanel pnlPlayer = new JPanel();
		
		GroupLayout gl_pnlMediaPlayer = new GroupLayout(pnlMediaPlayer);
		gl_pnlMediaPlayer.setHorizontalGroup(
			gl_pnlMediaPlayer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlMediaPlayer.createSequentialGroup()
					.addGroup(gl_pnlMediaPlayer.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlMediaPlayer.createSequentialGroup()
							.addGap(14)
							.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_pnlMediaPlayer.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnLogout, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
						.addGroup(gl_pnlMediaPlayer.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnSettings, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pnlPlayer, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_pnlMediaPlayer.setVerticalGroup(
			gl_pnlMediaPlayer.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_pnlMediaPlayer.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlMediaPlayer.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlPlayer, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
						.addGroup(gl_pnlMediaPlayer.createSequentialGroup()
							.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnSettings)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnLogout)))
					.addContainerGap())
		);
		
		JPanel pnlAlbumInfo = new JPanel();
		
		JPanel pnlPlayControl = new JPanel();
		
		JPanel pnlSongList = new JPanel();
		
		GroupLayout gl_pnlPlayer = new GroupLayout(pnlPlayer);
		gl_pnlPlayer.setHorizontalGroup(
			gl_pnlPlayer.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_pnlPlayer.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlPlayer.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlSongList, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
						.addComponent(pnlAlbumInfo, GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
						.addComponent(pnlPlayControl, GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_pnlPlayer.setVerticalGroup(
			gl_pnlPlayer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlPlayer.createSequentialGroup()
					.addContainerGap()
					.addComponent(pnlAlbumInfo, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(pnlSongList, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(pnlPlayControl, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JScrollPane scrlPaneSongs = new JScrollPane();
		GroupLayout gl_pnlSongList = new GroupLayout(pnlSongList);
		gl_pnlSongList.setHorizontalGroup(
			gl_pnlSongList.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSongList.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrlPaneSongs, GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_pnlSongList.setVerticalGroup(
			gl_pnlSongList.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSongList.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrlPaneSongs, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		albumTable = new JTable() {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		albumTable.setShowGrid(false);
		albumTable.setBackground(null);
		albumTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JTableHeader header = albumTable.getTableHeader();
		header.setBackground(Color.black);
		header.setForeground(Color.white);
		albumTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null}
			},
			new String[] {
				"#", "Track Name", "Artist", "Duration"
			}
		));
		
		TableColumn trackNumber = albumTable.getColumnModel().getColumn(0);
		trackNumber.setPreferredWidth(50);
		trackNumber.setCellRenderer(centerRenderer);
		TableColumn trackName = albumTable.getColumnModel().getColumn(1);
		trackName.setPreferredWidth(400);
		trackName.setCellRenderer(centerRenderer);
		TableColumn tcArtist = albumTable.getColumnModel().getColumn(0);
		tcArtist.setPreferredWidth(100);
		tcArtist.setCellRenderer(centerRenderer);
		TableColumn trackDuration = albumTable.getColumnModel().getColumn(2);
		trackDuration.setPreferredWidth(125);
		trackDuration.setCellRenderer(centerRenderer);
		albumTable.getTableHeader().setReorderingAllowed(false);
		albumTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrlPaneSongs.setViewportView(albumTable);
		pnlSongList.setLayout(gl_pnlSongList);
		
		lblAlbumTitle = new JLabel("Select an album");
		lblAlbumTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlbumTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		lblAlbumArtist = new JLabel("");
		lblAlbumArtist.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlbumArtist.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		btnFavoriteAlbum = new JButton("");
		
		btnFavoriteAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				onBtnFavoritesPress();
			}
		});
		btnFavoriteAlbum.setEnabled(false);
		
		ImageIcon icon = new ImageIcon(MainWindow.class.getResource("/icons/Favorite.png"));
		btnFavoriteAlbum.setIcon(new ImageIcon(icon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		
		icon = new ImageIcon(MainWindow.class.getResource("/icons/Favorite_disabled.png"));
		btnFavoriteAlbum.setDisabledIcon(new ImageIcon(icon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));

		
		GroupLayout gl_pnlAlbumInfo = new GroupLayout(pnlAlbumInfo);
		gl_pnlAlbumInfo.setHorizontalGroup(
			gl_pnlAlbumInfo.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlAlbumInfo.createSequentialGroup()
					.addContainerGap(168, Short.MAX_VALUE)
					.addGroup(gl_pnlAlbumInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_pnlAlbumInfo.createSequentialGroup()
							.addComponent(lblAlbumTitle, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE)
							.addGap(64))
						.addGroup(Alignment.TRAILING, gl_pnlAlbumInfo.createSequentialGroup()
							.addComponent(lblAlbumArtist, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
							.addGap(135)))
					.addComponent(btnFavoriteAlbum, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
		);
		gl_pnlAlbumInfo.setVerticalGroup(
			gl_pnlAlbumInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlAlbumInfo.createSequentialGroup()
					.addGroup(gl_pnlAlbumInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlAlbumInfo.createSequentialGroup()
							.addGap(18)
							.addComponent(lblAlbumTitle)
							.addGap(18)
							.addComponent(lblAlbumArtist))
						.addGroup(gl_pnlAlbumInfo.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnFavoriteAlbum, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		pnlAlbumInfo.setLayout(gl_pnlAlbumInfo);
		
		JLabel lblZero = new JLabel("0:00");
		lblZero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JSlider sliderSong = new JSlider();
		sliderSong.setValue(0);
		
		JLabel lblSongDuration = new JLabel("1:00");
		lblSongDuration.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		icon = new ImageIcon(MainWindow.class.getResource("/icons/Pause.png"));
		
		icon = new ImageIcon(MainWindow.class.getResource("/icons/play.png"));

		JButton btnStop = new JButton("");
//		btnStop.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/Stop.png")));
		btnStop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		icon = new ImageIcon(MainWindow.class.getResource("/icons/Stop.png"));
		btnStop.setIcon(new ImageIcon(icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		
		JLabel lblSongName = new JLabel("Song Name - Album Name");
		lblSongName.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JToggleButton tglbtnPlayPause = new JToggleButton("");
		tglbtnPlayPause.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/Play.png")));
		tglbtnPlayPause.setSelectedIcon(new ImageIcon(MainWindow.class.getResource("/icons/Pause.png")));
		
		tglbtnPlayPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedSong = selectedAlbum.getSong((String) albumTable.getValueAt(albumTable.getSelectedRow(), 1));
				if(playPauseSelected) {
					playPauseSelected = true;
						PlayerController.playSong();
				} else {
					playPauseSelected = false;
						PlayerController.pauseSong(selectedSong);
				}
			}
		});
		
		GroupLayout gl_pnlPlayControl = new GroupLayout(pnlPlayControl);
		gl_pnlPlayControl.setHorizontalGroup(
			gl_pnlPlayControl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlPlayControl.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlPlayControl.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlPlayControl.createSequentialGroup()
							.addComponent(lblZero)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(sliderSong, GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
							.addGap(12)
							.addComponent(lblSongDuration)
							.addContainerGap())
						.addGroup(gl_pnlPlayControl.createSequentialGroup()
							.addGroup(gl_pnlPlayControl.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlPlayControl.createSequentialGroup()
									.addGap(16)
									.addComponent(tglbtnPlayPause, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnStop, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblSongName))
							.addGap(220))))
		);
		gl_pnlPlayControl.setVerticalGroup(
			gl_pnlPlayControl.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlPlayControl.createSequentialGroup()
					.addComponent(lblSongName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlPlayControl.createParallelGroup(Alignment.LEADING)
						.addComponent(tglbtnPlayPause, 0, 0, Short.MAX_VALUE)
						.addComponent(btnStop))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlPlayControl.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblSongDuration)
						.addComponent(lblZero)
						.addComponent(sliderSong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		pnlPlayControl.setLayout(gl_pnlPlayControl);
		pnlPlayer.setLayout(gl_pnlPlayer);
		pnlMediaPlayer.setLayout(gl_pnlMediaPlayer);
		cl.show(panelControl, "1");

		frameMain.getContentPane().add(panelControl);
		frameMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameMain.setVisible(true);
		txtfldPIN.requestFocusInWindow();
	}

	// Populates users on the main login page to be selected and logged into
	private void populateUserImages(JPanel pnlUsers)
	{
        userRadioButtons = new ButtonGroup();
        
		for(int i = 0; i < profiles.size(); i++)
		{
			UserProfiles user = profiles.get(i);
			JPanel pnlUserOne = new JPanel();
			pnlUserOne.setBounds(10, 11, 216, 316);
			pnlUsers.add(pnlUserOne);
			
			JButton btnIconUserOne = new JButton("");
			btnIconUserOne.setIcon(new ImageIcon(MainWindow.class.getResource(user.getProfilePic())));
			btnIconUserOne.setOpaque(false);
			btnIconUserOne.setContentAreaFilled(false);
			btnIconUserOne.setBorderPainted(false);
			
			JRadioButton rdbtnUserOne = new JRadioButton(user.getName());
			rdbtnUserOne.setActionCommand(user.getName());
			rdbtnUserOne.setFont(new Font("Tahoma", Font.PLAIN, 16));
			GroupLayout gl_pnlUserOne = new GroupLayout(pnlUserOne);
			gl_pnlUserOne.setHorizontalGroup(
				gl_pnlUserOne.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_pnlUserOne.createSequentialGroup()
						.addGroup(gl_pnlUserOne.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_pnlUserOne.createSequentialGroup()
								.addContainerGap()
								.addComponent(btnIconUserOne, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_pnlUserOne.createSequentialGroup()
								.addGap(58)
								.addComponent(rdbtnUserOne)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			gl_pnlUserOne.setVerticalGroup(
				gl_pnlUserOne.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_pnlUserOne.createSequentialGroup()
						.addGap(64)
						.addComponent(btnIconUserOne, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(rdbtnUserOne)
						.addContainerGap(58, Short.MAX_VALUE))
			);
			pnlUserOne.setLayout(gl_pnlUserOne);
	        userRadioButtons.add(rdbtnUserOne);
		}
		
	}
	
	private void checkPassword()
	{
		ButtonModel model = userRadioButtons.getSelection();
		
		if(model == null)
		{
			txtfldPIN.setText("");
			JOptionPane.showMessageDialog(null, "No user selected! Please select a user and try again.", "Error Message", JOptionPane.INFORMATION_MESSAGE);	
			return;
		}
		
		String username = model.getActionCommand();		
		UserProfiles loginUser = Settings.getInstance().getProfileByName(username);
		
		if(txtfldPIN.getText().length() != 4)
		{
			txtfldPIN.setText("");
			JOptionPane.showMessageDialog(null, "Invalid PIN entered! PINs must be 4 characters. Please try again.", "Error Message", JOptionPane.INFORMATION_MESSAGE);			
		}
		
		else
		{		
			if(loginUser.validatePIN(Integer.parseInt(txtfldPIN.getText())))
			{
				// Login user
				txtfldPIN.setText("");
				frameMain.setTitle("Media Player");
				
				Settings.getInstance().setCurrentUser(loginUser);
				
				// Resolve approved albums and favorite albums
				Album album = new Album("Album A");
				Settings.getInstance().addApprovedAlbum(album, 1);
				
				Map<String, Album> libraryAlbums = Settings.getInstance().getLibraryAlbums();
				libraryAlbums.put(album.getName(), album);
				album = new Album("Album B");
				Settings.getInstance().addApprovedAlbum(album, 3);
				libraryAlbums.put(album.getName(), album);
				album = new Album("Album C");
				Settings.getInstance().addApprovedAlbum(album, 3);
				libraryAlbums.put(album.getName(), album);
				
				
				loginUser.addFavorite(Settings.getInstance().getAlbum("Album A"));
				/////////////////////
				
				// Calculated in this order because createLibraryTemplate() generates albums
				Component compLibrary = createLibraryTemplate();
				tabbedPane.addTab("Favorites", createFavoritesTemplate());
				tabbedPane.addTab("Library", compLibrary);

				cl.show(panelControl, "2");	   				
			}
			
			else
			{
				txtfldPIN.setText("");
				JOptionPane.showMessageDialog(null, "Incorrect PIN entered! Please try again.", "Error Message", JOptionPane.INFORMATION_MESSAGE);	
			}
			
			
			
			
			// Load settings for individual user should go here (setting the currentUser variable in the process)
			// But for now, default some things (Please delete/rewrite between these slashes when you have something functional
			/////////////////////
			
			/*

			// Resolve currentUser
			UserProfiles currentUser = new UserProfiles();
			currentUser.setName("John Smith");
			currentUser.setRestrictionLevel(5);
			
			// Remember currentUser
			Settings.getInstance().setCurrentUser(currentUser);
			
			// Resolve approved albums and favorite albums
			Album album = new Album("Album A");
			Settings.getInstance().addApprovedAlbum(album, 1);
			
			Map<String, Album> libraryAlbums = Settings.getInstance().getLibraryAlbums();
			libraryAlbums.put(album.getName(), album);
			album = new Album("Album B");
			Settings.getInstance().addApprovedAlbum(album, 3);
			libraryAlbums.put(album.getName(), album);
			album = new Album("Album C");
			Settings.getInstance().addApprovedAlbum(album, 3);
			libraryAlbums.put(album.getName(), album);
			
			
			currentUser.addFavorite(Settings.getInstance().getAlbum("Album A"));
			/////////////////////
			
			// Calculated in this order because createLibraryTemplate() generates albums
			Component compLibrary = createLibraryTemplate();
			tabbedPane.addTab("Favorites", createFavoritesTemplate());
			tabbedPane.addTab("Library", compLibrary);
			
			*/ 
		}
	}
	
	// Create JPanels that are used in Favorites tab
	private JPanel createFavoritesTemplate()
	{
		JPanel pnlTreePanel = new JPanel();
		pnlTreePanel.setName("Favorites");
		
		UIManager.put("Tree.rendererFillBackground", false);
		JTree treeFavLib = new JTree();
		
		treeFavLib.setBackground(null);
		treeFavLib.setModel(new DefaultTreeModel( 
				TreeNodeController.calculateFavoritesTreeNode(Settings.getInstance().getCurrentUser().getFavorites())));

		treeFavLib.setName("Favorites" + "Tree");
		GroupLayout gl_pnlTreePanel = new GroupLayout(pnlTreePanel);
		gl_pnlTreePanel.setHorizontalGroup(
			gl_pnlTreePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTreePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(treeFavLib, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_pnlTreePanel.setVerticalGroup(
			gl_pnlTreePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTreePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(treeFavLib, GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
					.addContainerGap())
		);
		pnlTreePanel.setLayout(gl_pnlTreePanel);
		return pnlTreePanel;
	}
	
	// Create JPanels that are used in Library tab
	private JPanel createLibraryTemplate()
	{
		JPanel template = new JPanel();
		template.setName("Library");
		
		UIManager.put("Tree.rendererFillBackground", false);
		final JTree tree = new JTree();
		
		tree.setBackground(null);
		try
		{
			tree.setModel(new DefaultTreeModel(TreeNodeController.calculateTreeNode("Library", Settings.getInstance().getURL(),
					Settings.getInstance().getCurrentUser().getRestrictionLevel())));
		} catch(IOException ex)
		{
			tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("No compatible server found")));
		}
		
		// Debug print out all albums and contents
//		for(Iterator<Entry<String, Album> > i = libraryAlbums.entrySet().iterator(); i.hasNext();)
//		{
//			Album next = (Album) ((Entry<String, Album>) i.next()).getValue();
//			System.out.println(next.getName());
//			for(Iterator<Song> j = next.getSongs().iterator(); j.hasNext();)
//			{
//				Song nextSong = (Song) j.next();
//				System.out.println("-  " + nextSong.getName() + " | " + nextSong.getArtist() + " | " + nextSong.getLength() + " | " + nextSong.getURL());
//			}
//		}
		
		tree.addTreeSelectionListener(new TreeSelectionListener()
		{
			public void valueChanged(TreeSelectionEvent e)
			{
				String[] info = onLibraryTreeChange(((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).toString());
				
				lblAlbumTitle.setText(info[0]);
				lblAlbumArtist.setText(info[1]);
			}
		});
		

		tree.setName("Library" + "Tree");
		GroupLayout gl_template = new GroupLayout(template);
		gl_template.setHorizontalGroup(
			gl_template.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_template.createSequentialGroup()
					.addContainerGap()
					.addComponent(tree, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_template.setVerticalGroup(
			gl_template.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_template.createSequentialGroup()
					.addContainerGap()
					.addComponent(tree, GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
					.addContainerGap())
		);
		template.setLayout(gl_template);
		return template;
	}
	
	// Adds the specified album to the album table
	// obj[0] = Track number
	// obj[1] = Track name
	// obj[2] = Artist
	// obj[3] = Song duration
	public void addToAlbumTable(Object[] obj)
	{
		((DefaultTableModel) albumTable.getModel()).addRow(obj);
	}
	
	// Clears the album table of all entries, and resets the album title and artist labels
	public void resetAlbumTable()
	{
		lblAlbumArtist.setText("");
		lblAlbumTitle.setText("Select an Album");
		DefaultTableModel model = ((DefaultTableModel) albumTable.getModel());
		
		while(model.getRowCount() > 0)
		{
			model.removeRow(0);
		}
	}
	
	// Downloads the selected library album, and adds it to the current user's favorites
	public void onBtnFavoritesPress()
	{
		btnFavoriteAlbum.setEnabled(false);
		UserProfiles currentUser = Settings.getInstance().getCurrentUser();
		
		if(currentUser.hasFavorite(selectedAlbum))
		{
			return;
		}
		currentUser.addFavorite(selectedAlbum);
		ArrayList<Song> songs;
		
		songs = Settings.getInstance().getLibraryAlbums().get(selectedAlbum.getName()).getSongs();
		 
		try 
		{
			for(Iterator<Song> i = songs.iterator(); i.hasNext();)
			{
				Song next = (Song) i.next();						 
				DownloadController.downloadTrack(next.getURL());
			}
		} catch (IOException e) 
		{
			currentUser.removeFavorite(selectedAlbum);
			btnFavoriteAlbum.setEnabled(true);
		}
		return;
		
	}
	
	// When a new node is selected in the library tree,
	// * Check to see if the selected node is an album.
	//   - If the node is an album,
	//      ~ Set the MainWindow selectedAlbum variable to the album with the same name
	//      ~ If the album is not already in the user's faovirtes, enable the favorite button
	//      ~ Add all songs in the album to the album table
	//   - If the node is not an album,
	//      ~ Disable the favorites button
	// Returns an array of Strings
	// * ret[0] = String containing new value for lblAlbumTitle
	// * ret[1] = String containing new value for lblAlbumArtist
	private String[] onLibraryTreeChange(String treeName)
	{
		resetAlbumTable();
		String[] ret = {"Select an Album", ""};
		
		Album album = Settings.getInstance().getLibraryAlbums().get( treeName );
		if(album != null)
		{
			selectedAlbum = album;
			ret[0] = album.getName();
			
			if(album.getSongs().size() != 0)
			{
				ret[1] = album.getSongs().get(0).getArtist();
			}

			if(!Settings.getInstance().getCurrentUser().hasFavorite(album))
			{
				btnFavoriteAlbum.setEnabled(true);
			}
			else
			{
				btnFavoriteAlbum.setEnabled(false);
			}
			
			int counter = 1;
			for(Iterator<Song> j = album.getSongs().iterator(); j.hasNext();)
			{
				Song song = j.next();
				Object[] row = new Object[]{ new Integer(counter++), song.getName(), song.getArtist(), song.getLength() };
				addToAlbumTable(row);
			}	
		}
		else
		{
			btnFavoriteAlbum.setEnabled(false);
		}
		
		return ret;
	}
}
