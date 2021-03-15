package util;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import gui.panel.WorkingPanel;

public class CentralPanel extends JPanel {
	private double rate;
	private JComponent c;
	private boolean strech;
	
	public CentralPanel(double rate, boolean strech) {
		this.rate = rate;
		this.strech = strech;
		this.setLayout(null);
	}
	
	public CentralPanel(double rate) {
		this(rate, true);
	}
	
	@Override
	public void repaint() {
		if(c != null) {
			Dimension containerSize = this.getSize();
			Dimension componentSize = c.getPreferredSize();
			if(strech) {
				c.setSize((int) (containerSize.width * rate), 
						(int) (containerSize.height * rate));
			}
			else {
				c.setSize(componentSize);
			}
			c.setLocation((int) (containerSize.width / 2 - c.getSize().width / 2),
					(int) (containerSize.height / 2 - c.getSize().height / 2));
		}
		super.repaint();
	}
	
	public void show(JComponent p) {
		this.c = p;
		Component[] cs = getComponents();
		for(Component item : cs) {
			remove(item);
		}
		add(p);
		//will provoke repaint()
        if (p instanceof WorkingPanel)
            ((WorkingPanel) p).updateData();
		this.updateUI();
	}
	

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(200, 200);
		f.setLocationRelativeTo(null);
		CentralPanel cp = new CentralPanel(0.85, true);
		f.setContentPane(cp);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		JButton b = new JButton("abc");
		cp.show(b);
		
	}
}
