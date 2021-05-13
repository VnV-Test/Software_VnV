
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class VMFrame extends JFrame{

    Container frame = this.getContentPane();
    JPanel vmPanel;
    MainFrame parent = null;
    String drinkname;

    public VMFrame(MainFrame parent, Vector<VM> vmList,String drinkname) {
        super("VM-List");
        this.parent = parent;
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.drinkname = drinkname;
        init(vmList);
        this.setVisible(true);
    }
    private void init(Vector<VM> vmList) {
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
        initVM(vmList);
        frame.add(centerPanel,BorderLayout.CENTER);
    }
    private void initVM(Vector<VM> vmList){
        //VMPanel 넣는곳
        if(vmList.size() == 0){
            this.parent.showMessage("Error","There are no VMs in stock.");
            dispose();
        }
        for(int i = 0; i < vmList.size(); i++){
            VM vm = vmList.elementAt(i);
            VM thisvm = parent.getVM();
            // 거리계산
            Double distance = Math.sqrt(Math.pow(thisvm.getLocation()[0]-vm.getLocation()[0],2)+Math.pow(thisvm.getLocation()[1]-vm.getLocation()[1],2));
            VMPanel vmpanel = new VMPanel(vm.getID(),distance);
            vmpanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // TODO Auto-generated method stub
                    parent.getVM().requestPrepay(drinkname,vm.getID());
                }

            });
            vmPanel.add(vmpanel);
        }
    }
}
