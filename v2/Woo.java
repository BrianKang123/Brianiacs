import java.io.*;
import java.util.*;

public class Woo{
  private Tile[] board;
  private Token p1, p2, p3, p4;
  private InputStreamReader isr;
  private BufferedReader in;


  public Woo(){
    board = new Tile[40];
    newGame();
  }

  //plays a turn for [tok]. Returns bankruptcy state.
  public void playTurn(Token tok){
    System.out.println("Rolling your roll: ");
    int spaces = roll();
    System.out.println("Player " + tok.getPlayer() + " rolled a " + spaces);
    //advance the spaces
    tok.advance(spaces);
    //current space, for future reference
    int cur = tok.getPos();
    int response = 0;
    //if the current tile is a property
    if(board[tok.getPos()] instanceof Property){
      Property currentProperty = (Property)board[tok.getPos()];

      //If the Token lands on a property that is unowned
      if(currentProperty.getOwned() == 0){
        try{
          System.out.println("You landed on tile " + currentProperty.getPos() + ", " + currentProperty.getName() + ".");
          System.out.println("This property is unowned, do you wish to buy it for $" + currentProperty.getCost() + "?");
          System.out.println("1 for yes, 2 for no, 3 for further details about the property");
          response = Integer.parseInt(in.readLine());
        }
        catch(IOException e){}
          //Buy
          if(response == 1){
            //Has enough money
            if(currentProperty.getCost() < tok.getBalance()){
              currentProperty.buy(tok);
            }
            //Does not have enough money
            else{
              System.out.println("You do not have enough!");
            }
          }
          //Do not buy
          if(response == 2){

          }
          //More info, code later
          if(response == 3){
            System.out.println("WORK IN PROGRESS!");
          }
          //???
          else{
            System.out.println("Please enter a valid response (WORK IN PROGRESS)");
          }

      }
      //If the Token lands on an owned property that is theirs
      if(currentProperty.getOwned() == tok.getPlayer()){

      }
      //If the Token lands on an owned property that is NOT theirs
      else{

      }

    }
  }

  public void newBoard(Tile[] bored){
    for(int i = 0 ; i < 40 ; i += 1){
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
    this.p1 = new Player(1);
    this.p2 = new AI(2);
    this.p3 = new AI(3);
    this.p4 = new AI(4);
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
    int players = 4;

    //Game lasts as long as there is still 2 or more players
    while(players > 1){
      //player 1 turn
      game.playTurn(game.p1);
      //player 2 turn

      //player 3 turn

      //player 4 turn
      break; //diag
    }


  }

}
