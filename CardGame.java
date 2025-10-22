//import Player.*;

import java.io.*;
import java.util.*;

//import inputPack;

public class CardGame extends Thread {


    public void run() {
        System.out.println("hello Thread");
        

    }

    public static void main (String args []){
    ArrayList<Integer> pack = inputPack.getPack("input.txt");
        for (int i = 0; i < pack.size(); i++){
            System.out.println(pack.get(i));
        }

  }

}



///this goes in main
 //   (new CardGame()).start();
        //Player player1 = new Player("player1");
        //player1.start();
       // System.out.println("name = " + player1.name);

//get bumber of players
//create the pack if cards
//create decks and players
//
