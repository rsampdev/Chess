package Chess;

import java.util.HashSet;
import java.util.Set;

public class Mover {

    public static boolean move(Piece piece, Square newLocation) {
        HashSet<Square> moves = movesFor(piece);
        return isWithinTheSetOfActualPossibleMovesAndThusWasMoved(piece, newLocation, moves);
    }

    public static HashSet<Square> movesFor(Piece piece) {
        HashSet<Square> possibleMoves = new HashSet<>();

        switch (piece.getType()) {
            case KING:
                kingMoves(piece, possibleMoves);
                break;
            case QUEEN:
                queenMoves(piece, possibleMoves);
                break;
            case BISHOP:
                bishopMoves(piece, possibleMoves);
                break;
            case KNIGHT:
                knightMoves(piece, possibleMoves);
                break;
            case ROOK:
                rookMoves(piece, possibleMoves);
                break;
            case PAWN:
                pawnMoves(piece, possibleMoves);
                break;
            default:
                break;
        }

        Set<String> occupiedSquares = Board.getOccupiedSquares();
        HashSet<Square> actualMoves = new HashSet<>();

        for (Square square : possibleMoves) {
            if (occupiedSquares.contains(square.keyPair())) {
                Piece pieceInSpace = Board.getPiece(square);

                if (piece.getColor() != pieceInSpace.getColor()) {
                    actualMoves.add(square);
                }
            } else {
                actualMoves.add(square);
            }
        }

        return actualMoves;
    }

    private static void kingMoves(Piece king, HashSet<Square> possibleMoves) {
        Square current = king.getLocation();
        Square temp = null;

        for (int x = Board.LOWER_BOUND; x <= Board.UPPER_BOUND; x++) {
            for (int y = Board.LOWER_BOUND; y <= Board.UPPER_BOUND; y++) {
                temp = new Square(x, y);

                if ((y == current.y + 1 || y == current.y - 1) && (x == current.x - 1 || x == current.x || x == current.x + 1)) {
                    possibleMoves.add(temp);
                    continue;
                } else if (y == current.y && (x == current.x - 1 || x == current.x + 1)) {
                    possibleMoves.add(temp);
                }
            }
        }
    }

    private static void queenMoves(Piece queen, HashSet<Square> possibleMoves) {
        bishopMoves(queen, possibleMoves);
        rookMoves(queen, possibleMoves);
    }

    private static void bishopMoves(Piece bishop, HashSet<Square> possibleMoves) {
        Square current = bishop.getLocation();
        Square temp = null;

        for (int x = Board.LOWER_BOUND; x <= Board.UPPER_BOUND; x++) {
            for (int y = Board.LOWER_BOUND; y <= Board.UPPER_BOUND; y++) {
                temp = new Square(x, y);

                if (current.x + x == current.y + y) {
                    possibleMoves.add(temp);
                } else if (((x + current.x) - (y - current.y)) == 2 * x) {
                    possibleMoves.add(temp);
                }
            }
        }
    }

    private static void knightMoves(Piece knight, HashSet<Square> possibleMoves) {
        Square current = knight.getLocation();
        Square temp = null;

        for (int x = Board.LOWER_BOUND; x <= Board.UPPER_BOUND; x++) {
            for (int y = Board.LOWER_BOUND; y <= Board.UPPER_BOUND; y++) {
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
    }

    private static void rookMoves(Piece rook, HashSet<Square> possibleMoves) {
        Square current = rook.getLocation();
        Square temp = null;

        for (int x = Board.LOWER_BOUND; x <= Board.UPPER_BOUND; x++) {
            for (int y = Board.LOWER_BOUND; y <= Board.UPPER_BOUND; y++) {
                temp = new Square(x, y);

                if (x == current.x || y == current.y) {
                    possibleMoves.add(temp);
                }
            }
        }
    }

    private static void pawnMoves(Piece pawn, HashSet<Square> possibleMoves) {
        Square current = pawn.getLocation();
        Color color = pawn.getColor();
        Square temp = null;

        boolean firstMove = (color == Color.WHITE && current.y == (Board.LOWER_BOUND + 1)) || (color == Color.BLACK && current.y == (Board.UPPER_BOUND + 1));

        for (int x = Board.LOWER_BOUND; x <= Board.UPPER_BOUND; x++) {
            for (int y = Board.LOWER_BOUND; y <= Board.UPPER_BOUND; y++) {
                int direction = color.direction();
                temp = new Square(x, y);

                if(firstMove && x == current.x && y == (current.y + direction * 2)) {
                    possibleMoves.add(temp);
                } else if (x == current.x && y == (current.y + direction)) {
                    possibleMoves.add(temp);
                } else if (y == current.y + 1 && (x == current.x - 1 || x == current.x + 1)) {
                    if (Board.getPiece(temp) != null) {
                        possibleMoves.add(temp);
                    }
                }
            }
        }
    }

    private static boolean isWithinTheSetOfActualPossibleMovesAndThusWasMoved(Piece piece, Square newLocation, HashSet<Square> moves) {
        Set<String> occupiedSquares = Board.getOccupiedSquares();
        boolean moved = false;

        for (Square square : moves) {
            if (newLocation.x == square.x && newLocation.y == square.y) {
                if (occupiedSquares.contains(square.keyPair()) && moves.contains(square)) { // capture an enemy piece?
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
