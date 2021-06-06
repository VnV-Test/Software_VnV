
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VMFrame extends JFrame{

    Container frame = this.getContentPane();
    JPanel vmPanel;
    MainFrame parent = null;
    String drinkname;
    int price;
    boolean ispaint = false;

    public VMFrame(MainFrame frame,String drinkname,int price) {
        super("VM-List");
        this.parent = frame;
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.drinkname = drinkname;
        this.price = price;
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
        JLabel error = new JLabel("                    재고를 가지고 있는 다른 VM이 없습니다.");
        vmPanel.add(error);
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
        if(!ispaint){
            vmPanel.removeAll();
            repaint();
            validate();
            ispaint = true;
        }
        VM vm = parent.getVM();
        Double distance = Math.sqrt(Math.pow(otherVm.getLocation()[0]-vm.getLocation()[0],2)+Math.pow(otherVm.getLocation()[1]-vm.getLocation()[1],2));
        VMPanel vmpanel = new VMPanel(otherVm.getID(),otherVm.getMarkID(),distance);
        vmpanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                //결제창 띄우기
                System.out.println("\nVMFrame name :" + drinkname + "\n");
                parent.dlg = new PaymentDialog(parent, "Payment",false, price, drinkname,true, otherVm);

            }

        });
        vmPanel.add(vmpanel);
        repaint();
        validate();
    }
}
