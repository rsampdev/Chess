package Chess;

// There are three ways of escaping check:
//  1) Moving your king to a non-attacked square
//  2) Blocking the piece(s) delivering check
//  3) Capturing the checking piece.

import java.util.HashSet;

public class Checkmate {

    public static boolean isInCheck() {
        Color playerTurn = Board.playerTurn;
        boolean canEscape = true;

        Piece king = null;
        Square kingLocation = null;
        HashSet<Piece> enemies = null;

        if(playerTurn == Color.BLACK) {
            king = (Piece) Board.getBlackPieces().stream().filter(piece -> piece.getType() == Type.KING).toArray()[0];
            enemies = Board.getWhitePieces();
        } else {
            king = (Piece) Board.getWhitePieces().stream().filter(piece -> piece.getType() == Type.KING).toArray()[0];
            enemies = Board.getBlackPieces();
        }

        kingLocation = king.getLocation();

        HashSet<Square> possibleKingMoves = Mover.movesFor(king);
        HashSet<Square> actualKingMoves = Mover.movesFor(king);
        HashSet<Square> possibleEnemyMoves = new HashSet<>();

        for (Piece enemy : enemies) {
            possibleEnemyMoves.addAll(Mover.movesFor(enemy));
        }

        for (Square possibleEnemyMove : possibleEnemyMoves) {
            if (possibleEnemyMove.x == kingLocation.x && possibleEnemyMove.y == kingLocation.y) {
                Board.kingInCheck = true;
                break;
            }
        }

        for (Square possibleEnemyMove : possibleEnemyMoves) {
            for (Square possibleKingMove : possibleKingMoves) {
                if(possibleKingMove.x == possibleEnemyMove.x && possibleKingMove.y == possibleEnemyMove.y) {
                    actualKingMoves.remove(possibleKingMove);
                }
            }
        }

        if (actualKingMoves.isEmpty()) {
            canEscape = false;
        }

        return !canEscape;
    }
}
