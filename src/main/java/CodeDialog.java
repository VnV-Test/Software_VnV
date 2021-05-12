

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CodeDialog extends JDialog{

    JTextField tf = new JTextField("",14);
    JButton ok = new JButton("입력");
    Container pane = this.getContentPane();
    MainFrame parent = null;

    public CodeDialog(MainFrame parent, String title, boolean modal) {
        super(parent,title,modal);
        this.parent = parent;
        this.setSize(330,80);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        init();
        this.setVisible(true);
    }
    public void init() {
        pane.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        JLabel idLabel = new JLabel("Code: ");
        idLabel.setPreferredSize(new Dimension(60,20));
        pane.add(idLabel);
        pane.add(tf);
        ok.setForeground(Color.white);
        ok.setBackground(new Color(80,188,223));
        pane.add(ok);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                parent.checkCode();
                parent.dlg = null;
                dispose();
            }

        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                super.windowClosing(e);
                tf.setText("");
                parent.dlg = null;
                dispose();
            }
        });

    }
    public String getCode() {
        if(tf.getText().length() == 0)
            return null;
        else
            return tf.getText();
    }

}

