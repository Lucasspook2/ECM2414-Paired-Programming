import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Player extends Thread{
    //class attributes 
    private final int playername;
    private final int preferredValue;
    private final ArrayList<Card> hand;
    private Deck discardDeck;
    private Deck drawDeck;
    private CyclicBarrier barrier;
    private FileWriter outFile;

    //constructor 
    public Player(int playername, int preferredValue, CyclicBarrier barrier, FileWriter outFile){
        this.playername = playername;
        this.preferredValue = preferredValue;
        this.hand = new ArrayList<Card>();
        this.barrier = barrier;
        this.outFile = outFile;
    }

    //sets the deck to discard to 
    public synchronized void setDiscardDeck(Deck deck){
        this.discardDeck = deck;
    }

    //sets deck to draw from 
    public synchronized void setDrawDeck(Deck deck){
        this.drawDeck = deck;
    }

    //returns discard deck
    public synchronized Deck getDiscardDeck(){
        return this.discardDeck;
    }

    //returns draw deck 
    public synchronized Deck getDrawDeck(){
        return this.drawDeck;
    }

    //returns player name 
    public synchronized int getPlayerName(){
        return this.playername;
    }

    //returns hand 
    public synchronized ArrayList<Card> getHand(){
        return this.hand;
    }


    //determines if the player has won the game 
    public boolean hasWon(){

        return hand.get(0).getValue() == preferredValue
            && hand.get(1).getValue() == preferredValue
            && hand.get(2).getValue() == preferredValue
            && hand.get(3).getValue() == preferredValue;
        
    }

    //writes to the output file 
    private void writeLine(String line) {
    try {
        outFile.write(line + System.lineSeparator());
        outFile.flush();
    }catch (IOException e) {
        System.err.println("Player " + playername + " cannot write to file: " + e.getMessage());
    }
    }

    //starts the thread 
    public void run () {
        writeLine("player " + playername + " running!");

        System.out.println("");

        
        //gameplay logic 
        while(!CardGame.isGameOver()) {
            
            if(!hasWon()){
                
                Card i = discardCard();
                writeLine("Player " + playername + " discards " + i.getValue());
                discardDeck.discard(i);
                Card j = drawDeck.draw();
                writeLine("Player " + playername + " draws " + j.getValue());
                addCard(j);
                while (hand.size() > 4){
                    discardDeck.discard(discardCard());
                }
                writeLine("Hand:");
                for(Card x : hand){
                    writeLine(x.getValue() + " ");
                }
                try {
                    barrier.await(); 
                } catch (InterruptedException e) {
                
                    Thread.currentThread().interrupt(); // restore flag
                    break;
                } catch (BrokenBarrierException e) {
                    
                    break;
                } 


        }else{
            synchronized (CardGame.class) {
                CardGame.endGame(this.playername);
                writeLine("Victory for player " + playername);
            }
        }
    }
        
        
        




    }
    //player picks up a card
    public void addCard(Card card) {
        if (hand.size() < 4) { //each player has 4 cards
            hand.add(card);
        }
    }

    //method to smoothly end the game 
    public void cleanupOnExit(int nameofWinner){
            writeLine("Player " + nameofWinner + " has informed player: " + this.getPlayerName() + " of their victory.");
            writeLine("Player " + this.getPlayerName() + " exiting.");
            writeLine("Player " + this.getPlayerName() + "'s final hand:");
            for (Card c : getHand()) {
                writeLine(c.getValue() + " ");
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

    public void endGame(int nameofWinner){

    }

}