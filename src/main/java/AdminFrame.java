import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminFrame extends JFrame{

    int selectProduct = -1;

    private JPanel center = new JPanel(new GridLayout(3,1,15,15));
    private JTextField nametf = new JTextField("",8);
    private JTextField pricetf = new JTextField("",8);
    private JTextField stocktf = new JTextField("",8);

    private JTextField idtf = new JTextField("",8);
    private JTextField locationtf = new JTextField("",8);

    private JTextField contacttf = new JTextField("",8);
    MainFrame parent = null;

    public AdminFrame(MainFrame parent) {
        super("Admin Mode");
        this.parent = parent;
        this.setSize(500,650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //frame.setBackground(Color.white);
        init();
        this.setVisible(true);
    }
    private void init() {
        initDrink();
        initVM();
        initContact();
        center.setBackground(Color.white);
        this.add(center);
    }
    private void initDrink() {
        JPanel drinkPanel = new JPanel();
        drinkPanel.setPreferredSize(new Dimension(500,350));
        drinkPanel.setBackground(Color.white);
        drinkPanel.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
        JPanel drinkMainPanel = new JPanel(new GridLayout(1,3,5,5));
        drinkMainPanel.setPreferredSize(new Dimension(400,150));
        drinkMainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Drink Edit"));
        drinkMainPanel.setBackground(Color.white);
        drinkPanel.add(drinkMainPanel);

        // textPanel
        JPanel textPanel = new JPanel(new GridLayout(3,1,0,0));
        textPanel.setBackground(Color.white);
        textPanel.setBorder(BorderFactory.createEmptyBorder(20,10,10,0));
        JLabel name = new JLabel("Item name: ");
        JLabel price = new JLabel("Item price: ");
        JLabel stock = new JLabel("stock: ");

        textPanel.add(name);
        textPanel.add(price);
        textPanel.add(stock);
        drinkMainPanel.add(textPanel);
        // textfieldPanel
        JPanel tfPanel = new JPanel(new GridLayout(3,1,0,0));
        tfPanel.setBackground(Color.white);
        tfPanel.setBorder(BorderFactory.createEmptyBorder(20,0,10,0));
        tfPanel.add(nametf);
        tfPanel.add(pricetf);
        tfPanel.add(stocktf);
        drinkMainPanel.add(tfPanel);
        // btnPanel
        JPanel btnPanel = new JPanel(new GridLayout(3,1,10,5));
        btnPanel.setBackground(Color.white);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(20,30,10,10));
        JButton choose = new JButton("Choose");
        choose.setBackground(new Color(80,188,223));
        choose.setForeground(Color.white);
        choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                DrinkDialog drink = new DrinkDialog("Drink Choose",false,AdminFrame.this.parent.getVM(),AdminFrame.this);
            }
        });
        JButton change = new JButton("Edit");
        change.setBackground(new Color(80,188,223));
        change.setForeground(Color.white);
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(AdminFrame.this.selectProduct == -1) { return; }
                Drink drinklist[] = parent.getVM().getDrinkArray();
                Item item = parent.getVM().findItem(drinklist[selectProduct].getName());
                if(nametf.getText() != null){
                    drinklist[selectProduct].setName(nametf.getText());
                    if(item != null){
                        item.editName(nametf.getText());
                    }
                }
                if(pricetf.getText() != null){
                    int price;
                    try{
                        price  = Integer.parseInt(pricetf.getText());
                    }catch(NumberFormatException e2){
                        JOptionPane.showMessageDialog(null, "\n" + "Strings other than integers cannot be entered for price.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    drinklist[selectProduct].setPrice(price);
                    if(item != null){
                        item.editPrice(price);
                    }
                }
                if( stocktf.isEnabled() && stocktf.getText() != null){
                    int stock;
                    try{
                        stock  = Integer.parseInt(stocktf.getText());
                    }catch(NumberFormatException e2){
                        JOptionPane.showMessageDialog(null, "\n" + "Strings other than integers cannot be entered for price.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(item != null){
                        if(stock < 0 || stock > 99){
                            JOptionPane.showMessageDialog(null, "\n" + "Stocks input only 0 ~ 99 .", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        item.editStock(stock);
                    }
                }
                JOptionPane.showMessageDialog(null, "Has been changed.", "Guidance", JOptionPane.INFORMATION_MESSAGE);
                parent.showDrink();
            }
        });
        btnPanel.add(choose);
        btnPanel.add(change);
        drinkMainPanel.add(btnPanel);
        center.add(drinkPanel);
    }
    private void initVM() {
        JPanel vmPanel = new JPanel();
        vmPanel.setPreferredSize(new Dimension(500,300));
        vmPanel.setBackground(Color.white);
        vmPanel.setBorder(BorderFactory.createEmptyBorder(25,25,35,25));
        JPanel vmMainPanel = new JPanel(new GridLayout(1,3,5,5));
        vmMainPanel.setPreferredSize(new Dimension(400,150));
        vmMainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"VM Edit"));
        vmMainPanel.setBackground(Color.white);
        vmPanel.add(vmMainPanel);

        // textPanel
        JPanel textPanel = new JPanel(new GridLayout(2,1,0,0));
        textPanel.setBackground(Color.white);
        textPanel.setBorder(BorderFactory.createEmptyBorder(30,10,30,0));
        JLabel name = new JLabel("VM ID: ");
        JLabel price = new JLabel("VM Location: ");

        textPanel.add(name);
        textPanel.add(price);
        vmMainPanel.add(textPanel);
        // textfieldPanel
        JPanel tfPanel = new JPanel(new GridLayout(2,1,0,0));
        tfPanel.setBackground(Color.white);
        tfPanel.setBorder(BorderFactory.createEmptyBorder(30,0,30,0));
        tfPanel.add(idtf);
        tfPanel.add(locationtf);
        vmMainPanel.add(tfPanel);
        // btnPanel
        JPanel btnPanel = new JPanel(new GridLayout(2,1,10,5));
        btnPanel.setBackground(Color.white);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,10));
        JButton change = new JButton("Edit");
        change.setBackground(new Color(80,188,223));
        change.setForeground(Color.white);
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(!(locationtf.getText() == ""))
                    parent.getVM().editVMAddress(idtf.getText());
                int id;
                if(!(idtf.getText() == "")){
                    try{
                        id  = Integer.parseInt(idtf.getText());
                    }catch(NumberFormatException e2){
                        JOptionPane.showMessageDialog(null, "\n" + "Strings other than integers cannot be entered for id.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    parent.getVM().editVMID(id);
                    JOptionPane.showMessageDialog(null, "Has been changed.", "Guidance", JOptionPane.INFORMATION_MESSAGE);
                }else{ return; }
            }
        });
        btnPanel.add(change);
        vmMainPanel.add(btnPanel);

        center.add(vmPanel);
    }
    private void initContact() {
        JPanel contactPanel = new JPanel();
        contactPanel.setPreferredSize(new Dimension(500,300));
        contactPanel.setBackground(Color.white);
        contactPanel.setBorder(BorderFactory.createEmptyBorder(25,25,35,25));
        JPanel contactMainPanel = new JPanel(new GridLayout(1,3,5,5));
        contactMainPanel.setPreferredSize(new Dimension(400,90));
        contactMainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Contact Edit"));
        contactMainPanel.setBackground(Color.white);
        contactPanel.add(contactMainPanel);

        // textPanel
        JPanel textPanel = new JPanel(new GridLayout(2,1,0,0));
        textPanel.setBackground(Color.white);
        textPanel.setBorder(BorderFactory.createEmptyBorder(20,10,10,0));
        JLabel name = new JLabel("Contact: ");

        textPanel.add(name);
        contactMainPanel.add(textPanel);
        // textfieldPanel
        JPanel tfPanel = new JPanel(new GridLayout(2,1,0,0));
        tfPanel.setBackground(Color.white);
        tfPanel.setBorder(BorderFactory.createEmptyBorder(20,0,10,0));
        tfPanel.add(contacttf);
        contactMainPanel.add(tfPanel);
        // btnPanel
        JPanel btnPanel = new JPanel(new GridLayout(2,1,10,5));
        btnPanel.setBackground(Color.white);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(20,30,10,10));
        JButton change = new JButton("Edit");
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(contacttf.getText() == "")
                    return;
                else {
                    parent.getAdmin().editContact(contacttf.getText());
                    JOptionPane.showMessageDialog(null, "변경되었습니다.", "안내", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        change.setBackground(new Color(80,188,223));
        change.setForeground(Color.white);
        btnPanel.add(change);
        contactMainPanel.add(btnPanel);

        center.add(contactPanel);
    }
    public void setSelectProduct(int index){
        this.selectProduct = index;
    }
    public void setProduct(String name,int price){
        nametf.setText(name);
        pricetf.setText(String.valueOf(price));
        Item item = parent.getVM().findItem(name);
        if( item == null) {
            stocktf.setEnabled(false);
            stocktf.setText("");
        }else{
            stocktf.setEnabled(true);
            stocktf.setText(String.valueOf(item.getStock()));
        }

    }
}