import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VMPanel extends JPanel{
    // 속성
    private int vmid;
    private double distance;
    private boolean isWhite = true;

    public VMPanel(int id, double distance) {
        this.vmid = id;
        this.distance = distance;
        this.setLayout(new BorderLayout(0,1));
        this.setPreferredSize(new Dimension(300,150));
        this.setBackground(Color.white);
        init();
    }
    private void init() {
        this.setBorder(BorderFactory.createLineBorder(Color.black,2));
        //this.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
        JPanel center = new JPanel(new GridLayout(1,3,5,5));
        center.setBorder( BorderFactory.createEmptyBorder(10,0,10,0));
        center.setBackground(Color.white);
        // img
        ImageIcon icon = new ImageIcon("img/dvm.png");
        JLabel imgLabel = new JLabel(icon);
        center.add(imgLabel);

        // DVM id
        JLabel idLabel = new JLabel("VM(id: "  + this.vmid + ")");
        idLabel.setVerticalAlignment(JLabel.TOP);
        idLabel.setPreferredSize(new Dimension(200,130));
        Font font = new Font("Arial",Font.BOLD,13);
        idLabel.setFont(font);
        center.add(idLabel);

        // distance
        JLabel distanceLabel = new JLabel("    Distance: " + String.format("%.2f", this.distance)+ " km  ");
        distanceLabel.setVerticalAlignment(JLabel.BOTTOM);
        Font font2 = new Font("Arial",Font.PLAIN,11);
        distanceLabel.setFont(font2);
        center.add(distanceLabel);
        this.add(center);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                colorChange();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                colorChange();
            }
        });
    }
    private void colorChange() {
        if(isWhite) {
            this.setBorder(BorderFactory.createLineBorder(new Color(80,188,223),2));
            isWhite = false;
        }else {
            this.setBorder(BorderFactory.createLineBorder(Color.black,2));
            isWhite = true;
        }
    }
    public int getID() {
        return this.vmid;
    }
}
