package Chess;

// There are three ways of escaping check:
//  1) Moving your king to a non-attacked square
//  2) Blocking the piece(s) delivering check
//  3) Capturing the checking piece.

import java.util.HashSet;

public class Checkmate {

    public static boolean isInCheck() {
        Color playerTurn = Board.playerTurn;
        boolean inCheck = false;

        Piece king = null;
        HashSet<Piece> enemies = null;

        if(playerTurn == Color.BLACK) {
            king = (Piece) Board.getBlackPieces().stream().filter(piece -> piece.getType() == Type.KING).toArray()[0];
            enemies = (HashSet<Piece>) Board.getWhitePieces();
        } else {
            king = (Piece) Board.getWhitePieces().stream().filter(piece -> piece.getType() == Type.KING).toArray()[0];
            enemies = (HashSet<Piece>) Board.getBlackPieces();
        }

        HashSet<Square> possibleKingMoves = Mover.movesFor(king);
        HashSet<Square> possibleEnemyMoves = new HashSet<Square>();

        for (Piece enemy : enemies) {
            possibleEnemyMoves.addAll(Mover.movesFor(enemy));
        }

        // calculate checkmate

        return inCheck;
    }
}
