import java.util.ArrayList;
public class Chance extends Tile{
  public Chance(int pos){
    super(pos);
  }
  //We will only code the cards that would work with our game

  //Draws a random effect card
  public void draw(Token tok, Tile[] board, int players){
    //Arraylist to store the unused cards
    ArrayList<Integer> cards = new ArrayList();
    //Populate the arraylist with all 16 cards
    for(int i = 0 ; i < 14 ; i += 1){
      cards.add(i);
    }

    //random card draw
    int card = (int)(Math.random() * cards.size());

    System.out.println("Drawing...");
    //card effects
    if(card == 0){
      System.out.println("Advance to Boardwalk!");
      tok.changePos(39);
      cards.remove(card);
    }
    else if(card == 1){
      System.out.println("Advance to go! Collect $200.");
      tok.addBalance(200);
      System.out.println("Current Balance: $" + tok.getBalance());
      tok.changePos(0);
      cards.remove(card);
    }
    else if(card == 2){
      System.out.println("Advance to Illinois Avenue. If you pass Go, collect $200");
      if(tok.getPos() > 24){
        tok.addBalance(200);
        System.out.println("Current Balance: $" + tok.getBalance());
      }
      tok.changePos(24);
      cards.remove(card);
    }
    else if(card == 3){
      System.out.println("Advance to St. Charles Place. If you pass Go, collect $200");
      if(tok.getPos() > 11){
        tok.addBalance(200);
        System.out.println("Current Balance: $" + tok.getBalance());
      }
      cards.remove(card);
    }
    else if(card == 4){
      System.out.println("Advance to the nearest Railroad.");
      if(tok.getPos() < 5){
        tok.changePos(5);
        Property currentProperty = (Property)board[tok.getPos()];
        System.out.println("Advanced to Reading Railroad (Tile 5).");
        if(currentProperty != null && currentProperty.getOwned().getNumber() != tok.getNumber()){
          System.out.println("Reading Railroad is owned by player " + currentProperty.getOwned().getNumber() + ". Paying double the rent, or $500.");
          if(tok.getBalance() > 500){
            tok.pay(currentProperty.getOwned(), 500);
            System.out.println("Current Balance: $" + tok.getBalance());
          }
          else{
            tok.pay(currentProperty.getOwned(), 500);
            System.out.println("The rent had to be paid by the bank, as you have no money left.");
            System.out.println("You have gone bankrupt.");
            for(int i = 0 ; i < tok.getProperties() ; i += 1){
              tok.getOwnedArray()[i].resetOwned();
            }
            players -= 1;
            tok.changeBankrupt(true);
          }
        }
      }
      else if(tok.getPos() < 15){
        System.out.println("Advanced to Pennsylvania Railroad (Tile 15).");
        tok.changePos(15);
        Property currentProperty = (Property)board[tok.getPos()];
        if(currentProperty.getOwned() != null && currentProperty.getOwned().getNumber() != tok.getNumber()){
          System.out.println("Pennsylvania Railroad is owned by player " + currentProperty.getOwned().getNumber() + ". Paying double the rent, or $500.");
          if(tok.getBalance() > 500){
            tok.pay(currentProperty.getOwned(), 500);
            System.out.println("Current Balance: $" + tok.getBalance());
          }
          else{
            tok.pay(currentProperty.getOwned(), 500);
            System.out.println("The rent had to be paid by the bank, as you have no money left.");
            System.out.println("You have gone bankrupt.");
            for(int i = 0 ; i < tok.getProperties() ; i += 1){
              tok.getOwnedArray()[i].resetOwned();
            }
            players -= 1;
            tok.changeBankrupt(true);
          }
        }
      }
      else if(tok.getPos() < 25){
        System.out.println("Advanced B&O Railroad (Tile 25).");
        tok.changePos(25);
        Property currentProperty = (Property)board[tok.getPos()];
        if(currentProperty.getOwned() != null && currentProperty.getOwned().getNumber() != tok.getNumber()){
          System.out.println("B&O Railroad is owned by player " + currentProperty.getOwned().getNumber() + ". Paying double the rent, or $500.");
          if(tok.getBalance() > 500){
            tok.pay(currentProperty.getOwned(), 500);
            System.out.println("Current Balance: $" + tok.getBalance());
          }
          else{
            tok.pay(currentProperty.getOwned(), 500);
            System.out.println("The rent had to be paid by the bank, as you have no money left.");
            System.out.println("You have gone bankrupt.");
            for(int i = 0 ; i < tok.getProperties() ; i += 1){
              tok.getOwnedArray()[i].resetOwned();
            }
            players -= 1;
            tok.changeBankrupt(true);
          }
        }
      }
      else if(tok.getPos() < 35){
        System.out.println("Advanced to Short Railroad (Tile 35).");
        tok.changePos(35);
        Property currentProperty = (Property)board[tok.getPos()];
        if(currentProperty.getOwned() != null && currentProperty.getOwned().getNumber() != tok.getNumber()){
          System.out.println("Short Railroad is owned by player " + currentProperty.getOwned().getNumber() + ". Paying double the rent, or $500.");
          if(tok.getBalance() > 500){
            tok.pay(currentProperty.getOwned(), 500);
            System.out.println("Current Balance: $" + tok.getBalance());
          }
          else{
            tok.pay(currentProperty.getOwned(), 500);
            System.out.println("The rent had to be paid by the bank, as you have no money left.");
            System.out.println("You have gone bankrupt.");
            for(int i = 0 ; i < tok.getProperties() ; i += 1){
              tok.getOwnedArray()[i].resetOwned();
            }
            players -= 1;
            tok.changeBankrupt(true);
          }
        }
      }
      else{
        tok.changePos(5);
        Property currentProperty = (Property)board[tok.getPos()];
        System.out.println("Advanced to Reading Railroad (Tile 5), and passed Go.");
        tok.addBalance(200);
        System.out.println("Current Balance: $" + tok.getBalance());
        if(currentProperty.getOwned() != null && currentProperty.getOwned().getNumber() != tok.getNumber()){
          System.out.println("Reading Railroad is owned by player " + currentProperty.getOwned().getNumber() + ". Paying double the rent, or $500.");
          if(tok.getBalance() > 500){
            tok.pay(currentProperty.getOwned(), 500);
            System.out.println("Current Balance: $" + tok.getBalance());
          }
          else{
            tok.pay(currentProperty.getOwned(), 500);
            System.out.println("The rent had to be paid by the bank, as you have no money left.");
            System.out.println("You have gone bankrupt.");
            for(int i = 0 ; i < tok.getProperties() ; i += 1){
              tok.getOwnedArray()[i].resetOwned();
            }
            players -= 1;
            tok.changeBankrupt(true);
          }
        }
      }
      cards.remove(card);
    }
    else if(card == 5){
      System.out.println("Advance to the nearest Railroad.");
      if(tok.getPos() < 5){
        tok.changePos(5);
        Property currentProperty = (Property)board[tok.getPos()];
        System.out.println("Advanced to Reading Railroad (Tile 5).");
        if(currentProperty != null && currentProperty.getOwned().getNumber() != tok.getNumber()){
          System.out.println("Reading Railroad is owned by player " + currentProperty.getOwned().getNumber() + ". Paying double the rent, or $500.");
          if(tok.getBalance() > 500){
            tok.pay(currentProperty.getOwned(), 500);
            System.out.println("Current Balance: $" + tok.getBalance());
          }
          else{
            tok.pay(currentProperty.getOwned(), 500);
            System.out.println("The rent had to be paid by the bank, as you have no money left.");
            System.out.println("You have gone bankrupt.");
            for(int i = 0 ; i < tok.getProperties() ; i += 1){
              tok.getOwnedArray()[i].resetOwned();
            }
            players -= 1;
            tok.changeBankrupt(true);
          }
        }
      }
      else if(tok.getPos() < 15){
        System.out.println("Advanced to Pennsylvania Railroad (Tile 15).");
        tok.changePos(15);
        Property currentProperty = (Property)board[tok.getPos()];
        if(currentProperty.getOwned() != null && currentProperty.getOwned().getNumber() != tok.getNumber()){
          System.out.println("Pennsylvania Railroad is owned by player " + currentProperty.getOwned().getNumber() + ". Paying double the rent, or $500.");
          if(tok.getBalance() > 500){
            tok.pay(currentProperty.getOwned(), 500);
            System.out.println("Current Balance: $" + tok.getBalance());
          }
          else{
            tok.pay(currentProperty.getOwned(), 500);
            System.out.println("The rent had to be paid by the bank, as you have no money left.");
            System.out.println("You have gone bankrupt.");
            for(int i = 0 ; i < tok.getProperties() ; i += 1){
              tok.getOwnedArray()[i].resetOwned();
            }
            players -= 1;
            tok.changeBankrupt(true);
          }
        }
      }
      else if(tok.getPos() < 25){
        System.out.println("Advanced B&O Railroad (Tile 25).");
        tok.changePos(25);
        Property currentProperty = (Property)board[tok.getPos()];
        if(currentProperty.getOwned() != null && currentProperty.getOwned().getNumber() != tok.getNumber()){
          System.out.println("B&O Railroad is owned by player " + currentProperty.getOwned().getNumber() + ". Paying double the rent, or $500.");
          if(tok.getBalance() > 500){
            tok.pay(currentProperty.getOwned(), 500);
            System.out.println("Current Balance: $" + tok.getBalance());
          }
          else{
            tok.pay(currentProperty.getOwned(), 500);
            System.out.println("The rent had to be paid by the bank, as you have no money left.");
            System.out.println("You have gone bankrupt.");
            for(int i = 0 ; i < tok.getProperties() ; i += 1){
              tok.getOwnedArray()[i].resetOwned();
            }
            players -= 1;
            tok.changeBankrupt(true);
          }
        }
      }
      else if(tok.getPos() < 35){
        System.out.println("Advanced to Short Railroad (Tile 35).");
        tok.changePos(35);
        Property currentProperty = (Property)board[tok.getPos()];
        if(currentProperty.getOwned() != null && currentProperty.getOwned().getNumber() != tok.getNumber()){
          System.out.println("Short Railroad is owned by player " + currentProperty.getOwned().getNumber() + ". Paying double the rent, or $500.");
          if(tok.getBalance() > 500){
            tok.pay(currentProperty.getOwned(), 500);
            System.out.println("Current Balance: $" + tok.getBalance());
          }
          else{
            tok.pay(currentProperty.getOwned(), 500);
            System.out.println("The rent had to be paid by the bank, as you have no money left.");
            System.out.println("You have gone bankrupt.");
            for(int i = 0 ; i < tok.getProperties() ; i += 1){
              tok.getOwnedArray()[i].resetOwned();
            }
            players -= 1;
            tok.changeBankrupt(true);
          }
        }
      }
      else{
        tok.changePos(5);
        Property currentProperty = (Property)board[tok.getPos()];
        System.out.println("Advanced to Reading Railroad (Tile 5), and passed Go.");
        tok.addBalance(200);
        System.out.println("Current Balance: $" + tok.getBalance());
        if(currentProperty.getOwned() != null && currentProperty.getOwned().getNumber() != tok.getNumber()){
          System.out.println("Reading Railroad is owned by player " + currentProperty.getOwned().getNumber() + ". Paying double the rent, or $500.");
          if(tok.getBalance() > 500){
            tok.pay(currentProperty.getOwned(), 500);
            System.out.println("Current Balance: $" + tok.getBalance());
          }
          else{
            tok.pay(currentProperty.getOwned(), 500);
            System.out.println("The rent had to be paid by the bank, as you have no money left.");
            System.out.println("You have gone bankrupt.");
            for(int i = 0 ; i < tok.getProperties() ; i += 1){
              tok.getOwnedArray()[i].resetOwned();
            }
            players -= 1;
            tok.changeBankrupt(true);
          }
        }
      }
      cards.remove(card);
    }
    else if(card == 6){
      if(tok.getPos() < 12){
        System.out.println("Advanced to Electric Company (Tile 12).");
        tok.changePos(12);
        Property currentProperty = (Property)board[tok.getPos()];
        if(currentProperty.getOwned() != null && currentProperty.getOwned().getNumber() != tok.getNumber()){
          int roll = (((int)(Math.random() * 6) + 1) + (int)(Math.random() * 6) + 1);
          System.out.println("Electric Company is owned by player " + currentProperty.getOwned().getNumber() + ". Paying 100 times the roll of " + roll + ".");
          if(tok.getBalance() > 100 * roll){
            tok.pay(currentProperty.getOwned(), 100 * roll);
            System.out.println("Current Balance: $" + tok.getBalance());
          }
          else{
            tok.pay(currentProperty.getOwned(), 100 * roll);
            System.out.println("The rent had to be paid by the bank, as you have no money left.");
            System.out.println("You have gone bankrupt.");
            for(int i = 0 ; i < tok.getProperties() ; i += 1){
              tok.getOwnedArray()[i].resetOwned();
            }
            players -= 1;
            tok.changeBankrupt(true);
          }
        }
      }
      else if(tok.getPos() < 28){
        System.out.println("Advanced to Water Works (Tile 28).");
        tok.changePos(28);
        Property currentProperty = (Property)board[tok.getPos()];
        if(currentProperty.getOwned() != null && currentProperty.getOwned().getNumber() != tok.getNumber()){
          int roll = (((int)(Math.random() * 6) + 1) + (int)(Math.random() * 6) + 1);
          System.out.println("Water Works is owned by player " + currentProperty.getOwned().getNumber() + ". Paying 100 times the roll of " + roll + ".");
          if(tok.getBalance() > 100 * roll){
            tok.pay(currentProperty.getOwned(), 100 * roll);
            System.out.println("Current Balance: $" + tok.getBalance());
          }
          else{
            tok.pay(currentProperty.getOwned(), 100 * roll);
            System.out.println("The rent had to be paid by the bank, as you have no money left.");
            System.out.println("You have gone bankrupt.");
            for(int i = 0 ; i < tok.getProperties() ; i += 1){
              tok.getOwnedArray()[i].resetOwned();
            }
            players -= 1;
            tok.changeBankrupt(true);
          }
        }
      }
      else{
        System.out.println("Advanced to Electric Company (Tile 12), and passed go.");
        tok.changePos(12);
        tok.addBalance(200);
        System.out.println("Current Balance: $" + tok.getBalance());
        Property currentProperty = (Property)board[tok.getPos()];
        if(currentProperty.getOwned() != null && currentProperty.getOwned().getNumber() != tok.getNumber()){
          int roll = (((int)(Math.random() * 6) + 1) + (int)(Math.random() * 6) + 1);
          System.out.println("Electric Company is owned by player " + currentProperty.getOwned().getNumber() + ". Paying 100 times the roll of " + roll + ".");
          if(tok.getBalance() > 100 * roll){
            tok.pay(currentProperty.getOwned(), 100 * roll);
            System.out.println("Current Balance: $" + tok.getBalance());
          }
          else{
            tok.pay(currentProperty.getOwned(), 100 * roll);
            System.out.println("The rent had to be paid by the bank, as you have no money left.");
            System.out.println("You have gone bankrupt.");
            for(int i = 0 ; i < tok.getProperties() ; i += 1){
              tok.getOwnedArray()[i].resetOwned();
            }
            players -= 1;
            tok.changeBankrupt(true);
          }
        }
      }
      cards.remove(card);
    }
    else if(card == 7){
      System.out.println("Bank pays you a dividend of $50");
      tok.addBalance(50);
      System.out.println("Current Balance: $" + tok.getBalance());
      cards.remove(card);
    }
    else if(card == 8){
      System.out.println("Go back 3 spaces");
      tok.changePos(tok.getPos() - 3);
      cards.remove(card);
    }
    else if(card == 9){
      System.out.println("Go to Jail. Go directly to Jail, do not pass Go, do not collect $200");
      System.out.println("You landed on Go To Jail. You will be unable to take the next 3 turns.");
      tok.setJailed(1);
      cards.remove(card);
    }
    else if(card == 10){
      System.out.println("Speeding fine $30");
      tok.subtractBalance(30);
      cards.remove(card);
    }
    else if(card == 11){
      System.out.println("Take a trip to Reading Railroad. If you pass Go, collect $200");
      if(tok.getPos() > 5){
        tok.addBalance(200);
        System.out.println("Current Balance: $" + tok.getBalance());
      }
      cards.remove(card);
    }
    else if(card == 12){
      System.out.println("You have been elected Chairman of the Board. Pay $300");
      tok.subtractBalance(300);
      System.out.println("Current Balance: $" + tok.getBalance());
      cards.remove(card);
    }
    else if(card == 13){
      System.out.println("Your building loan matures. Collect $150");
      tok.addBalance(150);
      System.out.println("Current Balance: $" + tok.getBalance());
      cards.remove(card);
    }

  }
}
