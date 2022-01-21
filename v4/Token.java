//Token is the name of the items you play with in monopoly
public class Token{
  protected int position;
  protected int balance;
  protected int number;
  protected boolean bankrupt = false;

  public Token(int num){
    number = num;
    position = 0; //start on go
    balance = 1500; //starting balance is $1500
  }

  //Moves the Token forward by [tiles] tiles
  public int advance(int tiles){
    position += tiles;
    //Checks if pass go
    if(position > 39){
      balance += 200; //collect $200 if pass go
      System.out.println("Player " + number + " passed go! You collected $200");
      position -= 40;
    }
    return position;
  }



  //Pays [other] [amount] dollars
  public void pay(Token other, int amount){
    other.addBalance(amount);
    subtractBalance(amount);
  }

  //increases the balance by [amount]
  public void addBalance(int amount){
    balance += amount;
  }

  //decreases the balance by [amount]
  public void subtractBalance(int amount){
    balance -= amount;
  }

  //changes the Token position
  public void changePos(int pos){
    position = pos;
  }

  //changes the token's bankruptcy state
  public void changeBankrupt(boolean state){
    bankrupt = state;
  }

  //returns the position of the Token
  public int getPos(){
    return position;
  }

  //returns the player
  public Token getPlayer(){
    return this;
  }

  //returns the number of the players
  public int getNumber(){
    return number;
  }

  //returns the balance of the players
  public int getBalance(){
    return balance;
  }

  //returns the player's bankruptcy state
  public boolean getBankrupt(){
    return bankrupt;
  }


}
