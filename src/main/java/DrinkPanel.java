import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrinkPanel extends JPanel {

    // 속성
    String drinkname;
    int price;
    boolean isWhite = true;

    // Panel
    JPanel center;
    JPanel southPanel;

    //Label
    JLabel nameLabel;
    JLabel priceLabel;

    //image
    String[] imgpath = {"img/coke.png"};
    ImageIcon icon;

    public DrinkPanel(String name, int price) {
        this.drinkname = name;
        this.price = price;
        this.setLayout(new BorderLayout(0,1));
        this.setPreferredSize(new Dimension(140,140));
        this.setBackground(Color.white);
        init();
    }

    private void init() {
        this.setBorder(BorderFactory.createLineBorder(Color.black,2));
        initCenter();
        initSouth();

        // 마우스 리스너 등록
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                allColorChange();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                allColorChange();
            }
        });
    }
    private void initCenter() {
        center = new JPanel();
        center.setBackground(Color.white);
        center.setPreferredSize(new Dimension(138,95));
        switch (this.drinkname){
            case "콜라":
                icon = new ImageIcon(imgpath[0]);
                break;
        }
        JLabel centerLabel = new JLabel(icon);
        centerLabel.setBackground(Color.white);
        centerLabel.setPreferredSize(new Dimension(120,80));
        centerLabel.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        center.add(centerLabel);

        this.add(center,BorderLayout.CENTER);
    }
    private void initSouth() {
        southPanel = new JPanel(new GridLayout(2,1,0,0));
        southPanel.setBackground(Color.white);
        southPanel.setPreferredSize(new Dimension(138,40));

        JLabel nameLabel = new JLabel(this.drinkname);
        Font namefont = new Font("나눔고딕",Font.BOLD,12);
        nameLabel.setFont(namefont);
        nameLabel.setForeground(new Color(80,188,223));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        southPanel.add(nameLabel);

        JLabel priceLabel = new JLabel("\\ "+this.price);
        Font pricefont = new Font("새굴림",Font.PLAIN,10);
        priceLabel.setFont(pricefont);
        priceLabel.setHorizontalAlignment(JLabel.CENTER);
        southPanel.add(priceLabel);

        this.add(southPanel,BorderLayout.SOUTH);
    }
    private void allColorChange(){
        if(isWhite){
            this.setBorder(BorderFactory.createLineBorder(new Color(80,188,223),2));

            isWhite = false;
        }else{
            this.setBorder(BorderFactory.createLineBorder(Color.black,2));
            isWhite = true;
        }
    }
    public String getName() {
        return this.drinkname;
    }
    public int getPrice() {
        return this.price;
    }
}
