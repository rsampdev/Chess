package Chess;

import java.util.LinkedList;
import java.util.ListIterator;

public class Board {
    private LinkedList<Piece> pieces;

    public Board() {
        pieces = new LinkedList<>();
    }

    public void move(Square pieceLocation, Square pieceDestination) {
        ListIterator<Piece> iterator = pieces.listIterator(0);
        Piece piece = null;

        while (iterator.hasNext()) {
            piece = iterator.next();
            Square square = piece.getLocation();

            if (square.x == pieceLocation.x && square.y == pieceLocation.y) {
                break;
            }
        }

        if(piece != null) {
            piece.move(pieceDestination);
        }
    }
}
