public class Character{
  private int position;
  private int balance;
  private int number;

  public Character(int number){
    position = 0; //start on go
    balance = 1500; //starting balance is $1500
  }

  //Moves the Character forward by [tiles] tiles
  public static int advance(int tiles){
    position += tiles;
    //Checks if pass go
    if(position > 40){
      balance += 200; //collect $200 if pass go
    }
    return position;
  }



  //Pays [other] [amount] dollars
  public static int pay(Character other, int amount){
    other.addBalance(amount);
    subtractBalance(amount);
  }

  //increases the balance by [amount]
  public int addBalance(int amount){
    balance += amount;
  }

  //decreases the balance by [amount]
  public int subtractBalance(int amount){
    balance -= amount;
  }

  //returns the position of the character
  public int getPos(){
    return position;
  }

  //returns the number of the player
  public int getPlayer(){
    return number;
  }


}
