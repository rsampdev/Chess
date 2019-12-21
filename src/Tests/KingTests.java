package Tests;

import Chess.Board;
import Chess.Color;
import Chess.Piece;
import Chess.Square;
import Chess.Type;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KingTests {

    static Piece king;
    static Square location;

    @BeforeEach
    public void before() {
        king = Piece.setup(new Square(2,2), Color.BLACK, Type.KING);
        location = king.getLocation();
    }

    @AfterEach
    public void after() {
        Board.remove(king.getLocation());
        location = null;
        king = null;
    }

    @Test
    public void stayStillMove() {
        boolean moved = Board.move(location, new Square(location.x, location.y));

        assertEquals(false, moved);
        assertEquals(2, king.getLocation().x);
        assertEquals(2, king.getLocation().y);
    }

    @Test
    public void moveUp() {
        boolean moved = Board.move(location, new Square(location.x, location.y + 1));

        assertEquals(true, moved);
        assertEquals(2, king.getLocation().x);
        assertEquals(3, king.getLocation().y);
    }

    @Test
    public void moveDown() {
        boolean moved = Board.move(location, new Square(location.x, location.y - 1));

        assertEquals(true, moved);
        assertEquals(2, king.getLocation().x);
        assertEquals(1, king.getLocation().y);
    }

    @Test
    public void moveLeft() {
        boolean moved = Board.move(location, new Square(location.x - 1, location.y));

        assertEquals(true, moved);
        assertEquals(1, king.getLocation().x);
        assertEquals(2, king.getLocation().y);
    }

    @Test
    public void moveRight() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y));

        assertEquals(true, moved);
        assertEquals(3, king.getLocation().x);
        assertEquals(2, king.getLocation().y);
    }

    @Test
    public void moveUpLeft() {
        boolean moved = Board.move(location, new Square(location.x - 1, location.y + 1));

        assertEquals(true, moved);
        assertEquals(1, king.getLocation().x);
        assertEquals(3, king.getLocation().y);
    }

    @Test
    public void moveUpRight() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y + 1));

        assertEquals(true, moved);
        assertEquals(3, king.getLocation().x);
        assertEquals(3, king.getLocation().y);
    }

    @Test
    public void moveDownLeft() {
        boolean moved = Board.move(location, new Square(location.x - 1, location.y - 1));

        assertEquals(true, moved);
        assertEquals(1, king.getLocation().x);
        assertEquals(1, king.getLocation().y);
    }

    @Test
    public void moveDownRight() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y - 1));

        assertEquals(true, moved);
        assertEquals(3, king.getLocation().x);
        assertEquals(1, king.getLocation().y);
    }

    @Test
    public void moveTwice() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y + 1));

        assertEquals(true, moved);
        assertEquals(3, king.getLocation().x);
        assertEquals(3, king.getLocation().y);

        moved = Board.move(location, new Square(location.x + 1, location.y + 1));

        assertEquals(true, moved);
        assertEquals(4, king.getLocation().x);
        assertEquals(4, king.getLocation().y);
    }

    @Test
    public void moveAndMoveBack() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y + 1));

        assertEquals(true, moved);
        assertEquals(3, king.getLocation().x);
        assertEquals(3, king.getLocation().y);

        moved = Board.move(location, new Square(location.x - 1, location.y - 1));

        assertEquals(true, moved);
        assertEquals(2, king.getLocation().x);
        assertEquals(2, king.getLocation().y);
    }

    @Test
    public void captureOnePiece() {
        Piece pawn = Piece.setup(new Square(3,3), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(3, king.getLocation().x);
        assertEquals(3, king.getLocation().y);
        assertEquals(true, Board.getWhitePieces().isEmpty());
    }

    @Test
    public void captureOnePieceOfSeveral() {
        Piece pawn = Piece.setup(new Square(3,2), Color.WHITE, Type.PAWN);
        Piece pawnNotToCapture = Piece.setup(new Square(4,2), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(3, king.getLocation().x);
        assertEquals(2, king.getLocation().y);
        assertEquals(1, Board.getWhitePieces().size());

        Board.remove(pawnNotToCapture.getLocation());

        assertEquals(0, Board.getWhitePieces().size());
    }

    @Test
    public void captureOnePieceThenAnother() {
        Piece pawn = Piece.setup(new Square(3,2), Color.WHITE, Type.PAWN);
        Piece nextPawn = Piece.setup(new Square(4,2), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(3, king.getLocation().x);
        assertEquals(2, king.getLocation().y);
        assertEquals(1, Board.getWhitePieces().size());

        moved = Board.move(location, nextPawn.getLocation());

        assertEquals(true, moved);
        assertEquals(4, king.getLocation().x);
        assertEquals(2, king.getLocation().y);

        assertEquals(0, Board.getWhitePieces().size());
    }

    @Test
    public void attemptToCaptureTeammate() {
        Piece pawn = Piece.setup(new Square(3, 3), Color.BLACK, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(false, moved);
        assertEquals(2, king.getLocation().x);
        assertEquals(2, king.getLocation().y);
        assertEquals(2, Board.getBlackPieces().size());

        Board.remove(pawn.getLocation());

        assertEquals(1, Board.getBlackPieces().size());
    }
}
