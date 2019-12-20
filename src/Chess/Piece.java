package Chess;

public class Piece {
    private Square location;
    private Color color;
    private Type type;

    public Piece(Square location, Color color, Type type) {
        this.location = location;
        this.color = color;
        this.type = type;
    }

    public Square getLocation() {
        return this.location;
    }

    public Color getColor() {
        return this.color;
    }

    public Type getType() {
        return type;
    }

    public static Piece setup(Square location, Color color, Type type) {
        Piece piece = new Piece(location, color, type);
        piece.location = location;
        piece.color = color;
        piece.type = type;
        Board.add(piece.location, piece);
        return piece;
    }

    public boolean move(Square newLocation) {
        return Mover.move(this, newLocation);
    }
}
