import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainWindow {

	JFrame frame = new JFrame("Media Player Login");
	JPanel panelControl = new JPanel();
	JPanel loginPanel = new JPanel();
	JPanel mediaPlayerPanel = new JPanel();
	JButton userOneButton = new JButton("User One");
	JButton userTwoButton = new JButton("User Two");
	JButton userThreeButton = new JButton("User Three");
	JButton buttonSecond = new JButton("Switch to first panel/workspace");
	CardLayout cl = new CardLayout();

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
		frame.setSize(1000, 750);
		loginPanel.add(userOneButton);
		loginPanel.add(userTwoButton);
		loginPanel.add(userThreeButton);

		panelControl.setLayout(cl);
		mediaPlayerPanel.add(buttonSecond);

		panelControl.add(loginPanel, "1");

		userOneButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.setTitle("Media Player");
				cl.show(panelControl, "2");
			}
		});

		userTwoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.setTitle("Media Player");
				cl.show(panelControl, "2");
			}
		});

		userThreeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.setTitle("Media Player");
				cl.show(panelControl, "2");
			}
		});

		panelControl.add(mediaPlayerPanel, "2");
		cl.show(panelControl, "1");

		buttonSecond.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelControl, "1");
			}
		});

		frame.getContentPane().add(panelControl);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

	}

}
