import java.util.ArrayList;
public class Chest extends Tile{
  public Chest(int pos){
    super(pos);
  }
  //We will only code the cards that would work with our game

  //Draws a random effect card
  public void draw(Token tok){
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
      System.out.println("Advance to go! Collect $200.");
      tok.addBalance(200);
      System.out.println("Current Balance: $" + tok.getBalance());
      tok.changePos(0);
    }
    else if(card == 1){
      System.out.println("Bank error in your favor. Collect $200");
      tok.addBalance(200);
      System.out.println("Current Balance: $" + tok.getBalance());
    }
    else if(card == 2){
      System.out.println("Doctor's fee. Pay $100");
      tok.subtractBalance(100);
      System.out.println("Current Balance: $" + tok.getBalance());
    }
    else if(card == 3){
      System.out.println("From sale of stock you get $50");
      tok.addBalance(100);
      System.out.println("Current Balance: $" + tok.getBalance());
    }
    else if(card == 4){
      System.out.println("Go to Jail. Go directly to Jail, do not pass Go, do not collect $200");
      System.out.println("You landed on Go To Jail. You will be unable to take the next 3 turns.");
      tok.setJailed(1);
      cards.remove(card);
    }
    else if(card == 5){
      System.out.println("Holiday fund matures. Recieve $100");
      tok.addBalance(100);
      System.out.println("Current Balance: $" + tok.getBalance());
    }
    else if(card == 6){
      System.out.println("Income tax refund. Collect $20");
      tok.addBalance(20);
      System.out.println("Current Balance: $" + tok.getBalance());
    }
    else if(card == 7){
      System.out.println("It is your birthday. Collect $30");
      tok.addBalance(30);
      System.out.println("Current Balance: $" + tok.getBalance());
    }
    else if(card == 8){
      System.out.println("Life insurance matures. Collect $100");
      tok.addBalance(100);
      System.out.println("Current Balance: $" + tok.getBalance());
    }
    else if(card == 9){
      System.out.println("Pay hospital fees of $200");
      tok.subtractBalance(200);
      System.out.println("Current Balance: $" + tok.getBalance());
    }
    else if(card == 10){
      System.out.println("Pay school fees of $100");
      tok.subtractBalance(100);
      System.out.println("Current Balance: $" + tok.getBalance());
    }
    else if(card == 11){
      System.out.println("Recieve $25 consultancy fee");
      tok.addBalance(25);
      System.out.println("Current Balance: $" + tok.getBalance());
    }
    else if(card == 12){
      System.out.println("You have won second prize in a beauty contest. Collect $10");
      tok.addBalance(10);
      System.out.println("Current Balance: $" + tok.getBalance());
    }
    else if(card == 13){
      System.out.println("You inherit $100");
      tok.addBalance(100);
      System.out.println("Current Balance: $" + tok.getBalance());
    }

  }
}
