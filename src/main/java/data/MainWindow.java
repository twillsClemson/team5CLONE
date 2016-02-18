package data;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class MainWindow {

	JFrame frame = new JFrame("Media Player Login");
	JPanel panelControl = new JPanel();
	JPanel loginPanel = new JPanel();
	JPanel mediaPlayerPanel = new JPanel();
	JButton userOneButton = new JButton("");
	JButton userTwoButton = new JButton("");
	JButton userThreeButton = new JButton("");
	CardLayout cl = new CardLayout();
	private JPasswordField passwordField;
	private JTable albumTable;
	private static SettingsWindow settingsWindow = null;
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

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
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		userOneButton.setBorder(emptyBorder);
		userOneButton.setOpaque(false);
		userOneButton.setContentAreaFilled(false);
		userOneButton.setBorderPainted(false);
		
		userTwoButton.setBorder(emptyBorder);
		userTwoButton.setOpaque(false);
		userTwoButton.setContentAreaFilled(false);
		userTwoButton.setBorderPainted(false);
		
		userThreeButton.setBorder(emptyBorder);
		userThreeButton.setOpaque(false);
		userThreeButton.setContentAreaFilled(false);
		userThreeButton.setBorderPainted(false);
		
		frame.setResizable(false);
		frame.setSize(1000, 750);

		panelControl.setLayout(cl);

		panelControl.add(loginPanel, "1");
		
		JLabel lblEnterPin = new JLabel("Enter PIN:");
		lblEnterPin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		passwordField = new JPasswordField(4);
		passwordField.setAction(
				new AbstractAction()
				{
				    @Override
				    public void actionPerformed(ActionEvent e)
				    {
				    	checkPassword();
				    }
				}
		);
		
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
				checkPassword();
			}
		});
		
		JLabel lblUser = new JLabel("User 1");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblUser_1 = new JLabel("User 2");
		lblUser_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblUser_2 = new JLabel("User 3");
		lblUser_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		
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
							.addComponent(userThreeButton, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
							.addGap(141))))
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addGap(215)
					.addComponent(lblUser)
					.addGap(180)
					.addComponent(lblUser_1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addGap(182)
					.addComponent(lblUser_2, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(257, Short.MAX_VALUE))
		);
		gl_loginPanel.setVerticalGroup(
			gl_loginPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addGap(158)
					.addGroup(gl_loginPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(userTwoButton, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
						.addComponent(userThreeButton, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
						.addComponent(userOneButton, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_loginPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUser_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUser)
						.addComponent(lblUser_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(70)
					.addGroup(gl_loginPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterPin)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(loginButton))
					.addGap(287))
		);
		userOneButton.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/Dog1.png")));
		userTwoButton.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/Dog2.png")));
		userThreeButton.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/Dog3.png")));
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Favorites", createTemplate("Favorites"));
		tabbedPane.addTab("Library", createTemplate("Library"));
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				passwordField.setText("");
				frame.setTitle("Media Player Login");
				cl.show(panelControl, "1");
			}
		});
		JButton settingsButton = new JButton("Settings");
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(settingsWindow == null)
				{
					settingsWindow = new SettingsWindow();
					settingsWindow.main(null);
				}
				else
				{
					settingsWindow.makeVisible();
				}
			}
		});
		
		JPanel playerPanel = new JPanel();
		
		GroupLayout gl_mediaPlayerPanel = new GroupLayout(mediaPlayerPanel);
		gl_mediaPlayerPanel.setHorizontalGroup(
			gl_mediaPlayerPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mediaPlayerPanel.createSequentialGroup()
					.addGroup(gl_mediaPlayerPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_mediaPlayerPanel.createSequentialGroup()
							.addGap(14)
							.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_mediaPlayerPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(logoutButton, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
						.addGroup(gl_mediaPlayerPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(settingsButton, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(playerPanel, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_mediaPlayerPanel.setVerticalGroup(
			gl_mediaPlayerPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_mediaPlayerPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_mediaPlayerPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(playerPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
						.addGroup(gl_mediaPlayerPanel.createSequentialGroup()
							.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(settingsButton)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(logoutButton)))
					.addContainerGap())
		);
		
		JPanel albumInfoPanel = new JPanel();
		
		JPanel playControlPanel = new JPanel();
		
		JPanel albumListPanel = new JPanel();
		GroupLayout gl_playerPanel = new GroupLayout(playerPanel);
		gl_playerPanel.setHorizontalGroup(
			gl_playerPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_playerPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_playerPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(albumListPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
						.addComponent(albumInfoPanel, GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
						.addComponent(playControlPanel, GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_playerPanel.setVerticalGroup(
			gl_playerPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_playerPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(albumInfoPanel, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(albumListPanel, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(playControlPanel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_albumListPanel = new GroupLayout(albumListPanel);
		gl_albumListPanel.setHorizontalGroup(
			gl_albumListPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_albumListPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_albumListPanel.setVerticalGroup(
			gl_albumListPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_albumListPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JTable albumTable = new JTable() {
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
				{new Integer(1), "Symphony #9 in D minor", "24:25"},
				{new Integer(2), "Moonlight Sonata", "14:59"},
				{new Integer(3), "Pathetique", "20:18"},
				{new Integer(4), "Emperor Concerto", "7:45"},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"#", "Track Name", "Song Duration"
			}
		));
		TableColumn trackNumber = albumTable.getColumnModel().getColumn(0);
		trackNumber.setPreferredWidth(50);
		trackNumber.setCellRenderer(centerRenderer);
		TableColumn trackName = albumTable.getColumnModel().getColumn(1);
		trackName.setPreferredWidth(525);
		trackName.setCellRenderer(centerRenderer);
		TableColumn trackDuration = albumTable.getColumnModel().getColumn(2);
		trackDuration.setPreferredWidth(125);
		trackDuration.setCellRenderer(centerRenderer);
		albumTable.getTableHeader().setReorderingAllowed(false);
		albumTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrollPane.setViewportView(albumTable);
		albumListPanel.setLayout(gl_albumListPanel);
		
		JLabel lblAlbumTitle = new JLabel("Album Title");
		lblAlbumTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlbumTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblAlbumArtist = new JLabel("Album Artist");
		lblAlbumArtist.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlbumArtist.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton favoriteAlbumButton = new JButton("Favorite Album");
		
		JButton restrictButton = new JButton("Restrict Album");
		GroupLayout gl_albumInfoPanel = new GroupLayout(albumInfoPanel);
		gl_albumInfoPanel.setHorizontalGroup(
			gl_albumInfoPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_albumInfoPanel.createSequentialGroup()
					.addContainerGap(314, Short.MAX_VALUE)
					.addGroup(gl_albumInfoPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_albumInfoPanel.createSequentialGroup()
							.addComponent(lblAlbumArtist, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
							.addComponent(favoriteAlbumButton, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_albumInfoPanel.createSequentialGroup()
							.addComponent(lblAlbumTitle, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
							.addComponent(restrictButton, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_albumInfoPanel.setVerticalGroup(
			gl_albumInfoPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_albumInfoPanel.createSequentialGroup()
					.addGap(18)
					.addComponent(lblAlbumTitle)
					.addGap(18)
					.addComponent(lblAlbumArtist)
					.addContainerGap(16, Short.MAX_VALUE))
				.addGroup(gl_albumInfoPanel.createSequentialGroup()
					.addContainerGap(19, Short.MAX_VALUE)
					.addComponent(restrictButton)
					.addGap(18)
					.addComponent(favoriteAlbumButton)
					.addContainerGap())
		);
		albumInfoPanel.setLayout(gl_albumInfoPanel);
		
		JLabel label = new JLabel("0:00");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JSlider slider = new JSlider();
		slider.setValue(0);
		
		JLabel label_1 = new JLabel("1:00");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton pauseButton = new JButton("Pause");
		pauseButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton playButton = new JButton("Play");
		playButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton stopButton = new JButton("Stop");
		stopButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblSongName = new JLabel("Song Name - Album Name");
		lblSongName.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout gl_playControlPanel = new GroupLayout(playControlPanel);
		gl_playControlPanel.setHorizontalGroup(
			gl_playControlPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_playControlPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_playControlPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_playControlPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_playControlPanel.createSequentialGroup()
								.addComponent(label)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(slider, GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(label_1)
								.addContainerGap())
							.addGroup(Alignment.TRAILING, gl_playControlPanel.createSequentialGroup()
								.addComponent(pauseButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(playButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(stopButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addGap(183)))
						.addGroup(Alignment.TRAILING, gl_playControlPanel.createSequentialGroup()
							.addComponent(lblSongName)
							.addGap(254))))
		);
		gl_playControlPanel.setVerticalGroup(
			gl_playControlPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_playControlPanel.createSequentialGroup()
					.addComponent(lblSongName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_playControlPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_playControlPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(pauseButton)
							.addComponent(playButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(stopButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
					.addGroup(gl_playControlPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(label))
					.addContainerGap())
		);
		playControlPanel.setLayout(gl_playControlPanel);
		playerPanel.setLayout(gl_playerPanel);
		mediaPlayerPanel.setLayout(gl_mediaPlayerPanel);
		cl.show(panelControl, "1");

		frame.getContentPane().add(panelControl);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		passwordField.requestFocusInWindow();
	}
	
	private void checkPassword()
	{
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
	
	// Create JPanels that are used in each tab
	private JPanel createTemplate(String tabName)
	{
		JPanel template = new JPanel();
		template.setName(tabName);
		
		UIManager.put("Tree.rendererFillBackground", false);
		JTree tree = new JTree();
		
		tree.setBackground(null);
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode(tabName) {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("Bach");
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Beethoven");
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Motzart");
					add(node_1);
				}
			}
		));
		tree.setName(tabName + "Tree");
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
}
