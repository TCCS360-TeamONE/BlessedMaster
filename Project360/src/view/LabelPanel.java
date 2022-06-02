/**

Andrew & Betelhem
 */

package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.lang.reflect.Array;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import main.Main;
import model.AppFile;
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

				int i = table.getSelectedRow();

				if(i>=0){
					String labelDeleteName = String.valueOf(tableModel.getValueAt(i, 0));
					tableModel.removeRow(i);
					boolean deleted = labelLibrary.removeLabel(labelDeleteName);
					if(deleted){
						System.out.println("deleted");
					}else{
						System.out.println("Not deleted");
					}
					delButton.setEnabled(tableModel.getRowCount() > 0);
					applyButton.setEnabled(tableModel.getRowCount() > 0);
				}
				else{
					JOptionPane.showMessageDialog(buttonPanel, "Select Label To Delete");

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

		JList labelLibraryList = new JList(labelLibrary.getLabelLibraryArray().toArray());


		labelLibraryList.setFont(new Font("Arial", Font.PLAIN, 20));


		ArrayList<String> associateLabelName = new ArrayList<>();
		//default
		if(searchField.getText().equals("file to select")){
			//do nothing;
		}else{
			AppLabel[] associateLabelArray = labelLibrary.getFile(searchField.getText()).getLabelsArray();
			for(int i = 0; i < associateLabelArray.length; i++){
				associateLabelName.add(associateLabelArray[i].toString());
			}
		}

		JList fileLabelList = new JList(associateLabelName.toArray());



		JLabel addLabelToFile = new JLabel("Add Label", SwingConstants.CENTER);
		addLabelToFile.setPreferredSize(new Dimension(250,30));
		addLabelToFile.setFont(defaultTableFont);
		JLabel removeLabelFromFile = new JLabel("Remove Label", SwingConstants.CENTER);
		removeLabelFromFile.setPreferredSize(new Dimension(250,30));
		removeLabelFromFile.setFont(defaultTableFont);


		JScrollPane labelLibraryScroll = new JScrollPane(labelLibraryList);
		labelLibraryScroll.setPreferredSize(new Dimension(250,270));
		JScrollPane fileLabelScroll = new JScrollPane(fileLabelList);
		fileLabelScroll.setPreferredSize(new Dimension(250,270));

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
	private void setUpScrollPane(){
		setLabelPanel();

		add(table,BorderLayout.CENTER);
		scrollPane = new JScrollPane(table);

	}

}

/*



		labelPanel = new JPanel(new GridLayout(0,3,30,20));

		String[] btnNameList = new String[10];
		for(int index = 0; index <= 0; index++){
		String name = "Button " + index;
		btnNameList[index] = name;


		}


		try{
			for(int i = 0; i < btnNameList.size(); i++){
				btn[i] = new JButton(btnNameList.get(i));
				btn[i].setName(btnNameList.get(i));
				btn[i].setPreferredSize(new Dimension(200,150));
				labelPanel.add(btn[i]);
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Can't generate labelPanel\n" + e);
		}




	}

 */



