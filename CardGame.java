//import Player.*;

import java.io.*;
import java.util.*;

//import inputPack;

public class CardGame extends Thread {


    public static boolean FileSearch(String fileName){
        File file = new File(fileName);
        return file.exists() && file.isFile();
    }

    public void run() {
        System.out.println("hello Thread");
        

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
    

    ArrayList<Integer> pack = inputPack.getPack(pack_name);
       // for (int i = 0; i < pack.size(); i++){
         //   System.out.println(pack.get(i));
        //}

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
