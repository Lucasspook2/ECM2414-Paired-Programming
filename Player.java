import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Player extends Thread{
    private final int playername;
    private final int preferredValue;
    private final ArrayList<Card> hand;
    private Deck discardDeck;
    private Deck drawDeck;
    private CyclicBarrier barrier;
    private ArrayList<Player> playerList;

    public Player(int playername, int preferredValue, CyclicBarrier barrier){
        this.playername = playername;
        this.preferredValue = preferredValue;
        this.hand = new ArrayList<Card>();
        this.barrier = barrier;
    }

    public synchronized void setPlayerList(ArrayList<Player> playerList){
        this.playerList = playerList;
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

    public boolean hasWon(){
        return hand.get(0).getValue() == preferredValue
            && hand.get(1).getValue() == preferredValue
            && hand.get(2).getValue() == preferredValue
            && hand.get(3).getValue() == preferredValue;
            
    }

    
    public void run () {
        System.out.println("player " + playername + " running!");
        

        System.out.println("");
        
        while(!hasWon()){
            Card i = discardCard();
            System.out.println("Player " + playername + " discards " + i.getValue());
            discardDeck.discard(i);
            Card j = drawDeck.draw();
            System.out.println("Player " + playername + " draws " + j.getValue());
            addCard(j);
            while (hand.size() > 4){
                discardDeck.discard(discardCard());
            }
            System.out.println("Hand:");
            for(Card x : hand){
                System.out.print(x.getValue() + " ");
            }
            System.out.println();
            try{
            barrier.await();
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (BrokenBarrierException e){
                e.printStackTrace();
            }
           

            }

            System.out.println("Victory for player " + playername);

             for(Player i : playerList){
                if(i.getPlayerName() != playername){
                    i.interrupt();
                }
             }
        


    }
    //player picks up a card
    public void addCard(Card card) {
        if (hand.size() < 4) { //each player has 4 cards
            hand.add(card);
        }

    }
    //discard card that is not preferred value
    public Card discardCard() {
        for (Card card : hand) {
            if (card.getValue() != preferredValue) {
                hand.remove(card);
                return card;
            }
        }
        Card j = hand.remove(0);
        return j;
    }


}