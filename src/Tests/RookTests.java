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

public class RookTests {

    static Piece rook;
    static Square location;

    private static Piece blackKing;
    private static Piece whiteKing;

    @BeforeEach
    public void before() {
        blackKing = Piece.setup(new Square(1, 8), Color.BLACK, Type.KING);
        whiteKing = Piece.setup(new Square(5, 5), Color.WHITE, Type.KING);
        rook = Piece.setup(new Square(2, 2), Color.BLACK, Type.ROOK);
        location = rook.getLocation();
        Board.playerTurn = Color.BLACK;
    }

    @AfterEach
    public void after() {
        Board.remove(blackKing.getLocation());
        Board.remove(whiteKing.getLocation());
        Board.remove(rook.getLocation());
        blackKing = null;
        whiteKing = null;
        location = null;
        rook = null;
    }

    @Test
    public void rook_stayStillMove() {
        boolean moved = Board.move(location, new Square(location.x, location.y));

        assertEquals(false, moved);
        assertEquals(2, rook.getLocation().x);
        assertEquals(2, rook.getLocation().y);
    }

    @Test
    public void rook_moveUp() {
        boolean moved = Board.move(location, new Square(location.x, location.y + 1));

        assertEquals(true, moved);
        assertEquals(2, rook.getLocation().x);
        assertEquals(3, rook.getLocation().y);
    }

    @Test
    public void rook_moveDown() {
        boolean moved = Board.move(location, new Square(location.x, location.y - 1));

        assertEquals(true, moved);
        assertEquals(2, rook.getLocation().x);
        assertEquals(1, rook.getLocation().y);
    }

    @Test
    public void rook_moveLeft() {
        boolean moved = Board.move(location, new Square(location.x - 1, location.y));

        assertEquals(true, moved);
        assertEquals(1, rook.getLocation().x);
        assertEquals(2, rook.getLocation().y);
    }

    @Test
    public void rook_moveRight() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y));

        assertEquals(true, moved);
        assertEquals(3, rook.getLocation().x);
        assertEquals(2, rook.getLocation().y);
    }

    @Test
    public void rook_moveUpAndLeft() {
        boolean moved = Board.move(location, new Square(location.x, location.y + 1));

        assertEquals(true, moved);
        assertEquals(2, rook.getLocation().x);
        assertEquals(3, rook.getLocation().y);

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, new Square(location.x - 1, location.y));

        assertEquals(true, moved);
        assertEquals(1, rook.getLocation().x);
        assertEquals(3, rook.getLocation().y);
    }

    @Test
    public void rook_moveUpAndRight() {
        boolean moved = Board.move(location, new Square(location.x, location.y + 1));

        assertEquals(true, moved);
        assertEquals(2, rook.getLocation().x);
        assertEquals(3, rook.getLocation().y);

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, new Square(location.x + 1, location.y));

        assertEquals(true, moved);
        assertEquals(3, rook.getLocation().x);
        assertEquals(3, rook.getLocation().y);
    }

    @Test
    public void rook_moveDownAndLeft() {
        boolean moved = Board.move(location, new Square(location.x, location.y - 1));

        assertEquals(true, moved);
        assertEquals(2, rook.getLocation().x);
        assertEquals(1, rook.getLocation().y);

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, new Square(location.x - 1, location.y));

        assertEquals(true, moved);
        assertEquals(1, rook.getLocation().x);
        assertEquals(1, rook.getLocation().y);
    }

    @Test
    public void rook_moveDownAndRight() {
        boolean moved = Board.move(location, new Square(location.x, location.y - 1));

        assertEquals(true, moved);
        assertEquals(2, rook.getLocation().x);
        assertEquals(1, rook.getLocation().y);

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, new Square(location.x + 1, location.y));

        assertEquals(true, moved);
        assertEquals(3, rook.getLocation().x);
        assertEquals(1, rook.getLocation().y);
    }

    @Test
    public void rook_moveTwice() {
        boolean moved = Board.move(location, new Square(3, 2));

        assertEquals(true, moved);
        assertEquals(3, rook.getLocation().x);
        assertEquals(2, rook.getLocation().y);

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, new Square(4, 2));

        assertEquals(true, moved);
        assertEquals(4, rook.getLocation().x);
        assertEquals(2, rook.getLocation().y);
    }

    @Test
    public void rook_moveAndMoveBack() {
        boolean moved = Board.move(location, new Square(3, 2));

        assertEquals(true, moved);
        assertEquals(3, rook.getLocation().x);
        assertEquals(2, rook.getLocation().y);

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, new Square(2, 2));

        assertEquals(true, moved);
        assertEquals(2, rook.getLocation().x);
        assertEquals(2, rook.getLocation().y);
    }

    @Test
    public void rook_captureOnePiece() {
        Piece pawn = Piece.setup(new Square(location.x + 1, location.y), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(3, rook.getLocation().x);
        assertEquals(2, rook.getLocation().y);
        assertEquals(1, Board.getWhitePieces().size());
    }

    @Test
    public void rook_captureOnePieceOfSeveral() {
        Piece pawn = Piece.setup(new Square(3, 2), Color.WHITE, Type.PAWN);
        Piece pawnToNotCapture = Piece.setup(new Square(4, 2), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(3, rook.getLocation().x);
        assertEquals(2, rook.getLocation().y);
        assertEquals(2, Board.getWhitePieces().size());

        Board.remove(pawnToNotCapture.getLocation());

        assertEquals(1, Board.getWhitePieces().size());
    }

    @Test
    public void rook_captureOnePieceThenAnother() {
        Piece pawn = Piece.setup(new Square(3, 2), Color.WHITE, Type.PAWN);
        Piece nextPawn = Piece.setup(new Square(4, 2), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(3, rook.getLocation().x);
        assertEquals(2, rook.getLocation().y);
        assertEquals(2, Board.getWhitePieces().size());

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, nextPawn.getLocation());

        assertEquals(true, moved);
        assertEquals(4, rook.getLocation().x);
        assertEquals(2, rook.getLocation().y);
        assertEquals(1, Board.getWhitePieces().size());
    }

    @Test
    public void rook_attemptToCaptureTeammate() {
        Piece pawn = Piece.setup(new Square(3, 2), Color.BLACK, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(false, moved);
        assertEquals(2, rook.getLocation().x);
        assertEquals(2, rook.getLocation().y);
        assertEquals(3, Board.getBlackPieces().size());

        Board.remove(pawn.getLocation());

        assertEquals(2, Board.getBlackPieces().size());
    }
}
