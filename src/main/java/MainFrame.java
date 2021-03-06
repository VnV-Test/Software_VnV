import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class MainFrame extends JFrame{

    private Container frame = this.getContentPane();
    private JPanel menuPanel = new JPanel();
    public JDialog dlg = null;
    private VM vm = null;
    private Admin admin = new Admin("2721ckd","875421","");
    public VMFrame vmframe = null;
    private String predrinkname = null;

    public MainFrame(VM vm) {
        super("Distributed Vending Machine(" + vm.getMarkID()+ ")");
        this.setSize(500,650);
        this.setLocationRelativeTo(null);
        this.vm = vm;
        vm.setUI(this);
        vm.setAdmin(this.admin);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //frame.setBackground(Color.white);
        init();
        this.setVisible(true);
    }
    //for test
    public void setpreDrinkname_test(String drinkname){
        predrinkname=drinkname;
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

        JButton cardBtn = new JButton("Card");
        cardBtn.setBackground(new Color(80,188,223));
        cardBtn.setForeground(Color.white);
        cardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardListFrame cardList = new CardListFrame(vm.getCardList());
            }

        });
        northPanel.add(cardBtn);

        JButton adminBtn = new JButton("Admin");
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

        JButton codeBtn = new JButton("Code");
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
            //DrinkPanel drink = new DrinkPanel("??????",1000);
            drink.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // TODO Auto-generated method stub
                    super.mouseClicked(e);
                    String drinkname = drink.getName();
                    // ????????? ????????? ???????????? ????????? ????????? ??????.
                    boolean isStock = vm.checkStock(drinkname);
                    if(isStock) {
                        // ?????? ??????????????? ??????
                        if(dlg == null)
                            dlg = new PaymentDialog(MainFrame.this, "Payment",false,drink.getPrice(),drinkname);
                        else {	dlg.requestFocus();
                        }
                    }else {
                        // VM List Frame ??????
                        if(MainFrame.this.vmframe != null){
                            MainFrame.this.vmframe.dispose();
                            MainFrame.this.vmframe = null;
                        }
                        MainFrame.this.predrinkname = drinkname;
                        System.out.println("\nVMFrame make :" + predrinkname + "\n");
                        MainFrame.this.vmframe = new VMFrame(MainFrame.this,predrinkname,drink.getPrice());
                        vm.getOtherVM(drinkname);

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
        // ?????????, ??????????????? ??????????????? ??????
        boolean isAdmin = admin.checkIDPW(id,pw);
        if(isAdmin) {
            AdminFrame adminframe = new AdminFrame(this);
            this.dlg.dispose();
            this.dlg = null;
        }else {
            JOptionPane.showMessageDialog(null, "\n" + "Invalid administrator ID or PW", "Error", JOptionPane.ERROR_MESSAGE);
            this.dlg.requestFocus();
        }
    }
    public void checkCode() {
        String id = ((CodeDialog)dlg).getCode();
        if(id == null) {return;}
        int inputcode;
        try{
            inputcode  = Integer.parseInt(id);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "You cannot enter a character string other than an integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String isCode = vm.checkCode(inputcode);
        // ??????????????? ????????? ??????
        if(isCode != null) {
            giveProduct(isCode);
        }else {
            JOptionPane.showMessageDialog(null, "\n" + "This is an incorrect authentication code.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void cardCheck() {
        String num1 = ((CardDialog)dlg).getNum1();
        String num2 = ((CardDialog)dlg).getNum2();
        String num3 = ((CardDialog)dlg).getNum3();
        String num4 = ((CardDialog)dlg).getNum4();
        if(num1 == null || num2 == null || num3 == null ||num4 == null ) {
            JOptionPane.showMessageDialog(null, "\n" + "Card number cannot be blank", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String cardNum = num1 + num2 + num3 + num4;
        String cvcStr = ((CardDialog)dlg).getCvc();
        if(cvcStr == null ) {
            JOptionPane.showMessageDialog(null, "CVC cannot be blank", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int cvc;
        try{
            cvc  = Integer.parseInt(cvcStr);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "\n" + "Strings other than integers cannot be entered in CVC.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String validityStr = ((CardDialog)dlg).getCon();
        int validity;
        if(validityStr == null ) {
            JOptionPane.showMessageDialog(null, "Validity cannot be blank", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            validity  = Integer.parseInt(validityStr);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "strings other than integers cannot be entered in valitdity", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String pwStr = ((CardDialog)dlg).getPW();
        if(pwStr == null ) {
            JOptionPane.showMessageDialog(null, "PW cannot be blank", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int pw;
        try{
            pw  = Integer.parseInt(pwStr);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "strings other than integers cannot be entered in Password", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Card payCard = vm.findCard(cardNum,cvc,pw,validity);
        if(payCard == null){
            JOptionPane.showMessageDialog(null, "This card does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int price = ((CardDialog)dlg).getPrice();
        if(payCard.payment(price)){
            Item item = this.vm.findItem(((CardDialog)dlg).getName());
            item.editStock(item.getStock()-1);
            giveProduct(((CardDialog)dlg).getName());
        }else{
            JOptionPane.showMessageDialog(null, "There is not enough balance.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void cardCheck(String drinkname,VM otherVm) {
        String num1 = ((CardDialog)dlg).getNum1();
        String num2 = ((CardDialog)dlg).getNum2();
        String num3 = ((CardDialog)dlg).getNum3();
        String num4 = ((CardDialog)dlg).getNum4();
        if(num1 == null || num2 == null || num3 == null ||num4 == null ) {
            JOptionPane.showMessageDialog(null, "\n" + "Card number cannot be blank", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String cardNum = num1 + num2 + num3 + num4;
        String cvcStr = ((CardDialog)dlg).getCvc();
        if(cvcStr == null ) {
            JOptionPane.showMessageDialog(null, "CVC cannot be blank", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int cvc;
        try{
            cvc  = Integer.parseInt(cvcStr);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "\n" + "Strings other than integers cannot be entered in CVC.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String validityStr = ((CardDialog)dlg).getCon();
        int validity;
        if(validityStr == null ) {
            JOptionPane.showMessageDialog(null, "Validity cannot be blank", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            validity  = Integer.parseInt(validityStr);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "strings other than integers cannot be entered in valitdity", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String pwStr = ((CardDialog)dlg).getPW();
        if(pwStr == null ){
            JOptionPane.showMessageDialog(null, "PW cannot be blank", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int pw;
        try{
            pw  = Integer.parseInt(pwStr);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "strings other than integers cannot be entered in Password", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Card payCard = vm.findCard(cardNum,cvc,pw,validity);
        if(payCard == null){
            JOptionPane.showMessageDialog(null, "This card does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int price = ((CardDialog)dlg).getPrice();
        if(payCard.payment(price)){
            System.out.println("\n request prepay : drinkname(" + drinkname +")\n");
            this.getVM().requestPrepay(drinkname,otherVm.getID());
        }else{
            JOptionPane.showMessageDialog(null, "There is not enough balance.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void giveProduct(String name){
        JOptionPane.showMessageDialog(null, "            Give you a " + "Product : " + name,"Message", JOptionPane.PLAIN_MESSAGE);
    }
    public void showCardDialog(int price, String name){
        this.dlg = new CardDialog(this, "Payment",false,price, name);
    }
    public void showCardDialog(int price, String name,VM othervm){
        this.dlg = new CardDialog(this, "Payment",false,price, name,true,othervm);
    }
    public Admin getAdmin(){
        return this.admin;
    }
    public VM getVM(){
        return this.vm;
    }
    public void showMessage(String type,String description){
        JOptionPane.showMessageDialog(null,  description, type, JOptionPane.PLAIN_MESSAGE);
    }
    public void showVMFrame(VM vm){
        System.out.println("display VM:" + vm.getID());
        System.out.println("Message from " + vm.getID() + ": vmframe = "  + this.vmframe);
        if(this.vmframe == null){
            Drink drink = vm.findDrink(predrinkname);
        }else{
            vmframe.initVM(vm);
        }


    }
    public void setPredrinkname(String predrinkname){
        this.predrinkname = predrinkname;
    }
}
