package view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class FilePanel extends JPanel {
	
	
	
	/**
	 *  
	 */
	private static final long serialVersionUID = -3452605865536486557L;

	public FilePanel() {
		super();
		JLabel fooBar = new JLabel("THIS IS THE FILE PANEL");
		fooBar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
		add(fooBar);
		
	}

}
