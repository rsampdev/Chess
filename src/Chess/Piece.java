package Chess;

public enum Piece {
    KING, QUEEN, BISHOP, ROOK, KNIGHT, PAWN;

    private Square location;

    public Square getLocation() {
        return new Square(this.location.x, this.location.y);
    }

    public void setup(Square location) {
        this.location = location;
    }

    public void move(Square newLocation) {
        Mover.move(this, newLocation);
    }
}
