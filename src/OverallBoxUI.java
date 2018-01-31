import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class OverallBoxUI extends JComponent{
	private OverallBox _box;
	
	private static final long serialVersionUID = 1L;
		private JPanel mainPanel;
		private Color _buttonC1;
		private Color _buttonC2;
		
		public OverallBoxUI(OverallBox box, JFrame _mainFrame) {
			_box = box;
			_buttonC1 =  new Color(255, 200, 130);
			_buttonC2 =  new Color(255, 150, 60);
			mainPanel = new JPanel();
			mainPanel.setBorder(new EmptyBorder(10,10,10,10));
			Color _buttonC;
			boolean change = false;
			for(int i = 0 ; i<16;i++) {
				SlidingTile tile = _box.getTileByPosition(i); 
				if(!tile.equals(new BlankTile())) {
					if(change)_buttonC = _buttonC1;	
					else _buttonC = _buttonC2;	
					
					SlidingTileUI ui = new SlidingTileUI(tile,_buttonC, _mainFrame);
					ui.setBorder(new LineBorder(_buttonC, 3 , true));
					mainPanel.add(ui);
					change = !change;
				}else {
					mainPanel.add(new BlankTileUI(_mainFrame));
				}
				
			}

			_mainFrame.add(mainPanel);
			_mainFrame.setVisible(true);
		}
}
