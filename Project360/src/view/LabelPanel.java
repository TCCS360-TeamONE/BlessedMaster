package view;

import java.awt.*;

import javax.swing.*;

public class LabelPanel extends JPanel {
	
	/**
	 *  
	 */
	private static final long serialVersionUID = 3058061381038686629L;

	private JButton addButton;
	private JButton delButton;
	private JButton applyButton;
	private JLabel emptyLabel;
	private JPanel buttonPanel;
	private JScrollPane scrollPane;
	private JPanel labelPanel;


	private final Font defaultButtonFont = new Font("Arial", Font.PLAIN, 20);
	private final Dimension defaultButtonDimension = new Dimension(130,50);
	private GridLayout buttonLayout = new GridLayout(1,0);




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
	}

	private void setDeleteButton(){
		delButton = new JButton("- Delete");
		delButton.setPreferredSize(defaultButtonDimension);
		delButton.setFont(defaultButtonFont);
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

		String[] btnNameList = new String[1000];
		for(int index = 0; index < 1000; index++){
			String name = "Button " + index;
			btnNameList[index] = name;

		}

		JButton[] btn = new JButton[btnNameList.length];

		try{
			for(int i = 0; i < btnNameList.length; i++){
				btn[i] = new JButton(btnNameList[i]);
				btn[i].setPreferredSize(new Dimension(200,80));
				labelPanel.add(btn[i]);
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, 3);
		}


	}

	private void setUpScrollPane(){
		setLabelPanel();
		scrollPane = new JScrollPane(labelPanel);



	}


}
