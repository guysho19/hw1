public class Player {
    private String name;
    private GamePiece gamePiece;

    public Player(String name, GamePiece gamePiece) {
        this.name = name;
        this.gamePiece = gamePiece;
    }

    public String getName() {
        return name;
    }

    public GamePiece getGamePiece() {
        return gamePiece;
    }
}
