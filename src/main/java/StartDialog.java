import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class StartDialog extends JDialog {
    JPanel radiobtnPanel = new JPanel();
    JButton ok = new JButton("START");
    Container pane = this.getContentPane();
    MainFrame parent = null;
    String[] data = {"0","1","2"};
    String[] portdata = {"9999","9998","9997","9996","9995","9994","9993","9992","9991","9990"};
    JRadioButton[] division = new JRadioButton[3];
    ButtonGroup group= new ButtonGroup();
    int divisionIndex = 0;
    int portNum = 9999;
    JComboBox portCombo = new JComboBox(portdata);

    public StartDialog() {
        this.setTitle("START");
        this.parent = parent;
        this.setSize(300,150);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        init();
        this.setVisible(true);
    }
    public void init() {

        pane.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        JLabel idLabel = new JLabel("Port: ");
        idLabel.setPreferredSize(new Dimension(80,20));
        pane.add(idLabel);
        portCombo.setPreferredSize(new Dimension(60,20));
        portCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                int index = comboBox.getSelectedIndex();
                portNum = Integer.parseInt(portdata[index]);
            }
        });
        pane.add(portCombo);
        ok.setForeground(Color.white);
        ok.setBackground(new Color(80,188,223));
        pane.add(ok);
        JLabel pwLabel = new JLabel("Division: ");
        pwLabel.setPreferredSize(new Dimension(80,20));
        pane.add(pwLabel);
        for(int i = 0; i < data.length; i++) {
            division[i] = new JRadioButton(data[i]);
            if(i == 0){division[i].setSelected(true);}
            division[i].setBackground(Color.white);
            group.add(division[i]);
            radiobtnPanel.add(division[i]);
            division[i].addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(e.getSource() == division[0]) {
                        divisionIndex = 0;
                    } else if(e.getSource() == division[1]) {
                        divisionIndex = 1;
                    }else if (e.getSource() == division[2]) {
                        divisionIndex = 2;
                    }
                }
            });
        }
        radiobtnPanel.setBackground(Color.white);
        pane.add(radiobtnPanel);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.out.println("start vm: " + portNum);
                Random random = new Random();
                double x = random.nextDouble()*10;
                double y = random.nextDouble()*10;
                double[] location = {x,y};
                int port = portNum;
                int division = divisionIndex;
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
                dispose();
            }
        });
    }

}
