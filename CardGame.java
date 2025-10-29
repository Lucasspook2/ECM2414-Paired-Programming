//import Player.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

//import inputPack;

public class CardGame extends Thread {

    public static ArrayList<Player> playerList = new ArrayList<Player>();
    public static ArrayList<Deck> deckList = new ArrayList<Deck>();
    
    private static final AtomicBoolean gameOver = new AtomicBoolean(false);
    private static final AtomicInteger winner = new AtomicInteger(-1);
 
    public int getWinner() {
        return winner.get();
    }
    
    public static boolean isGameOver() {
        return gameOver.get();
    }

    public static boolean FileSearch(String fileName){
        File file = new File(fileName);
        return file.exists() && file.isFile();
    }

    public void run() {
        System.out.println("hello Thread");

        for(Player i : playerList){
            i.start();
        }
        

    }
/*
 //new function for deck file writing
public static void endGame(int winner) {
    // Write deck contents to deck files
    for (Deck deck : deckList) {
        try (FileWriter fw = deck.getFileWriter()) { // try-with-resources auto-closes
            fw.write("Deck " + deck.getName() + " contents: " + deck.getContents().toString() + System.lineSeparator());
            fw.flush();
        } catch (IOException e) {
            System.err.println("Could not write to deck output file for deck " + deck.getName() + ": " + e.getMessage());
        }
    }
    // Notify and clean up players
    for (Player p : playerList) {
        if (p.getPlayerName() != winner) { 
            p.cleanupOnExit(winner);
        }
    }
}
 */
    public static void endGame(int winnerplayer) {
        gameOver.set(true);//will signal players to stop playing
        winner.set(winnerplayer);//sets winner variable
        
        for (Player p : playerList) {//player cleanup and handling
            if (p.getPlayerName() != winnerplayer) {
                p.cleanupOnExit(winnerplayer);
            }
        }
        
        for (Deck deck : deckList) {//deck cleanup and finalising
        try (FileWriter fw = deck.getFileWriter()) {
            
            StringBuilder sb = new StringBuilder();
            sb.append("deck").append(deck.getName()).append(" contents:");
            for (Card c : deck.getContents()) {
                sb.append(" ").append(c.getValue());
            }
            fw.write(sb.toString());
            fw.write(System.lineSeparator());
            fw.flush();
        } catch (IOException e) {
            System.err.println("Could not write to deck output file for deck " + deck.getName() + ": " + e.getMessage());
        }
        }

    // notify and clean up players

}


    /*
    //need to fix the deck files
    public static void endGame(int winner){//GAME SHOULD WORK, TESTED TWICE 
    try{//MUST DELETE DECK AND PLAYER FILES BEFORE RUNNING!!!!!
        for(Deck i : deckList){
        i.getFileWriter().write("Deck" + i.getContents().toString());
    }
    } catch (IOException e){
        System.err.println("Could not write to deck output file: " + e.getMessage());
    }
    for(Player i : playerList){
        if (i.getPlayerName() != winner){
            i.cleanupOnExit(winner);
        }
    }
 
    }
    */
    public static void main (String args []){
    
    Scanner scanner = new Scanner(System.in);
    int player_count = 0;

    boolean valid = false;
    while (valid == false){
        System.out.println("Please enter the number of players:");
        try{
        player_count = Integer.parseInt(scanner.nextLine());
        valid = true;
        } catch (NumberFormatException e){
            System.out.println("Invalid input!");
            valid = false;
        }
        if(player_count <= 0){
            valid = false;
            System.out.println("Invalid input!");
        }
    }


    CyclicBarrier barrier = new CyclicBarrier(player_count);

    valid = false;
    String pack_name = "";
    while(valid == false){
        System.out.println("Please enter location of pack to load:");
         pack_name = scanner.nextLine();
        if (FileSearch(pack_name) == true && inputPack.countLines(pack_name) == (8 * player_count)){
            valid = true;
        } else{
            System.out.println("Invalid input!");
        }
    }
    

    System.out.println("file chosen = " + pack_name);
    

    ArrayList<Card> pack = inputPack.getPack(pack_name);
    
    
    for (int i = 1; i < player_count + 1; i ++){
        try{
            playerList.add(new Player(i, i, barrier, new FileWriter("player"+(i)+"_output.txt",true)));//adds players to player list
            //creates output file for each player
        } catch (IOException e){
            System.err.println("Could not create output file for player " + i + ": " + e.getMessage());
        }
    }

    for(int i = 0; i < 4; i ++){
        for(Player j : playerList){
        j.addCard(pack.remove(0));
        }
    }

    for(Player j : playerList){
        System.out.println("Player " + j.getPlayerName() + " hand:");
        for(Card i : j.getHand()){
            System.out.println(i.getValue());
        }
    }

    System.out.println();
    System.out.println("remaining pack:");

    for(Card i : pack){
       System.out.println(i.getValue());
    }
    
    //ArrayList<Deck> deckList = new ArrayList<Deck>();
    for (int i = 1;i <= player_count; i ++){//one deck for each player
        try{
            deckList.add(new Deck(i,new FileWriter("deck"+(i)+"_output.txt",true)));//adds decks to deck list
        } catch (IOException e){
            System.err.println("Could not create output file for deck " + i + ": " + e.getMessage());
        }
    }

    System.out.println();
    System.out.println("decks:");

    for(Deck i : deckList){
       System.out.println(i.getContents());
    }

    

    int counter = 0;
    while(pack.size() > 0){
        System.out.println("Adding card " + pack.get(0).getValue() + " to deck " + deckList.get((counter % player_count)).getName());
        deckList.get((counter % player_count)).addCard(pack.remove(0));
        counter ++;
    }

    //for (int i = 0; i < pack.size() -1 ; i++){
    //    deckList.get((i % player_count)).addCard(pack.remove(0));
    //}

    for (Deck i : deckList){
        System.out.println("Deck " + i.getName());
        for(Card j : i.getContents()){
            System.out.println(j.getValue());;
        }
    }



    for (int i = 0; i < player_count - 1; i++){

        playerList.get(i).setDiscardDeck(deckList.get((i+1 % player_count)));
        playerList.get(i).setDrawDeck(deckList.get(i));
  
    }

    playerList.get(player_count-1).setDiscardDeck(deckList.get(0));
    playerList.get(player_count -1).setDrawDeck(deckList.get(player_count-1));




    for(Player i : playerList){
        System.out.println("Player " + i.getPlayerName() + " draws from deck " + i.getDrawDeck().getName());
        System.out.println("Player " + i.getPlayerName() + " discards to deck " + i.getDiscardDeck().getName());
        System.out.println();
    }
    


    (new CardGame()).start();
    

  }

}



///this goes in main


//get bumber of players
//create the pack if cards
//create decks and players
//
