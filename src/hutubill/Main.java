package hutubill;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame();
		f.setSize(600, 600);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JToolBar tb = new JToolBar();
		JButton bSpend = new JButton("OverView");
		JButton bRecord = new JButton("Write Notes");
		JButton bCategory = new JButton("Category");
		JButton bReport = new JButton("Record By months");
		JButton bConfig = new JButton("Config");
		JButton bBackup = new JButton("Backup");
		JButton bRecover = new JButton("Recover");
		
		tb.add(bSpend);
		tb.add(bRecord);
		tb.add(bCategory);
		tb.add(bReport);
		tb.add(bConfig);
		tb.add(bBackup);
		tb.add(bRecover);
		f.setLayout(new BorderLayout());
		f.add(tb, BorderLayout.NORTH);
		f.add(new JPanel(), BorderLayout.CENTER);
		f.setVisible(true);
		
		
	}

}
