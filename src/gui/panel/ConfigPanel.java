package gui.panel;
 
import java.awt.BorderLayout;
import java.awt.GridLayout;
 
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
import util.ColorUtil;
import util.GUIUtil;
import gui.listener.ConfigListener;
import service.ConfigService;
 
public class ConfigPanel extends WorkingPanel {
    
    public static ConfigPanel instance = new ConfigPanel();
 
    JLabel lBudget = new JLabel("Budget($)");
    public JTextField tfBudget = new JTextField("0");
     
    JLabel lMysql = new JLabel("Mysql Install Path");
    public JTextField tfMysqlPath = new JTextField("");
 
    JButton bSubmit = new JButton("update");
 
    public ConfigPanel() {
        GUIUtil.setColor(ColorUtil.grayColor, lBudget,lMysql);
        GUIUtil.setColor(ColorUtil.blueColor, bSubmit);
         
        JPanel pInput =new JPanel();
        JPanel pSubmit = new JPanel();
        int gap =40;
        pInput.setLayout(new GridLayout(4,1,gap,gap));
         
        pInput.add(lBudget);
        pInput.add(tfBudget);
        pInput.add(lMysql);
        pInput.add(tfMysqlPath);
        this.setLayout(new BorderLayout());
        this.add(pInput,BorderLayout.NORTH);
         
        pSubmit.add(bSubmit);
        this.add(pSubmit,BorderLayout.CENTER);
        addListener();
    }
 
    public static void main(String[] args) {
        GUIUtil.showPanel(ConfigPanel.instance);
    }
    
    public void addListener() {
    	ConfigListener l = new ConfigListener();
    	bSubmit.addActionListener(l);
    }
    
    @Override
    public void updateData() {
        String budget = new ConfigService().get(ConfigService.budget);
        String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
        tfBudget.setText(budget);
        tfMysqlPath.setText(mysqlPath);
        tfBudget.grabFocus();
    }
     
}
