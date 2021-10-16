package BlackJackGame;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack{  
    public static void main(String[] args){
      
        GameLoop game = new GameLoop();
        boolean quit = false;
        while (!quit){
            String input = System.console().readLine();
            String output ="";
            
                switch (input){
                    case "help":
                        System.out.println(game.helpCommand());
                        break;
                    case "play":
                        System.out.println(game.start());
                        break;
                    case "hit":
                        System.out.println(game.hit());
                        break;
                    case "hold":
                        System.out.println(game.hold());
                        break;
                    case "balance":
                        System.out.println(game.getBalance());
                        break;
                    case "checkCards":
                        System.out.println(game.checkCards());
                        break;
                    case "quit":
                        System.out.println(game.quit());
                        quit = true;
                        break;
                    case "bet":
                        System.out.print("Enter Bet Amount $");
                        String betAmount = System.console().readLine();
                        System.out.println(game.setBet(Integer.parseInt(betAmount)));
                        break;
                    default:
                        break;
                }
            }  
    }
}
