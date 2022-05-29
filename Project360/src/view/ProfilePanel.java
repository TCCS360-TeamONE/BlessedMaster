package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.Main;
import model.FileIO;
import model.Profile;
import model.ProfileManager;

public class ProfilePanel extends JPanel {
	
	/**
	 *  
	 */
	private static final long serialVersionUID = 9085434569766998412L;
	
	private JTable tableUsers;
	
	private JPanel buttonGrid;

	private JButton bImport;
	private JButton bExport;
	private JButton bNew;
	private JButton bRemove;
	private JButton bLoad;
	
	private int selectedProfileIndex = -1;
	
	private final Dimension defaultButtonDimensions = new Dimension(130,40);
	private final Font defaultButtonFont = new Font("Arial", Font.PLAIN, 20);
	private final String[] columnNames = {"Profiles"};
	
	private Profile currentlyLoadedProfile;
	
	

	public ProfilePanel() {
		super();

		initButtons();
		initUserTable();
		
		setCurrentlyLoadedProfile(Main.mainProfileManger.getProfile("default"));
		
		JScrollPane scrollPane = new JScrollPane(tableUsers);
		scrollPane.setPreferredSize(new Dimension(300, 200));
		add(scrollPane);
		add(buttonGrid);
	}
	
	/**
	 * Helper method to set up JTable tableUsers
	 * @author Alan Thompson
	 */
	private void initUserTable() {
				
		tableUsers = new JTable(generateTableUsersData(Main.mainProfileManger), columnNames);
		tableUsers.setRowHeight((int) defaultButtonDimensions.getHeight());
		
		tableUsers.setFont(defaultButtonFont);
		tableUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableUsers.setDefaultEditor(Object.class, null);
		
		tableUsers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && tableUsers.getSelectedRow() != -1) {
					selectedProfileIndex = tableUsers.getSelectedRow();
					bExport.setEnabled(true);
					bRemove.setEnabled(true);
				}
			}
		});
		
	}
	
	private String[][] generateTableUsersData(ProfileManager profiles) {
		
		int profilesSize = profiles.getProfileList().size();
		String[][] users = new String[profilesSize][3];
		
		for (var i = 0; i < profilesSize; i++) {
			users[i][0] = profiles.getProfileList().get(i).getUserName();
		}
		return users;
	}
	
	private void updateTableUsers() {
		removeAll();

		initUserTable();
		initButtons();
		
		JScrollPane scrollPane = new JScrollPane(tableUsers);
		scrollPane.setPreferredSize(new Dimension(300, 200));
		add(scrollPane);
		add(buttonGrid);
		
	}
	
	private void initImportProfileButton() {
		bImport = new JButton("Import Profile");
		bImport.setFont(defaultButtonFont);
		bImport.setPreferredSize(defaultButtonDimensions);
		
		bImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileIO.importProfile(Main.mainProfileManger);
					updateTableUsers();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}
	
	
	
	private void initExportProfileButton() {
		bExport = new JButton("Export Profile");
		bExport.setFont(defaultButtonFont);
		bExport.setPreferredSize(defaultButtonDimensions);
		
		bExport.setEnabled(false);
		
		bExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileIO.exportProfile(Main.mainProfileManger.getProfileList().get(selectedProfileIndex));
 				} catch (Exception ex) {
 					ex.printStackTrace();
 				}
			}
		});
	}
	
	private void initNewProfileButton() {
		bNew = new JButton("New Profile");
		bNew.setFont(defaultButtonFont);
		bNew.setPreferredSize(defaultButtonDimensions);
		
		String nameInputMessage = "Please enter a name for the new profile:";
		String passInputMessage = "Please enter a password.";
		String userExistsMessage = 	"A profile with that name already exists!\n" + 
									"Please try creating a profile with a different name.";
		String userCreatedMessage = "Profile created successfully!";
		String invalidUserMessage = "Profile name entered is invalid or blank. Profile creation aborted.";
		String invalidPassMessage = "Password entered is invalid or blank. Profile creation aborted.";
		
		bNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String profileName = JOptionPane.showInputDialog(nameInputMessage);
				if (profileName == null) return;
				if ( !profileName.isBlank() && 
					 !profileName.isEmpty() ) {
					if (!Main.mainProfileManger.userAlreadyExists(profileName)) {
						String profilePass = JOptionPane.showInputDialog(passInputMessage);
						if (profilePass == null) return;
						if (!profilePass.isEmpty() && !profilePass.isBlank()) {
							Main.mainProfileManger.createNewUser(profileName, profilePass);
							updateTableUsers();
							JOptionPane.showMessageDialog(null, userCreatedMessage);
						} else {
							JOptionPane.showMessageDialog(null, invalidPassMessage);
						}
					} else {
						JOptionPane.showMessageDialog(null, userExistsMessage);
						return;
					}
				} else {
					JOptionPane.showMessageDialog(null, invalidUserMessage);
				}
			}
		});
	}
	
	private void initRemoveProfileButton() {
		bRemove = new JButton("Remove Profile");
		bRemove.setFont(defaultButtonFont);
		bRemove.setPreferredSize(defaultButtonDimensions);
		
		bRemove.setEnabled(false);
		
		
		
		bRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String profileUserName = Main.mainProfileManger.getProfileList().get(selectedProfileIndex).getUserName();
				String confirmRemoveMessage = 	"Are you sure you want to remove profile \"" +
						profileUserName +
						"\"?";
				String abortMessage = "Profile removal aborted!";
				int choice = JOptionPane.showConfirmDialog(null, confirmRemoveMessage);
				if (choice == JOptionPane.YES_OPTION) {
					Main.mainProfileManger.removeProfile(profileUserName);
					updateTableUsers();
				} else {
					JOptionPane.showMessageDialog(null, abortMessage);
				}
			}
		});
	}
	
	private void initLoadProfileButton() {
		bLoad = new JButton("Load Profile");
		bLoad.setFont(defaultButtonFont);
		bLoad.setPreferredSize(defaultButtonDimensions);
		
		//bLoad.setEnabled(false);
		
		bLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCurrentlyLoadedProfile(Main.mainProfileManger.getProfileList().get(selectedProfileIndex));
			}
		});
	}
	
	private void initButtons() {
		initImportProfileButton();
		initExportProfileButton();
		initNewProfileButton();
		initRemoveProfileButton();
		initLoadProfileButton();
		
		buttonGrid = new JPanel(new GridLayout(0,1));
		buttonGrid.setPreferredSize(new Dimension(200, 150));
		buttonGrid.add(bLoad);
		buttonGrid.add(bNew);
		buttonGrid.add(bRemove);
		buttonGrid.add(bImport);
		buttonGrid.add(bExport);
	}

	public Profile getCurrentlyLoadedProfile() {
		return currentlyLoadedProfile;
	}

	public void setCurrentlyLoadedProfile(Profile currentlyLoadedProfile) {
		this.currentlyLoadedProfile = currentlyLoadedProfile;
	}

}
