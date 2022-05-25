package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.Main;
import model.FileIO;
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
	
	

	public ProfilePanel() {
		super();

		initButtons();
		initUserTable();
		
		JScrollPane scrollPane = new JScrollPane(tableUsers);
		scrollPane.setPreferredSize(new Dimension(300, 200));
		add(scrollPane);
		add(buttonGrid);
	}
	
	/**
	 * Helper method to set up JTable tableUsers
	 * @author Alan Thompson
	 */
	public void initUserTable() {
				
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
		
		bNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(bNew.getText());
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
				System.out.println(bRemove.getText());
			}
		});
	}
	
	private void initLoadProfileButton() {
		bLoad = new JButton("Load Profile");
		bLoad.setFont(defaultButtonFont);
		bLoad.setPreferredSize(defaultButtonDimensions);
		
		bLoad.setEnabled(false);
		
		bLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(bLoad.getText());
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

}
