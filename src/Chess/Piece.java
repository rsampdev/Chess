package Chess;

public enum Piece {
    KING, QUEEN, BISHOP, KNIGHT, ROOK, PAWN;

    private Square location;
    private Color color;

    public Square getLocation() {
        return this.location;
    }

    public Color getColor() {
        return this.color;
    }

    public static void setup(Square location, Color color, Piece type) {
        type.location = location;
        type.color = color;
        Board.add(type);
    }

    public boolean move(Square newLocation) {
        return Mover.move(this, newLocation);
    }
}

enum Color {
    BLACK, WHITE;

    public int direction() {
        int direction = 0;

        switch (this) {
            case BLACK:
                direction = -1;
                break;
            case WHITE:
                direction = 1;
                break;
        }

        return direction;
    }
}
