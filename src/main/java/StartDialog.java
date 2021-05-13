import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StartDialog extends JDialog {
    JTextField porttf = new JTextField("",12);
    JTextField divitf = new JTextField("",12);
    JButton ok = new JButton("OK");
    Container pane = this.getContentPane();
    MainFrame parent = null;

    public StartDialog() {
        this.setTitle("START");
        this.parent = parent;
        this.setSize(330,120);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        init();
        this.setVisible(true);
    }
    public void init() {
        pane.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        JLabel idLabel = new JLabel("Port");
        idLabel.setPreferredSize(new Dimension(80,20));
        pane.add(idLabel);
        pane.add(porttf);
        ok.setForeground(Color.white);
        ok.setBackground(new Color(80,188,223));
        pane.add(ok);
        JLabel pwLabel = new JLabel("Division");
        pwLabel.setPreferredSize(new Dimension(80,20));
        pane.add(pwLabel);
        pane.add(divitf);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.out.println("start vm: " + porttf.getText());
                double[] location = { 37.54164, 127.07880 };
                int port = Integer.valueOf(porttf.getText());
                int division = Integer.valueOf(divitf.getText());
                VM vm = new VM(port, location,division);
                MainFrame main = new MainFrame(vm);
                Thread t1 = new RecieveMail(vm);
                Thread t2 = new ImListening(vm);

                t1.start();
                t2.start();
                dispose();
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                super.windowClosing(e);
                porttf.setText("");
                divitf.setText("");
                dispose();
            }
        });
    }

}
