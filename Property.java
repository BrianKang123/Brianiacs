public class Property extends Tile{
  protected Token owned;  //Null is unowned
  protected int cost;
  protected int rent;
  protected int houses;
  protected String name;
  protected int set; //The set that the property belongs to
  protected int remaningSet; //How many pieces in the set are not owned by this player
  protected int houseCost;
  protected boolean mortgaged;


  public Property(int pos){
    super(pos);
    houses = 0;
    mortgaged = false;
  }

  //purchase a Property, returns remaining balance
  public int buy(Token buyer){
    buyer.subtractBalance(cost);
    owned = buyer.getPlayer();
    return buyer.getBalance();
  }

  //returns rent
  public int getRent(){
    return rent;
  }

  //returns owner, 0 for no owner
  public Token getOwned(){
    return owned;
  }

  public String getName(){
    return name;
  }

  //returns set number;
  public int getSet(){
    return set;
  }

  //returns house cost
  public int getHouseCost(){
    return houseCost;
  }

  //returns cost of deed
  public int getCost(){
    return cost;
  }

  //returns the position of deed
  public int getPos(){
    return position;
  }
}
