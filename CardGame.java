

public class CardGame extends Thread {
    public void run() {
        System.out.println("hello Thread");

    }

    public static void main (String args []){
        (new CardGame()).start();
        (new Player()).start();
    }

}
//get bumber of players
//create the pack if cards
//create decks and players
//
