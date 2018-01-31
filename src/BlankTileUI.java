import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class BlankTileUI extends JComponent {

	private static final long serialVersionUID = 1L;

	private JPanel _panel;
	private Color _background;

	public BlankTileUI(JFrame main) {
		_background = new Color(120,120,120);
		Dimension size = main.getSize();
		_panel = new JPanel(new GridLayout(0, 2));

		_panel.setBorder(new LineBorder(_background, 4, true));
		_panel.setSize(new Dimension(size.height / 10, size.width / 10));

		_panel.setBackground(_background);
		_panel.setVisible(true);
		main.add(_panel);
		main.setVisible(true);
	}
}
