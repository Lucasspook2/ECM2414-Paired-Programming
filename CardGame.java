//import Player.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.CyclicBarrier;

//import inputPack;

public class CardGame extends Thread {

    public static ArrayList<Player> playerList = new ArrayList<Player>();



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
        playerList.add(new Player(i, i, barrier));
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
    
    ArrayList<Deck> deckList = new ArrayList<Deck>();
    for (int i = 1; i < player_count + 1; i ++){
        deckList.add(new Deck(i));
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
        playerList.get(i).setPlayerList(playerList);
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
