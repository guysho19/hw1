public class GamePiece {
    private Color color;
    private int currentSquare;

    public GamePiece(Color color) {
        this.currentSquare=1;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getCurrentSquare() {
        return currentSquare;
    }

    public void setCurrentSquare(int currentSquare) {
        this.currentSquare = currentSquare;
    }

}
