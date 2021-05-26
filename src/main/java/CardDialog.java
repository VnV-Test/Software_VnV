import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CardDialog extends JDialog{
    Container pane = this.getContentPane();
    MainFrame parent = null;
    JTextField num1 = new JTextField("",4);
    JTextField num2 = new JTextField("",4);
    JTextField num3 = new JTextField("",4);
    JTextField num4 = new JTextField("",4);

    JTextField contf = new JTextField("",4);
    JTextField cvc = new JTextField("",4);
    JPasswordField pw = new JPasswordField("",4);
    int price;
    String name;
    VM otherVM = null;

    public CardDialog(MainFrame parent, String title, boolean modal, int price, String name) {
        super(parent,title,modal);
        this.parent = parent;
        this.price = price;
        this.name = name;
        this.setSize(320,200);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        init(false);
        charLimit(num1,4);
        charLimit(num2,4);
        charLimit(num3,4);
        charLimit(num4,4);
        charLimit(contf,4);
        charLimit(cvc,3);
        charLimit(pw,2);
        pw.setEchoChar('*');
        this.setVisible(true);
    }
    public CardDialog(MainFrame parent, String title, boolean modal, int price, String name,boolean isPre,VM otherVm) {
        super(parent,title,modal);
        this.parent = parent;
        this.price = price;
        this.name = name;
        this.otherVM = otherVm;
        this.setSize(320,200);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        init(isPre);
        charLimit(num1,4);
        charLimit(num2,4);
        charLimit(num3,4);
        charLimit(num4,4);
        charLimit(contf,4);
        charLimit(cvc,3);
        charLimit(pw,2);
        pw.setEchoChar('*');
        this.setVisible(true);
    }
    private void init(boolean isPre) {
        System.out.println("\nCardDialog name :" + name + "\n");
        pane.setBackground(Color.white);
        JPanel centerPanel = new JPanel(new GridLayout(5,1,5,5));
        centerPanel.setBackground(Color.white);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10,20,10,30));
        // text 1
        JLabel cardNum = new JLabel("Card Num");
        cardNum.setBorder(BorderFactory.createEmptyBorder(0,0,0,25));
        centerPanel.add(cardNum);
        // textfield 1
        JPanel cardNumtfPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
        cardNumtfPanel.setBackground(Color.white);
        cardNumtfPanel.add(num1);
        cardNumtfPanel.add(num2);
        cardNumtfPanel.add(num3);
        cardNumtfPanel.add(num4);
        centerPanel.add(cardNumtfPanel);
        // text 2
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,25,0));
        textPanel.setBackground(Color.white);
        JLabel confirm = new JLabel("Validity");
        JLabel cvc = new JLabel("CVC");
        cvc.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
        JLabel pw = new JLabel("PW");
        pw.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
        textPanel.add(confirm);
        textPanel.add(cvc);
        textPanel.add(pw);
        centerPanel.add(textPanel);
        //textfield 2
        JPanel cardtfPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,25,0));
        cardtfPanel.setBackground(Color.white);
        cardtfPanel.add(contf);
        cardtfPanel.add(this.cvc);
        cardtfPanel.add(this.pw);
        centerPanel.add(cardtfPanel);
        // btnPanel
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,0));
        btnPanel.setBackground(Color.white);
        JButton okBtn = new JButton("OK");
        okBtn.setBackground(new Color(80,180,223));
        okBtn.setForeground(Color.white);
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(isPre){
                    parent.cardCheck(name,otherVM);
                    if(parent.vmframe != null){
                        parent.vmframe.dispose();
                        parent.vmframe = null;
                    }
                }else{
                    parent.cardCheck();
                }
                parent.dlg = null;
                dispose();
            }
        });
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(new Color(80,180,223));
        cancelBtn.setForeground(Color.white);
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                parent.dlg = null;
                dispose();
                if(isPre){
                    if(parent.vmframe != null){
                        parent.vmframe.requestFocus();
                    }
                }
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                super.windowClosing(e);
                parent.dlg = null;
                if(isPre){
                    if(parent.vmframe != null){
                        parent.vmframe.requestFocus();
                    }
                }
                dispose();
            }
        });
        btnPanel.add(okBtn);
        btnPanel.add(cancelBtn);
        centerPanel.add(btnPanel);
        pane.add(centerPanel);
    }
    public String getNum1() {
        if(num1.getText().length() == 0)
            return null;
        else{
            return num1.getText();
        }

    }
    public String getNum2() {
        if(num2.getText().length() == 0)
            return null;
        else{
            return num2.getText();
        }
    }
    public String getNum3() {
        if(num3.getText().length() == 0)
            return null;
        else{
            return num3.getText();
        }
    }
    public String getNum4() {
        if(num4.getText().length() == 0)
            return null;
        else{
            return num4.getText();
        }
    }
    public String getCvc() {
        if(cvc.getText().length() == 0)
            return null;
        else
            return cvc.getText();
    }
    public String getPW() {
        if(pw.getText().length() == 0)
            return null;
        else
            return pw.getText();
    }
    public String getCon() {
        if(contf.getText().length() == 0)
            return null;
        else
            return contf.getText();
    }
    public int getPrice(){
        return this.price;
    }
    public String getName(){
        return this.name;
    }
    private void charLimit(JTextField tf,int limit){
        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                JTextField src = (JTextField) e.getSource();
                if(src.getText().length() >= limit){
                    e.consume();
                }
            }
        });
    }
}

