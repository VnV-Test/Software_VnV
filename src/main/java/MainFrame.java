

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame{

    private Container frame = this.getContentPane();
    private JPanel menuPanel = new JPanel();
    public JDialog dlg = null;
    private VM vm = null;
    private Admin admin = new Admin("2721ckd","875421","");

    public MainFrame(VM vm) {
        super("Distributed Vending Machine");
        this.setSize(500,650);
        this.setLocationRelativeTo(null);
        this.vm = vm;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //frame.setBackground(Color.white);
        init();
        this.setVisible(true);
    }
    private void init() {
        initNorth();
        initCenter();
    }
    private void initNorth() {
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));
        northPanel.setBackground(Color.white);
        northPanel.setPreferredSize(new Dimension(500,50));
        northPanel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));

        JButton adminBtn = new JButton("관리자모드");
        adminBtn.setBackground(new Color(80,188,223));
        adminBtn.setForeground(Color.white);
        adminBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(dlg ==null) {
                    dlg = new AdminDialog(MainFrame.this, "Admin Mode",false);
                }
                else {	dlg.requestFocus();
                }
                if(dlg instanceof AdminDialog) {
                    String id = ((AdminDialog)dlg).getID();
                    String pw = ((AdminDialog)dlg).getPW();
                    if(id == null || pw == null) return;
                }
            }

        });
        northPanel.add(adminBtn);

        JButton codeBtn = new JButton("인증코드");
        codeBtn.setBackground(new Color(80,188,223));
        codeBtn.setForeground(Color.white);
        codeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(dlg ==null)
                    dlg = new CodeDialog(MainFrame.this, "Input Code",false);
                else {	dlg.requestFocus();
                }
            }

        });
        northPanel.add(codeBtn);
        frame.add(northPanel,BorderLayout.NORTH);
    }
    private void initCenter(){
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
        showDrink();
        centerPanel.add(menuScroll,BorderLayout.CENTER);

        frame.add(centerPanel,BorderLayout.CENTER);
    }
    public void showDrink() {
        menuPanel.removeAll();
        Drink[] drinkArray = vm.getDrinkArray();
        for(int i = 0; i<20; i++) {
            DrinkPanel drink = new DrinkPanel(drinkArray[i].getName(),drinkArray[i].getPrice());
            //DrinkPanel drink = new DrinkPanel("콜라",1000);
            drink.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // TODO Auto-generated method stub
                    super.mouseClicked(e);
                    String drinkname = drink.getName();
                    // 음료수 이름을 기반으로 재고가 있는지 판단.
                    boolean isStock = vm.CheckStoke(drinkname);
                    if(isStock) {
                        // 결제 다이얼로그 생성
                        if(dlg == null)
                            dlg = new PaymentDialog(MainFrame.this, "Payment",false,drink.getPrice(),drinkname);
                        else {	dlg.requestFocus();
                        }
                    }else {
                        // VM List Frame 생성
                        System.out.println("재고가 없습니다.");
                    }
                }

            });
            menuPanel.add(drink);
        }
        menuPanel.repaint();
        repaint();
        validate();
    }
    public void checkAdmin() {
        String id = ((AdminDialog)dlg).getID();
        String pw = ((AdminDialog)dlg).getPW();
        if(id == null || pw == null) {return;}
        // 아이디, 비밀번호가 일치하는지 체크
        boolean isAdmin = admin.checkIDPW(id,pw);
        if(isAdmin) {
            AdminFrame admin = new AdminFrame(this);
        }else {
            JOptionPane.showMessageDialog(null, "잘못된 관리자 ID 혹은 PW 입니다.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void checkCode() {
        String id = ((CodeDialog)dlg).getCode();
        System.out.println(id);
        if(id == null) {return;}
        int inputcode;
        try{
            inputcode  = Integer.parseInt(id);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "정수이외의 문자열을 입력할 수 없습니다.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String isCode = vm.checkCode(inputcode);
        // 인증코드가 맞는지 체크
        if(isCode != null) {
            giveProduct(isCode);
            // 코드 삭제
        }else {
            JOptionPane.showMessageDialog(null, "잘못된 인증코드 입니다.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void cardCheck() {
        String num1 = ((CardDialog)dlg).getNum1();
        String num2 = ((CardDialog)dlg).getNum2();
        String num3 = ((CardDialog)dlg).getNum3();
        String num4 = ((CardDialog)dlg).getNum4();
        if(num1 == null || num2 == null || num3 == null ||num4 == null ) {
            JOptionPane.showMessageDialog(null, "카드번호는 공백일 수 없습니다.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String cardNum = num1 + num2 + num3 + num4;
        String cvcStr = ((CardDialog)dlg).getCvc();
        if(cvcStr == null ) {
            JOptionPane.showMessageDialog(null, "CVC는 공백일 수 없습니다.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int cvc;
        try{
            cvc  = Integer.parseInt(cvcStr);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "CVC에는 정수 이외의 문자열을 입력할 수 없습니다.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String validityStr = ((CardDialog)dlg).getCon();
        int validity;
        if(validityStr == null ) {
            JOptionPane.showMessageDialog(null, "유효기간은 공백일 수 없습니다.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            validity  = Integer.parseInt(validityStr);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "유효기간에는 정수 이외의 문자열을 입력할 수 없습니다.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String pwStr = ((CardDialog)dlg).getPW();
        if(pwStr == null ) {
            JOptionPane.showMessageDialog(null, "비밀번호는 공백일 수 없습니다.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int pw;
        try{
            pw  = Integer.parseInt(pwStr);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "비밀번호에는 정수 이외의 문자열을 입력할 수 없습니다.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Card payCard = vm.findCard(cardNum,cvc,pw,validity);
        System.out.println(payCard);
        if(payCard == null){
            JOptionPane.showMessageDialog(null, "존재하지 않는 카드입니다.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int price = ((CardDialog)dlg).getPrice();
        if(payCard.payment(price)){
           giveProduct(((CardDialog)dlg).getName());
        }else{
            JOptionPane.showMessageDialog(null, "카드 잔액이 부족합니다.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void giveProduct(String name){
        JOptionPane.showMessageDialog(null, "    제품: " + name + "을(를) 지급해드렸습니다.","Message", JOptionPane.PLAIN_MESSAGE);
    }
    public void repaintMenu(){

    }
    public void setDlg(JDialog dialog){
        this.dlg = dialog;
    }
    public void showCardDialog(int price, String name){
        this.dlg = new CardDialog(this, "Payment",false,price, name);
        System.out.println(dlg);
    }
    public Admin getAdmin(){
        return this.admin;
    }
    public VM getVM(){
        return this.vm;
    }
}
