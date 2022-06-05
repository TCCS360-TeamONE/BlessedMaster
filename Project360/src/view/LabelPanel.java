/**

Andrew & Betelhem
 */

package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import main.Main;
import model.AppLabel;
import model.Library;

public class LabelPanel extends JPanel {

	/**
	 *
	 */
	@Serial
	private static final long serialVersionUID = 3058061381038686629L;


	private JButton addButton;
	private JButton delButton;
	private JButton applyButton;
	private JPanel buttonPanel;
	private JScrollPane scrollPane;
	private JTable table;

	//apply panel
	JFrame applyFrame;
	JPanel startApplyPanel;
	JTextField searchField;
	JButton selectBtn;
	JPanel midApplyPanel;
	JList labelLibraryList;
	ArrayList<String> associateLabelName;
	AppLabel[] associateLabelArray;
	JList fileLabelList;
	JLabel addLabelToFile;
	JLabel removeLabelFromFile;
	JScrollPane labelLibraryScroll;
	JScrollPane fileLabelScroll;
	JPanel botApplyPanel;
	JButton closeApplyWindowBtn;
	JButton applyApplyWindowBtn;




	private final Font defaultButtonFont = new Font("Arial", Font.PLAIN, 22);
	private final Font defaultTableFont = new Font("", Font.BOLD, 20);
	private final Dimension defaultButtonDimension = new Dimension(130,50);
	private final GridLayout buttonLayout = new GridLayout(1,0);
			//1,0,30);
	private final DefaultTableModel tableModel = new DefaultTableModel();
	Object[] row = new Object[1];
	Object[] columns ={"Label Names"};

	private final Library labelLibrary = Main.mainProfileManger.getLoadedProfile().getLibrary();


	public LabelPanel() {
		super();
		this.setLayout(new BorderLayout());
		setUpButtonPane();
		setUpScrollPane();

		add(buttonPanel, BorderLayout.PAGE_START);
		add(scrollPane, BorderLayout.CENTER);
	}


	private void setAddButton(){
		addButton = new JButton("+ Label");
		addButton.setPreferredSize(defaultButtonDimension);
		addButton.setFont(defaultButtonFont);

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("Enter Label Name ");
				if (!name.equals("") || name == null) {
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


	private void setApplyButton(){
		applyButton = new JButton("Apply Label");
		applyButton.setPreferredSize(defaultButtonDimension);
		applyButton.setFont(defaultButtonFont);

		applyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setUpApplyWindow();

			}
		});
	}

	private void setUpApplyWindow(){
		//create a pop up window
		applyFrame = new JFrame("Apply Window");
		applyFrame.setLayout(new BorderLayout());
		applyFrame.setSize(650,450);
		applyFrame.getDefaultCloseOperation();
		applyFrame.setLocationRelativeTo(null);
		applyFrame.setVisible(true);
		applyFrame.setResizable(false);

		setStartApplyPanel();
		setMidApplyPanel();
		setBotApplyPanel();



		applyFrame.add(startApplyPanel,BorderLayout.PAGE_START);
		applyFrame.add(midApplyPanel, BorderLayout.CENTER);
		applyFrame.add(botApplyPanel,BorderLayout.PAGE_END);

	}

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
					//refresh the panel
					setMidApplyPanel();
					applyFrame.revalidate();
					applyFrame.repaint();
				}
			}
		});


		startApplyPanel.add(searchField);

		startApplyPanel.add(selectBtn);


	}

	private void setMidApplyPanel(){
		midApplyPanel = new JPanel(new FlowLayout());
		/**
		 * Create two JLists
		 * one for apply new labels
		 * one for remove old labels
		 */

		labelLibraryList = new JList(labelLibrary.getLabelLibraryArray().toArray());


		labelLibraryList.setFont(new Font("Arial", Font.PLAIN, 20));


		associateLabelName = new ArrayList<>();
		//default
		if(searchField.getText().equals("file to select")){
			//do nothing;
		}else{
			associateLabelArray = labelLibrary.getFile(searchField.getText()).getLabelsArray();
			for(int i = 0; i < associateLabelArray.length; i++){
				associateLabelName.add(associateLabelArray[i].toString());
			}

			//check the file is attached to which label
			System.out.println(associateLabelName.toString());
		}

		fileLabelList = new JList(associateLabelName.toArray());

		addLabelToFile = new JLabel("Add Label", SwingConstants.CENTER);
		addLabelToFile.setPreferredSize(new Dimension(250,30));
		addLabelToFile.setFont(defaultTableFont);
		removeLabelFromFile = new JLabel("Remove Label", SwingConstants.CENTER);
		removeLabelFromFile.setPreferredSize(new Dimension(250,30));
		removeLabelFromFile.setFont(defaultTableFont);


		labelLibraryScroll = new JScrollPane(labelLibraryList);
		labelLibraryScroll.setPreferredSize(new Dimension(250,270));
		fileLabelScroll = new JScrollPane(fileLabelList);
		fileLabelScroll.setPreferredSize(new Dimension(250,270));

		//adding to the panel
		midApplyPanel.add(addLabelToFile);
		midApplyPanel.add(Box.createHorizontalStrut(10));
		midApplyPanel.add(removeLabelFromFile);

		midApplyPanel.add(labelLibraryScroll);
		midApplyPanel.add(Box.createHorizontalStrut(10));
		midApplyPanel.add(fileLabelScroll);

	}

	private void setBotApplyPanel(){
		botApplyPanel = new JPanel(new FlowLayout());
		applyApplyWindowBtn = new JButton("Apply");
		closeApplyWindowBtn = new JButton("Close");
		applyApplyWindowBtn.setPreferredSize(new Dimension(90,40));
		closeApplyWindowBtn.setPreferredSize(new Dimension(90,40));

		applyApplyWindowBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//check if file name is correct
				if (!labelLibrary.containsFile(searchField.getText())) {
					JOptionPane.showMessageDialog(null, "Wrong file path");
					return;
				}
				//check if add label list is selected (APPLY)
				if (!labelLibraryList.isSelectionEmpty()) {
					AppLabel selectedLabel = (AppLabel) labelLibraryList.getSelectedValue();
					labelLibrary.applyLabelToFile(labelLibrary.getFile(searchField.getText()), selectedLabel);
					JOptionPane.showMessageDialog(null, "Label");
				}	//check if remove label list is selected (REMOVE)

				else {    //else no label is selected
					System.out.println(labelLibraryList.getSelectedIndex());
					System.out.println(fileLabelList.getSelectedIndex());
					JOptionPane.showMessageDialog(null, "No label is selected");
				}

				if (!fileLabelList.isSelectionEmpty()) {
						AppLabel selectedLabel = labelLibrary.getLabel((String) fileLabelList.getSelectedValue());
						labelLibrary.removeLabelFromFile(labelLibrary.getFile(searchField.getText()), selectedLabel);


				}

			}

		});

		closeApplyWindowBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applyFrame.dispose();
			}
		});

		botApplyPanel.add(Box.createHorizontalStrut(430));
		botApplyPanel.add(applyApplyWindowBtn);
		botApplyPanel.add(closeApplyWindowBtn);

	}

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
	 */


	private void sort(){
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		rowSorter.setSortKeys(sortKeys);


	}
	private void setUpScrollPane(){
		setLabelPanel();
		sort();
		add(table,BorderLayout.CENTER);
		scrollPane = new JScrollPane(table);

	}

}
