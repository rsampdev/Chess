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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class KnightTests {

    static Piece knight;
    static Square location;

    @BeforeEach
    public void before() {
        knight = Piece.setup(new Square(5,4), Color.BLACK, Type.KNIGHT);
        location = knight.getLocation();
    }

    @AfterEach
    public void after() {
        Board.remove(knight.getLocation());
        location = null;
        knight = null;
    }

    @Test
    public void stayStillMove() {
        boolean moved = Board.move(location, new Square(location.x, location.y));

        assertEquals(false, moved);
        assertEquals(5, knight.getLocation().x);
        assertEquals(4, knight.getLocation().y);
    }

    @Test
    public void moveUpAndRight() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y + 2));

        assertEquals(true, moved);
        assertEquals(6, knight.getLocation().x);
        assertEquals(6, knight.getLocation().y);
    }

    @Test
    public void moveRightAndUp() {
        boolean moved = Board.move(location, new Square(location.x + 2, location.y + 1));

        assertEquals(true, moved);
        assertEquals(7, knight.getLocation().x);
        assertEquals(5, knight.getLocation().y);
    }

    @Test
    public void moveRightAndDown() {
        boolean moved = Board.move(location, new Square(location.x + 2, location.y - 1));

        assertEquals(true, moved);
        assertEquals(7, knight.getLocation().x);
        assertEquals(3, knight.getLocation().y);
    }

    @Test
    public void moveDownAndRight() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y - 2));

        assertEquals(true, moved);
        assertEquals(6, knight.getLocation().x);
        assertEquals(2, knight.getLocation().y);
    }

    @Test
    public void moveDownAndLeft() {
        boolean moved = Board.move(location, new Square(location.x - 1, location.y - 2));

        assertEquals(true, moved);
        assertEquals(4, knight.getLocation().x);
        assertEquals(2, knight.getLocation().y);
    }

    @Test
    public void moveLeftAndDown() {
        boolean moved = Board.move(location, new Square(location.x - 2, location.y - 1));

        assertEquals(true, moved);
        assertEquals(3, knight.getLocation().x);
        assertEquals(3, knight.getLocation().y);
    }

    @Test
    public void moveLeftAndUp() {
        boolean moved = Board.move(location, new Square(location.x - 2, location.y + 1));

        assertEquals(true, moved);
        assertEquals(3, knight.getLocation().x);
        assertEquals(5, knight.getLocation().y);
    }

    @Test
    public void moveUpAndLeft() {
        boolean moved = Board.move(location, new Square(location.x - 1, location.y + 2));

        assertEquals(true, moved);
        assertEquals(4, knight.getLocation().x);
        assertEquals(6, knight.getLocation().y);
    }

    @Test
    public void moveTwice() {
        boolean moved = Board.move(location, new Square(6, 6));

        assertEquals(true, moved);
        assertEquals(6, knight.getLocation().x);
        assertEquals(6, knight.getLocation().y);

        moved = Board.move(location, new Square(7, 4));

        assertEquals(true, moved);
        assertEquals(7, knight.getLocation().x);
        assertEquals(4, knight.getLocation().y);
    }

    @Test
    public void moveAndMoveBack() {
        boolean moved = Board.move(location, new Square(6, 6));

        assertEquals(true, moved);
        assertEquals(6, knight.getLocation().x);
        assertEquals(6, knight.getLocation().y);

        moved = Board.move(location, new Square(7, 4));

        assertEquals(true, moved);
        assertEquals(7, knight.getLocation().x);
        assertEquals(4, knight.getLocation().y);

        moved = Board.move(location, new Square(6, 2));

        assertEquals(true, moved);
        assertEquals(6, knight.getLocation().x);
        assertEquals(2, knight.getLocation().y);

        moved = Board.move(location, new Square(5, 4));

        assertEquals(true, moved);
        assertEquals(5, knight.getLocation().x);
        assertEquals(4, knight.getLocation().y);
    }

    @Test
    public void captureOnePiece() {
        Piece pawn = Piece.setup(new Square(location.x + 1, location.y + 2), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, new Square(location.x + 1, location.y + 2));

        assertTrue(moved);
        assertEquals(6, knight.getLocation().x);
        assertEquals(6, knight.getLocation().y);

        assertTrue(Board.getWhitePieces().isEmpty());
    }

    @Test
    public void captureOnePieceOfSeveral() {
        Piece pawn = Piece.setup(new Square(6, 6), Color.WHITE, Type.PAWN);
        Piece pawnToNotCapture = Piece.setup(new Square(4, 6), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, new Square(6, 6));

        assertEquals(true, moved);
        assertEquals(6, knight.getLocation().x);
        assertEquals(6, knight.getLocation().y);
        assertEquals(1, Board.getWhitePieces().size());

        Board.remove(pawnToNotCapture.getLocation());

        assertEquals(0, Board.getWhitePieces().size());
    }

    @Test
    public void captureOnePieceThenAnother() {
        Piece pawn = Piece.setup(new Square(6, 6), Color.WHITE, Type.PAWN);
        Piece nextPawn = Piece.setup(new Square(7, 4), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, new Square(6, 6));

        assertEquals(true, moved);
        assertEquals(6, knight.getLocation().x);
        assertEquals(6, knight.getLocation().y);
        assertEquals(1, Board.getWhitePieces().size());

        moved = Board.move(location, new Square(7, 4));

        assertEquals(true, moved);
        assertEquals(7, knight.getLocation().x);
        assertEquals(4, knight.getLocation().y);
        assertEquals(0, Board.getWhitePieces().size());
    }
}
