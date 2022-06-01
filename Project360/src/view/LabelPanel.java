package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class LabelPanel extends JPanel {
	
	/**
	 *  
	 */
	private static final long serialVersionUID = 3058061381038686629L;

	private JButton addButton;
	private JButton delButton;
	private JButton applyButton;
	private JPanel buttonPanel;
	private JScrollPane scrollPane;
	private JPanel labelPanel;


	private final Font defaultButtonFont = new Font("Arial", Font.PLAIN, 20);
	private final Dimension defaultButtonDimension = new Dimension(130,50);
	private GridLayout buttonLayout = new GridLayout(1,0);



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


		String labelNameInputMessage = "Please enter a name for a new label";

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String labelName = JOptionPane.showInputDialog(labelNameInputMessage);
				//No duplicate name
				for(int i = 0; i < btnNameList.size(); i++){
					if(labelName.equals(btnNameList.get(i))){
						JOptionPane.showMessageDialog(null,"Duplicate Label Name");
						return;
					}
				}
				btnNameList.add(labelName);
				int indexOfLabel = btnNameList.size() - 1;
				btn[indexOfLabel] = new JButton(btnNameList.get(indexOfLabel));
				btn[indexOfLabel].setName(btnNameList.get(indexOfLabel));
				btn[indexOfLabel].setPreferredSize(new Dimension(200,150));
				labelPanel.add(btn[indexOfLabel]);

				//repaint
				labelPanel.revalidate();
				labelPanel.repaint();

			}
		});
	}

	private void setDeleteButton(){
		delButton = new JButton("- Delete");
		delButton.setPreferredSize(defaultButtonDimension);
		delButton.setFont(defaultButtonFont);

		String deleteLabelMessage = "Please enter the label name to delete";

		delButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//ask for the name of label that wants to delete
				String deleteLabelName = JOptionPane.showInputDialog(deleteLabelMessage);
				//search for that label in the label name list and button list
				//see if the label name exists in the label name list
				if(btnNameList.contains(deleteLabelName)){

					//remove the label in both list
					int indexOfLabel = findIndex(btn, deleteLabelName);
					btnNameList.remove(deleteLabelName);
					labelPanel.remove(btn[indexOfLabel]);

					//repaint the panel
					labelPanel.revalidate();
					labelPanel.repaint();

				}else{
					JOptionPane.showMessageDialog(null, "Does not exists");
				}


			}
		});
	}


	//get the index of the btn list(label list)
	private int findIndex(JButton[] labelArray, String labelName ){
		//check if array is null
		if(labelArray == null){
			return -1;
		}
		// find length of array
		int len = labelArray.length;
		int i = 0;

		// traverse in the array
		while (i < len) {

			// if the i-th element is t
			// then return the index
			if (labelArray[i].getName().equals(labelName)) {
				return i;
			}
			if(labelArray[i].getName() == null){
				System.out.println("Name is null");
			}
			else {
				i = i + 1;
			}
		}
		return -1;

	}

	private void setApplyButton(){
		applyButton = new JButton("Apply Label");
		applyButton.setPreferredSize(defaultButtonDimension);
		applyButton.setFont(defaultButtonFont);
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
		labelPanel = new JPanel(new GridLayout(0,3));

		for(int index = 0; index < 10; index++){
			String name = "Button " + index;
			btnNameList.add(name);

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

	private void setUpScrollPane(){
		setLabelPanel();
		scrollPane = new JScrollPane(labelPanel);



	}


}
