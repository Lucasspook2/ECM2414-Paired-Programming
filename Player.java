public class Player extends Thread{

public String name;

public Player(String name){
    this.name = name;
}
public void run () {
    System.out.println("player thread!");

}

}