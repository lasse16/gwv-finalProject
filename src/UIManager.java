import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class UIManager implements Watcher{
	private OverallBox _box;
	private OverallBoxUI _boxUI;
	private JFrame _mainFrame;
	private JFrame _buttonFrame;

	public UIManager(OverallBox box) {
		_mainFrame = new JFrame("15-Puzzle");
		_buttonFrame = new JFrame();
		_buttonFrame.setSize(200,200);
		_buttonFrame.setLayout(new GridLayout(3,3));
		_buttonFrame.setLocation(1100, 100);
		_mainFrame.setSize(1000, 1000);
	
		

		_box = box;
		_box.addWatcher(this);
		_boxUI = new OverallBoxUI(box, _mainFrame);
		
		_mainFrame.add(_boxUI);
		
		_buttonFrame.add(new JLabel(""));
		JButton upButton = new JButton("^");
		
		upButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				_box.applyMove(new Move(Direction.UP, _box));	
			}
		});
		_buttonFrame.add(upButton);
		_buttonFrame.add(new JLabel(""));
		
		JButton leftButton = new JButton("<");
		leftButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				_box.applyMove(new Move(Direction.LEFT, _box));	
			}
		});
		_buttonFrame.add(leftButton);
		JButton downButton = new JButton("v");
		downButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				_box.applyMove(new Move(Direction.DOWN, _box));	
			}
		});
		_buttonFrame.add(downButton);
		
		JButton rightButton = new JButton(">");
		rightButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				_box = _box.applyMove(new Move(Direction.RIGHT, _box));	
			}
		});
		_buttonFrame.add(rightButton);
		
		JButton shuffleButton = new JButton("shuffle");
		shuffleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				_box = _box.shuffle();	
			}
		});
				
		_buttonFrame.add(shuffleButton);
		
		
		
		_buttonFrame.setVisible(true);
		_mainFrame.setVisible(true);
	}

	@Override
	public void update() {
		
		_boxUI.update();
		System.out.print(_box.toString());

		_mainFrame.revalidate();
		_mainFrame.repaint();
		
	}
}