public class GameBoard {
    private final int NUMBER_OF_SQUARE=100;
    private Square[] squares;

    public GameBoard() {
        this.squares = new Square[NUMBER_OF_SQUARE];
    }

    public Square[] getSquares() {
        return squares;
    }
}
