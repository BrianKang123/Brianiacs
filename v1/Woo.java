public class Woo{
  private Tile[] board;


  public Woo(){
    board = new Tile[40];
    newGame();
  }

  //plays a turn for [tok]. Returns bankruptcy state.
  public void playTurn(Token tok){
    int spaces = roll();
    System.out.println(tok.getPlayer() + " rolled a " + spaces);
    //advance the spaces
    tok.advance(spaces);
    //current space, for future reference
    int cur = tok.getPos();
    //if the current tile is a property
    if(board[tok.getPos()] instanceof Property){
      //If the Token lands on a property that is unowned
      if((Property)(board[cur]).getOwned() == 0){

      }
      //If the Token lands on an owned property that is theirs
      if((Property)(board[cur]).getOwned() == tok.getPlayer()){

      }
      //If the Token lands on an owned property that is NOT theirs
      else{

      }
    }
    return true;
  }

  public void newBoard(Tile[] bored){
    for(int i = 0 ; i < 40 ; i += 1){
      bored[i] = new MediterraneanAvenue(i);
    }
  }

  public void newGame(){
    //create the board (WIP!!!)
    newBoard(this.board);
    //randomize turn order
    int playerTurn = (int)(Math.random()*4) + 1;
    System.out.println("Your turn is number " + playerTurn + "!");
    Token p1, p2, p3, p4;
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
      return (int)(Math.random() * 11) + 2;
  }

  public static void main(String[] StandardOil){
    Woo game = new Woo();
    int players = 4;

    //Game lasts as long as there is still 2 or more players
    while(players > 1){
      //player 1 turn
      playTurn(p1);
      //player 2 turn

      //player 3 turn

      //player 4 turn
      break; //diag
    }


  }

}
