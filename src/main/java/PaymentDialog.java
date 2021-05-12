
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PaymentDialog extends JDialog{

    Container pane = this.getContentPane();
    MainFrame parent = null;
    int price;
    String name;

    public PaymentDialog(MainFrame parent, String title, boolean modal, int price, String name) {
        super(parent,title,modal);
        this.parent = parent;
        this.price = price;
        this.name = name;
        this.setSize(330,120);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        init();
        this.setVisible(true);
    }
    public void init() {
        JPanel centerPanel = new JPanel(new FlowLayout());
        JLabel centerLabel = new JLabel("Would you like to pay?");
        Font font = new Font("Arial",Font.PLAIN,13);
        centerPanel.setBackground(Color.white);
        centerLabel.setFont(font);
        centerLabel.setVerticalAlignment(JLabel.CENTER);
        centerPanel.add(centerLabel);
        pane.add(centerPanel,BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
        southPanel.setBackground(Color.white);
        JButton okBtn = new JButton("OK");
        okBtn.setBackground(new Color(80,180,223));
        okBtn.setForeground(Color.white);
        int payPrice = this.price;
        String payName = this.name;
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                // 카드 입력창 생성
                parent.showCardDialog(payPrice,payName);
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
            }
        });
        southPanel.add(okBtn);
        southPanel.add(cancelBtn);
        pane.add(southPanel,BorderLayout.SOUTH);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                super.windowClosing(e);
                parent.dlg = null;
                dispose();
            }
        });
    }

}
