package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.Main;
import model.FileIO;
import model.Profile;
import model.ProfileManager;

/**
 * The Profile tab/panel inside of the application. Controls which profile is currently active/loaded,
 * managing profiles (create/remove), and importing or exporting profiles to other machines.
 * @author Alan Thompson
 */

public class ProfilePanel extends JPanel {
	
	private static final long serialVersionUID = 9085434569766998412L;
	
	// A JTable for storing the list
	private JTable tableUsers;
	
	// A sub-panel to arrange the buttons in a grid
	private JPanel buttonGrid;
	
	private JPanel leftPanel;
	private JPanel rightPanel;
	
	
	// The buttons for use in the profile panel.
	private JButton bImport;
	private JButton bExport;
	private JButton bNew;
	private JButton bRemove;
	private JButton bLoad;
	
	// The indexs of the profiles that are currently selected and loaded
	private int selectedProfileIndex = -1;
	
	// Some default Swing component values to prevent re-typing code
	private final Dimension defaultButtonDimensions = new Dimension(130,50);
	private final Font defaultButtonFont = new Font("Arial", Font.PLAIN, 32);
	private final String[] columnNames = {"Profiles"};

	public ProfilePanel() {
		super(new GridLayout(1,2,0,0));
		initProfilePanel();
	}
	
	private void initProfilePanel() {
		leftPanel = new JPanel(new GridLayout());
		rightPanel = new JPanel(new GridLayout());
		
		add(leftPanel);
		add(rightPanel);

		initButtons();
		initUserTable();
		
		
		
		JScrollPane scrollPane = new JScrollPane(tableUsers);
		leftPanel.add(scrollPane);
		rightPanel.add(buttonGrid);
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
					checkProfileQuantity();
					bExport.setEnabled(true);
				}
			}
		});
		
	}
	
	/**
	 * A helper method to generate the needed 2d String array for use with JTables
	 * @author Alan Thompson
	 * @param profiles
	 * @return generated 2d array of users
	 */
	private String[][] generateTableUsersData(ProfileManager profiles) {
		
		int profilesSize = profiles.getProfileList().size();
		String[][] users = new String[profilesSize][3];
		
		for (var i = 0; i < profilesSize; i++) {
			users[i][0] = profiles.getProfileList().get(i).getUserName();
		}
		return users;
	}
	
	/**
	 * A helper method to update the table if there is a change to the set of users.
	 * @author Alan Thompson
	 */
	private void updateTableUsers() {
		removeAll();
		initUserTable();
		initProfilePanel();
		repaint();
	}
	
	/**
	 * A helper method to init the "Import Profile" button and set up it's action listener.
	 * @author Alan Thompson
	 */
	private void initImportProfileButton() {
		bImport = new JButton("Import Profile");
		bImport.setFont(defaultButtonFont);
		bImport.setPreferredSize(defaultButtonDimensions);
		
		Component theParent = this; // <- needed to connect the file dialog to this panel
		
		bImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//System.out.println(FileIO.importProfile(theParent)); // <- prints true if it was successful
					boolean success = FileIO.importProfile(theParent);
					if (!success)
						JOptionPane.showMessageDialog(null, "Error! Profile already exists.");
					else
						updateTableUsers();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * A helper method to init the "Export Profile" button and set up it's action listener.
	 * @author Alan Thompson
	 * @author Christopher Henderson
	 */
	private void initExportProfileButton() {
		bExport = new JButton("Export Profile");
		bExport.setFont(defaultButtonFont);
		bExport.setPreferredSize(defaultButtonDimensions);
		
		bExport.setEnabled(false);
		
		Component theParent = this; // <- needed to connect the file dialog to this panel
		
		bExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean win = FileIO.exportProfile(theParent,
							Main.mainProfileManger.getProfileList().get(selectedProfileIndex));
					
					//System.out.println(win); // <- prints true if it was successful
					updateTableUsers();
 				} catch (Exception ex) {
 					ex.printStackTrace();
 				}
			}
		});
	}
	
	/**
	 * A helper method to init the "New Profile" button and set up it's action listener.
	 * @author Alan Thompson
	 */
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
							Main.mainProfileManger.addProfile(new Profile(profileName, profilePass));
							JOptionPane.showMessageDialog(null, userCreatedMessage);
							updateTableUsers();
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
				checkProfileQuantity();
			}
		});
		
	}
	
	/**
	 * A helper method to init the "Remove Profile" button and set up it's action listener.
	 * @author Alan Thompson
	 */
	private void initRemoveProfileButton() {
		bRemove = new JButton("Remove Profile");
		bRemove.setFont(defaultButtonFont);
		bRemove.setPreferredSize(defaultButtonDimensions);
		
		bRemove.setEnabled(false);
		
		
		
		bRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String profileUserName = Main.mainProfileManger.getProfileList().get(selectedProfileIndex).getUserName();
				String loadedUserName = Main.mainProfileManger.getLoadedProfile().getUserName();
				if (selectedProfileIndex != 0) {
					System.out.println(loadedUserName);
					if (loadedUserName.equals(profileUserName)) {
						JOptionPane.showMessageDialog(null, "Can't remove currently loaded profile!");
						return;
					}
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
					checkProfileQuantity();
				} else {
					String cantRemoveDefaultMessage = "Can't remove default profile. Please select a different Profile and try again.";
					JOptionPane.showMessageDialog(null, cantRemoveDefaultMessage);
				}
			}
		});
	}
	
	/**
	 * A helper method to check if there is 1 or more profiles. If there is 1, disable the remove button.
	 * Otherwise enable it.
	 * @author Alan Thompson
	 */
	private void checkProfileQuantity() {
		if (Main.mainProfileManger.getProfileList().size() <= 1)
			bRemove.setEnabled(false);
		else
			bRemove.setEnabled(true);
	}
	
	/**
	 * A helper method to init the "Load Profile" button and set up it's action listener.
	 * @author Alan Thompson
	 */
	private void initLoadProfileButton() {
		bLoad = new JButton("Load Profile");
		bLoad.setFont(defaultButtonFont);
		bLoad.setPreferredSize(defaultButtonDimensions);
				
		//bLoad.setEnabled(false);
		
		bLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedProfileIndex > -1) {
					//setCurrentlyLoadedProfile(Main.mainProfileManger.getProfileList().get(selectedProfileIndex));
					Profile selectedProfile = Main.mainProfileManger.getProfileList().get(selectedProfileIndex);
					Main.mainProfileManger.setloadedProfile(selectedProfile);
					Main.refreshAll();
					Main.window.fileAndLabelTabsUnlock(); // to unlock File and Label tabs
				} else {
					String noProfileSelectedMessage = "No profile selected. Please select a profile from the list to the left and try again.";
					JOptionPane.showMessageDialog(null, noProfileSelectedMessage);
				}
			}
		});
	}
	
	/**
	 * A helper method to call all the other button init helper methods and then add them to the frame.
	 * @author Alan Thompson
	 */
	private void initButtons() {
		initImportProfileButton();
		initExportProfileButton();
		initNewProfileButton();
		initRemoveProfileButton();
		initLoadProfileButton();
		
		buttonGrid = new JPanel(new GridLayout(0,1));
		buttonGrid.setPreferredSize(new Dimension(300, 300));
		buttonGrid.add(bLoad);
		buttonGrid.add(bNew);
		buttonGrid.add(bRemove);
		buttonGrid.add(bImport);
		buttonGrid.add(bExport);
	}
}
