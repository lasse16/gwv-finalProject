import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class BlankTileUI extends JPanel {

	private static final long serialVersionUID = 1L;


	private Color _background;

	public BlankTileUI(JFrame main) {
		_background = new Color(120,120,120);
		Dimension size = main.getSize();
	
		this.setBorder(new LineBorder(_background, 4, true));
		this.setSize(new Dimension(size.height / 4, size.height / 4));

		this.setBackground(_background);
		this.setVisible(true);
		main.add(this);
		main.setVisible(true);
	}
}
