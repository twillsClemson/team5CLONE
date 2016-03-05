package cpsc3720.team5.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import cpsc3720.team5.model.Settings;
import cpsc3720.team5.model.UserProfiles;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addUserWindow {

	private static JFrame frmNewUser;
	private JTextField txtNewUserName;
	private JTextField txtNewUserPIN;
	private JTextField txtRestricionLevel;
	private JTextField textField;
	Settings setting = Settings.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmNewUser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void makeVisible()
	{
		frmNewUser.setVisible(true);
		
	}
	/**
	 * Create the application.
	 */
	public addUserWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNewUser = new JFrame();
		frmNewUser.setVisible(true);
		frmNewUser.setTitle("New User");
		frmNewUser.setBounds(100, 100, 622, 196);
		frmNewUser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frmNewUser.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		txtNewUserName = new JTextField();
		txtNewUserName.setColumns(10);
		
		JLabel lblNewUserName = DefaultComponentFactory.getInstance().createLabel("New User Name");
		
		JLabel lblNewUserPin = DefaultComponentFactory.getInstance().createLabel("New User PIN");
		
		txtNewUserPIN = new JTextField();
		txtNewUserPIN.setColumns(10);
		
		JLabel lblNewUserLevel = DefaultComponentFactory.getInstance().createLabel("New User Restriction Level");
		
		txtRestricionLevel = new JTextField();
		txtRestricionLevel.setColumns(10);
		
		JLabel lblProfilePicturePath = DefaultComponentFactory.getInstance().createLabel("Profile Picture Path");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		final JRadioButton rdbtnAdministrator = new JRadioButton("Administrator");
		
		JButton btnSaveUserProfile = new JButton("Save User Profile");
		btnSaveUserProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserProfiles prof = new UserProfiles();
				prof.setName(txtNewUserName.getText());
				prof.setRestrictionLevel(Integer.valueOf(txtRestricionLevel.getText()));
				prof.setPIN(Integer.valueOf(txtNewUserPIN.getText()));
				prof.setAdmin(rdbtnAdministrator.isSelected());
				setting.addUserProfile(prof);
				frmNewUser.setVisible(false);
				frmNewUser.dispose();
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewUserName)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtNewUserName, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewUserPin)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtNewUserPIN)))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewUserLevel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtRestricionLevel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblProfilePicturePath)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(166)
							.addComponent(rdbtnAdministrator))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnSaveUserProfile)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancel)))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNewUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewUserName)
						.addComponent(lblNewUserLevel)
						.addComponent(txtRestricionLevel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewUserPin)
						.addComponent(txtNewUserPIN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProfilePicturePath)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtnAdministrator)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSaveUserProfile)
						.addComponent(btnCancel))
					.addContainerGap(121, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frmNewUser.getContentPane().setLayout(groupLayout);
	}
}
