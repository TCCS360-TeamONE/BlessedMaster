package view;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import main.Main;
import model.Profiles;

public class ProfilePanel extends JPanel {
	
	/**
	 *  
	 */
	private static final long serialVersionUID = 9085434569766998412L;
	
	private JList<String> userJList;

	public ProfilePanel() {
		super();
		
		initUserList();
		
		JLabel fooBar = new JLabel("THIS IS THE PROFILE PANEL");
		fooBar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
		
		//add(fooBar);
		add(userJList);
		
	}
	
	/**
	 * Helper method to set up JList userList
	 * @author Alan Thompson
	 */
	public void initUserList() {
		ArrayList<Profiles.LoginProfile> profiles = Main.loginProfiles.getProfileList();
		String[] users = new String[profiles.size()];
		for (int i = 0; i < profiles.size(); i++) {
			users[i] = profiles.get(i).getUserName();
		}
		userJList = new JList<String>(users);
		
		userJList.setFixedCellWidth(200);
		userJList.setFixedCellHeight(50);
		userJList.setAlignmentX(JList.CENTER_ALIGNMENT);
		userJList.setAlignmentY(JList.CENTER_ALIGNMENT);
		
	}

}
