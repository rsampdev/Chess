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

        return isWithinTheListOfPossibleMoves(king, newLocation, possibleMoves);
    }

    private static boolean moveQueen(Piece queen, Square newLocation) {
        HashSet<Square> possibleMoves = new HashSet<>();
        Square current = queen.getLocation();

        for (int x = LOWER_BOUND; x <= UPPER_BOUND; x++) {
            for (int y = LOWER_BOUND; y <= UPPER_BOUND; y++) {
                if ((x == (current.x - 1) || x == (current.x + 1)) && (y == (current.y - 1) || y == (current.y + 1))) {
                    possibleMoves.add(new Square(x, y));
                } else if (x == current.x && (y == (current.y - 1) || y == (current.y + 1))) {
                    possibleMoves.add(new Square(x, y));
                } else if (y == current.y && (x == (current.x - 1) || x == (current.x + 1))) {
                    possibleMoves.add(new Square(x, y));
                } else if ((current.x + x) == (current.y + y)) {
                    possibleMoves.add(new Square((current.x + x), (current.y + y)));
                }
            }
        }

        return isWithinTheListOfPossibleMoves(queen, newLocation, possibleMoves);
    }

    private static boolean moveBishop(Piece bishop, Square newLocation) {
        HashSet<Square> possibleMoves = new HashSet<>();
        Square current = bishop.getLocation();

        for (int x = LOWER_BOUND; x <= UPPER_BOUND; x++) {
            for (int y = LOWER_BOUND; y <= UPPER_BOUND; y++) {
                if ((current.x + x) == (current.y + y)) {
                    possibleMoves.add(new Square((current.x + x), (current.y + y)));
                }
            }
        }

        return isWithinTheListOfPossibleMoves(bishop, newLocation, possibleMoves);
    }

    private static boolean moveKnight(Piece knight, Square newLocation) {
        HashSet<Square> possibleMoves = new HashSet<>();
        Square current = knight.getLocation();

        for (int x = LOWER_BOUND; x <= UPPER_BOUND; x++) {
            for (int y = LOWER_BOUND; y <= UPPER_BOUND; y++) {
                if (y == current.y - 2 || y == current.y + 2) {
                    if (x == current.x - 1 || x == current.x + 1) {
                        possibleMoves.add(new Square(x, y));
                    }
                } else if (x == current.x - 2 || x == current.x + 2) {
                    if (y == current.y - 1 || y == current.y + 1) {
                        possibleMoves.add(new Square(x, y));
                    }
                }
            }
        }

        return isWithinTheListOfPossibleMoves(knight, newLocation, possibleMoves);
    }

    private static boolean moveRook(Piece rook, Square newLocation) {
        HashSet<Square> possibleMoves = new HashSet<>();
        Square current = rook.getLocation();

        for (int x = LOWER_BOUND; x <= UPPER_BOUND; x++) {
            for (int y = LOWER_BOUND; y <= UPPER_BOUND; y++) {
                if (x == current.x || y == current.y) {
                    possibleMoves.add(new Square(x, y));
                }
            }
        }

        return isWithinTheListOfPossibleMoves(rook, newLocation, possibleMoves);
    }

    private static boolean movePawn(Piece pawn, Square newLocation) {
        HashSet<Square> possibleMoves = new HashSet<>();
        Square current = pawn.getLocation();

        boolean firstMove = (current.y == (LOWER_BOUND + 1)) || (current.y == (UPPER_BOUND + 1));

        for (int x = LOWER_BOUND; x <= UPPER_BOUND; x++) {
            for (int y = LOWER_BOUND; y <= UPPER_BOUND; y++) {
                int direction = pawn.getColor().direction();

                if(firstMove && x == current.x && y == (current.y + direction * 2)) {
                    possibleMoves.add(new Square(x, y));
                } else if (x == current.x && y == (current.y + direction)) {
                    possibleMoves.add(new Square(x, y));
                }

                // diagonal capture moves;
            }
        }

        return isWithinTheListOfPossibleMoves(pawn, newLocation, possibleMoves);
    }

    private static boolean isWithinTheListOfPossibleMoves(Piece piece, Square newLocation, HashSet<Square> possibleMoves) {
        Iterator<Square> tempIterator = possibleMoves.iterator();
        boolean moved = false;

        Set<Square> occupiedSquares = Board.getOccupiedSquares();
        HashSet<Square> actualMoves = new HashSet<>();

        while (tempIterator.hasNext()) {
            Square square = tempIterator.next();

            if (occupiedSquares.contains(square)) {

                Piece pieceInSpace = Board.getPiece(square);

                if (piece.getColor() != pieceInSpace.getColor()) {
                    actualMoves.add(square);
                }

            } else {
                actualMoves.add(square);
            }
        }

        Iterator<Square> iterator = actualMoves.iterator();

        while (iterator.hasNext()) {
            Square square = iterator.next();

            if (newLocation.x == square.x && newLocation.y == square.y) {
                if (occupiedSquares.contains(square) && actualMoves.contains(square)) { // capture an enemy piece?
                    Board.remove(square);
                }

                piece.getLocation().x = newLocation.x;
                piece.getLocation().y = newLocation.y;
                moved = true;
                break;
            }
        }

        return moved;
    }
}
