package cpsc3720.team5.window;
import java.awt.EventQueue;

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
import javax.swing.JButton;
import java.awt.Dimension;

public class SettingsWindow {

	private static JFrame frame;
	private JTextField txtDefaultUrl;
	private JTable table;
	private JTable table_1;
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
		frame.setSize(new Dimension(600, 301));
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
		txtDefaultUrl.setText("Server.sys.org");
		txtDefaultUrl.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JLabel lblRestrictedAlbums = new JLabel("Restricted Albums");
		
		JButton btnApplyChanges_1 = new JButton("Apply Changes");
		
		JButton btnUndoChanges_1 = new JButton("Undo Changes");
		GroupLayout groupLayout_1 = new GroupLayout(panel);
		groupLayout_1.setHorizontalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout_1.createSequentialGroup()
					.addGroup(groupLayout_1.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout_1.createSequentialGroup()
							.addGap(12)
							.addGroup(groupLayout_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblRestrictedAlbums)
								.addGroup(groupLayout_1.createSequentialGroup()
									.addComponent(lblServerUrl, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtDefaultUrl, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane_1, 0, 0, Short.MAX_VALUE)))
						.addGroup(groupLayout_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout_1.createSequentialGroup()
								.addComponent(btnApplyChanges_1)
								.addPreferredGap(ComponentPlacement.RELATED))
							.addComponent(btnUndoChanges_1)))
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
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
					{"1989", "4"},
					{"21", "3"},
					{"To Pimp a Butterfly", "5"},
					{"Beethoven", "0"},
				},
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
		
		JButton btnUndoChanges = new JButton("Undo Changes");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnApplyChanges)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnUndoChanges)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnApplyChanges)
						.addComponent(btnUndoChanges))
					.addContainerGap())
		);
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{"Jonathan Sarasua", "1111", "Admin", "0", "pic.img"},
					{"Christian Trull", "2222", "Child", "3", "loser.jpeg"},
					{"Scott Seeman", "3333", "Child", "5", "bronzelyfe.jpeg"},
					{"TJ", "4444", "Child", "1", "goldLord.gif"},
				},
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
	}

}
