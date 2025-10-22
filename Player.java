import java.util.*;

public class Player extends Thread{
    private final int playername;
    private final int preferredValue;
    private final ArrayList<Card> hand;

    public Player(int playername, int preferredValue){
        this.playername = playername;
        this.preferredValue = preferredValue;
        this.hand = new ArrayList<Card>();
    }

    public void run () {
        System.out.println("player thread!");

    }
    //player picks up a card
    public void addCard(Card card) {
        if (hand.size() < 4) { //each player has 4 cards
            hand.add(card);
        }

    }
    //discard card that is not prefferred value
    public Card discardCard() {
        for (Card card : hand) {
            if (card.getValue() != preferredValue) {
                hand.remove(card);
                return card;
            }
        }
        return hand.get(0);
    }


}