import java.util.*;

public class Player extends Thread{
    private final int playername;
    private final int preferredValue;
    private final ArrayList<Card> hand;
    private Deck discardDeck;
    private Deck drawDeck;

    public Player(int playername, int preferredValue){
        this.playername = playername;
        this.preferredValue = preferredValue;
        this.hand = new ArrayList<Card>();
    }

    public synchronized void setDiscardDeck(Deck deck){
        this.discardDeck = deck;
    }

    public synchronized void setDrawDeck(Deck deck){
        this.drawDeck = deck;
    }

    public synchronized Deck getDiscardDeck(){
        return this.discardDeck;
    }


    public synchronized Deck getDrawDeck(){
        return this.drawDeck;
    }

    public synchronized int getPlayerName(){
        return this.playername;
    }

    public synchronized ArrayList<Card> getHand(){
        return this.hand;
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