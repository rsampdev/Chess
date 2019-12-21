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

public class PawnTests {

    static Piece pawn;
    static Square location;

    @BeforeEach
    public void before() {
        pawn = Piece.setup(new Square(2,2), Color.WHITE, Type.PAWN);
        location = pawn.getLocation();
        Board.playerTurn = Color.WHITE;
    }

    @AfterEach
    public void after() {
        Board.remove(pawn.getLocation());
        location = null;
        pawn = null;
    }

    @Test
    public void stayStillMove() {
        boolean moved = Board.move(location, new Square(location.x, location.y));

        assertEquals(false, moved);
        assertEquals(2, pawn.getLocation().x);
        assertEquals(2, pawn.getLocation().y);
    }

    @Test
    public void moveUp() {
        boolean moved = Board.move(location, new Square(location.x, location.y + 1));

        assertEquals(true, moved);
        assertEquals(2, pawn.getLocation().x);
        assertEquals(3, pawn.getLocation().y);
    }

    @Test
    public void moveDown() {
        boolean moved = Board.move(location, new Square(location.x, location.y - 1));

        assertEquals(false, moved);
        assertEquals(2, pawn.getLocation().x);
        assertEquals(2, pawn.getLocation().y);
    }

    @Test
    public void moveUpTwoSpaces() {
        boolean moved = Board.move(location, new Square(location.x, location.y + 2));

        assertEquals(true, moved);
        assertEquals(2, pawn.getLocation().x);
        assertEquals(4, pawn.getLocation().y);
    }

    @Test
    public void moveLeft() {
        boolean moved = Board.move(location, new Square(location.x - 1, location.y));

        assertEquals(false, moved);
        assertEquals(2, pawn.getLocation().x);
        assertEquals(2, pawn.getLocation().y);
    }

    @Test
    public void moveRight() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y));

        assertEquals(false, moved);
        assertEquals(2, pawn.getLocation().x);
        assertEquals(2, pawn.getLocation().y);
    }

    @Test
    public void moveAndMoveBack() {
        boolean moved = Board.move(location, new Square(location.x, location.y + 1));

        assertEquals(true, moved);
        assertEquals(2, pawn.getLocation().x);
        assertEquals(3, pawn.getLocation().y);

        Board.playerTurn = Color.WHITE;
        moved = Board.move(location, new Square(location.x, location.y - 1));

        assertEquals(false, moved);
        assertEquals(2, pawn.getLocation().x);
        assertEquals(3, pawn.getLocation().y);
    }

    @Test
    public void captureUpToTheLeft() {
        Piece otherPawn = Piece.setup(new Square(location.x + 1, location.y + 1), Color.BLACK, Type.PAWN);

        boolean moved = Board.move(location, otherPawn.getLocation());

        assertEquals(true, moved);
        assertEquals(3, pawn.getLocation().x);
        assertEquals(3, pawn.getLocation().y);
    }

    @Test
    public void captureUpToTheRight() {
        Piece otherPawn = Piece.setup(new Square(location.x - 1, location.y + 1), Color.BLACK, Type.PAWN);

        boolean moved = Board.move(location, otherPawn.getLocation());

        assertEquals(true, moved);
        assertEquals(1, pawn.getLocation().x);
        assertEquals(3, pawn.getLocation().y);
    }

    @Test
    public void captureOnePieceOfSeveral() {
        Piece otherPawn = Piece.setup(new Square(3,3), Color.BLACK, Type.PAWN);
        Piece pawnNotToCapture = Piece.setup(new Square(4,4), Color.BLACK, Type.PAWN);

        boolean moved = Board.move(location, otherPawn.getLocation());

        assertEquals(true, moved);
        assertEquals(3, pawn.getLocation().x);
        assertEquals(3, pawn.getLocation().y);
        assertEquals(1, Board.getBlackPieces().size());

        Board.remove(pawnNotToCapture.getLocation());

        assertEquals(0, Board.getBlackPieces().size());
    }

    @Test
    public void captureOnePieceThenAnother() {
        Piece firstPawn = Piece.setup(new Square(3,3), Color.BLACK, Type.PAWN);
        Piece nextPawn = Piece.setup(new Square(4,4), Color.BLACK, Type.PAWN);

        boolean moved = Board.move(location, firstPawn.getLocation());

        assertEquals(true, moved);
        assertEquals(3, pawn.getLocation().x);
        assertEquals(3, pawn.getLocation().y);
        assertEquals(1, Board.getBlackPieces().size());

        Board.playerTurn = Color.WHITE;
        moved = Board.move(location, nextPawn.getLocation());

        assertEquals(true, moved);
        assertEquals(4, pawn.getLocation().x);
        assertEquals(4, pawn.getLocation().y);
        assertEquals(0, Board.getBlackPieces().size());
    }

    @Test
    public void attemptToCaptureTeammate() {
        Piece otherPawn = Piece.setup(new Square(3, 3), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, otherPawn.getLocation());

        assertEquals(false, moved);
        assertEquals(2, pawn.getLocation().x);
        assertEquals(2, pawn.getLocation().y);
        assertEquals(2, Board.getWhitePieces().size());

        Board.remove(otherPawn.getLocation());

        assertEquals(1, Board.getWhitePieces().size());
    }
}
