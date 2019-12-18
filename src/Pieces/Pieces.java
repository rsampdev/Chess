package Pieces;

public enum Pieces {
    KING, QUEEN, BISHOP, ROOK, KNIGHT, PAWN;

    Square location;

    public void move(Square newLocation) {
        switch (this) {
            case KING:
                break;
            case QUEEN:
                break;
            case BISHOP:
                break;
            case ROOK:
                break;
            case KNIGHT:
                break;
            case PAWN:
                break;
            default:
                break;
        }
    }
}
