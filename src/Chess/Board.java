package Chess;

import java.util.LinkedList;
import java.util.ListIterator;

public class Board {
    private LinkedList<Piece> pieces;

    public Board() {
        pieces = new LinkedList<>();
        setup();
    }

    public boolean move(Square pieceLocation, Square pieceDestination) {
        ListIterator<Piece> iterator = pieces.listIterator(0);
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

    private void setup() {

    }
}
