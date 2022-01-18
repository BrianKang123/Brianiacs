public class Woo{
  private Tile[] board;
  int players;

  public Woo(){
    board = new Tile[40];
    players = 4;
    newGame();
  }

  public static void newGame(){
    //randomize turn order
    int playerTurn = (int)(Math.random()*4) + 1;
    if(playerTurn == 1){
      Character 1 = new Player(1);
      Character 2 = new AI(2);
      Character 3 = new AI(3);
      Character 4 = new AI(4);
    }
    if(playerTurn == 2){
      Character 1 = new AI(1);
      Character 2 = new Player(2);
      Character 3 = new AI(3);
      Character 4 = new AI(4);
    }
    if(playerTurn == 3){
      Character 1 = new AI(1);
      Character 2 = new AI(2);
      Character 3 = new Player(3);
      Character 4 = new AI(4);
    }
    if(playerTurn == 4){
      Character 1 = new AI(1);
      Character 2 = new AI(2);
      Character 3 = new AI(3);
      Character 4 = new Player(4);
    }
    1.changePos(0);
    2.changePos(0);
    3.changePos(0);
    4.changePos(0);
  }

  //rolls 2 die
  public static int roll(){
      return (int)(Math.random() * 12) + 1;
  }

  //plays a turn for [char]. Returns bankruptcy state.
  public static boolean playTurn(Character char){

  }

  public static void main(String[] StandardOil){
    Woo game = new Woo();
    System.out.println(roll());
    //Game lasts as long as there is still 2 or more players
    while(players > 1){
      //player 1 turn

      //player 2 turn

      //player 3 turn

      //player 4 turn

    }


  }

}
