package BlackJackGame;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack{
    public static int getValue(String card){
    char temp = card.charAt(1);
    char whitespace = " ".charAt(0);
        if (temp == whitespace){
            temp = card.charAt(0);
            if (Character.isDigit(temp)){
                return Character.getNumericValue(temp); 
            }else{
                if (card.charAt(0) == "A".charAt(0)){
                    return 11;
                }else{
                    return 10;
                }
            }
        }else{
            int a = 10 * Character.getNumericValue(card.charAt(0));
            int b = Character.getNumericValue(card.charAt(1));
            return a+b;
        }
    }
    public static int getHandValue(ArrayList<String> hand){
        int total = 0;
        int temp;
        int [] ace = new int[16];
        for (int i =0; i < hand.size(); i++){
            temp = getValue(hand.get(i));
            if (temp == 11){
                ace[i] = 11;
            }else{
                total += getValue(hand.get(i));
            }
        }
        for(int i = 0; i < ace.length; i++){
            temp = ace[i];
            if (temp == 11){
                total += temp;
                if (total > 21){
                    total -= 11;
                    total +=1;
                }
            }
        }
        return total;
    }
    public static void gameLoop(Deck Cards){
        
        ArrayList<String> player = new ArrayList<String>();
        ArrayList<String> house = new ArrayList<String>();
        int money = 2000;
        int bet = 0;
        while(true){
            String input = System.console().readLine();
            
            
            if (input.equals("help")){
                System.out.println("---:BlackJack:---");
                System.out.println("Play To 21 whos ever closes wins");
                System.out.println("Controls: play-Starts game. quit-Quits the game. hit-adds card. hold-keeps cards and checks opionets.");
            }
            if (input.equals("play")){
                if (player.size() == 0){
                    player.add(Cards.drawCard());
                    player.add(Cards.drawCard());
                    house.add(Cards.drawCard());
                    house.add(Cards.drawCard());
                    System.out.println("Your Balance: " + money);
                    System.out.println("House: ?? " + house.get(1));
                    System.out.println("You: " + player.get(0) + " " + player.get(1));
                    
                }else{
                    System.out.println("Game Already Started");
                }
            }
            if (input.equals("quit") ){
                System.out.println("Quiting Game");
                break;
            }
            if (input.equals("balance")){
                System.out.println("Your Balance: " + money);
            }
            if (input.equals("bet")){
                if(player.size() ==0){
                    String Samount = System.console().readLine();
                    int amount = Integer.parseInt(Samount);
                    if (amount > money){
                        System.out.println("Insufficient Funds");
                    }else{
                        System.out.println("Bet of "+ amount + " has been placed.");
                        bet += amount;
                        money -= amount;
                    }
                }else{
                    System.out.println("You can only bet before a game.");
                }
            }
            if (input.equals("hit")){
                if (player.size() == 0){
                    System.out.println("Type play to start the game");
                
                }else{
                    String temp = Cards.drawCard();
                    player.add(temp);
                    System.out.println(temp);
                    
                }
            }
            if (input.equals("hold")){
                if (player.size() == 0){
                    System.out.println("Type play to start the game");
                
                }else{
                    int botHand = getHandValue(house);
                    while (botHand < 16){
                        house.add(Cards.drawCard());
                        botHand = getHandValue(house);
                    }
                    System.out.print("House: ");
                    for (int i = 0; i < house.size(); i++){
                        System.out.print(house.get(i) + " ");
                    }
                    System.out.print("\n");

                    int playerHand = getHandValue(player);
                    
                    if (playerHand> 21){
                        System.out.println("You Busted");
                    }else if(botHand > 21){
                        System.out.println("You won");
                        money += bet *2;
                        bet =0;
                    }else if (playerHand < botHand){
                        System.out.println("Your hand was worse");
                    }else{
                        System.out.println("You won");
                        money += bet *2;
                        bet =0;
                    }
                    house.clear();
                    player.clear();
                }
                int playerHand = getHandValue(player);
                    
                if (playerHand> 21){
                    System.out.println("You Busted");
                    house.clear();
                    player.clear();
                }
            }
        }
        
    }
    public static void main(String[] args){
        Deck Cards = new Deck();
        gameLoop(Cards);
    }
}
