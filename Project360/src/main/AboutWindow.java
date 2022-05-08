package main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;



public class AboutWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public AboutWindow(int width, int height) {
		
		setTitle("About...");
		
		if (Main.getUser().equals("unregistered")) {
			String newUser = JOptionPane.showInputDialog("Please register a user:",Main.getUser());
			Main.setUser(newUser);
		}
		
		String[] strAbout = {"FileSorter " + Main.VERSION,
							 "\n",
							 "registered user: " + Main.getUser(),
		                     "\n",
							 "made by TCSS360 TeamONE",
		                     "[Alan T, Chris H, Anthony C, Betelhem B, Andrew L]"};
		
		for (int i = 0; i < strAbout.length; i++) {
			JLabel label = new JLabel(strAbout[i]);
			add(label);
			label.setBounds(10, 20*i, 300, 20);
			
		}
		
		setSize(width, height);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
