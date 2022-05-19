package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 
 * @author Alan Thompson
 * 
 * MainWindow.java
 * 
 * A class to handle creation of the main window frame and its associated components.
 *
 */

public class OLD_MainWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Class members
	 */
	private JButton bAbout;
	
	/**
	 * MainWindow constructor
	 * @param width
	 * @param height
	 */
	public OLD_MainWindow(int width, int height) {
		initWindow(width, height);
	}
	
	/**
	 * Helper method to initialize the window and make it visible.
	 * @param width
	 * @param height
	 */
	public void initWindow(int width, int height) {
		setTitle("TeamONE FileSorter");
		
		createAboutButton();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * Helper method to initialize the About button and handle it's click event
	 */
	public void createAboutButton() {
		bAbout = new JButton("About");
		bAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AboutWindow(Main.DEFAULT_WIDTH/2, (Main.DEFAULT_HEIGHT/2));
			}
		});
		bAbout.setBounds(10, 10, 100, 50);
		add(bAbout);
	}
}


