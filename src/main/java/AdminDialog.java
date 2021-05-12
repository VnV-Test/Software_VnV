
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class AdminDialog extends JDialog{

    JTextField idtf = new JTextField("",12);
    JPasswordField pwtf = new JPasswordField("",12);
    JButton ok = new JButton("확인");
    Container pane = this.getContentPane();
    MainFrame parent = null;

    public AdminDialog(MainFrame parent, String title, boolean modal) {
        super(parent,title,modal);
        this.parent = parent;
        this.setSize(330,120);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        init();
        this.setVisible(true);
    }
    public void init() {
        pwtf.setEchoChar('*');
        pane.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        JLabel idLabel = new JLabel("Admin ID");
        idLabel.setPreferredSize(new Dimension(80,20));
        pane.add(idLabel);
        pane.add(idtf);
        ok.setForeground(Color.white);
        ok.setBackground(new Color(80,188,223));
        pane.add(ok);
        JLabel pwLabel = new JLabel("Admin PW");
        pwLabel.setPreferredSize(new Dimension(80,20));
        pane.add(pwLabel);
        pane.add(pwtf);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                parent.checkAdmin();
                parent.dlg = null;
                dispose();
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                super.windowClosing(e);
                idtf.setText("");
                pwtf.setText("");
                parent.dlg = null;
                dispose();
            }
        });
    }
    public String getID() {
        if(idtf.getText().length() == 0)
            return null;
        else
            return idtf.getText();
    }
    public String getPW() {
        String str = String.valueOf(pwtf.getPassword());
        if(str.length() == 0)
            return null;
        else
            return str;
    }

}
