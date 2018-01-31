import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class OverallBoxUI extends JPanel{
	private OverallBox _box;
	
	private static final long serialVersionUID = 1L;
		private Color _buttonC1;
		private Color _buttonC2;
		private JFrame _main;
		
		public OverallBoxUI(OverallBox box, JFrame _mainFrame) {
			_box = box;
			_main = _mainFrame;
			this.setSize(1000,1000);
			this.setLayout(new GridLayout(4,4,3,3));
			_buttonC1 =  new Color(255, 200, 130);
			_buttonC2 =  new Color(255, 150, 60);
			
			this.setBorder(new EmptyBorder(10,10,10,10));
			Color _buttonC;
			boolean change = false;
			for(int i = 0 ; i<16;i++) {
				SlidingTile tile = _box.getTileByPosition(i); 
				if(!tile.equals(new BlankTile())) {
					if(change)_buttonC = _buttonC1;	
					else _buttonC = _buttonC2;	
					
					SlidingTileUI ui = new SlidingTileUI(tile,_buttonC, _mainFrame);
				
					ui.setBorder(new LineBorder(_buttonC, 3 , true));
					this.add(ui);
					change = !change;
				}else {
					this.add(new BlankTileUI(_mainFrame));
				}
				
			}

			_mainFrame.add(this);
			_mainFrame.setVisible(true);
		}
		
		public void update() {
			this.removeAll();
			this.setBorder(new EmptyBorder(10,10,10,10));
			Color _buttonC;
			boolean change = false;
			for(int i = 0 ; i<16;i++) {
				SlidingTile tile = _box.getTileByPosition(i); 
				if(!tile.equals(new BlankTile())) {
					if(change)_buttonC = _buttonC1;	
					else _buttonC = _buttonC2;	
					
					SlidingTileUI ui = new SlidingTileUI(tile,_buttonC, _main);
					ui.setBorder(new LineBorder(_buttonC, 3 , true));
					this.add(ui);
					change = !change;
				}else {
					this.add(new BlankTileUI(_main));
				}
			}

			this.revalidate();
			_main.revalidate();
			_main.repaint();
			_main.setVisible(true);
			if(_box.isGoal()) {
				JDialog finished = new JDialog(_main, "finished");
				finished.add(new JLabel("You`ve finished the game. \n hit shuffle for a new one"));
				finished.setModal(true);
				finished.setSize(400,200);
				
				
				finished.setVisible(true);
			}
		}
		
}
