package Tests;

import Chess.Board;
import Chess.Color;
import Chess.Piece;
import Chess.Square;
import Chess.Type;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueenTests {

    static Piece queen;
    static Square location;

    private static Piece blackKing;
    private static Piece whiteKing;

    @BeforeEach
    public void before() {
        blackKing = Piece.setup(new Square(1, 8), Color.BLACK, Type.KING);
        whiteKing = Piece.setup(new Square(8, 1), Color.WHITE, Type.KING);
        queen = Piece.setup(new Square(4, 4), Color.BLACK, Type.QUEEN);
        location = queen.getLocation();
        Board.playerTurn = Color.BLACK;
    }

    @AfterEach
    public void after() {
        Board.remove(blackKing.getLocation());
        Board.remove(whiteKing.getLocation());
        Board.remove(queen.getLocation());
        blackKing = null;
        whiteKing = null;
        location = null;
        queen = null;
    }

    @Test
    public void queen_stayStillMove() {
        boolean moved = Board.move(location, new Square(location.x, location.y));

        assertEquals(false, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
    }

    @Test
    public void queen_moveUp() {
        boolean moved = Board.move(location, new Square(location.x, location.y + 1));

        assertEquals(true, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);
    }

    @Test
    public void queen_moveDown() {
        boolean moved = Board.move(location, new Square(location.x, location.y - 1));

        assertEquals(true, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(3, queen.getLocation().y);
    }

    @Test
    public void queen_moveLeft() {
        boolean moved = Board.move(location, new Square(location.x - 1, location.y));

        assertEquals(true, moved);
        assertEquals(3, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
    }

    @Test
    public void queen_moveRight() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y));

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
    }

    @Test
    public void queen_moveUpAndLeft() {
        boolean moved = Board.move(location, new Square(location.x, location.y + 1));

        assertEquals(true, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, new Square(location.x - 1, location.y));

        assertEquals(true, moved);
        assertEquals(3, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);
    }

    @Test
    public void queen_moveUpAndRight() {
        boolean moved = Board.move(location, new Square(location.x, location.y + 1));

        assertEquals(true, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, new Square(location.x + 1, location.y));

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);
    }

    @Test
    public void queen_moveDownAndLeft() {
        boolean moved = Board.move(location, new Square(location.x, location.y - 1));

        assertEquals(true, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(3, queen.getLocation().y);

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, new Square(location.x - 1, location.y));

        assertEquals(true, moved);
        assertEquals(3, queen.getLocation().x);
        assertEquals(3, queen.getLocation().y);
    }

    @Test
    public void queen_moveDownAndRight() {
        boolean moved = Board.move(location, new Square(location.x, location.y - 1));

        assertEquals(true, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(3, queen.getLocation().y);

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, new Square(location.x + 1, location.y));

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(3, queen.getLocation().y);
    }

    @Test
    public void queen_moveUpLeft() {
        boolean moved = Board.move(location, new Square(location.x - 1, location.y + 1));

        assertEquals(true, moved);
        assertEquals(3, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);
    }

    @Test
    public void queen_moveUpRight() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y + 1));

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);
    }

    @Test
    public void queen_moveDownLeft() {
        boolean moved = Board.move(location, new Square(location.x - 1, location.y - 1));

        assertEquals(true, moved);
        assertEquals(3, queen.getLocation().x);
        assertEquals(3, queen.getLocation().y);
    }

    @Test
    public void queen_moveDownRight() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y - 1));

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(3, queen.getLocation().y);
    }

    @Test
    public void queen_moveAllTheWayUpAndAllTheWayOver() {
        boolean moved = Board.move(location, new Square(location.x, 8));

        assertEquals(true, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(8, queen.getLocation().y);

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, new Square(8, location.y));

        assertEquals(true, moved);
        assertEquals(8, queen.getLocation().x);
        assertEquals(8, queen.getLocation().y);
    }

    @Test
    public void queen_moveTwiceLikeRook() {
        boolean moved = Board.move(location, new Square(5, 4));

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, new Square(6, 4));

        assertEquals(true, moved);
        assertEquals(6, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
    }

    @Test
    public void queen_moveAndMoveBackLikeBishop() {
        boolean moved = Board.move(location, new Square(5, 5));

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, new Square(4, 4));

        assertEquals(true, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
    }

    @Test
    public void queen_moveTwiceLikeBishop() {
        boolean moved = Board.move(location, new Square(5, 5));

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, new Square(6, 6));

        assertEquals(true, moved);
        assertEquals(6, queen.getLocation().x);
        assertEquals(6, queen.getLocation().y);
    }

    @Test
    public void queen_moveAndMoveBackLikeRook() {
        boolean moved = Board.move(location, new Square(5, 4));

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, new Square(4, 4));

        assertEquals(true, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
    }

    @Test
    public void queen_captureOnePieceLikeRook() {
        Piece pawn = Piece.setup(new Square(location.x + 1, location.y), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
        assertEquals(1, Board.getWhitePieces().size());
    }

    @Test
    public void queen_captureOnePieceOfSeveralLikeRook() {
        Piece pawn = Piece.setup(new Square(5, 4), Color.WHITE, Type.PAWN);
        Piece pawnToNotCapture = Piece.setup(new Square(6, 4), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
        assertEquals(2, Board.getWhitePieces().size());

        Board.remove(pawnToNotCapture.getLocation());

        assertEquals(1, Board.getWhitePieces().size());
    }

    @Test
    public void queen_captureOnePieceThenAnotherLikeRook() {
        Piece pawn = Piece.setup(new Square(5, 4), Color.WHITE, Type.PAWN);
        Piece nextPawn = Piece.setup(new Square(6, 4), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
        assertEquals(2, Board.getWhitePieces().size());

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, nextPawn.getLocation());

        assertEquals(true, moved);
        assertEquals(6, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
        assertEquals(1, Board.getWhitePieces().size());
    }

    @Test
    public void queen_attemptToCaptureTeammateLikeRook() {
        Piece pawn = Piece.setup(new Square(5, 4), Color.BLACK, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(false, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
        assertEquals(3, Board.getBlackPieces().size());

        Board.remove(pawn.getLocation());

        assertEquals(2, Board.getBlackPieces().size());
    }

    @Test
    public void queen_captureOnePieceLikeBishop() {
        Piece pawn = Piece.setup(new Square(location.x + 1, location.y + 1), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);
        assertEquals(1, Board.getWhitePieces().size());
    }

    @Test
    public void queen_captureOnePieceOfSeveralLikeBishop() {
        Piece pawn = Piece.setup(new Square(5, 5), Color.WHITE, Type.PAWN);
        Piece pawnToNotCapture = Piece.setup(new Square(6, 6), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);
        assertEquals(2, Board.getWhitePieces().size());

        Board.remove(pawnToNotCapture.getLocation());

        assertEquals(1, Board.getWhitePieces().size());
    }

    @Test
    public void queen_captureOnePieceThenAnotherLikeBishop() {
        Piece pawn = Piece.setup(new Square(5, 5), Color.WHITE, Type.PAWN);
        Piece nextPawn = Piece.setup(new Square(6, 6), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);
        assertEquals(2, Board.getWhitePieces().size());

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, nextPawn.getLocation());

        assertEquals(true, moved);
        assertEquals(6, queen.getLocation().x);
        assertEquals(6, queen.getLocation().y);
        assertEquals(1, Board.getWhitePieces().size());
    }

    @Test
    public void queen_attemptToCaptureTeammateLikeBishop() {
        Piece pawn = Piece.setup(new Square(5, 5), Color.BLACK, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(false, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
        assertEquals(3, Board.getBlackPieces().size());

        Board.remove(pawn.getLocation());

        assertEquals(2, Board.getBlackPieces().size());
    }
}
