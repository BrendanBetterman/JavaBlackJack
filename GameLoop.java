package BlackJackGame;
public class GameLoop{
    private Deck cards = new Deck();
    private int money;
    private Hand Player;
    private Hand House = new Hand();
    private int bet;
    public GameLoop(){
        this.money = 2000;
        this.bet = 0;
        this.Player = new Hand();
    }
    public String helpCommand(){
        String finalString;
        finalString="---BlackJack---\n";
        finalString += "Play to 21\n";
        finalString += "Closest Player Wins\n";
        finalString += "---Controls---\n";
        finalString += "play : Starts the game.\n";
        finalString += "quit : Quits the game.\n";
        finalString += "balance : Gets your current Balance.\n";
        finalString += "bet : puts you in the betting menu then you enter your bet.\n";
        finalString += "hit : Adds a card to your hand\n";
        finalString += "hold : Keeps your hand and ends the current game";
        return finalString;
    }
    public String checkCards(Hand Hand,String delimiter){
        String output ="";
        String[] hand = Hand.getHand();
        for (int i =0; i<Hand.size(); i++){
            output += hand[i] + delimiter;
        }
        return output;
    }
    public String checkCards(){
        return checkCards(Player," ");
    }
    public String start(){
        String output;
        if (Player.empty()){
            for (int i = 0; i<=1; i++){
                this.hit(Player);
                this.hit(House);
            }
            output = "House: ??, " + House.getHand()[1] + "\n";
            output += "You: " + checkCards(Player,", ");
            return output;
        }
        return "The Game was Already Started";
    }
    public String quit(){
        return "Quiting";
    }
    public String getBalance(){
        return "Your Balance is $" + money;
    }
    public String setBet(int amount){
        if (Player.empty()){
            if (amount > this.money){
                return "Insufficient Funds";
            }else{
                this.money -= amount;
                this.bet += amount;
                return "Bet Has Been Made.";
            }
        }
        return "Game Already Started";
    }
    public String hit(Hand Hand){
        String output = cards.drawCard();
        Hand.addCard(output);
        return output;
    }
    public String hit(){
        if(Player.empty()){
            return "Game Not Started";
        }
        String temp = this.hit(Player);
        if (hasBusted()){
            temp += "\n" + this.hold();
        }
        return temp;
    }
    private void houseAI(){
        int handValue = House.getHandValue();
        if (House.hasAce() && handValue == 17){
            this.hit(House);
        }else if (handValue < 16){
            this.hit(House);
        }
    }
    public boolean hasBusted(Hand Hand){
        if (!Hand.empty()){
            return Hand.getHandValue() > 21;
        }
        return false;
    }
    public boolean hasBusted(){
        return hasBusted(Player);
    }
    public String hold(){
        String output;
        if (Player.empty()){
            return "Game Not Started";
        }
        this.houseAI();
        output = "House " + checkCards(House, ", ") +"\n";
        if ((hasBusted(Player) && hasBusted(House)) 
        || Player.getHandValue() == House.getHandValue()){
            output += "tie";
            this.money += this.bet;
        }else if (hasBusted(Player) 
        || Player.getHandValue() < House.getHandValue()){
            output += "lost";
        }else{
            output += "win";
            this.money += this.bet*2;
        }
        House.clear();
        Player.clear();
        this.bet = 0;
        return output;
    }
}