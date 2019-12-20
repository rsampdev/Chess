package Tests;

import Chess.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void moveKingLeft() {
        boolean moved = Board.move(location, new Square(location.x - 1, location.y));

        assertTrue(moved);
        assertEquals(1, king.getLocation().x);
        assertEquals(2, king.getLocation().y);
    }

    @Test
    public void moveKingRight() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y));

        assertTrue(moved);
        assertEquals(3, king.getLocation().x);
        assertEquals(2, king.getLocation().y);
    }

    @Test
    public void moveKingUp() {
        boolean moved = Board.move(location, new Square(location.x, location.y + 1));

        assertTrue(moved);
        assertEquals(2, king.getLocation().x);
        assertEquals(3, king.getLocation().y);
    }

    @Test
    public void moveKingDown() {
        boolean moved = Board.move(location, new Square(location.x, location.y - 1));

        assertTrue(moved);
        assertEquals(2, king.getLocation().x);
        assertEquals(1, king.getLocation().y);
    }

    @Test
    public void moveKingUpLeft() {
        boolean moved = Board.move(location, new Square(location.x - 1, location.y + 1));

        assertTrue(moved);
        assertEquals(1, king.getLocation().x);
        assertEquals(3, king.getLocation().y);
    }

    @Test
    public void moveKingUpRight() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y + 1));

        assertTrue(moved);
        assertEquals(3, king.getLocation().x);
        assertEquals(3, king.getLocation().y);
    }

    @Test
    public void moveKingDownLeft() {
        boolean moved = Board.move(location, new Square(location.x - 1, location.y - 1));

        assertTrue(moved);
        assertEquals(1, king.getLocation().x);
        assertEquals(1, king.getLocation().y);
    }

    @Test
    public void moveKingDownRight() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y - 1));

        assertTrue(moved);
        assertEquals(3, king.getLocation().x);
        assertEquals(1, king.getLocation().y);
    }

    @Test
    public void moveKingTwice() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y + 1));

        assertTrue(moved);
        assertEquals(3, king.getLocation().x);
        assertEquals(3, king.getLocation().y);

        moved = Board.move(location, new Square(location.x + 1, location.y + 1));

        assertTrue(moved);
        assertEquals(4, king.getLocation().x);
        assertEquals(4, king.getLocation().y);
    }

    @Test
    public void moveAndMoveBack() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y + 1));

        assertTrue(moved);
        assertEquals(3, king.getLocation().x);
        assertEquals(3, king.getLocation().y);

        moved = Board.move(location, new Square(location.x - 1, location.y - 1));

        assertTrue(moved);
        assertEquals(2, king.getLocation().x);
        assertEquals(2, king.getLocation().y);
    }

    @Test
    public void captureOnePiece() {
        Board.remove(location);

        king = Piece.setup(new Square(4,4), Color.BLACK, Type.KING);
        location = king.getLocation();

        Piece pawn = Piece.setup(new Square(5,5), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertTrue(moved);
        assertEquals(5, king.getLocation().x);
        assertEquals(5, king.getLocation().y);

        assertTrue(Board.getWhitePieces().isEmpty());
    }

    @Test
    public void captureOnePieceOfSeveral() {
        Board.remove(location);

        king = Piece.setup(new Square(4,4), Color.BLACK, Type.KING);
        location = king.getLocation();

        Piece pawn = Piece.setup(new Square(5,5), Color.WHITE, Type.PAWN);
        Piece pawnNotToCapture = Piece.setup(new Square(3,5), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertTrue(moved);
        assertEquals(5, king.getLocation().x);
        assertEquals(5, king.getLocation().y);

        assertTrue(Board.getWhitePieces().size() == 1);

        Board.remove(pawnNotToCapture.getLocation());

        assertTrue(Board.getWhitePieces().size() == 0);
    }

    @Test
    public void captureOnePieceThenAnother() {
        Board.remove(location);

        king = Piece.setup(new Square(4,4), Color.BLACK, Type.KING);
        location = king.getLocation();

        Piece pawn = Piece.setup(new Square(5,5), Color.WHITE, Type.PAWN);
        Piece nextPawn = Piece.setup(new Square(3,5), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertTrue(moved);
        assertEquals(5, king.getLocation().x);
        assertEquals(5, king.getLocation().y);

        assertTrue(Board.getWhitePieces().size() == 1);

        moved = Board.move(location, new Square(4, 5));
        assertTrue(moved);
        assertEquals(4, king.getLocation().x);
        assertEquals(5, king.getLocation().y);


        moved = Board.move(location, nextPawn.getLocation());
        assertTrue(moved);
        assertEquals(3, king.getLocation().x);
        assertEquals(5, king.getLocation().y);
        
        assertTrue(Board.getWhitePieces().size() == 0);
    }
}
