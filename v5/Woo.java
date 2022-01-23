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
    //If jailed, do nothing
    if(tok.getJailed() != 0){
      tok.setJailed(tok.getJailed() + 1);
      System.out.println("Player " + tok.getNumber() + " is currently jailed! Do nothing this turn.");
      System.out.println("Player " + tok.getNumber() + " is jailed for " + (4 - tok.getJailed()) + " more turns.");
      if(tok.getJailed() == 4){ //if 3 turns are over
        tok.setJailed(0);
        System.out.println("Player " + tok.getNumber() + " is now out of jail!");
      }
    }
    else{
      System.out.println("Current Balance of player " + tok.getNumber() + ": $" + tok.getBalance());
      System.out.println("Rolling the roll of player " + tok.getNumber() + ": ");
      int spaces = roll();
      System.out.println("Player " + tok.getNumber() + " rolled a " + spaces);
      //advance the spaces
      tok.advance(spaces);
      //current space, for future reference
      int cur = tok.getPos();
      int response = 0;

      //if the current tile is a special tile (go, just visiting, free parking, go to jail)
      if(cur == 0){
        System.out.println("Player " + tok.getNumber() + " landed on Go!");
      }
      if(cur == 10){
        System.out.println("Player " + tok.getNumber() + " landed on Just Visiting! Nothing happens.");
      }
      if(cur == 20){
        System.out.println("Player " + tok.getNumber() + " landed on Free Parking! Nothing happens.");
      }
      if(cur == 30){
        System.out.println("Player " + tok.getNumber() + " landed on Go To Jail. They will be unable to take the next 3 turns.");
        tok.setJailed(1);
      }


      //if the current tile is a property
      if(board[tok.getPos()] instanceof Property){
        Property currentProperty = (Property)board[tok.getPos()];
        //If the Token lands on a property that is unowned
        if(currentProperty.getOwned() == null){
          System.out.println("Player " + tok.getNumber() + " landed on tile " + currentProperty.getPos() + ", " + currentProperty.getName() + ".");
          if(currentProperty.getCost() < tok.getBalance() / .9){
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
          System.out.println("Player " + tok.getNumber() + " landed on " + currentProperty.getName() + ", a property that player " + tok.getNumber() + " owns. There is no need to do anything this turn.");
        }
        //If the Token lands on an owned property that is NOT theirs
        else{
          System.out.println("Player " + tok.getNumber() + " landed on " + currentProperty.getName() + ", owned by player " + currentProperty.getOwned().getNumber() + "." );
          System.out.println("Player " + tok.getNumber() + " must pay player " + currentProperty.getOwned().getNumber() + " $" + currentProperty.getRent() * 10 + ".");
          if(tok.getBalance() > currentProperty.getRent() * 10){
            tok.pay(currentProperty.getOwned(), currentProperty.getRent() * 10);
            System.out.println("Current Balance of player " + tok.getNumber() + ": $" + tok.getBalance());
          }
          else{
            tok.pay(currentProperty.getOwned(), currentProperty.getRent() * 10);
            System.out.println("The rent had to be paid by the bank, as player " + tok.getNumber() + " does not have enough.");
            System.out.println("Player " + tok.getNumber() + " has gone bankrupt.");
            for(int i = 0 ; i < tok.getProperties() ; i += 1){
              tok.getOwnedArray()[i].resetOwned();
            }
            players -= 1;
            tok.changeBankrupt(true);
          }
        }

      }
      //If land on tax, hardcoded values?
      //4, 12, 28, 38
      else if(tok.getPos() == 4 || tok.getPos() == 38){
        //I should probably hardcode this

        //Income Tax, $400
        if(tok.getPos() == 4){
          if(tok.getBalance() > 400){
            tok.subtractBalance(400);
            System.out.println("Player " + tok.getNumber() + " landed on Income Tax, player " + tok.getNumber() + " must pay $400!");
            System.out.println("Current Balance of player " + tok.getNumber() + ": $" + tok.getBalance());
          }
          else{
            System.out.println("Player " + tok.getNumber() + " didn't have enough money to pay the tax!");
            System.out.println("Player " + tok.getNumber() + " have gone bankrupt.");
            for(int i = 0 ; i < tok.getProperties() ; i += 1){
              tok.getOwnedArray()[i].resetOwned();
            }
            players -= 1;
            tok.changeBankrupt(true);
          }
        }

        //Luxury Tax, $200
        if(tok.getPos() == 38){
          if(tok.getBalance() > 200){
            tok.subtractBalance(200);
            System.out.println("Player " + tok.getNumber() + " landed on Luxury Tax, player " + tok.getNumber() + " must pay $200!");
            System.out.println("Current Balance of player " + tok.getNumber() + ": $" + tok.getBalance());
          }
          else{
            System.out.println("Player " + tok.getNumber() + " didn't have enough money to pay the tax!");
            System.out.println("Player " + tok.getNumber() + " have gone bankrupt.");
            for(int i = 0 ; i < tok.getProperties() ; i += 1){
              tok.getOwnedArray()[i].resetOwned();
            }
            players -= 1;
            tok.changeBankrupt(true);
          }
        }

      }

      //If land on Chance
      else if(board[tok.getPos()] instanceof Chance){
        System.out.println("Player " + tok.getNumber() + " landed on a Chance spot!");
        Chance currentChance = (Chance)(board[tok.getPos()]);
        currentChance.draw(tok, board, players);
      }

      //If land on Community Chest
      else if(board[tok.getPos()] instanceof Chest){
        System.out.println("Player " + tok.getNumber() + " landed on a Community Chest spot!");
        Chest currentChest = (Chest)(board[tok.getPos()]);
        currentChest.draw(tok);
      }

    }

    System.out.println("----------------------------------------\n");
  }

  //plays a turn for [tok]. Returns bankruptcy state.
  public void playTurn(Token tok){
    //If jailed, do nothing
    if(tok.getJailed() != 0){
      tok.setJailed(tok.getJailed() + 1);
      System.out.println("You are currently jailed! Do nothing this turn.");
      System.out.println("You are jailed for " + (4 - tok.getJailed()) + " more turns.");
      if(tok.getJailed() == 4){ //If 3 turns are over
        tok.setJailed(0);
        System.out.println("You are now out of jail!");
      }
    }
    else{
      System.out.println("! YOUR TURN !");
      System.out.println("Current Balance: $" + tok.getBalance());
      System.out.println("Rolling your roll: ");
      int spaces = roll();
      System.out.println("You rolled a " + spaces);
      //advance the spaces
      tok.advance(spaces);
      //current space, for future reference
      int cur = tok.getPos();
      String response = "";

      //if the current tile is a special tile (go, just visiting, free parking, go to jail)
      if(cur == 0){
        System.out.println("You landed on Go!");
      }
      if(cur == 10){
        System.out.println("You landed on Just Visiting! Nothing happens.");
      }
      if(cur == 20){
        System.out.println("You landed on Free Parking! Nothing happens.");
      }
      if(cur == 30){
        System.out.println("You landed on Go To Jail. You will be unable to take the next 3 turns.");
        tok.setJailed(1);
      }

      //if the current tile is a property
      if(board[tok.getPos()] instanceof Property){
        Property currentProperty = (Property)board[tok.getPos()];

        //If the Token lands on a property that is unowned
        if(currentProperty.getOwned() == null){
          //turn only advances if property is bought or abandoned
          while(response.equals("yes") == false && response.equals("no") == false){
            try{
              System.out.println("You landed on tile " + currentProperty.getPos() + ", " + currentProperty.getName() + ".");
              System.out.println("This property is unowned, do you wish to buy it for $" + currentProperty.getCost() + "?");
              System.out.println("\"yes\" for yes");
              System.out.println("\"no\" for no");
              System.out.println("\"info\" for info about the property");
              System.out.println("\"owned\" for your deeds");
              response = in.readLine();
            }
            catch(IOException e){}
              //More info, code later
              if(response.equals("info") == true){
                System.out.println("\n---------------------------------------\nINFO:");
                System.out.println("Name: " + currentProperty.getName());
                System.out.println("Cost: " + currentProperty.getCost());
                System.out.println("Rent: " + currentProperty.getRent() * 10);
                System.out.println("Position: " + currentProperty.getPos());
                System.out.println();
              }

              //More info about others, code later
              else if(response.equals("owned") == true){
                System.out.println("\n---------------------------------------\nTitle Deeds owned by you: ");
                System.out.println("Player 1 (you):");
                if(tok.getProperties() > 0){
                  System.out.println(tok.getOwned() + "\n");
                }
                else{
                  System.out.println("You have no properties\n");
                }
              }

              //Buy
              else if(response.equals("yes") == true){
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
              else if(response.equals("no") == true){
                System.out.println("You pass on the property");
              }

              //???
              else{
                System.out.println("Please enter a valid response");
              }
            }
          }
          //If the Token lands on an owned property that is theirs
          else if(currentProperty.getOwned().getNumber() == tok.getNumber()){
            System.out.println("You landed on " + currentProperty.getName() + ", a property that you own. There is no need to do anything this turn.");
          }
          //If the Token lands on an owned property that is NOT theirs
          else{
            System.out.println("You landed on " + currentProperty.getName() + ", owned by player " + currentProperty.getOwned().getNumber() + "." );
            System.out.println("You must pay player " + currentProperty.getOwned().getNumber() + " $" + currentProperty.getRent() * 10 + ".");

            if(tok.getBalance() > currentProperty.getRent() * 10){
              tok.pay(currentProperty.getOwned(), currentProperty.getRent() * 10);
              System.out.println("Current Balance: $" + tok.getBalance());
            }
            else{
              tok.pay(currentProperty.getOwned(), currentProperty.getRent() * 10);
              System.out.println("The rent had to be paid by the bank, as you have no money left.");
              System.out.println("You have gone bankrupt.");
              players -= 1;
              tok.changeBankrupt(true);
            }
          }

        }

        //If land on tax, hardcoded values?
        //4, 12, 28, 38
        else if(tok.getPos() == 4 || tok.getPos() == 38){
          //I should probably hardcode this

          //Income Tax, $400
          if(tok.getPos() == 4){
            if(tok.getBalance() > 400){
              tok.subtractBalance(400);
              System.out.println("You landed on Income Tax, you must pay $400!");
              System.out.println("Current Balance: $" + tok.getBalance());
            }
            else{
              System.out.println("You didn't have enough money to pay the tax!");
              System.out.println("You have gone bankrupt.");
              players -= 1;
              tok.changeBankrupt(true);
            }
          }

          //Luxury Tax, $200
          if(tok.getPos() == 38){
            if(tok.getBalance() > 200){
              tok.subtractBalance(200);
              System.out.println("You landed on Luxury Tax, you must pay $200!");
              System.out.println("Current Balance: $" + tok.getBalance());
            }
            else{
              System.out.println("You didn't have enough money to pay the tax!");
              System.out.println("You have gone bankrupt.");
              players -= 1;
              tok.changeBankrupt(true);
            }
          }

        }

        //If land on Chance
        else if(board[tok.getPos()] instanceof Chance){
          System.out.println("You landed on a Chance spot!");
          Chance currentChance = (Chance)(board[tok.getPos()]);
          currentChance.draw(tok, board, players);
        }

        //If land on Community Chest
        else if(board[tok.getPos()] instanceof Chest){
          System.out.println("You landed on a Community Chest spot!");
          Chest currentChest = (Chest)(board[tok.getPos()]);
          currentChest.draw(tok);
        }

        //Failsafe check to make sure the player still has positive balance, ie. they get a negative balance chance card
        if(tok.getBalance() < 0){
          tok.changeBankrupt(true);
        }
      }
      //Make sure the player ends the turn manually
      String endTurn = "";
      while(endTurn.equals("end") == false){
        try{
          System.out.println("Type \"end\" to end the turn");
          endTurn = in.readLine();
        }
        catch(IOException e){}
        }

        System.out.println("----------------------------------------\n");

      }

      public void newBoard(Tile[] newBoard){
        //Create the board with the correct tiles on it
        newBoard[0] = new Tile(0);
        newBoard[1] = new MediterraneanAvenue(1);
        newBoard[2] = new Chest(2); //TODO
        newBoard[3] = new BalticAvenue(3);
        newBoard[4] = new Tile(4); //HARDCODED TAX
        newBoard[5] = new ReadingRailroad(5); //TODO
        newBoard[6] = new OrientalAvenue(6);
        newBoard[7] = new Chance(7);  //TODO
        newBoard[8] = new VermontAvenue(8);
        newBoard[9] = new ConnecticutAvenue(9);
        newBoard[10] = new Tile(10);  //Just visiting
        newBoard[11] = new StCharlesPlace(11);
        newBoard[12] = new ElectricCompany(12);
        newBoard[13] = new StatesAvenue(13);
        newBoard[14] = new VirginiaAvenue(14);
        newBoard[15] = new PennsylvaniaRailroad(15);
        newBoard[16] = new StJamesPlace(16);
        newBoard[17] = new Chest(17);
        newBoard[18] = new TennesseeAvenue(18);
        newBoard[19] = new NewYorkAvenue(19);
        newBoard[20] = new Tile(20);  //Free parking, not implemented, won't implement
        newBoard[21] = new KentuckyAvenue(21);
        newBoard[22] = new Chance(22);
        newBoard[23] = new IndianaAvenue(23);
        newBoard[24] = new IllinoisAvenue(24);
        newBoard[25] = new BAndORailroad(25);
        newBoard[26] = new AtlanticAvenue(26);
        newBoard[27] = new VentnorAvenue(27);
        newBoard[28] = new WaterWorks(28);
        newBoard[29] = new MarvinGardens(29);
        newBoard[30] = new Tile(30);  //Go to jail, maybe implment
        newBoard[31] = new PacificAvenue(31);
        newBoard[32] = new NorthCarolinaAvenue(32);
        newBoard[33] = new Chest(33);
        newBoard[34] = new PennsylvaniaAvenue(34);
        newBoard[35] = new ShortLine(35);
        newBoard[36] = new Chance(36);
        newBoard[37] = new ParkPlace(37);
        newBoard[38] = new Tile(38); //HARDCODED TAX
        newBoard[39] = new Boardwalk(39);
        //pass go



      }



      public void newGame(){
        //create the board (WIP!!!)
        newBoard(this.board);

        this.p1 = new Token(1);
        this.p2 = new Token(2);
        this.p3 = new Token(3);
        this.p4 = new Token(4);
        System.out.println("You are player 1.");
        this.p1.changePos(0);
        this.p2.changePos(0);
        this.p3.changePos(0);
        this.p4.changePos(0);
      }

      //rolls 2 die
      public static int roll(){
        return (((int)(Math.random() * 6) + 1) + (int)(Math.random() * 6) + 1);
      }

      public static void main(String[] StandardOil){
        Woo game = new Woo();
        int turnCounter = 0;

        //Game lasts as long as there is still 2 or more players
        //However the game will end if the player (you) goes bankrupt because that would be really boring.
        while(game.players > 1 && game.p1.getBankrupt() == false){
          System.out.println("TURN " + turnCounter + "\n=======================================\n");
          //player 1 turn
          if(!game.p1.getBankrupt()){
            game.playTurn(game.p1);
          }
          //player 2 turn
          if(!game.p2.getBankrupt()){
            game.playTurnAI(game.p2);
          }
          //player 3 turn
          if(!game.p3.getBankrupt()){
            game.playTurnAI(game.p3);
          }
          //player 4 turn
          if(!game.p4.getBankrupt()){
            game.playTurnAI(game.p4);
          }

          turnCounter += 1;
        }


      }


    }
