/*

Andrew & Betelhem
 */

package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import main.Main;
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

	private final Font defaultButtonFont = new Font("Arial", Font.PLAIN, 22);
	private final Dimension defaultButtonDimension = new Dimension(130,50);
	private final GridLayout buttonLayout = new GridLayout(1,0);
			//1,0,30);
	private final DefaultTableModel tableModel = new DefaultTableModel();

	private ArrayList<String > btnNameList = new ArrayList<>();
	private JButton btn[] = new JButton[1000];


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
				if(name != null) {
					//Main.mainProfileManger.getLoadedProfile()
					Object[] row = new Object[1];
					row[0] = name;
					tableModel.addRow(row);
					delButton.setEnabled(true);
					applyButton.setEnabled(true);
				}
			}
		});

	}

	private void setDeleteButton(){
		delButton = new JButton("- Delete");
		delButton.setPreferredSize(defaultButtonDimension);
		delButton.setFont(defaultButtonFont);
		delButton.setEnabled(false);

		delButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int i = table.getSelectedRow();
				if(i>=0){
					tableModel.removeRow(i);
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
		applyButton.setEnabled(false);
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
		Object[] columns ={"Label Names"};
		tableModel.setColumnIdentifiers(columns);
		table.setModel(tableModel);
		Font font = new Font("", Font.BOLD, 20);
		table.setRowHeight(30);
		table.setFont(font);


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



