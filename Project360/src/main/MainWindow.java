package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;



public class MainWindow extends JFrame{
	
	public static final String APP_VERSION = "v0.1";
	
	private String user = "unregistered";
	
	JButton bAbout;
	
	private static final long serialVersionUID = 1L;
	
	public MainWindow(int width, int height) {
		
		setTitle("TeamONE FileSorter");
		
		bAbout = new JButton("About");
		bAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AboutWindow(320, 240);
			}
		});
		
		bAbout.setBounds(10, 10, 100, 50);
		add(bAbout);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);	
	}
	
	public String getUser() {
		return user;
	}
}


