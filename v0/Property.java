public class Property extends Tile{
  private int owned;
  private int cost;
  private int rent;
  private int houses;
  private String name;
  private int set; //The set that the property belongs to
  private int remaningSet; //How many pieces in the set are not owned by this player
  private int houseCost;
  private boolean mortgaged;


  public Property(int pos){
    super(pos);
    owned = 0;  //0 is not owned, 1-4 is owned by player 1-4
    houses = 0;
    mortgaged = false;
  }

  //purchase a Property, returns remaining balance
  public int buy(Character buyer){
    buyer.subtractBalance(cost);
    owned = buyer.getPlayer();
  }

  //returns rent
  public int getRent(){
    return rent;
  }

  //returns owner, 0 for no owner
  public int getOwned(){
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
  };
}
