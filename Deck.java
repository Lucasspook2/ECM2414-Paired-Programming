import java.io.FileWriter;
import java.util.*;
public class Deck{

    private int name;
    private ArrayList<Card> contents;
    private FileWriter outFile;
    
    
    public Deck(int name, FileWriter outFile){
        this.name = name;
        contents = new ArrayList<Card>();
        this.outFile = outFile;
    }

    public FileWriter getFileWriter(){
        return this.outFile;
    }


    public int getName(){
        return this.name;
    }

    public ArrayList<Card> getContents(){
        return this.contents;
    }

    public synchronized void addCard(Card card){
        contents.add(0, card);
    }

    public synchronized void discard(Card card){
        contents.add(card);
    }

    public synchronized Card draw(){
        return contents.remove(0);
    }


    


    
}