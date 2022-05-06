import java.util.Scanner;

public class SnakesAndLaddersGame {
    private Player[] players;
    private int number_of_players;
    private GameBoard gameBoard;
    private static int MIN_SQUARE=1;
    private static int MAX_SQUARE=100;



    public SnakesAndLaddersGame(int maxDieValue,int minDieValue) {
        number_of_players=0;
        this.gameBoard=new GameBoard();
    }
    public SnakesAndLaddersGame() {
    this(6,1);
    }

    public void initializeGame(){
        Scanner scanner=Main.scanner;
        String currentLine;
        do{
            currentLine=scanner.nextLine();
            if (!currentLine.equals("end")) {
                String[] subString = currentLine.split(" ");
                if (subString[0].equals("add")) {
                    switch (subString[1]) {
                        case "player":
                            isPlayerValid(subString[2],subString[3]);
                            break;
                        case "ladder":
                            isLadderValid(Integer.parseInt(subString[2]),Integer.parseInt(subString[3]));
                            break;
                        case "snake":
                            isSnakeValid(Integer.parseInt(subString[2]),Integer.parseInt(subString[3]));
                            break;
                    }
                }
            }
        }while(!currentLine.equals("end"));
        if (currentLine.equals("end") && number_of_players<2){
            System.out.println("Cannot start the game, there are less then two players!");
        }
    }
    public boolean isPlayerValid(String name, String color){
        boolean isNameExist=false;
        boolean isColorExist=false;
        if (number_of_players==5){
            System.out.println("The maximal number of players is five !\n");
            return false;
        }
        for (int i = 0; i <number_of_players ; i++) {
            if (players[i].getName().equals(name)){
                isNameExist=true;
            }
            if ((players[i].getGamePiece().getColor().toString()).equals(color)){
                isNameExist=true;
            }
        }
        if (isNameExist==true && isColorExist==true){
            System.out.println("The name and the color are already taken!");
            return false;
        }else if (isNameExist==true && isColorExist==false){
            System.out.println("The name is already taken!");
            return false;
        }else if (isNameExist==false && isColorExist==true){
            System.out.println("The color is already taken!");
            return false;
        }else{
            GamePiece gamePiece=new GamePiece(Color.valueOf(color));
            players[number_of_players]=new Player(name,gamePiece);
            number_of_players++;
        }
        return true;
    }

    public boolean isLadderValid(int ladderLength, int startSquare){
        if (!(startSquare>=MIN_SQUARE && startSquare<=MAX_SQUARE)){
            System.out.println("The square is not within the board's boundaries!");
            return false;
        }
        if (!(startSquare+ladderLength>=MIN_SQUARE && startSquare+ladderLength<=MAX_SQUARE)){
            System.out.println("The ladder is too long!");
            return false;

        }
        Square currentSquare=gameBoard.getSquares()[startSquare-1];
        if (currentSquare.getSnake()!=null){
            System.out.println("This square already contains a head of a snake !");
        }
        if (currentSquare.getLadder()!=null){
            System.out.println("This square contains a bottom of a ladder !");
        }
        return true;
    }
    public boolean isSnakeValid(int snakeLength, int startSquare){
        if (!(startSquare>=MIN_SQUARE && startSquare<=MAX_SQUARE)){
            System.out.println("You cannot add a snake in the last square!");
            return false;
        }
        if (!(startSquare-snakeLength>=MIN_SQUARE && startSquare-snakeLength<=MAX_SQUARE)){
            System.out.println("The snake is too long!");
            return false;
        }
        Square currentSquare=gameBoard.getSquares()[startSquare-1];
        if (currentSquare.getSnake()!=null){
            System.out.println("This square already contains a head of a snake !");
        }
        if (currentSquare.getLadder()!=null){
            System.out.println("This square contains a bottom of a ladder !");
        }
        //add more conditions about the square
        return true;
    }
    public String start(){
        return "";
    }

}
