package Chess;

import java.util.*;
import java.util.stream.Collectors;

public class Board {
    private static HashMap<String, Piece> PIECES = new HashMap<>();

    static final int LOWER_BOUND = 1;
    static final int UPPER_BOUND = 8;

    public static Color playerTurn = Color.WHITE;

    public static boolean move(Square pieceLocation, Square pieceDestination) {
        Iterator<Piece> iterator = Board.PIECES.values().iterator();
        Piece piece = null;

        while (iterator.hasNext()) {
            piece = iterator.next();
            Square square = piece.getLocation();

            if (square.x == pieceLocation.x && square.y == pieceLocation.y) {
                if(piece.getColor() != playerTurn) {
                    piece = null;
                }

                break;
            }
        }

        boolean moved = false;

        if(piece != null) {
            moved = piece.move(pieceDestination);

            if(moved) {
                if(playerTurn == Color.WHITE) {
                    playerTurn = Color.BLACK;
                } else {
                    playerTurn = Color.WHITE;
                }
            }
        }

        return moved;
    }

    public static Set<String> getOccupiedSquares() {
        return PIECES.keySet();
    }

    public static HashSet<Piece> getBlackPieces() {
        Collection<Piece> pieces = PIECES.values();
        return pieces.stream().filter(piece -> piece.getColor() == Color.BLACK).collect(Collectors.toCollection(HashSet::new));
    }

    public static HashSet<Piece> getWhitePieces() {
        Collection<Piece> pieces = PIECES.values();
        return pieces.stream().filter(piece -> piece.getColor() == Color.WHITE).collect(Collectors.toCollection(HashSet::new));
    }

    public static Piece getPiece(Square square) {
        return PIECES.get(square.keyPair());
    }

    public static void add(Square square, Piece piece) {
        Board.PIECES.put(square.keyPair(), piece);
    }

    public static void remove(Square square) {
        PIECES.remove(square.keyPair());
    }

    public static void setup() {
        PIECES = new HashMap<>();

        Piece.setup(new Square(5, 1), Color.WHITE, Type.KING);
        Piece.setup(new Square(4, 1), Color.WHITE, Type.QUEEN);
        Piece.setup(new Square(3, 1), Color.WHITE, Type.BISHOP);
        Piece.setup(new Square(6, 1), Color.WHITE, Type.BISHOP);
        Piece.setup(new Square(2, 1), Color.WHITE, Type.KNIGHT);
        Piece.setup(new Square(7, 1), Color.WHITE, Type.KNIGHT);
        Piece.setup(new Square(1, 1), Color.WHITE, Type.ROOK);
        Piece.setup(new Square(8, 1), Color.WHITE, Type.ROOK);

        for (int i = LOWER_BOUND; i <= UPPER_BOUND; i++) {
            Piece.setup(new Square(i, 2), Color.WHITE, Type.PAWN);
        }

        Piece.setup(new Square(5, 8), Color.BLACK, Type.KING);
        Piece.setup(new Square(4, 8), Color.BLACK, Type.QUEEN);
        Piece.setup(new Square(3, 8), Color.BLACK, Type.BISHOP);
        Piece.setup(new Square(6, 8), Color.BLACK, Type.BISHOP);
        Piece.setup(new Square(2, 8), Color.BLACK, Type.KNIGHT);
        Piece.setup(new Square(7, 8), Color.BLACK, Type.KNIGHT);
        Piece.setup(new Square(1, 8), Color.BLACK, Type.ROOK);
        Piece.setup(new Square(8, 8), Color.BLACK, Type.ROOK);

        for (int i = LOWER_BOUND; i <= UPPER_BOUND; i++) {
            Piece.setup(new Square(i, 7), Color.BLACK, Type.PAWN);
        }
    }
}
