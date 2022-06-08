
package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import main.Main;
import model.AppLabel;
import model.Library;

/**
 * This is the label panel/tab
 *
 * @author Andrew & Betelhem
 */
public class LabelPanel extends JPanel {

	/**
	 * Serial Version UID
	 * @see Serializable
	 */
	@Serial
	private static final long serialVersionUID = 3058061381038686629L;

	/**
	 * Add button
	 */
	private JButton addButton;
	/**
	 * Delete button
	 */
	private JButton delButton;
	/**
	 * Apply button
	 */
	private JButton applyButton;
	/**
	 * Panel where the button goes
	 */
	private JPanel buttonPanel;
	/**
	 * ScrollPane for the collection of labels
	 */
	private JScrollPane scrollPane;
	/**
	 * One row JTable for the collection of labels
	 */
	private JTable table;

	/**
	 * Apply window frame
	 */
	JFrame applyFrame;
	/**
	 * top panel of the apply window
	 */
	JPanel startApplyPanel;
	/**
	 * TextField for user to input the file path
	 */
	JTextField searchField;
	/**
	 * Select button
	 */
	JButton selectBtn;
	/**
	 * Middle panel in the apply window
	 */
	JPanel midApplyPanel;
	/**
	 * Collection of the labels in the current profile that will be shown in the panel
	 */
	JList labelLibraryList;
	/**
	 * Collection of the labels in String type that is associated with the file
	 */
	ArrayList<String> associateLabelName;
	/**
	 * Collection of the labels in Applabel type that is associated with the file
	 */
	AppLabel[] associateLabelArray;
	/**
	 * Collection of the labels in the current profile that is associated with the file will be shown in the panel
	 */
	JList fileLabelList;
	/**
	 * List Model for fileLabelList
	 */
	DefaultListModel listModel = new DefaultListModel();
	/**
	 * Labels specifying JList labelLibraryList
	 */
	JLabel addLabelToFile;
	/**
	 * Label specifying JList FileLibraryList
	 */
	JLabel removeLabelFromFile;
	/**
	 * ScrollPane for labelLibrary
	 */
	JScrollPane labelLibraryScroll;
	/**
	 * ScrollPane for fileLabelLibrary
	 */
	JScrollPane fileLabelScroll;
	/**
	 * Bottom panel for Apply Window
	 */
	JPanel botApplyPanel;
	/**
	 * button that close apply window
	 */
	JButton closeApplyWindowBtn;
	/**
	 * Apply/remove label from/to file
	 */
	JButton applyApplyWindowBtn;
	/**
	 * Font for buttons
	 */
	private final Font defaultButtonFont = new Font("Arial", Font.PLAIN, 22);
	/**
	 * Font for table
	 */
	private final Font defaultTableFont = new Font("", Font.BOLD, 20);
	/**
	 * Dimension for buttons
	 */
	private final Dimension defaultButtonDimension = new Dimension(130,50);
	/**
	 * button Layout
	 */
	private final GridLayout buttonLayout = new GridLayout(1,0);
			//1,0,30);
	/**
	 * TableModel for table
	 */
	private final DefaultTableModel tableModel = new DefaultTableModel();
	/**
	 * object row for inserting labels
	 */
	Object[] row = new Object[1];
	/**
	 * default name in the label list
	 */
	Object[] columns ={"Label Names"};

	/**
	 * Library of the current loaded profile
	 */
	private final Library labelLibrary = Main.mainProfileManger.getLoadedProfile().getLibrary();

	/**
	 * Constructor for LabelPanel
	 *
	 * @author Andrew, Betelhum
	 */
	public LabelPanel() {
		super();
		this.setLayout(new BorderLayout());
		setUpButtonPane();
		setUpScrollPane();

		add(buttonPanel, BorderLayout.PAGE_START);
		add(scrollPane, BorderLayout.CENTER);

		setUpApplyWindow();

	}

	/**
	 * Method for setting up the add button
	 *
	 * @author Andrew, Betelhum
	 */
	private void setAddButton(){
		addButton = new JButton("+ Label");
		addButton.setPreferredSize(defaultButtonDimension);
		addButton.setFont(defaultButtonFont);

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("Enter Label Name ");
				if ( name != null && !name.equals("")) {
					if (!(labelLibrary.containsLabel(name))) {
						boolean added = labelLibrary.addLabel(name);
						if (added) {
							System.out.println("added");
						} else {
							System.out.println("Not added");
						}
						System.out.println(Main.mainProfileManger.getLoadedProfile().getLibrary().toString());
						row[0] = name;
						tableModel.addRow(row);

						delButton.setEnabled(tableModel.getRowCount() > 0);
						applyButton.setEnabled(tableModel.getRowCount() > 0);

					} else {
						JOptionPane.showMessageDialog(null, "This Label Already Exist");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Can't be empty");
				}

			}

		});

	}

	/**
	 * Method for setting up the delete button
	 *
	 * @author Andrew, Betelhum
	 */
	private void setDeleteButton(){
		delButton = new JButton("- Delete");
		delButton.setPreferredSize(defaultButtonDimension);
		delButton.setFont(defaultButtonFont);
		delButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int delMessage = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete? ","Delete", JOptionPane.YES_NO_OPTION);
				int i = table.getSelectedRow();

				if(delMessage ==0) {
					if (i >= 0) {
						String labelDeleteName = String.valueOf(tableModel.getValueAt(i, 0));
						tableModel.removeRow(i);
						boolean deleted = labelLibrary.removeLabel(labelDeleteName);
						if (deleted) {
							System.out.println("deleted");
						} else {
							System.out.println("Not deleted");
						}
						delButton.setEnabled(tableModel.getRowCount() > 0);
						applyButton.setEnabled(tableModel.getRowCount() > 0);

						JOptionPane.showMessageDialog(null, "Successfully Deleted!");

					} else {
						JOptionPane.showMessageDialog(buttonPanel, "Select Label To Delete");
					}
					}else{
					JOptionPane.showMessageDialog(null,"Label is Not Deleted");
				}
			}
		});



	}

	/**
	 * Method for setting the apply button
	 *
	 * @author Andrew, Betelhum
	 */
	private void setApplyButton(){
		applyButton = new JButton("Apply Label");
		applyButton.setPreferredSize(defaultButtonDimension);
		applyButton.setFont(defaultButtonFont);

		applyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setUpApplyWindow();
				applyFrame.setVisible(true);

			}
		});
	}

	/**
	 * Method that set up apply window
	 *
	 * @author Andrew, Betelhum
	 */
	private void setUpApplyWindow(){
		//create a pop up window
		applyFrame = new JFrame("Apply Window");
		applyFrame.setLayout(new BorderLayout());
		applyFrame.setSize(650,450);
		applyFrame.getDefaultCloseOperation();
		applyFrame.setLocationRelativeTo(null);
		applyFrame.setVisible(false);
		applyFrame.setResizable(false);


		setStartApplyPanel();
		setMidApplyPanel();
		setBotApplyPanel();


		applyFrame.add(startApplyPanel,BorderLayout.PAGE_START);
		applyFrame.add(midApplyPanel, BorderLayout.CENTER);
		applyFrame.add(botApplyPanel,BorderLayout.PAGE_END);




	}

	/**
	 * Method for setting the top of the panel in apply window
	 *
	 * @author Andrew
	 */
	private void setStartApplyPanel(){
		startApplyPanel = new JPanel(new FlowLayout());
		searchField = new JTextField("file to select");
		searchField.setPreferredSize(new Dimension(400,40));

		selectBtn = new JButton("Select File");
		selectBtn.setPreferredSize(new Dimension(110,40));

		selectBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//error checking
				if(!labelLibrary.containsFile(searchField.getText())){
					JOptionPane.showMessageDialog(null, "File does not exists/Wrong file path. Please try again");
				}else{
					if(!listModel.isEmpty()){
						listModel.clear();
					}
					associateLabelArray = labelLibrary.getFile(searchField.getText()).getLabelsArray();
					for (int i = 0; i < associateLabelArray.length; i++) {
						associateLabelName.add(associateLabelArray[i].toString());
						listModel.addElement(associateLabelName.get(i));
					}
				}
			}
		});


		startApplyPanel.add(searchField);
		startApplyPanel.add(selectBtn);


	}

	/**
	 * Method for setting the middle panel in apply window
	 *
	 * @author Andrew
	 */
	private void setMidApplyPanel() {
		midApplyPanel = new JPanel(new FlowLayout());
		/**
		 * Create two JLists
		 * one for apply new labels
		 * one for remove old labels
		 */

		labelLibraryList = new JList<>(labelLibrary.getLabelLibraryArray().toArray());


		labelLibraryList.setFont(new Font("Arial", Font.PLAIN, 20));


		associateLabelName = new ArrayList<>();
		//default
		/*
		if(!listModel.isEmpty()){
			listModel.clear();
		}else{
			if (searchField.getText().equals("file to select")) {
				//do nothing;
			} else {
				associateLabelArray = labelLibrary.getFile(searchField.getText()).getLabelsArray();
				for (int i = 0; i < associateLabelArray.length; i++) {
					associateLabelName.add(associateLabelArray[i].toString());
					listModel.addElement(associateLabelName.get(i));
				}

			}
		}
		 */

		fileLabelList = new JList(listModel);
		fileLabelList.setFont(new Font("Arial", Font.PLAIN, 20));


		addLabelToFile = new JLabel("Add Label", SwingConstants.CENTER);
		addLabelToFile.setPreferredSize(new Dimension(250, 30));
		addLabelToFile.setFont(defaultTableFont);
		removeLabelFromFile = new JLabel("Remove Label", SwingConstants.CENTER);
		removeLabelFromFile.setPreferredSize(new Dimension(250, 30));
		removeLabelFromFile.setFont(defaultTableFont);


		labelLibraryScroll = new JScrollPane(labelLibraryList);
		labelLibraryScroll.setPreferredSize(new Dimension(250, 270));
		fileLabelScroll = new JScrollPane(fileLabelList);
		fileLabelScroll.setPreferredSize(new Dimension(250, 270));

		//adding to the panel
		midApplyPanel.add(addLabelToFile);
		midApplyPanel.add(Box.createHorizontalStrut(10));
		midApplyPanel.add(removeLabelFromFile);

		midApplyPanel.add(labelLibraryScroll);
		midApplyPanel.add(Box.createHorizontalStrut(10));
		midApplyPanel.add(fileLabelScroll);




	}

	/**
	 * Method for setting up the bottom panel in apply window
	 *
	 * @author Andrew, Betelhum
	 */
	private void setBotApplyPanel(){
		botApplyPanel = new JPanel(new FlowLayout());
		applyApplyWindowBtn = new JButton("Apply / Remove");
		closeApplyWindowBtn = new JButton("Close");
		applyApplyWindowBtn.setPreferredSize(new Dimension(120,40));
		closeApplyWindowBtn.setPreferredSize(new Dimension(90,40));

		applyApplyWindowBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//check if file name is correct
				labelLibraryList.repaint();
				fileLabelList.repaint();

				if(!labelLibrary.containsFile(searchField.getText())){
					JOptionPane.showMessageDialog(null, "Wrong file path");
					return;
				}
				//check if add label list is selected (APPLY)
				if(!fileLabelList.isSelectionEmpty()) {
					AppLabel selectedLabel = labelLibrary.getLabel((String) fileLabelList.getSelectedValue());
					labelLibrary.removeLabelFromFile(labelLibrary.getFile(searchField.getText()), selectedLabel);
				}
				if(!labelLibraryList.isSelectionEmpty()){
					AppLabel selectedLabel = (AppLabel) labelLibraryList.getSelectedValue();
					System.out.println(selectedLabel.toString());

					labelLibrary.applyLabelToFile(labelLibrary.getFile(searchField.getText()), selectedLabel);
				}//check if remove label list is selected (REMOVE)
				if (labelLibraryList.isSelectionEmpty() && fileLabelList.isSelectionEmpty()){	//else no label list is selected
					System.out.println(labelLibraryList.getSelectedIndex());
					System.out.println(fileLabelList.getSelectedIndex());
					JOptionPane.showMessageDialog(null, "No label is selected");
				}



			}
		});


		closeApplyWindowBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listModel.clear();
				applyFrame.dispose();
			}
		});


		JButton clearBtn = new JButton("Clear");
		clearBtn.setPreferredSize(new Dimension(90,40));
		clearBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				labelLibraryList.clearSelection();
				fileLabelList.clearSelection();


			}
		});


		botApplyPanel.add(Box.createHorizontalStrut(300));
		botApplyPanel.add(applyApplyWindowBtn);
		botApplyPanel.add(clearBtn);
		botApplyPanel.add(closeApplyWindowBtn);

	}

	/**
	 * Method for setting up the button panel
	 *
	 * @author Andrew, Betelhum
	 */
	private void setUpButtonPane(){
		setAddButton();
		setDeleteButton();
		setApplyButton();

		buttonPanel = new JPanel(buttonLayout);
		buttonPanel.add(addButton);
		buttonPanel.add(delButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(applyButton);


	}

	/**
	 * Method for setting up the label panel
	 *
	 * @author Andrew, Betelhum
	 */
	private void setLabelPanel(){

		table = new JTable();
		tableModel.setColumnIdentifiers(columns);
		table.setModel(tableModel);
		table.setRowHeight(30);
		table.setFont(defaultTableFont);
		table.setDefaultEditor(Object.class, null);

		//load the existing profile
		System.out.println(labelLibrary.toString());
		ArrayList<AppLabel> labelArray = labelLibrary.getLabelLibraryArray();
		int lengthOfLabel = labelLibrary.getLabelLibraryArray().size();

		for(int i = 0; i < lengthOfLabel; i++) {
			String nameOfExistingLabel = labelArray.get(i).getMyName();
			row[0] = nameOfExistingLabel;
			tableModel.addRow(row);
		}

		delButton.setEnabled(tableModel.getRowCount() > 0);
		applyButton.setEnabled(tableModel.getRowCount() > 0);


	}

	/**
	 * Sort the labels Alphabetical order
	 * A-Z or Z - A
	 *
	 * @author Betelhum
	 */
	private void sort(){
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		rowSorter.setSortKeys(sortKeys);


	}

	/**
	 * Setting up the Scroll Panel
	 *
	 * @author Andrew, Betelhum
	 */
	private void setUpScrollPane(){
		setLabelPanel();
		sort();
		add(table,BorderLayout.CENTER);
		scrollPane = new JScrollPane(table);

	}

}
