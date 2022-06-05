package view;

import main.Main;
import model.AppFile;
import model.AppLabel;
import model.Library;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.*;


/**
 * File Panel
 *
 * @authors Anthony
 */

public class FilePanel extends JPanel {

	private final int MAX_LABELS = 5;
	private JPanel top;

	private JPanel buttonPanel;
	private JButton addButton;
	private JButton delButton;

	private JPanel searchPanel;
	private JLabel searchLabel;
	private JTextField searchInput;
	private JComboBox labelOptions;
	private JButton searchButton;
	private JButton removeButton;

	private JList filesList;
	private JScrollPane scrollPane;

	private JPanel labelDisplay;
	private JButton openFileButton;
	private JPanel openPanel;
	private JPanel bottomPannel;
	private JList labelsList;
	private ArrayList<String> appliedLabels = new ArrayList<>();

	private final Font font = new Font("Arial", Font.PLAIN, 15);
	private final Font textfont = new Font("Arial", Font.PLAIN, 20);
	private final Dimension defaultButtonDimension = new Dimension(100,40);
	private GridLayout buttonLayout = new GridLayout(1,0);

	private final Library fileLibrary = Main.mainProfileManger.getLoadedProfile().getLibrary();

	DefaultListModel dm;


	private static final long serialVersionUID = -3452605865536486557L;

	public FilePanel() {
		super();
		this.setLayout(new BorderLayout());

		buildTopPanel();
		add(top, BorderLayout.PAGE_START);

		buildScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		buildBottomPanel();
		add(bottomPannel, BorderLayout.PAGE_END);
	}

	public void buildTopPanel() {
		top = new JPanel();

		buildAddDeleteFilePane();
		buildSearchPane();

		top.add(buttonPanel);
		top.add(searchPanel);
	}
	public void buildSearchPane() {
		searchPanel = new JPanel();

		searchLabel = new JLabel("Label Filter");
		searchLabel.setFont(textfont);

		searchInput = new JTextField(20);
		searchInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchAction();
			}
		});
		buildSearchButton();
		buildRemoveLabelsButton();

		searchPanel.add(searchLabel);
		searchPanel.add(searchInput);
		searchPanel.add(searchButton);
		searchPanel.add(removeButton);

	}

	private void buildSearchButton() {
		searchButton = new JButton("Apply");
		searchButton.setPreferredSize(defaultButtonDimension);
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchAction();
			}
		});
	}

	private void searchAction() {
		if (appliedLabels.size() == MAX_LABELS) {
			JOptionPane.showMessageDialog(null, "You have reached the maximum amount of labels");
		} else if (searchInput.getText() != "" && fileLibrary.getLabel(searchInput.getText()) != null) {
			//test
			//fileLibrary.applyLabelToFile(fileLibrary.getFile("/Users/anthonycabrera/Desktop/pdfs/file1.pdf"), fileLibrary.getLabel("one"));
			//fileLibrary.applyLabelToFile(fileLibrary.getFile("/Users/anthonycabrera/Desktop/pdfs/file1.pdf"), fileLibrary.getLabel("two"));
			//fileLibrary.applyLabelToFile(fileLibrary.getFile("/Users/anthonycabrera/Desktop/pdfs/file2.pdf"), fileLibrary.getLabel("one"));
			appliedLabels.add(searchInput.getText());
			// version 1
			//searchFileList(searchInput.getText());

			//version 2
			searchFileList2();
			appliedLabelsDisplay();
		} else {
			JOptionPane.showMessageDialog(null, "This Label Does Not Exist");
		}
		searchInput.setText("");
	}

	private void buildRemoveLabelsButton() {
		removeButton = new JButton("Remove");
		removeButton.setPreferredSize(defaultButtonDimension);
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshList();
				appliedLabels.clear();
				appliedLabelsDisplay();
			}
		});
	}


	private void buildAddDeleteFilePane(){
		buildAddFileButton();
		fileDeleteButton();

		buttonPanel = new JPanel(buttonLayout);

		buttonPanel.add(addButton);
		buttonPanel.add(delButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(Box.createHorizontalGlue());

	}

	private void buildAddFileButton(){
		addButton = new JButton("Add File");
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
					String fileName = f.getName();
					if (!fileLibrary.containsFile(path)) {
						Boolean test = fileLibrary.addFile(path);
					} else {
						JOptionPane.showMessageDialog(null, "ERROR: Duplicate File");
					}
					refreshList();


				}
			}
		});
	}


	private void fileDeleteButton(){
		delButton = new JButton("Delete File");
		delButton.setPreferredSize(defaultButtonDimension);
		delButton.setFont(font);
		delButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int delMessage = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete? ","Delete", JOptionPane.YES_NO_OPTION);
				String deleteFile = (String) filesList.getSelectedValue();
				if (delMessage == 0){
					boolean deleted = fileLibrary.removeFile(deleteFile);
					JOptionPane.showMessageDialog(null,"File deleted");
					refreshList();
				} else {
					JOptionPane.showMessageDialog(null,"File was not deleted");
				}
			}
		});
	}

	private void buildFileList() {
		//test
		filesList = new JList(buildFileArrayList().toArray());
		filesList.setVisibleRowCount(15);
		filesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		filesList.setFont(font);
		add(new JScrollPane(filesList));
	}

	private ArrayList<String> buildFileArrayList() {
		ArrayList<String> list = new ArrayList<String>();
		for (AppFile f : fileLibrary.getFileLibraryArray()) {
			list.add(f.getFilePath());
		}
		return list;
	}

	private void buildScrollPane() {
		buildFileList();
		scrollPane = new JScrollPane(filesList);
	}

	private void buildLabelDisplay() {

		labelDisplay = new JPanel();
		labelsList = new JList(appliedLabels.toArray());
		labelsList.setVisibleRowCount(1);
		labelsList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		labelsList.setFont(textfont);

		JLabel labelLabels = new JLabel("Applied Label Filters: ");
		labelLabels.setFont(textfont);

		labelDisplay.add(labelLabels);
		labelDisplay.add(labelsList);
		labelDisplay.setPreferredSize(new Dimension(1000,40));
	}

	private void buildBottomPanel() {
		buildLabelDisplay();
		setOpenPanel();

		bottomPannel = new JPanel();
		bottomPannel.add(labelDisplay, BorderLayout.WEST);
		bottomPannel.add(openFileButton, BorderLayout.EAST);
	}

	private void setOpenPanel() {
		openPanel = new JPanel();

		openFileButton = new JButton("Open File");
		openFileButton.setPreferredSize(defaultButtonDimension);
		openFileButton.setFont(font);
		openFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String s = (String) filesList.getSelectedValue();
					Desktop.getDesktop().open(new File(s));
				} catch (Exception ex) {
					System.out.println("fail");
					JOptionPane.showMessageDialog(null, "Sorry, couldn't open file path.");
				}

			}
		});

		openPanel.add(openFileButton);

	}


	private void refreshList() {
		dm = new DefaultListModel();
		filesList.setModel(dm);
		for (AppFile f: fileLibrary.getFileLibraryArray()) {
			dm.addElement(f.getFilePath());
		}
	}

	private void appliedLabelsDisplay() {
		dm = new DefaultListModel();
		labelsList.setModel(dm);
		if (appliedLabels.size() > 0) {
			for (String label: appliedLabels) {
				dm.addElement(label);
			}
		}
	}

	private void searchFileList(String label) {
		AppLabel searchLabel;
		dm = new DefaultListModel();
		filesList.setModel(dm);

		searchLabel = fileLibrary.getLabel(label);

		for (AppFile f: searchLabel.getFilesArray()) {
			dm.addElement(f.getFilePath());
		}

	}

	private void searchFileList2() {
		ArrayList<String> locatedFiles = new ArrayList<>();
		ArrayList<String> finalList = new ArrayList<>();

		ArrayList<AppLabel> labLib = new ArrayList<>();
		// creates an arraylist for all selected labels
		for (String label: appliedLabels) {
			labLib.add(fileLibrary.getLabel(label));
		}

		for (AppLabel l: labLib) {
			AppFile[] tempFiles = l.getFilesArray();
			for (AppFile temp: tempFiles) {
				locatedFiles.add(temp.getFilePath());
			}
		}

		HashMap<String, Integer> map = new HashMap<>();

		for (String s: locatedFiles) {
			map.put(s, 0);
		}

		for (int i = 0; i < locatedFiles.size(); i++) {
			String current = locatedFiles.get(i);
			int count = map.get(current) + 1;
			map.put(current, count);
			if (count == appliedLabels.size()) {
				finalList.add(current);
			}
		}

		dm = new DefaultListModel();
		filesList.setModel(dm);

		for (String s: finalList) {
			dm.addElement(s);
		}

	}

}
