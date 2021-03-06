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
    String[] imgpath = {"img/coke.png","img/sprite.png","img/mintcoke.png","img/water.png"
    ,"img/sparklingwater.png","img/coffee.png","img/milkcoffee.png","img/demisoda.png","img/sunnyten.png",
    "img/sikhye.png","img/IDH.png","img/milkiss.png","img/mccall.png","img/2.png","img/gatorade.png","img/hotsix.png"
    ,"img/coco.png","img/minutemade.png","img/sprite.png","img/milkcoffee.png"};
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
            case "Coke":
                icon = new ImageIcon(imgpath[0]);
                break;
            case "Sprite":
                icon = new ImageIcon(imgpath[1]);
                break;
            case "Mint Coke":
                icon = new ImageIcon(imgpath[2]);
                break;
            case "Water":
                icon = new ImageIcon(imgpath[3]);
                break;
            case "Sparkling water":
                icon = new ImageIcon(imgpath[4]);
                break;
            case "Coffee":
                icon = new ImageIcon(imgpath[5]);
                break;
            case "Milk Coffee":
                icon = new ImageIcon(imgpath[6]);
                break;
            case "Demisoda":
                icon = new ImageIcon(imgpath[7]);
                break;
            case "SunnyTen":
                icon = new ImageIcon(imgpath[8]);
                break;
            case "Sikhye":
                icon = new ImageIcon(imgpath[9]);
                break;
            case "IDH":
                icon = new ImageIcon(imgpath[10]);
                break;
            case "Milkis":
                icon = new ImageIcon(imgpath[11]);
                break;
            case "McCall":
                icon = new ImageIcon(imgpath[12]);
                break;
            case "2%":
                icon = new ImageIcon(imgpath[13]);
                break;
            case "Gatorade":
                icon = new ImageIcon(imgpath[14]);
                break;
            case "Hot Six":
                icon = new ImageIcon(imgpath[15]);
                break;
            case "CoCo palm":
                icon = new ImageIcon(imgpath[16]);
                break;
            case "Minute Made":
                icon = new ImageIcon(imgpath[17]);
                break;
            case "Mint Sprite":
                icon = new ImageIcon(imgpath[18]);
                break;
            case "Mint Coffee":
                icon = new ImageIcon(imgpath[19]);
                break;
            default:
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

        nameLabel = new JLabel(this.drinkname);
        Font namefont = new Font("Arial",Font.BOLD,12);
        nameLabel.setFont(namefont);
        nameLabel.setForeground(new Color(80,188,223));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        southPanel.add(nameLabel);

        priceLabel = new JLabel("₩ "+this.price);
        Font pricefont = new Font("Arial",Font.PLAIN,10);
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
