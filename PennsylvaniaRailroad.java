public class PennsylvaniaRailroad extends Property{
  //Example format
  /*
    RENT EXPLANATION:
    0 houses: $2
    1 house: $10
    2 house: $30
    3 house: $90
    4 house: $160
    Hotel (5 houses): $250
    Rent with a monopoly is doubled if there are no houses or hotels

    EACH HOUSE COSTS $50

    Mortgate value is $30
  */
  public PennsylvaniaRailroad(int pos){
    super(pos);
    cost = 200;
    rent = 25;
    set = 0; //change value later
    name = "Pennsylvania Railroad";
  }
}
