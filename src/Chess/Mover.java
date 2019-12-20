package Chess;

import java.util.LinkedList;
import java.util.ListIterator;

public class Mover {
    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 8;

    public static boolean move(Piece piece, Square newLocation) {
        boolean moved = false;

        switch (piece) {
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
        LinkedList<Square> possibleMoves = new LinkedList<>();
        Square current = king.getLocation();

        for (int y = LOWER_BOUND; y <= UPPER_BOUND; y++) {
            for (int x = LOWER_BOUND; x <= UPPER_BOUND; x++) {
                if ((x == (current.x - 1) || x == (current.x + 1)) && (y == (current.y - 1) || y == (current.y + 1))) {
                    possibleMoves.add(new Square(x, y));
                } else if (x == current.x && (y == (current.y - 1) || y == (current.y + 1))) {
                    possibleMoves.add(new Square(x, y));
                } else if (y == current.y && (x == (current.x - 1) || x == (current.x + 1))) {
                    possibleMoves.add(new Square(x, y));
                }
            }
        }

        return isWithinTheListOfPossibleMoves(current, newLocation, possibleMoves);
    }

    private static boolean moveQueen(Piece queen, Square newLocation) {
        LinkedList<Square> possibleMoves = new LinkedList<>();
        Square current = queen.getLocation();

        for (int y = LOWER_BOUND; y <= UPPER_BOUND; y++) {
            for (int x = LOWER_BOUND; x <= UPPER_BOUND; x++) {
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

        return isWithinTheListOfPossibleMoves(current, newLocation, possibleMoves);
    }

    private static boolean moveBishop(Piece bishop, Square newLocation) {
        LinkedList<Square> possibleMoves = new LinkedList<>();
        Square current = bishop.getLocation();

        for (int y = LOWER_BOUND; y <= UPPER_BOUND; y++) {
            for (int x = LOWER_BOUND; x <= UPPER_BOUND; x++) {
                if ((current.x + x) == (current.y + y)) {
                    possibleMoves.add(new Square((current.x + x), (current.y + y)));
                }
            }
        }

        return isWithinTheListOfPossibleMoves(current, newLocation, possibleMoves);
    }

    private static boolean moveKnight(Piece knight, Square newLocation) {
        LinkedList<Square> possibleMoves = new LinkedList<>();
        Square current = knight.getLocation();

        for (int y = LOWER_BOUND; y <= UPPER_BOUND; y++) {
            for (int x = LOWER_BOUND; x <= UPPER_BOUND; x++) {
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

        return isWithinTheListOfPossibleMoves(current, newLocation, possibleMoves);
    }

    private static boolean moveRook(Piece rook, Square newLocation) {
        LinkedList<Square> possibleMoves = new LinkedList<>();
        Square current = rook.getLocation();

        for (int y = LOWER_BOUND; y <= UPPER_BOUND; y++) {
            for (int x = LOWER_BOUND; x <= UPPER_BOUND; x++) {
                if (x == current.x || y == current.y) {
                    possibleMoves.add(new Square(x, y));
                }
            }
        }

        return isWithinTheListOfPossibleMoves(current, newLocation, possibleMoves);
    }

    private static boolean movePawn(Piece pawn, Square newLocation) {
        LinkedList<Square> possibleMoves = new LinkedList<>();
        Square current = pawn.getLocation();

        boolean firstMove = (current.y == (LOWER_BOUND + 1)) || (current.y == (UPPER_BOUND + 1));

        for (int y = LOWER_BOUND; y <= UPPER_BOUND; y++) {
            for (int x = LOWER_BOUND; x <= UPPER_BOUND; x++) {
                int direction = pawn.getColor().direction();

                if(firstMove && x == current.x && y == (current.y + direction * 2)) {
                    possibleMoves.add(new Square(x, y));
                } else if (x == current.x && y == (current.y + direction)) {
                    possibleMoves.add(new Square(x, y));
                }

                // diagonal capture moves;
            }
        }

        return isWithinTheListOfPossibleMoves(current, newLocation, possibleMoves);
    }

    private static boolean isWithinTheListOfPossibleMoves(Square current, Square newLocation, LinkedList<Square> possibleMoves) {
        ListIterator<Square> iterator = possibleMoves.listIterator(0);
        boolean moved = false;

        while (iterator.hasNext()) {
            Square square = iterator.next();

            if (newLocation.x == square.x && newLocation.y == square.y) {
                current.x = newLocation.x;
                current.y = newLocation.y;
                moved = true;
                break;
            }
        }

        return moved;
    }
}
