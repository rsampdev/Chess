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

    @BeforeEach
    public void before() {
        rook = Piece.setup(new Square(2, 2), Color.BLACK, Type.ROOK);
        location = rook.getLocation();
        Board.playerTurn = Color.BLACK;
    }

    @AfterEach
    public void after() {
        Board.remove(rook.getLocation());
        location = null;
        rook = null;
    }

    @Test
    public void stayStillMove() {
        boolean moved = Board.move(location, new Square(location.x, location.y));

        assertEquals(false, moved);
        assertEquals(2, rook.getLocation().x);
        assertEquals(2, rook.getLocation().y);
    }

    @Test
    public void moveUp() {
        boolean moved = Board.move(location, new Square(location.x, location.y + 1));

        assertEquals(true, moved);
        assertEquals(2, rook.getLocation().x);
        assertEquals(3, rook.getLocation().y);
    }

    @Test
    public void moveDown() {
        boolean moved = Board.move(location, new Square(location.x, location.y - 1));

        assertEquals(true, moved);
        assertEquals(2, rook.getLocation().x);
        assertEquals(1, rook.getLocation().y);
    }

    @Test
    public void moveLeft() {
        boolean moved = Board.move(location, new Square(location.x - 1, location.y));

        assertEquals(true, moved);
        assertEquals(1, rook.getLocation().x);
        assertEquals(2, rook.getLocation().y);
    }

    @Test
    public void moveRight() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y));

        assertEquals(true, moved);
        assertEquals(3, rook.getLocation().x);
        assertEquals(2, rook.getLocation().y);
    }

    @Test
    public void moveUpAndLeft() {
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
    public void moveUpAndRight() {
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
    public void moveDownAndLeft() {
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
    public void moveDownAndRight() {
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
    public void moveTwice() {
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
    public void moveAndMoveBack() {
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
    public void captureOnePiece() {
        Piece pawn = Piece.setup(new Square(location.x + 1, location.y), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(3, rook.getLocation().x);
        assertEquals(2, rook.getLocation().y);
        assertEquals(true, Board.getWhitePieces().isEmpty());
    }

    @Test
    public void captureOnePieceOfSeveral() {
        Piece pawn = Piece.setup(new Square(3, 2), Color.WHITE, Type.PAWN);
        Piece pawnToNotCapture = Piece.setup(new Square(4, 2), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(3, rook.getLocation().x);
        assertEquals(2, rook.getLocation().y);
        assertEquals(1, Board.getWhitePieces().size());

        Board.remove(pawnToNotCapture.getLocation());

        assertEquals(0, Board.getWhitePieces().size());
    }

    @Test
    public void captureOnePieceThenAnother() {
        Piece pawn = Piece.setup(new Square(3, 2), Color.WHITE, Type.PAWN);
        Piece nextPawn = Piece.setup(new Square(4, 2), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(3, rook.getLocation().x);
        assertEquals(2, rook.getLocation().y);
        assertEquals(1, Board.getWhitePieces().size());

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, nextPawn.getLocation());

        assertEquals(true, moved);
        assertEquals(4, rook.getLocation().x);
        assertEquals(2, rook.getLocation().y);
        assertEquals(0, Board.getWhitePieces().size());
    }

    @Test
    public void attemptToCaptureTeammate() {
        Piece pawn = Piece.setup(new Square(3, 2), Color.BLACK, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(false, moved);
        assertEquals(2, rook.getLocation().x);
        assertEquals(2, rook.getLocation().y);
        assertEquals(2, Board.getBlackPieces().size());

        Board.remove(pawn.getLocation());

        assertEquals(1, Board.getBlackPieces().size());
    }
}
