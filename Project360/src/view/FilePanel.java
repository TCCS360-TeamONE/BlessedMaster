package view;

import main.Main;
import model.Library;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


/**
 * File Panel
 *
 * @authors Anthony
 */

public class FilePanel extends JPanel {
	private JPanel top;

	private JPanel buttonPanel;
	private JButton addButton;
	private JButton delButton;

	private JPanel searchPanel;
	private JLabel searchLabel;
	private JTextField searchInput;
	private JButton searchButton;

	private JList filesList;
	private String[] files;
	private JScrollPane scrollPane;

	private JPanel labelDisplay;
	private JList labelsList;

	private final Font font = new Font("Arial", Font.PLAIN, 20);
	private final Dimension defaultButtonDimension = new Dimension(120,40);
	private GridLayout buttonLayout = new GridLayout(1,0);

	private final Library fileLibrary = Main.mainProfileManger.getLoadedProfile().getLibrary();

	/**
	 *
	 */
	private static final long serialVersionUID = -3452605865536486557L;

	public FilePanel() {
		super();
		this.setLayout(new BorderLayout());

		buildTopPanel();
		add(top, BorderLayout.PAGE_START);

		buildScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		buildLabelDisplay();
		add(labelDisplay, BorderLayout.PAGE_END);
	}

	public void buildTopPanel() {
		top = new JPanel();

		buildButtonPane();
		buildSearchPane();

		top.add(buttonPanel);
		top.add(searchPanel);
	}
	public void buildSearchPane() {
		searchPanel = new JPanel();

		searchLabel = new JLabel("Search");
		searchLabel.setFont(font);

		ImageIcon searchIcon = new ImageIcon("./icons/search_A_32.png");

		searchInput = new JTextField(20);
		searchButton = new JButton(searchIcon);
		searchButton.setPreferredSize(defaultButtonDimension);

		searchPanel.add(searchLabel);
		searchPanel.add(searchInput);
		searchPanel.add(searchButton);

	}

	private void buildButtonPane(){
		setAddButton();
		setDeleteButton();

		buttonPanel = new JPanel(buttonLayout);

		buttonPanel.add(addButton);
		buttonPanel.add(delButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(Box.createHorizontalGlue());

	}

	private void setAddButton(){
		addButton = new JButton("Add");
		addButton.setPreferredSize(defaultButtonDimension);
		addButton.setFont(font);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				file.setMultiSelectionEnabled(true);
				file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				file.setFileHidingEnabled(false);
				if (file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					java.io.File f = file.getSelectedFile();
					String path = f.getPath();
					System.out.println(path);

					Boolean test = fileLibrary.addFile(path);
				}
			}
		});
	}


	private void setDeleteButton(){
		delButton = new JButton("Remove");
		delButton.setPreferredSize(defaultButtonDimension);
		delButton.setFont(font);
	}

	private void buildFileList() {
		generateFiles();
		//test
		filesList = new JList(fileLibrary.getFileLibraryArray().toArray());
		filesList.setVisibleRowCount(15);
		filesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		filesList.setFont(font);
		add(new JScrollPane(filesList));
	}

	private void generateFiles() {
		files = new String[100];
		for (int i = 0; i < 100; i++) {
			files[i] = "File" + i + ".pdf";
		}
	}

	private void buildScrollPane() {
		buildFileList();
		scrollPane = new JScrollPane(filesList);
	}

	private void buildLabelDisplay() {
		labelDisplay = new JPanel();
		String[] options = { "Label1;", "Label2;", "Label3" };
		labelsList = new JList(options);
		labelsList.setVisibleRowCount(1);
		labelsList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		labelsList.setFont(font);

		JLabel labelLabels = new JLabel("Attached Labels: ");
		labelLabels.setFont(font);

		labelDisplay.add(labelLabels);
		labelDisplay.add(labelsList);
	}


}
