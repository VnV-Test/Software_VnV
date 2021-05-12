
import javax.swing.*;
import java.awt.*;

public class VMFrame extends JFrame{

    Container frame = this.getContentPane();
    JPanel vmPanel;
    MainFrame parent = null;

    public VMFrame(MainFrame parent) {
        super("VM-List");
        this.parent = parent;
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        init();
        this.setVisible(true);
    }
    private void init() {
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.white);
        centerPanel.setPreferredSize(new Dimension(370,450));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(25,0,0,0));

        vmPanel = new JPanel(new GridLayout(10,1,5,5));
        vmPanel.setPreferredSize(new Dimension(340,1050));
        vmPanel.setBackground(Color.white);
        vmPanel.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
        JScrollPane vmScroll = new JScrollPane(vmPanel);
        vmScroll.setPreferredSize(new Dimension(372,400));
        centerPanel.add(vmScroll,BorderLayout.CENTER);
        initVM();
        frame.add(centerPanel,BorderLayout.CENTER);
    }
    private void initVM() {

        //VMPanel 넣는곳
        vmPanel.add(new VMPanel(56879,0.9));
        vmPanel.add(new VMPanel(568,1.9));
        vmPanel.add(new VMPanel(5687,2.9));
        vmPanel.add(new VMPanel(579,3.9));
    }
}
