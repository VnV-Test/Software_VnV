
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class VMFrame extends JFrame{

    Container frame = this.getContentPane();
    JPanel vmPanel;
    MainFrame parent = null;
    String drinkname;

    public VMFrame(MainFrame parent, VM otherVm,String drinkname) {
        super("VM-List");
        this.parent = parent;
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.drinkname = drinkname;
        init(otherVm);
        this.setVisible(true);
    }
    private void init(VM otherVm) {
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
        initVM(otherVm);
        frame.add(centerPanel,BorderLayout.CENTER);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                super.windowClosing(e);
                parent.setPredrinkname(null);
                parent.vmframe = null;
                dispose();
            }
        });
    }
    public void initVM(VM otherVm){
        // 거리계산
        VM vm = parent.getVM();
        Double distance = Math.sqrt(Math.pow(otherVm.getLocation()[0]-vm.getLocation()[0],2)+Math.pow(otherVm.getLocation()[1]-vm.getLocation()[1],2));
        VMPanel vmpanel = new VMPanel(vm.getID(),distance);
        vmpanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                parent.getVM().requestPrepay(drinkname,vm.getID());
            }

        });
        vmPanel.add(vmpanel);
        repaint();
        validate();
    }
}
