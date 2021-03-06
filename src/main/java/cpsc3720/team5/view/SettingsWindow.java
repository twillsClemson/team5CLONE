package cpsc3720.team5.view;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
//import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import cpsc3720.team5.model.Album;
import cpsc3720.team5.model.Settings;
import cpsc3720.team5.model.UserProfiles;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SettingsWindow {

	private static JFrame frame;
	private JTextField txtDefaultUrl;
	private JTable table;
	private JTable table_1;
	private Settings setting = Settings.getInstance();
	private addUserWindow newUserWindow = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					makeVisible();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void makeVisible()
	{
		frame.setVisible(true);
		
	}
	
	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public SettingsWindow() {
		initialize();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//frame.setPreferredSize(new Dimension(500, 300));
		//frame.setMaximumSize(new Dimension(500, 300));
		frame.setVisible(true);
		frame.setSize(new Dimension(754, 301));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 574, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Application Settings", null, panel, null);
		
		JLabel lblServerUrl = new JLabel("Server URL:");
		
		txtDefaultUrl = new JTextField();
		txtDefaultUrl.setText(setting.getServerURL());
		txtDefaultUrl.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JLabel lblRestrictedAlbums = new JLabel("Restricted Albums");
		
		JButton btnApplyChanges_1 = new JButton("Apply Changes");
		
		JButton btnUndoChanges_1 = new JButton("Undo Changes");
		GroupLayout groupLayout_1 = new GroupLayout(panel);
		groupLayout_1.setHorizontalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnApplyChanges_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnUndoChanges_1)
					.addContainerGap(345, Short.MAX_VALUE))
				.addGroup(groupLayout_1.createSequentialGroup()
					.addGap(12)
					.addGroup(groupLayout_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblRestrictedAlbums)
						.addGroup(groupLayout_1.createSequentialGroup()
							.addComponent(lblServerUrl, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtDefaultUrl, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_1, 0, 0, Short.MAX_VALUE))
					.addContainerGap(252, Short.MAX_VALUE))
		);
		groupLayout_1.setVerticalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtDefaultUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblServerUrl))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblRestrictedAlbums)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(24)
					.addGroup(groupLayout_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUndoChanges_1)
						.addComponent(btnApplyChanges_1))
					.addContainerGap())
		);
		
		table_1 = new JTable()
				{

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					public boolean isCellEditable(int row, int column) {              
		                if(column == 0)
		                	return false;
		                else
		                	return true;
					}
				};
		Map<String, Album> albums = setting.getLibraryAlbums();
		Object[][] albumLst = new Object[setting.getLibraryAlbums().size()][1];
		int i = 0;
		for(Entry<String, Album> al : albums.entrySet())
		{
			albumLst[i] = new Object[] {al.getValue().name, al.getValue().approvalLevel};
			i++;
		}
		table_1.setModel(new DefaultTableModel(albumLst,
				new String[] {
					"Album Name", "Restriction Level"
				}
			));
		table_1.setFillsViewportHeight(true);
		table_1.setColumnSelectionAllowed(true);
		table_1.setCellSelectionEnabled(true);
		table_1.setVisible(true);
		scrollPane_1.setViewportView(table_1);
		panel.setLayout(groupLayout_1);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("User Settings", null, panel_1, null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnApplyChanges = new JButton("Apply Changes");
		btnApplyChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Album> approve = new ArrayList<Album>();
				Map<String, Album> lib = setting.getLibraryAlbums();
				Album al = null;
				for(int i = 0; i < table_1.getRowCount(); i++)
				{
					al = new Album();
					System.out.println(table_1.getModel().getValueAt(i, 0));
					al = lib.get(((String)table_1.getModel().getValueAt(i, 0)));
					al.setApprovalLevel((int)table_1.getModel().getValueAt(i, 1));
					approve.add(al);
				}
				ArrayList<UserProfiles> profs = new ArrayList<UserProfiles>();
				UserProfiles temp = null;
				for(int i = 0; i < table.getRowCount(); i++)
				{
					temp = new UserProfiles();
					String temp1 = table.getModel().getValueAt(i, 3).toString();
					temp.setName((String)table.getModel().getValueAt(i, 0));
					temp.setPIN((int)table.getModel().getValueAt(i, 1));
					temp.setAdmin((boolean)table.getModel().getValueAt(i, 2));
					temp.setRestrictionLevel(Integer.parseInt(temp1));
					temp.setProfilePicture((String)table.getModel().getValueAt(i, 4));
					System.out.println(table.getModel().getValueAt(i, 0));
					profs.add(temp);
				}
			}
		});
		
		JButton btnUndoChanges = new JButton("Undo Changes");
		
		JButton btnCreateNewUser = new JButton("Create New User");
		btnCreateNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					newUserWindow = new addUserWindow();
					newUserWindow.main(null);
			}
		});
		
		JButton btnUpdateTable = new JButton("Update User Table");
		btnUpdateTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<UserProfiles> profs  = setting.getProfiles();
				Object[][] profLst = new Object[profs.size()][1];
				int i = 0;
				for(UserProfiles prof : profs)
				{
					profLst[i] = new Object[] {prof.getName(),prof.getPIN(), prof.getAdmin(), prof.getRestrictionLevel(), prof.getProfilePic()};
					i++;
				}
				table.setModel(new DefaultTableModel(
						profLst,
						new String[] {
							"User Name", "PIN", "User Type", "Restriction Level", "Profile Picture"
						}
					));
			}
		});
		
		JButton btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				String name = (String)table.getModel().getValueAt(index, 0);
				for(int i = 0; i < setting.getProfiles().size(); i++)
				{
					if(setting.getProfiles().get(i).getName().equals(name))
					{
						setting.getProfiles().remove(setting.getProfiles().get(i));
					}
				}
				ArrayList<UserProfiles> profs  = setting.getProfiles();
				Object[][] profLst = new Object[profs.size()][1];
				int i = 0;
				for(UserProfiles prof : profs)
				{
					profLst[i] = new Object[] {prof.getName(),prof.getPIN(), prof.getAdmin(), prof.getRestrictionLevel(), prof.getProfilePic()};
					i++;
				}
				table.setModel(new DefaultTableModel(
						profLst,
						new String[] {
							"User Name", "PIN", "User Type", "Restriction Level", "Profile Picture"
						}
					));
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnApplyChanges)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnUndoChanges)
							.addPreferredGap(ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
							.addComponent(btnDeleteUser)
							.addGap(18)
							.addComponent(btnUpdateTable)
							.addGap(18)
							.addComponent(btnCreateNewUser)
							.addGap(31))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnApplyChanges)
						.addComponent(btnUndoChanges)
						.addComponent(btnCreateNewUser)
						.addComponent(btnUpdateTable)
						.addComponent(btnDeleteUser))
					.addContainerGap())
		);
		table = new JTable();
		ArrayList<UserProfiles> profs  = setting.getProfiles();
		Object[][] profLst = new Object[profs.size()][1];
		i = 0;
		for(UserProfiles prof : profs)
		{
			profLst[i] = new Object[] {prof.getName(),prof.getPIN(), prof.getAdmin(), prof.getRestrictionLevel(), prof.getProfilePic()};
			i++;
		}
		table.setModel(new DefaultTableModel(
				profLst,
				new String[] {
					"User Name", "PIN", "User Type", "Restriction Level", "Profile Picture"
				}
			));
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setVisible(true);
		scrollPane.setViewportView(table);
		panel_1.setLayout(gl_panel_1);
		frame.getContentPane().setLayout(groupLayout);
		
		btnApplyChanges_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Album> approve = new ArrayList<Album>();
				Map<String, Album> lib = setting.getLibraryAlbums();
				Album al = null;
				for(int i = 0; i < table_1.getRowCount(); i++)
				{
					al = new Album();
					System.out.println(table_1.getModel().getValueAt(i, 0));
					al = lib.get(((String)table_1.getModel().getValueAt(i, 0)));
					al.setApprovalLevel((int)table_1.getModel().getValueAt(i, 1));
					approve.add(al);
				}
				ArrayList<UserProfiles> profs = new ArrayList<UserProfiles>();
				UserProfiles temp = null;
				for(int i = 0; i < table.getRowCount(); i++)
				{
					temp = new UserProfiles();
					String temp1 = table.getModel().getValueAt(i, 3).toString();
					temp.setName((String)table.getModel().getValueAt(i, 0));
					temp.setPIN((int)table.getModel().getValueAt(i, 1));
					temp.setAdmin((boolean)table.getModel().getValueAt(i, 2));
					temp.setRestrictionLevel(Integer.parseInt(temp1));
					temp.setProfilePicture((String)table.getModel().getValueAt(i, 4));
					System.out.println(table.getModel().getValueAt(i, 0));
					profs.add(temp);
				}
				setting.applySettings(approve, txtDefaultUrl.getText(), profs);
				setting.writeSettings();
			}
		});
	}
}
