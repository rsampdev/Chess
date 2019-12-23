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

public class KnightTests {

    static Piece knight;
    static Square location;

    private static Piece blackKing;
    private static Piece whiteKing;

    @BeforeEach
    public void before() {
        blackKing = Piece.setup(new Square(1, 8), Color.BLACK, Type.KING);
        whiteKing = Piece.setup(new Square(1, 1), Color.WHITE, Type.KING);
        knight = Piece.setup(new Square(5,4), Color.BLACK, Type.KNIGHT);
        location = knight.getLocation();
        Board.playerTurn = Color.BLACK;
    }

    @AfterEach
    public void after() {
        Board.remove(blackKing.getLocation());
        Board.remove(whiteKing.getLocation());
        Board.remove(knight.getLocation());
        blackKing = null;
        whiteKing = null;
        location = null;
        knight = null;
    }

    @Test
    public void knight_stayStillMove() {
        boolean moved = Board.move(location, new Square(location.x, location.y));

        assertEquals(false, moved);
        assertEquals(5, knight.getLocation().x);
        assertEquals(4, knight.getLocation().y);
    }

    @Test
    public void knight_moveUpAndRight() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y + 2));

        assertEquals(true, moved);
        assertEquals(6, knight.getLocation().x);
        assertEquals(6, knight.getLocation().y);
    }

    @Test
    public void knight_moveRightAndUp() {
        boolean moved = Board.move(location, new Square(location.x + 2, location.y + 1));

        assertEquals(true, moved);
        assertEquals(7, knight.getLocation().x);
        assertEquals(5, knight.getLocation().y);
    }

    @Test
    public void knight_moveRightAndDown() {
        boolean moved = Board.move(location, new Square(location.x + 2, location.y - 1));

        assertEquals(true, moved);
        assertEquals(7, knight.getLocation().x);
        assertEquals(3, knight.getLocation().y);
    }

    @Test
    public void knight_moveDownAndRight() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y - 2));

        assertEquals(true, moved);
        assertEquals(6, knight.getLocation().x);
        assertEquals(2, knight.getLocation().y);
    }

    @Test
    public void knight_moveDownAndLeft() {
        boolean moved = Board.move(location, new Square(location.x - 1, location.y - 2));

        assertEquals(true, moved);
        assertEquals(4, knight.getLocation().x);
        assertEquals(2, knight.getLocation().y);
    }

    @Test
    public void knight_moveLeftAndDown() {
        boolean moved = Board.move(location, new Square(location.x - 2, location.y - 1));

        assertEquals(true, moved);
        assertEquals(3, knight.getLocation().x);
        assertEquals(3, knight.getLocation().y);
    }

    @Test
    public void knight_moveLeftAndUp() {
        boolean moved = Board.move(location, new Square(location.x - 2, location.y + 1));

        assertEquals(true, moved);
        assertEquals(3, knight.getLocation().x);
        assertEquals(5, knight.getLocation().y);
    }

    @Test
    public void knight_moveUpAndLeft() {
        boolean moved = Board.move(location, new Square(location.x - 1, location.y + 2));

        assertEquals(true, moved);
        assertEquals(4, knight.getLocation().x);
        assertEquals(6, knight.getLocation().y);
    }

    @Test
    public void knight_moveTwice() {
        boolean moved = Board.move(location, new Square(6, 6));

        assertEquals(true, moved);
        assertEquals(6, knight.getLocation().x);
        assertEquals(6, knight.getLocation().y);

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, new Square(7, 4));

        assertEquals(true, moved);
        assertEquals(7, knight.getLocation().x);
        assertEquals(4, knight.getLocation().y);
    }

    @Test
    public void knight_moveAndMoveBack() {
        boolean moved = Board.move(location, new Square(6, 6));

        assertEquals(true, moved);
        assertEquals(6, knight.getLocation().x);
        assertEquals(6, knight.getLocation().y);

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, new Square(7, 4));

        assertEquals(true, moved);
        assertEquals(7, knight.getLocation().x);
        assertEquals(4, knight.getLocation().y);

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, new Square(6, 2));

        assertEquals(true, moved);
        assertEquals(6, knight.getLocation().x);
        assertEquals(2, knight.getLocation().y);

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, new Square(5, 4));

        assertEquals(true, moved);
        assertEquals(5, knight.getLocation().x);
        assertEquals(4, knight.getLocation().y);
    }

    @Test
    public void knight_captureOnePiece() {
        Piece pawn = Piece.setup(new Square(location.x + 1, location.y + 2), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(6, knight.getLocation().x);
        assertEquals(6, knight.getLocation().y);
        assertEquals(1, Board.getWhitePieces().size());
    }

    @Test
    public void knight_captureOnePieceOfSeveral() {
        Piece pawn = Piece.setup(new Square(6, 6), Color.WHITE, Type.PAWN);
        Piece pawnToNotCapture = Piece.setup(new Square(4, 6), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(6, knight.getLocation().x);
        assertEquals(6, knight.getLocation().y);
        assertEquals(2, Board.getWhitePieces().size());

        Board.remove(pawnToNotCapture.getLocation());

        assertEquals(1, Board.getWhitePieces().size());
    }

    @Test
    public void knight_captureOnePieceThenAnother() {
        Piece pawn = Piece.setup(new Square(6, 6), Color.WHITE, Type.PAWN);
        Piece nextPawn = Piece.setup(new Square(7, 4), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(6, knight.getLocation().x);
        assertEquals(6, knight.getLocation().y);
        assertEquals(2, Board.getWhitePieces().size());

        Board.playerTurn = Color.BLACK;
        moved = Board.move(location, nextPawn.getLocation());

        assertEquals(true, moved);
        assertEquals(7, knight.getLocation().x);
        assertEquals(4, knight.getLocation().y);
        assertEquals(1, Board.getWhitePieces().size());
    }

    @Test
    public void knight_attemptToCaptureTeammate() {
        Piece pawn = Piece.setup(new Square(6, 6), Color.BLACK, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(false, moved);
        assertEquals(5, knight.getLocation().x);
        assertEquals(4, knight.getLocation().y);
        assertEquals(3, Board.getBlackPieces().size());

        Board.remove(pawn.getLocation());

        assertEquals(2, Board.getBlackPieces().size());
    }
}
