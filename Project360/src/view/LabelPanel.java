package view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LabelPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3058061381038686629L;

	public LabelPanel() {
		super();
		JLabel fooBar = new JLabel("THIS IS THE LABELS PANEL");
		fooBar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
		add(fooBar);
	}

}
