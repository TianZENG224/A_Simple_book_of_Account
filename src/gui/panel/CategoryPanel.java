package gui.panel;
 
import java.awt.BorderLayout;
 
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
 
import gui.model.CategoryTableModel;
import util.ColorUtil;
import util.GUIUtil;
import service.CategoryService;
import entity.Category;
import gui.listener.CategoryListener;
 
public class CategoryPanel extends WorkingPanel{
    public static CategoryPanel instance = new CategoryPanel();
 
    public JButton bAdd = new JButton("Add");
    public JButton bEdit = new JButton("Edit");
    public JButton bDelete = new JButton("Delete");
    String columNames[] = new String[]{"Category Name","Consuming times"};
     
    public CategoryTableModel ctm = new CategoryTableModel();
    public JTable t =new JTable(ctm);
     
    public CategoryPanel() {
        GUIUtil.setColor(ColorUtil.blueColor, bAdd,bEdit,bDelete);
        JScrollPane sp =new JScrollPane(t);
        JPanel pSubmit = new JPanel();
        pSubmit.add(bAdd);
        pSubmit.add(bEdit);
        pSubmit.add(bDelete);
         
        this.setLayout(new BorderLayout());
        this.add(sp,BorderLayout.CENTER);
        this.add(pSubmit,BorderLayout.SOUTH);
        addListener();
    }
 
    public static void main(String[] args) {
        GUIUtil.showPanel(CategoryPanel.instance);
    }
    public Category getSelectedCategory() {
        int index = t.getSelectedRow();
        return ctm.cs.get(index);
    }
     
    public void updateData() {
        ctm.cs = new CategoryService().list();
        t.updateUI();
        t.getSelectionModel().setSelectionInterval(0, 0);
         
        if(0==ctm.cs.size()){
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
        }
        else{
            bEdit.setEnabled(true);
            bDelete.setEnabled(true);
        }
    }
    public void addListener() {
        CategoryListener listener = new CategoryListener();
        bAdd.addActionListener(listener);
        bEdit.addActionListener(listener);
        bDelete.addActionListener(listener);
    }
}