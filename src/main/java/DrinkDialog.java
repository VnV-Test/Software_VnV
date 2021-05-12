import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrinkDialog extends JDialog {

    VM vm = null;
    JPanel menuPanel = new JPanel();
    AdminFrame parent = null;

    public DrinkDialog(String title, boolean modal,VM vm, AdminFrame parent){
        super(parent,title,modal);
        this.vm = vm;
        this.parent = parent;
        this.setSize(500,450);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initDrink();
        this.setVisible(true);
    }
    private void initDrink(){
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.white);
        centerPanel.setPreferredSize(new Dimension(500,550));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(25,0,0,0));

        menuPanel = new JPanel(new FlowLayout(15));
        menuPanel.setPreferredSize(new Dimension(450,1050));
        menuPanel.setBackground(Color.white);
        JScrollPane menuScroll = new JScrollPane(menuPanel);
        menuScroll.setBackground(Color.white);
        menuScroll.setPreferredSize(new Dimension(472,450));
        menuScroll.setBackground(Color.white);
        menuScroll.setBorder(BorderFactory.createLineBorder(Color.white,2));
        centerPanel.add(menuScroll,BorderLayout.CENTER);

        this.add(centerPanel,BorderLayout.CENTER);
        Drink[] drinkArray = vm.getDrinkArray();
        for(int i = 0; i<20; i++) {
            DrinkPanel drink = new DrinkPanel(drinkArray[i].getName(),drinkArray[i].getPrice());
            int index = i;
            drink.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // TODO Auto-generated method stub
                    super.mouseClicked(e);
                    parent.setSelectProduct(index);
                    parent.setProduct(drinkArray[index].getName(),drinkArray[index].getPrice(),0);
                    dispose();
                    JOptionPane.showMessageDialog(null, drinkArray[index].getName() +"is selected", "Guidance", JOptionPane.INFORMATION_MESSAGE);
                }

            });
            menuPanel.add(drink);
            this.add(menuScroll);
        }
    }
}
