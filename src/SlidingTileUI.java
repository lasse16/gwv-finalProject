import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class SlidingTileUI extends JComponent {
	private SlidingTile _tile;
	private JPanel _panel;

	public SlidingTileUI(SlidingTile tile, Color c, JFrame main) {
		_tile = tile;
		Dimension size = main.getSize();
		_panel = new JPanel(new GridLayout(0,2));
		_panel.setAlignmentX(JLabel.CENTER);
		_panel.setAlignmentY(JLabel.CENTER);
		_panel.setBorder(new LineBorder(c, 4, true));
		_panel.setSize(new Dimension(size.height/10, size.width/10));
		_panel.add(new JLabel(""));
		_panel.add(new JLabel("" + _tile.getNumber()));
		_panel.setBackground(c);
		_panel.setVisible(true);
		main.add(_panel);
		main.setVisible(true);
	}

}
