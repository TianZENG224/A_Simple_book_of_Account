package util;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIUtil {
	 private static String imageFolder = "d:/project/hutubill/img";
	 public static void setImageIcon(JButton b, String fileName, String tip) {
		 ImageIcon i = new ImageIcon(new File(imageFolder, fileName).getAbsolutePath());
		 b.setIcon(i);
		 b.setPreferredSize(new Dimension(61, 81));
		 b.setToolTipText(tip);
		 b.setVerticalTextPosition(JButton.BOTTOM);
		 b.setHorizontalTextPosition(JButton.CENTER);
		 b.setText(tip);
	 }
	 
	 public static void setColor(Color color, JComponent... cs) {
		 for(JComponent jc : cs) {
			 jc.setForeground(color);
		 }
	 }
	 
	 public static void showPanel(JPanel p, double strechRate) {
//		 GUIUtil.useLNF();
		 JFrame f = new JFrame();
		 f.setSize(500, 500);
		 f.setLocationRelativeTo(null);
		 CentralPanel cp = new CentralPanel(strechRate);
		 f.setContentPane(cp);
		 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 f.setVisible(true);
		 cp.show(p);
	 }
	 
	 public static void showPanel(JPanel p) {
		 showPanel(p, 0.85);
	 }
	 
	 public static boolean checkEmpty(JTextField tf, String input) {
		 String text = tf.getText().trim();
		 if(0 == text.length() || text == null) {
			 JOptionPane.showMessageDialog(null, "Cannot be empty.");
			 tf.grabFocus();
			 return false;
		 }
		 return true;
	 }
	 
	 public static boolean checkNumber(JTextField tf, String input) {
		 if(!checkEmpty(tf, input)) {
			 return false;
		 }
		 String text = tf.getText().trim();
		 try {
			 Integer.parseInt(text);
			 return true;
		 }
		 catch(NumberFormatException e1) {
			 JOptionPane.showMessageDialog(null, input + "needs to be integers.");
			 tf.grabFocus();
			 return false;
		 }
		 
	 }
	 
	 public static boolean checkZero(JTextField tf, String input) {
		 if (!checkNumber(tf, input)) {
			 return false;
		 }
		 String text = tf.getText().trim();
		 if(0 == Integer.parseInt(text)) {
			 JOptionPane.showMessageDialog(null, input + "Cannot be zero.");
			 tf.grabFocus();
			 return false;
		 }
		 return true;
	 }
	 
	 public static int getInt(JTextField tf) {
		 return Integer.parseInt(tf.getText().trim());
	 }
	 
//	 public static void useLNF() {
//		 try {
//			 javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
//		 }
//		 catch(Exception e) {
//			 e.printStackTrace();
//		 }
//	 }
}
