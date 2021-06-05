import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class CardListFrame extends JFrame{

    public CardListFrame(Vector<Card> cardList){
        super("Card-List");
        this.setSize(300,100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        init(cardList);
        this.setVisible(true);
    }
    private void init(Vector<Card> cardList){
        String header[] = {"카드번호","잔액"};
        int cardNum = cardList.size();
        String contents[][] = new String[cardNum][2];
        for(int i = 0; i < cardNum; i++){
            contents[i][0] = cardList.elementAt(i).getCardNum();
            contents[i][1] = String.valueOf(cardList.elementAt(i).getBalance());
        }

        JTable cardTable = new JTable(contents,header);
        JScrollPane scroll = new JScrollPane(cardTable);
        scroll.setPreferredSize(new Dimension(250,90));
        this.getContentPane().add(scroll);
        this.pack();
    }
}
