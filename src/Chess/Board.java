package Chess;

import java.util.LinkedList;
import java.util.ListIterator;

public class Board {
    private static LinkedList<Piece> PIECES = new LinkedList<>();

    public static boolean move(Square pieceLocation, Square pieceDestination) {
        ListIterator<Piece> iterator = Board.PIECES.listIterator(0);
        Piece piece = null;

        while (iterator.hasNext()) {
            piece = iterator.next();
            Square square = piece.getLocation();

            if (square.x == pieceLocation.x && square.y == pieceLocation.y) {
                break;
            }
        }

        boolean moved = false;

        if(piece != null) {
            moved = piece.move(pieceDestination);
        }

        return moved;
    }

    static void add(Piece piece) {
        Board.PIECES.add(piece);
    }

    static void setup() {
        Piece.setup(new Square(5, 1), Color.WHITE, Piece.KING);
        Piece.setup(new Square(4, 1), Color.WHITE, Piece.QUEEN);
        Piece.setup(new Square(3, 1), Color.WHITE, Piece.BISHOP);
        Piece.setup(new Square(6, 1), Color.WHITE, Piece.BISHOP);
        Piece.setup(new Square(2, 1), Color.WHITE, Piece.KNIGHT);
        Piece.setup(new Square(7, 1), Color.WHITE, Piece.KNIGHT);
        Piece.setup(new Square(1, 1), Color.WHITE, Piece.ROOK);
        Piece.setup(new Square(8, 1), Color.WHITE, Piece.ROOK);

        for (int i = Mover.LOWER_BOUND; i <= Mover.UPPER_BOUND; i++) {
            Piece.setup(new Square(i, 2), Color.WHITE, Piece.PAWN);
        }

        Piece.setup(new Square(5, 8), Color.BLACK, Piece.KING);
        Piece.setup(new Square(4, 8), Color.BLACK, Piece.QUEEN);
        Piece.setup(new Square(3, 8), Color.BLACK, Piece.BISHOP);
        Piece.setup(new Square(6, 8), Color.BLACK, Piece.BISHOP);
        Piece.setup(new Square(2, 8), Color.BLACK, Piece.KNIGHT);
        Piece.setup(new Square(7, 8), Color.BLACK, Piece.KNIGHT);
        Piece.setup(new Square(1, 8), Color.BLACK, Piece.ROOK);
        Piece.setup(new Square(8, 8), Color.BLACK, Piece.ROOK);

        for (int i = Mover.LOWER_BOUND; i <= Mover.UPPER_BOUND; i++) {
            Piece.setup(new Square(i, 7), Color.BLACK, Piece.PAWN);
        }
    }
}
