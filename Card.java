//this class represents a singular card in the game
public class Card {
   // private final int value;
    
    public Card(int value) {
        this.value = value; //each card will be assigned a positive value (1, 2, 3...) 
        // the value is decided by the input pack file CODE IN CARDGAME!!
    }
    
    public int getValue() {
        return value;
    } //returns the value as a string
    
    @Override
    public String toString() {
        return String.valueOf(value);
    } //function checks if two cards are equal to each other 
    //helpful to determine if sll cards are equal and if a player has won
    //returns True if they do and False if not
    

    //do we add a function to check if the card value is negative? Probably not needed tbh
}