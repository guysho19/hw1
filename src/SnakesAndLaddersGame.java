import java.util.Scanner;

public class SnakesAndLaddersGame {
    private Player[] players;
    private int number_of_players;
    private GameBoard gameBoard;
    private final int MIN_SQUARE=1;
    private final int MAX_SQUARE=100;
    private Die die;



    public SnakesAndLaddersGame(int maxDieValue,int minDieValue) {
        number_of_players=0;
        die=new Die(maxDieValue,minDieValue);
        this.gameBoard=new GameBoard();
    }
    public SnakesAndLaddersGame() {
    this(6,1);
    }

    public void initializeGame(){
        System.out.println("Intializing the game...");
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
                            int length=Integer.parseInt(subString[2]),startSquare=Integer.parseInt(subString[3]);
                            if (isLadderValid(length,startSquare)){
                                Ladder l=new Ladder(length);
                                gameBoard.getSquares()[startSquare-1].setLadder(l);
                            }
                            break;
                        case "snake":
                            int length2=Integer.parseInt(subString[2]),startSquare2=Integer.parseInt(subString[3]);
                            if (isSnakeValid(length2,startSquare2)){
                                Snake s=new Snake(length2);
                                gameBoard.getSquares()[startSquare2-1].setSnake(s);
                            }
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
        }else {
            GamePiece gamePiece = new GamePiece(Color.valueOf(color));
            players[number_of_players] = new Player(name, gamePiece);
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
            return false;
        }
        if (currentSquare.getLadder()!=null){
            System.out.println("This square contains a bottom of a ladder !");
            return false;

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
            return false;
        }
        if (currentSquare.getLadder()!=null){
            System.out.println("This square contains a bottom of a ladder !");
            return false;
        }
        return true;
    }
    public String start(){
        sort(players);
        String winner="";
        int roundNumber=0,steps=0;
        while(winner!=""){
            System.out.println("------------------------- Round number "+roundNumber+" -------------------------");
            for (int i = 0; i < number_of_players; i++) {
                steps=this.die.roll();
                System.out.print(players[i].getName()+ " rolled "+steps+". The path to the next square: ");
                int currentSquare=players[i].getGamePiece().getCurrentSquare();
                if (currentSquare + steps < MAX_SQUARE && currentSquare + steps > MIN_SQUARE) {
                    printNextSquare(currentSquare, steps);
                    players[i].getGamePiece().setCurrentSquare(currentSquare + steps);
                }else if (currentSquare + steps <= MIN_SQUARE) {
                    printNextSquare(currentSquare, -1*currentSquare+1);
                    players[i].getGamePiece().setCurrentSquare(MIN_SQUARE);
                }else {
                    printNextSquare(currentSquare, steps);
                    players[i].getGamePiece().setCurrentSquare(2*MAX_SQUARE-(currentSquare + steps));
                }
                if (players[i].getGamePiece().getCurrentSquare()==MAX_SQUARE){
                    return players[i].getName();
                }
                boolean finishedTurn=false;
                while (finishedTurn==false) {
                    currentSquare=players[i].getGamePiece().getCurrentSquare();
                    if (gameBoard.getSquares()[currentSquare].getSnake()!=null){
                        steps=-1*gameBoard.getSquares()[currentSquare].getSnake().getLength();
                    }else if (gameBoard.getSquares()[currentSquare].getLadder()!=null){
                        steps=gameBoard.getSquares()[currentSquare].getSnake().getLength();
                    }else{
                        finishedTurn=true;
                    }
                    printNextSquare(currentSquare, steps);
                    players[i].getGamePiece().setCurrentSquare(currentSquare + steps);
                    if (players[i].getGamePiece().getCurrentSquare()==MAX_SQUARE){
                       return players[i].getName();
                    }
                }
            }

        }
        return "";
    }
    public void printNextSquare(int currentSquare,int steps){
        if (currentSquare + steps<MAX_SQUARE) {
            System.out.print(currentSquare + " -> " + currentSquare + steps);
        }else{
            System.out.print(currentSquare + " -> "+ (2*MAX_SQUARE-(currentSquare + steps)));
        }

    }
    public boolean isWinner(){
        return false;
    }
    public static void swap(Player[] list, int index1, int index2){
        Player temp = list[index1];
        list[index1] = list[index2];
        list[index2] = temp;
    }

    public static int index_of_min(Player[] list,int length){
        int index_of_min=0;
        for (int i=1;i<length; i++){
            if(list[i].getName().compareTo(list[index_of_min].getName())>0){
                index_of_min=i;
            }
        }
        return index_of_min;
    }

    public static void sort(Player[] list){
        int length=list.length;
        for (; length > 1; length--){
            int index_of_min= index_of_min(list, length);
            swap(list, length-1, index_of_min);
        }
    }

}
