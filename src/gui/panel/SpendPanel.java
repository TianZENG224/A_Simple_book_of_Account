package gui.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;

import util.CircleProgressBar;
import util.ColorUtil;
import util.GUIUtil;
import service.SpendService;
import gui.page.SpendPage;

public class SpendPanel extends WorkingPanel {
	public static SpendPanel instance = new SpendPanel();
	
	private SpendPanel() {
		this.setLayout(new BorderLayout());
		bar = new CircleProgressBar();
		bar.setBackgroundColor(ColorUtil.blueColor);
		GUIUtil.setColor(ColorUtil.grayColor, lMonthSpend, lTodaySpend, lAvgSpendPerDay, lMonthLeft, lDayAvgAvailable,
                lMonthLeftDay, vAvgSpendPerDay, vMonthLeft, vDayAvgAvailable, vMonthLeftDay);
        GUIUtil.setColor(ColorUtil.blueColor, vMonthSpend, vTodaySpend);
        vMonthSpend.setFont(new Font("TimesNewRoman", Font.BOLD, 23));
        vTodaySpend.setFont(new Font("TimesNewRoman", Font.BOLD, 23));
        this.add(center(), BorderLayout.CENTER);
        this.add(south(), BorderLayout.SOUTH);
	}
	
	JLabel lMonthSpend = new JLabel("The cost of this month: ");
	JLabel lTodaySpend = new JLabel("The cost of today: ");
	JLabel lAvgSpendPerDay = new JLabel("The avg cost of per day estimated: ");
	JLabel lMonthLeft = new JLabel("Left for this month: ");
	JLabel lDayAvgAvailable = new JLabel("Left for per day this month: ");
	JLabel lMonthLeftDay = new JLabel("Days to next month: ");
	
	JLabel vMonthSpend = new JLabel("$2300.00");
	JLabel vTodaySpend = new JLabel("$100.00");
	JLabel vAvgSpendPerDay = new JLabel("$150.00");
	JLabel vMonthLeft = new JLabel("$3000.00");
	JLabel vDayAvgAvailable = new JLabel("$300.00");
	JLabel vMonthLeftDay = new JLabel("15 day(s)");
	
	CircleProgressBar bar;
	
	private JPanel center() {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(west(), BorderLayout.WEST);
		p.add(center2(), BorderLayout.CENTER);
		return p;
	}
	
	private Component center2() {
		return bar;
	}
	
	private Component west() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4,1));
        p.add(lMonthSpend);
        p.add(vMonthSpend);
        p.add(lTodaySpend);
        p.add(vTodaySpend);
        return p;
	}
	
    private JPanel south() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 4));
 
        p.add(lAvgSpendPerDay);
        p.add(lMonthLeft);
        p.add(lDayAvgAvailable);
        p.add(lMonthLeftDay);
        p.add(vAvgSpendPerDay);
        p.add(vMonthLeft);
        p.add(vDayAvgAvailable);
        p.add(vMonthLeftDay);
 
        return p;
    }
    public static void main(String[] args) {
        GUIUtil.showPanel(SpendPanel.instance);
    }
    @Override
    public void updateData() {
        SpendPage spend = new SpendService().getSpendPage();
        vMonthSpend.setText(spend.monthSpend);
        vTodaySpend.setText(spend.todaySpend);
        vAvgSpendPerDay.setText(spend.avgSpendPerDay);
        vMonthLeft.setText(spend.monthAvailable);
        vDayAvgAvailable.setText(spend.dayAvgAvailable);
        vMonthLeftDay.setText(spend.monthLeftDay);
 
        bar.setProgress(spend.usagePercentage);
        if (spend.isOverSpend) {
        	vMonthLeft.setForeground(ColorUtil.warningColor);
            vMonthSpend.setForeground(ColorUtil.warningColor);
            vTodaySpend.setForeground(ColorUtil.warningColor);
 
        } else {
        	vMonthLeft.setForeground(ColorUtil.grayColor);
            vMonthSpend.setForeground(ColorUtil.blueColor);
            vTodaySpend.setForeground(ColorUtil.blueColor);
        }
        bar.setForegroundColor(ColorUtil.getByPercentage(spend.usagePercentage));
        addListener();
 
    }
 
    @Override
    public void addListener() {
        // TODO Auto-generated method stub
    }
}
