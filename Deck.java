import java.io.FileWriter;
import java.util.*;

//deck object to properly draw / discard cards 
public class Deck{

    //attributes 
    private int name;
    private ArrayList<Card> contents;
    private FileWriter outFile;
    
    //constructor 
    public Deck(int name, FileWriter outFile){
        this.name = name;
        contents = new ArrayList<Card>();
        this.outFile = outFile;
    }

    //returns output file 
    public FileWriter getFileWriter(){
        return this.outFile;
    }

    //returns name 
    public int getName(){
        return this.name;
    }

    //returns the list of cards in the deck 
    public ArrayList<Card> getContents(){
        return this.contents;
    }

    //adds a card to the front of the list - used when dealing cards out 
    public synchronized void addCard(Card card){
        contents.add(0, card);
    }

    //adds a card to the back of the list - used when a player discards to the deck 
    public synchronized void discard(Card card){
        contents.add(card);
    }

    //pops the first card in the list
    public synchronized Card draw(){
        return contents.remove(0);
    }


    


    
}