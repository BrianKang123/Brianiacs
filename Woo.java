import java.io.*;
import java.util.*;

public class Woo{
  private Tile[] board;
  private Token p1, p2, p3, p4;
  private InputStreamReader isr;
  private BufferedReader in;
  private int players;


  public Woo(){
    board = new Tile[40];
    players = 4;
    isr = new InputStreamReader(System.in);
    in = new BufferedReader(isr);
    newGame();
  }

  //plays a turn for an AI. The AI will currently buy a property if it owns more than 10x the cost of the property.
  public void playTurnAI(Token tok){
    System.out.println("Current Balance of player " + tok.getNumber() + ": $" + tok.getBalance());
    System.out.println("Rolling the roll of player " + tok.getNumber() + ": ");
    int spaces = roll();
    System.out.println("Player " + tok.getNumber() + " rolled a " + spaces);
    //advance the spaces
    tok.advance(spaces);
    //current space, for future reference
    int cur = tok.getPos();
    int response = 0;
    //if the current tile is a property
    if(board[tok.getPos()] instanceof Property){
      Property currentProperty = (Property)board[tok.getPos()];

      //If the Token lands on a property that is unowned
      if(currentProperty.getOwned() == null){
        System.out.println("Player " + tok.getNumber() + " landed on tile " + currentProperty.getPos() + ", " + currentProperty.getName() + ".");
        if(currentProperty.getCost() < tok.getBalance() / 10){
          currentProperty.buy(tok);
          System.out.println("Player " + tok.getNumber() + " just bought " + currentProperty.getName() + " for $" + currentProperty.getCost() + ".");
          System.out.println("Current Balance of player " + tok.getNumber() + ": $" + tok.getBalance());
        }
        //ifelse(){} //Case where AI has less tolerance for a set
        else{
          System.out.println("Player " + tok.getNumber() + " passes on " + currentProperty.getName() + ".");
        }

      }
      //If the Token lands on an owned property that is theirs
      else if(currentProperty.getOwned().getNumber() == tok.getNumber()){
        System.out.println("Player " + tok.getNumber() + " landed on a property that you own. There is no need to do anything this turn.");
      }
      //If the Token lands on an owned property that is NOT theirs
      else{
        System.out.println("Player " + tok.getNumber() + " landed on " + currentProperty.getName() + ", owned by player " + currentProperty.getOwned().getNumber() + "." );
        System.out.println("Player " + tok.getNumber() + " must pay player " + currentProperty.getOwned().getNumber() + " $" + currentProperty.getRent() + ".");
        if(tok.getBalance() > currentProperty.getRent()){
          tok.pay(currentProperty.getOwned(), currentProperty.getRent());
          System.out.println("Current Balance of player " + tok.getNumber() + ": $" + tok.getBalance());
        }
        else{
          tok.pay(currentProperty.getOwned(), currentProperty.getRent());
          System.out.println("The rent had to be paid by the bank, as player " + tok.getNumber() + " does not have enough.");
          System.out.println("Player " + tok.getNumber() + " has gone bankrupt.");
          players -= 1;
        }
      }

    }
    System.out.println("----------------------------------------\n");
  }

  //plays a turn for [tok]. Returns bankruptcy state.
  public void playTurn(Token tok){
    System.out.println("! YOUR TURN !");
    System.out.println("Current Balance: $" + tok.getBalance());
    System.out.println("Rolling your roll: ");
    int spaces = roll();
    System.out.println("You rolled a " + spaces);
    //advance the spaces
    tok.advance(spaces);
    //current space, for future reference
    int cur = tok.getPos();
    int response = 0;
    //if the current tile is a property
    if(board[tok.getPos()] instanceof Property){
      Property currentProperty = (Property)board[tok.getPos()];

      //If the Token lands on a property that is unowned
      if(currentProperty.getOwned() == null){
        try{
          System.out.println("You landed on tile " + currentProperty.getPos() + ", " + currentProperty.getName() + ".");
          System.out.println("This property is unowned, do you wish to buy it for $" + currentProperty.getCost() + "?");
          System.out.println("1 for yes");
          System.out.println("2 for no");
          System.out.println("3 for info about the property");
          System.out.println("4 for deed titleships of other players");
          response = Integer.parseInt(in.readLine());
        }
        catch(IOException e){}
          //Buy
          if(response == 1){
            //Has enough money
            if(currentProperty.getCost() < tok.getBalance()){
              currentProperty.buy(tok);
              System.out.println("You bought " + currentProperty.getName() + "!");
              System.out.println("Current Balance: $" + tok.getBalance());
            }
            //Does not have enough money
            else{
              System.out.println("You do not have enough!");
            }
          }
          //Do not buy
          else if(response == 2){
            System.out.println("You pass on the property");
          }
          //More info, code later
          else if(response == 3){
            System.out.println("WORK IN PROGRESS!");
          }
          //More info about others, code later
          else if(response == 4){
            System.out.println("WORK IN PROGRESS!");
          }
          //???
          else{
            System.out.println("Please enter a valid response (WORK IN PROGRESS)");
          }

      }
      //If the Token lands on an owned property that is theirs
      else if(currentProperty.getOwned().getNumber() == tok.getNumber()){
        System.out.println("You landed on a property that they own. There is no need to do anything this turn.");
      }
      //If the Token lands on an owned property that is NOT theirs
      else{
        System.out.println("You landed on " + currentProperty.getName() + ", owned by player " + currentProperty.getOwned().getNumber() + "." );
        System.out.println("You must pay player " + currentProperty.getOwned().getNumber() + " $" + currentProperty.getRent() + ".");
        if(tok.getBalance() > currentProperty.getRent()){
          tok.pay(currentProperty.getOwned(), currentProperty.getRent());
          System.out.println("Current Balance: $" + tok.getBalance());
        }
        else{
          tok.pay(currentProperty.getOwned(), currentProperty.getRent());
          System.out.println("The rent had to be paid by the bank, as you have no money left.");
          System.out.println("You have gone bankrupt.");
          players -= 1;
        }
      }

    }
    System.out.println("----------------------------------------\n");
  }

  public void newBoard(Tile[] bored){
    for(int i = 0 ; i < 40 ; i += 1){05b
      bored[i] = new MediterraneanAvenue(i);
    }
  }

  public void newGame(){
    //create the board (WIP!!!)
    newBoard(this.board);
    //randomize turn order

    //probably unneccesary complexity
    //Maybe implement later?
    /*
    int playerTurn = (int)(Math.random()*4) + 1;
    System.out.println("Your turn is number " + playerTurn + "!");

    if(playerTurn == 1){
      p1 = new Player(1);
      p2 = new AI(2);
      p3 = new AI(3);
      p4 = new AI(4);
    }
    if(playerTurn == 2){
      p1 = new AI(1);
      p2 = new Player(2);
      p3 = new AI(3);
      p4 = new AI(4);
    }
    if(playerTurn == 3){
      p1 = new AI(1);
      p2 = new AI(2);
      p3 = new Player(3);
      p4 = new AI(4);
    }
    else{
      p1 = new AI(1);
      p2 = new AI(2);
      p3 = new AI(3);
      p4 = new Player(4);
    }
    */
    this.p1 = new Token(1);
    this.p2 = new Token(2);
    this.p3 = new Token(3);
    thiatom://teletype/portal/13cd23c8-2958-4946-9b40-354f4618305bs.p4 = new Token(4);
    System.out.println("You are player 1.");
    this.p1.changePos(0);
    this.p2.changePos(0);
    this.p3.changePos(0);
    this.p4.changePos(0);
  }

  //rolls 2 die
  public static int roll(){
      return (int)(Math.random() * 11) + 2;
  }

  public static void main(String[] StandardOil){
    Woo game = new Woo();
    int turnCounter = 0;

    //Game lasts as long as there is still 2 or more players
    //However the game will end if the player (you) goes bankrupt because that would be really boring.
    while(game.players > 1 || game.p1.getBankrupt() == true){
      System.out.println("TURN " + turnCounter + "\n=======================================\n");
      //player 1 turn
      game.playTurn(game.p1);
      //player 2 turn
      if(!game.p2.getBankrupt()){
        game.playTurnAI(game.p2);
      }
      //player 3 turn
      if(!game.p3.getBankrupt()){
        game.playTurnAI(game.p3);
      }
      //player 4 turn
      if(!game.p2.getBankrupt()){
        game.playTurnAI(game.p2);
      }

      turnCounter += 1;
    }


  }

}
