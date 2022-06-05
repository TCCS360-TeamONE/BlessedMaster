package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.AboutWindow;
import main.Main;

/**
 * MainWindow contains the tabbed pane, and within those, the file, label, profile panels.
 * 
 *    * Connected to the java classes: AboutWindow, FilePanel, LabelPanel, and ProfilePane.
 *    *
 */
public class MainWindow extends JFrame {
	
	/** The title given to the main window. */
	private static final String TITLE = "TeamONE :: FileSorter";
	
	/** The default size of the main window. */
	private static final Dimension WINDOW_DEFAULT_SIZE = new Dimension(1200,800);
	
	/** The minimum size of the main window. */
	private static final Dimension WINDOW_MINIMUM_SIZE = new Dimension(800,500);
	
	/** The font used my the tabs in the tabbed pane. */
//	private static final Font TABS_FONT = new Font("Lucida Sans", Font.PLAIN, 22); <= I like this font better
	private static final Font TABS_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 22);
	
	private static final Color LOCKED_TAB_COLOR = new Color(100,100,100);
	
	private static final Color UNLOCKED_TAB_COLOR = new Color(200,200,200);
	
	private static final Dimension TAB_SIZE = new Dimension(180, 40);
	
	/** The tabbed pane, hold the file, label, profile tabs. */
	private static JTabbedPane tabbedPane;

	/** Big number go Brrrrrrrrrr */
	private static final long serialVersionUID = 5846307300982824039L;

	
	public MainWindow() {
		super(TITLE);
		setSize(WINDOW_DEFAULT_SIZE);
		setMinimumSize(WINDOW_MINIMUM_SIZE);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				sysSaveAndExit();
			}
		});
		
		tabbedPane = new JTabbedPane();
        buildTabbedPane();
        tabChangeListener();
        tabbedPane.setSelectedIndex(2);
		
        setContentPane(tabbedPane);
        setVisible(true);
        setLocationRelativeTo(null);
	}
	
	public JPanel getComponent(int index) {
		return (JPanel) tabbedPane.getComponentAt(index);
	}
	
	/**
	 * Runs a custom set of actions when the MainWindow is closed.<p>
	 * 
	 * Do not use this and the "setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)"
	 * at the same time. This does what that does but also saves the persistent
	 * data of the application. 
	 * 
	 * @author Christopher
	 */
	private void sysSaveAndExit() {
		Main.savePersistentData();
		System.out.println("All Done");
		System.exit(0);
	}
	
	private void tabChangeListener() {
		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("Tab " + tabbedPane.getSelectedIndex() + " open");
				Main.savePersistentData();
			}
			
		});
	}
	
	private void buildFilePane() {
		FilePanel myFilePanel = new FilePanel();
		tabbedPane.addTab("error", myFilePanel);
		tabbedPane.setToolTipTextAt(0, "this is where the Files live");
		
		ImageIcon fileIcon = new ImageIcon("./icons/file_A_32.png");
		JLabel fileTab = new JLabel();
		fileTab.setText(" Files ");
		fileTab.setIcon(fileIcon);
		fileTab.setFont(TABS_FONT);
		fileTab.setPreferredSize(TAB_SIZE);

		tabbedPane.setTabComponentAt(0, fileTab);
	}
	
	private void buildLabelPane() {
		LabelPanel myLabelPanel = new LabelPanel();

		tabbedPane.addTab("", myLabelPanel);
		tabbedPane.setToolTipTextAt(1, "this is where the Labels live");

		ImageIcon labelIcon = new ImageIcon("./icons/label_A_32.png");
		JLabel labelTab = new JLabel();
		labelTab.setText(" Labels ");
		labelTab.setIcon(labelIcon);
		labelTab.setFont(TABS_FONT);
		labelTab.setPreferredSize(TAB_SIZE);
		tabbedPane.setTabComponentAt(1, labelTab);
	}
	
	private void buildProfilePane() {
		ProfilePanel myProfilePanel = new ProfilePanel();
		
		tabbedPane.addTab("", myProfilePanel);
		tabbedPane.setToolTipTextAt(2, "Manage Proflies and Import/export here!");

		ImageIcon profileIcon = new ImageIcon("./icons/user_A_32.png");
		JLabel profileTab = new JLabel();
		profileTab.setText(" Profile ");
		profileTab.setIcon(profileIcon);
		profileTab.setFont(TABS_FONT);
		profileTab.setPreferredSize(TAB_SIZE);
        
		tabbedPane.setTabComponentAt(2, profileTab);
	}
	
	/**
	 * Sets up the main tabbed pane and puts the FilesPanel, LabelPanel,
	 * ProfilePanel, and AboutWindow button into 4 tabs.
	 * 
	 * @author Christopher
	 */
	private void buildTabbedPane() {

        tabbedPane.setFont(TABS_FONT);
        tabbedPane.setBackground(UNLOCKED_TAB_COLOR); // un-selected tab background color
        tabbedPane.setForeground(Color.BLACK); // sets text color in tabs
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        
        buildFilePane();
        buildLabelPane();
        buildProfilePane();
        
        
		tabbedPane.addTab("", null);
		int aboutIndex = tabbedPane.getTabCount() - 1;
		tabbedPane.setTabComponentAt(aboutIndex, aboutButon());
        tabbedPane.setToolTipTextAt(aboutIndex, "Opens the About Window");
        tabbedPane.setBackgroundAt(aboutIndex, new Color(50,50,50));

        ///////////////////////
        
        tabbedPane.setFocusable(false);
        tabbedPane.setTabPlacement(JTabbedPane.TOP);
	}
	

	/**
	 * Helper method to build the About button and handle it's click event.
	 * 
	 * @author Alan T.
	 * @return the About Button
	 */
	private JButton aboutButon() {
		JButton bAbout = new JButton("About");
		bAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AboutWindow(Main.DEFAULT_WIDTH/2, (Main.DEFAULT_HEIGHT/2));
			}
		});
		
		bAbout.setFocusPainted(false);;
		bAbout.setBorder(null);
		bAbout.setContentAreaFilled(false);
		bAbout.setFont(TABS_FONT);
		bAbout.setPreferredSize(new Dimension(100,39));
		bAbout.setForeground(Color.white);
		
		return bAbout;
	}

}
