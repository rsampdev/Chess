package Chess;

public class Mover {
    private final int LOWER_BOUND = 1;
    private final int UPPER_BOUND = 8;

    public static void move(Piece piece, Square newLocation) {
        switch (piece) {
            case KING:
                moveKing(piece, newLocation);
                break;
            case QUEEN:
                moveQueen(piece, newLocation);
                break;
            case BISHOP:
                moveBishop(piece, newLocation);
                break;
            case KNIGHT:
                moveKnight(piece, newLocation);
                break;
            case ROOK:
                moveRook(piece, newLocation);
                break;
            case PAWN:
                movePawn(piece, newLocation);
                break;
            default:
                break;
        }
    }

    private static void moveKing(Piece king, Square newLocation) {

    }

    private static void moveQueen(Piece queen, Square newLocation) {

    }

    private static void moveBishop(Piece bishop, Square newLocation) {

    }

    private static void moveKnight(Piece knight, Square newLocation) {

    }

    private static void moveRook(Piece rook, Square newLocation) {

    }

    private static void movePawn(Piece pawn, Square newLocation) {

    }

}
