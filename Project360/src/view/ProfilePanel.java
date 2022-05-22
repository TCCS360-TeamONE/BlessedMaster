package view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import main.Main;
import model.Profiles;

public class ProfilePanel extends JPanel {
	
	/**
	 *  
	 */
	private static final long serialVersionUID = 9085434569766998412L;
	
	private JList<String> userJList;

	private JButton importSetting;
	private JButton exportSetting;

	public ProfilePanel() {
		super();

		buttonSetUp();
		initUserList();

		JLabel fooBar = new JLabel("THIS IS THE PROFILE PANEL");
		fooBar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
		
		//add(fooBar);
		add(userJList);
		add(importSetting);
		add(exportSetting);
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
		//userJList.setAlignmentX(JList.CENTER_ALIGNMENT);
		//userJList.setAlignmentY(JList.CENTER_ALIGNMENT);
		
	}

	public void buttonSetUp() {
		importSetting = new JButton("Import");
		importSetting.setFont(new Font("Arial", Font.PLAIN, 20));
		importSetting.setPreferredSize(new Dimension(130,40));

		exportSetting = new JButton("Export");
		exportSetting.setFont(new Font("Arial", Font.PLAIN, 20));
		exportSetting.setPreferredSize(new Dimension(130,40));

	}

}
