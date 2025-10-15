public class Player extends Thread{
    private final int playername;
    private final int preferredValue;
    private final List<Card> hand;

    public Player(String playername){
        this.playername = playername;
        this.preferredValue = playername;
        this.hand = new ArrayList<>();
    }
    public void run () {
        System.out.println("player thread!");

    }
    //player picks up a card
    public void addCard(Card card) {
        if (hand.size() < 4) { //each player has 4 cards
            hand.add(card);
        }

    }
    //discard card that is not prefferred value
    public Card discardCard() {
        for (Card card : hand) {
            if (card.getValue() != preferreedValue) {
                hand.remove(card);
                return card;
            }
        }
    }


}