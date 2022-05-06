public class Square {
    private Ladder ladder;
    private Snake snake;

    public Square(Ladder ladder, Snake snake) {
        this.ladder = ladder;
        this.snake = snake;
    }

    public Square() {
        this.ladder=null;
        this.snake=null;
    }

    public Ladder getLadder() {
        return ladder;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setLadder(Ladder ladder) {
        this.ladder = ladder;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }
}
