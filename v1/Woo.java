public class Woo{
  private Tile[] board;


  public Woo(){
    board = new Tile[40];
    newGame();
  }

  public static void newGame(){
    //randomize turn order
    int playerTurn = (int)(Math.random()*4) + 1;
    System.out.println("Your turn is number " + playerTurn + "!");
    Character p1, p2, p3, p4;
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
    p1.changePos(0);
    p2.changePos(0);
    p3.changePos(0);
    p4.changePos(0);
  }

  //rolls 2 die
  public static int roll(){
      return (int)(Math.random() * 12) + 1;
  }

  //plays a turn for [char]. Returns bankruptcy state.
  //public static boolean playTurn(Character char){

  //}

  public static void main(String[] StandardOil){
    Woo game = new Woo();
    int players = 4;
    System.out.println(roll());
    //Game lasts as long as there is still 2 or more players
    while(players > 1){
      //player 1 turn

      //player 2 turn

      //player 3 turn

      //player 4 turn
      break; //diag
    }


  }

}
