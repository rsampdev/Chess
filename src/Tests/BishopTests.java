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

public class BishopTests {

    static Piece bishop;
    static Square location;

    @BeforeEach
    public void before() {
        bishop = Piece.setup(new Square(4, 4), Color.BLACK, Type.BISHOP);
        location = bishop.getLocation();
    }

    @AfterEach
    public void after() {
        Board.remove(bishop.getLocation());
        location = null;
        bishop = null;
    }

    @Test
    public void stayStillMove() {
        boolean moved = Board.move(location, new Square(location.x, location.y));

        assertEquals(false, moved);
        assertEquals(4, bishop.getLocation().x);
        assertEquals(4, bishop.getLocation().y);
    }

    @Test
    public void moveUpLeft() {
        boolean moved = Board.move(location, new Square(location.x - 1, location.y + 1));

        assertEquals(true, moved);
        assertEquals(3, bishop.getLocation().x);
        assertEquals(5, bishop.getLocation().y);
    }

    @Test
    public void moveUpRight() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y + 1));

        assertEquals(true, moved);
        assertEquals(5, bishop.getLocation().x);
        assertEquals(5, bishop.getLocation().y);
    }

    @Test
    public void moveDownLeft() {
        boolean moved = Board.move(location, new Square(location.x - 1, location.y - 1));

        assertEquals(true, moved);
        assertEquals(3, bishop.getLocation().x);
        assertEquals(3, bishop.getLocation().y);
    }

    @Test
    public void moveDownRight() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y - 1));

        assertEquals(true, moved);
        assertEquals(5, bishop.getLocation().x);
        assertEquals(3, bishop.getLocation().y);
    }

    @Test
    public void captureOnePiece() {
        Piece pawn = Piece.setup(new Square(location.x + 1, location.y + 1), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(5, bishop.getLocation().x);
        assertEquals(5, bishop.getLocation().y);
        assertEquals(true, Board.getWhitePieces().isEmpty());
    }

    @Test
    public void captureOnePieceOfSeveral() {
        Piece pawn = Piece.setup(new Square(5, 5), Color.WHITE, Type.PAWN);
        Piece pawnToNotCapture = Piece.setup(new Square(6, 6), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(5, bishop.getLocation().x);
        assertEquals(5, bishop.getLocation().y);
        assertEquals(1, Board.getWhitePieces().size());

        Board.remove(pawnToNotCapture.getLocation());

        assertEquals(0, Board.getWhitePieces().size());
    }

    @Test
    public void captureOnePieceThenAnother() {
        Piece pawn = Piece.setup(new Square(5, 5), Color.WHITE, Type.PAWN);
        Piece nextPawn = Piece.setup(new Square(6, 6), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(5, bishop.getLocation().x);
        assertEquals(5, bishop.getLocation().y);
        assertEquals(1, Board.getWhitePieces().size());

        moved = Board.move(location, nextPawn.getLocation());

        assertEquals(true, moved);
        assertEquals(6, bishop.getLocation().x);
        assertEquals(6, bishop.getLocation().y);
        assertEquals(0, Board.getWhitePieces().size());
    }

    @Test
    public void attemptToCaptureTeammate() {
        Piece pawn = Piece.setup(new Square(5, 5), Color.BLACK, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(false, moved);
        assertEquals(4, bishop.getLocation().x);
        assertEquals(4, bishop.getLocation().y);
        assertEquals(2, Board.getBlackPieces().size());

        Board.remove(pawn.getLocation());

        assertEquals(1, Board.getBlackPieces().size());
    }
}
