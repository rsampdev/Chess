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

    private static Piece blackKing;
    private static Piece whiteKing;

    @BeforeEach
    public void before() {
        blackKing = Piece.setup(new Square(1, 8), Color.BLACK, Type.KING);
        whiteKing = Piece.setup(new Square(8, 1), Color.WHITE, Type.KING);
        pawn = Piece.setup(new Square(2,2), Color.WHITE, Type.PAWN);
        location = pawn.getLocation();
        Board.playerTurn = Color.WHITE;
    }

    @AfterEach
    public void after() {
        Board.remove(blackKing.getLocation());
        Board.remove(whiteKing.getLocation());
        Board.remove(pawn.getLocation());
        blackKing = null;
        whiteKing = null;
        location = null;
        pawn = null;
    }

    @Test
    public void pawn_stayStillMove() {
        boolean moved = Board.move(location, new Square(location.x, location.y));

        assertEquals(false, moved);
        assertEquals(2, pawn.getLocation().x);
        assertEquals(2, pawn.getLocation().y);
    }

    @Test
    public void pawn_moveUp() {
        boolean moved = Board.move(location, new Square(location.x, location.y + 1));

        assertEquals(true, moved);
        assertEquals(2, pawn.getLocation().x);
        assertEquals(3, pawn.getLocation().y);
    }

    @Test
    public void pawn_moveDown() {
        boolean moved = Board.move(location, new Square(location.x, location.y - 1));

        assertEquals(false, moved);
        assertEquals(2, pawn.getLocation().x);
        assertEquals(2, pawn.getLocation().y);
    }

    @Test
    public void pawn_moveUpTwoSpaces() {
        boolean moved = Board.move(location, new Square(location.x, location.y + 2));

        assertEquals(true, moved);
        assertEquals(2, pawn.getLocation().x);
        assertEquals(4, pawn.getLocation().y);
    }

    @Test
    public void pawn_moveLeft() {
        boolean moved = Board.move(location, new Square(location.x - 1, location.y));

        assertEquals(false, moved);
        assertEquals(2, pawn.getLocation().x);
        assertEquals(2, pawn.getLocation().y);
    }

    @Test
    public void pawn_moveRight() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y));

        assertEquals(false, moved);
        assertEquals(2, pawn.getLocation().x);
        assertEquals(2, pawn.getLocation().y);
    }

    @Test
    public void pawn_moveAndMoveBack() {
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
    public void pawn_captureUpToTheLeft() {
        Piece otherPawn = Piece.setup(new Square(location.x + 1, location.y + 1), Color.BLACK, Type.PAWN);

        boolean moved = Board.move(location, otherPawn.getLocation());

        assertEquals(true, moved);
        assertEquals(3, pawn.getLocation().x);
        assertEquals(3, pawn.getLocation().y);
    }

    @Test
    public void pawn_captureUpToTheRight() {
        Piece otherPawn = Piece.setup(new Square(location.x - 1, location.y + 1), Color.BLACK, Type.PAWN);

        boolean moved = Board.move(location, otherPawn.getLocation());

        assertEquals(true, moved);
        assertEquals(1, pawn.getLocation().x);
        assertEquals(3, pawn.getLocation().y);
    }

    @Test
    public void pawn_captureOnePieceOfSeveral() {
        Piece otherPawn = Piece.setup(new Square(3,3), Color.BLACK, Type.PAWN);
        Piece pawnNotToCapture = Piece.setup(new Square(4,4), Color.BLACK, Type.PAWN);

        boolean moved = Board.move(location, otherPawn.getLocation());

        assertEquals(true, moved);
        assertEquals(3, pawn.getLocation().x);
        assertEquals(3, pawn.getLocation().y);
        assertEquals(2, Board.getBlackPieces().size());

        Board.remove(pawnNotToCapture.getLocation());

        assertEquals(1, Board.getBlackPieces().size());
    }

    @Test
    public void pawn_captureOnePieceThenAnother() {
        Piece firstPawn = Piece.setup(new Square(3,3), Color.BLACK, Type.PAWN);
        Piece nextPawn = Piece.setup(new Square(4,4), Color.BLACK, Type.PAWN);

        boolean moved = Board.move(location, firstPawn.getLocation());

        assertEquals(true, moved);
        assertEquals(3, pawn.getLocation().x);
        assertEquals(3, pawn.getLocation().y);
        assertEquals(2, Board.getBlackPieces().size());

        Board.playerTurn = Color.WHITE;
        moved = Board.move(location, nextPawn.getLocation());

        assertEquals(true, moved);
        assertEquals(4, pawn.getLocation().x);
        assertEquals(4, pawn.getLocation().y);
        assertEquals(1, Board.getBlackPieces().size());
    }

    @Test
    public void pawn_attemptToCaptureTeammate() {
        Piece otherPawn = Piece.setup(new Square(3, 3), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, otherPawn.getLocation());

        assertEquals(false, moved);
        assertEquals(2, pawn.getLocation().x);
        assertEquals(2, pawn.getLocation().y);
        assertEquals(3, Board.getWhitePieces().size());

        Board.remove(otherPawn.getLocation());

        assertEquals(2, Board.getWhitePieces().size());
    }
}
