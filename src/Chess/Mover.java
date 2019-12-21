package Chess;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Mover {
    static final int LOWER_BOUND = 1;
    static final int UPPER_BOUND = 8;

    public static boolean move(Piece piece, Square newLocation) {
        boolean moved = false;

        switch (piece.getType()) {
            case KING:
                moved = moveKing(piece, newLocation);
                break;
            case QUEEN:
                moved = moveQueen(piece, newLocation);
                break;
            case BISHOP:
                moved = moveBishop(piece, newLocation);
                break;
            case KNIGHT:
                moved = moveKnight(piece, newLocation);
                break;
            case ROOK:
                moved = moveRook(piece, newLocation);
                break;
            case PAWN:
                moved = movePawn(piece, newLocation);
                break;
            default:
                break;
        }

        return moved;
    }

    private static boolean moveKing(Piece king, Square newLocation) {
        HashSet<Square> possibleMoves = new HashSet<>();
        Square current = king.getLocation();
        Square temp = null;

        for (int x = LOWER_BOUND; x <= UPPER_BOUND; x++) {
            for (int y = LOWER_BOUND; y <= UPPER_BOUND; y++) {
                temp = new Square(x, y);

                if ((y == current.y + 1 || y == current.y - 1) && (x == current.x - 1 || x == current.x || x == current.x + 1)) {
                    possibleMoves.add(temp);
                    continue;
                }

                if (y == current.y && (x == current.x - 1 || x == current.x + 1)) {
                    possibleMoves.add(temp);
                }
            }
        }

        return isWithinTheSetOfActualPossibleMovesAndThusWasMoved(king, newLocation, possibleMoves);
    }

    private static boolean moveQueen(Piece queen, Square newLocation) {
        HashSet<Square> possibleMoves = new HashSet<>();
        Square current = queen.getLocation();
        Square temp = null;

        for (int x = LOWER_BOUND; x <= UPPER_BOUND; x++) {
            for (int y = LOWER_BOUND; y <= UPPER_BOUND; y++) {
                temp = new Square(x, y);
                return false; // figure our rook and bishop first
            }
        }

        return isWithinTheSetOfActualPossibleMovesAndThusWasMoved(queen, newLocation, possibleMoves);
    }

    private static boolean moveBishop(Piece bishop, Square newLocation) {
        HashSet<Square> possibleMoves = new HashSet<>();
        Square current = bishop.getLocation();
        Square temp = null;

        for (int x = LOWER_BOUND; x <= UPPER_BOUND; x++) {
            for (int y = LOWER_BOUND; y <= UPPER_BOUND; y++) {
                for (int k = -UPPER_BOUND + 1; k < UPPER_BOUND - 1; k ++) {
                    temp = new Square(x, y);

                    if(x == current.x + k && y == current.y + k) { // what even does this mean?
                        possibleMoves.add(temp);
                    }
                }
            }
        }

        return isWithinTheSetOfActualPossibleMovesAndThusWasMoved(bishop, newLocation, possibleMoves);
    }

    private static boolean moveKnight(Piece knight, Square newLocation) {
        HashSet<Square> possibleMoves = new HashSet<>();
        Square current = knight.getLocation();
        Square temp = null;

        for (int x = LOWER_BOUND; x <= UPPER_BOUND; x++) {
            for (int y = LOWER_BOUND; y <= UPPER_BOUND; y++) {
                temp = new Square(x, y);

                if (y == current.y - 2 || y == current.y + 2) {
                    if (x == current.x - 1 || x == current.x + 1) {
                        possibleMoves.add(temp);
                    }
                } else if (x == current.x - 2 || x == current.x + 2) {
                    if (y == current.y - 1 || y == current.y + 1) {
                        possibleMoves.add(temp);
                    }
                }
            }
        }

        return isWithinTheSetOfActualPossibleMovesAndThusWasMoved(knight, newLocation, possibleMoves);
    }

    private static boolean moveRook(Piece rook, Square newLocation) {
        HashSet<Square> possibleMoves = new HashSet<>();
        Square current = rook.getLocation();
        Square temp = null;

        for (int x = LOWER_BOUND; x <= UPPER_BOUND; x++) {
            for (int y = LOWER_BOUND; y <= UPPER_BOUND; y++) {
                temp = new Square(x, y);

                if (x == current.x || y == current.y) {
                    possibleMoves.add(temp);
                }
            }
        }

        return isWithinTheSetOfActualPossibleMovesAndThusWasMoved(rook, newLocation, possibleMoves);
    }

    private static boolean movePawn(Piece pawn, Square newLocation) {
        HashSet<Square> possibleMoves = new HashSet<>();
        Square current = pawn.getLocation();
        Square temp = null;

        boolean firstMove = (current.y == (LOWER_BOUND + 1)) || (current.y == (UPPER_BOUND + 1));

        for (int x = LOWER_BOUND; x <= UPPER_BOUND; x++) {
            for (int y = LOWER_BOUND; y <= UPPER_BOUND; y++) {
                int direction = pawn.getColor().direction();
                temp = new Square(x, y);

                if(firstMove && x == current.x && y == (current.y + direction * 2)) {
                    possibleMoves.add(temp);
                } else if (x == current.x && y == (current.y + direction)) {
                    possibleMoves.add(temp);
                }

                // diagonal capture moves;
            }
        }

        return isWithinTheSetOfActualPossibleMovesAndThusWasMoved(pawn, newLocation, possibleMoves);
    }

    private static boolean isWithinTheSetOfActualPossibleMovesAndThusWasMoved(Piece piece, Square newLocation, HashSet<Square> possibleMoves) {
        Iterator<Square> tempIterator = possibleMoves.iterator();
        boolean moved = false;

        Set<String> occupiedSquares = Board.getOccupiedSquares();
        HashSet<Square> actualMoves = new HashSet<>();

        while (tempIterator.hasNext()) {
            Square square = tempIterator.next();

            if (occupiedSquares.contains(square.keyPair())) {

                Piece pieceInSpace = Board.getPiece(square);

                if (piece.getColor() != pieceInSpace.getColor()) {
                    actualMoves.add(square);
                }

            } else {
                actualMoves.add(square);
            }
        }

        for (Square square : actualMoves) {
            if (newLocation.x == square.x && newLocation.y == square.y) {
                if (occupiedSquares.contains(square.keyPair()) && actualMoves.contains(square)) { // capture an enemy piece?
                    Board.remove(square);
                }

                Board.remove(piece.getLocation());
                piece.getLocation().x = newLocation.x;
                piece.getLocation().y = newLocation.y;
                Board.add(piece.getLocation(), piece);

                moved = true;
                break;
            }
        }

        return moved;
    }
}
