package view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProfilePanel extends JPanel {
	
	/**
	 *  
	 */
	private static final long serialVersionUID = 9085434569766998412L;

	public ProfilePanel() {
		super();
		JLabel fooBar = new JLabel("THIS IS THE PROFILE PANEL");
		fooBar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
		add(fooBar);
	}

}
