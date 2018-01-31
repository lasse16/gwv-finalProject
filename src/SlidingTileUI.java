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

public class SlidingTileUI extends JLabel {
	private SlidingTile _tile;

	public SlidingTileUI(SlidingTile tile, Color c, JFrame main) {
		_tile = tile;
		this.setOpaque(true);
		Dimension size = main.getSize();
		this.setLayout(new GridLayout(4,2));
		this.setAlignmentX(JLabel.CENTER);
		this.setAlignmentY(JLabel.CENTER);
		this.setBorder(new LineBorder(c, 4, true));
		this.setSize(new Dimension(size.height / 4, size.height / 4));
		this.add(new JLabel(""));
		this.setText(""+tile.getNumber());
		
		this.setBackground(c);
		this.setVisible(true);
		main.add(this);
		main.setVisible(true);
	}

}
