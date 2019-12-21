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

    @BeforeEach
    public void before() {
        queen = Piece.setup(new Square(4, 4), Color.BLACK, Type.QUEEN);
        location = queen.getLocation();
    }

    @AfterEach
    public void after() {
        Board.remove(queen.getLocation());
        location = null;
        queen = null;
    }

    @Test
    public void stayStillMove() {
        boolean moved = Board.move(location, new Square(location.x, location.y));

        assertEquals(false, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
    }

    @Test
    public void moveUp() {
        boolean moved = Board.move(location, new Square(location.x, location.y + 1));

        assertEquals(true, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);
    }

    @Test
    public void moveDown() {
        boolean moved = Board.move(location, new Square(location.x, location.y - 1));

        assertEquals(true, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(3, queen.getLocation().y);
    }

    @Test
    public void moveLeft() {
        boolean moved = Board.move(location, new Square(location.x - 1, location.y));

        assertEquals(true, moved);
        assertEquals(3, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
    }

    @Test
    public void moveRight() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y));

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
    }

    @Test
    public void moveUpAndLeft() {
        boolean moved = Board.move(location, new Square(location.x, location.y + 1));

        assertEquals(true, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);

        moved = Board.move(location, new Square(location.x - 1, location.y));

        assertEquals(true, moved);
        assertEquals(3, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);
    }

    @Test
    public void moveUpAndRight() {
        boolean moved = Board.move(location, new Square(location.x, location.y + 1));

        assertEquals(true, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);

        moved = Board.move(location, new Square(location.x + 1, location.y));

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);
    }

    @Test
    public void moveDownAndLeft() {
        boolean moved = Board.move(location, new Square(location.x, location.y - 1));

        assertEquals(true, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(3, queen.getLocation().y);

        moved = Board.move(location, new Square(location.x - 1, location.y));

        assertEquals(true, moved);
        assertEquals(3, queen.getLocation().x);
        assertEquals(3, queen.getLocation().y);
    }

    @Test
    public void moveDownAndRight() {
        boolean moved = Board.move(location, new Square(location.x, location.y - 1));

        assertEquals(true, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(3, queen.getLocation().y);

        moved = Board.move(location, new Square(location.x + 1, location.y));

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(3, queen.getLocation().y);
    }

    @Test
    public void moveUpLeft() {
        boolean moved = Board.move(location, new Square(location.x - 1, location.y + 1));

        assertEquals(true, moved);
        assertEquals(3, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);
    }

    @Test
    public void moveUpRight() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y + 1));

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);
    }

    @Test
    public void moveDownLeft() {
        boolean moved = Board.move(location, new Square(location.x - 1, location.y - 1));

        assertEquals(true, moved);
        assertEquals(3, queen.getLocation().x);
        assertEquals(3, queen.getLocation().y);
    }

    @Test
    public void moveDownRight() {
        boolean moved = Board.move(location, new Square(location.x + 1, location.y - 1));

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(3, queen.getLocation().y);
    }

    @Test
    public void moveAllTheWayUpAndAllTheWayOver() {
        boolean moved = Board.move(location, new Square(location.x, 8));

        assertEquals(true, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(8, queen.getLocation().y);

        moved = Board.move(location, new Square(8, location.y));

        assertEquals(true, moved);
        assertEquals(8, queen.getLocation().x);
        assertEquals(8, queen.getLocation().y);
    }

    @Test
    public void moveTwiceLikeRook() {
        boolean moved = Board.move(location, new Square(5, 4));

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);

        moved = Board.move(location, new Square(6, 4));

        assertEquals(true, moved);
        assertEquals(6, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
    }

    @Test
    public void moveAndMoveBackLikeBishop() {
        boolean moved = Board.move(location, new Square(5, 5));

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);

        moved = Board.move(location, new Square(4, 4));

        assertEquals(true, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
    }

    @Test
    public void moveTwiceLikeBishop() {
        boolean moved = Board.move(location, new Square(5, 5));

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);

        moved = Board.move(location, new Square(6, 6));

        assertEquals(true, moved);
        assertEquals(6, queen.getLocation().x);
        assertEquals(6, queen.getLocation().y);
    }

    @Test
    public void moveAndMoveBackLikeRook() {
        boolean moved = Board.move(location, new Square(5, 4));

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);

        moved = Board.move(location, new Square(4, 4));

        assertEquals(true, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
    }

    @Test
    public void captureOnePieceLikeRook() {
        Piece pawn = Piece.setup(new Square(location.x + 1, location.y), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
        assertEquals(true, Board.getWhitePieces().isEmpty());
    }

    @Test
    public void captureOnePieceOfSeveralLikeRook() {
        Piece pawn = Piece.setup(new Square(5, 4), Color.WHITE, Type.PAWN);
        Piece pawnToNotCapture = Piece.setup(new Square(6, 4), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
        assertEquals(1, Board.getWhitePieces().size());

        Board.remove(pawnToNotCapture.getLocation());

        assertEquals(0, Board.getWhitePieces().size());
    }

    @Test
    public void captureOnePieceThenAnotherLikeRook() {
        Piece pawn = Piece.setup(new Square(5, 4), Color.WHITE, Type.PAWN);
        Piece nextPawn = Piece.setup(new Square(6, 4), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
        assertEquals(1, Board.getWhitePieces().size());

        moved = Board.move(location, nextPawn.getLocation());

        assertEquals(true, moved);
        assertEquals(6, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
        assertEquals(0, Board.getWhitePieces().size());
    }

    @Test
    public void attemptToCaptureTeammateLikeRook() {
        Piece pawn = Piece.setup(new Square(5, 4), Color.BLACK, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(false, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
        assertEquals(2, Board.getBlackPieces().size());

        Board.remove(pawn.getLocation());

        assertEquals(1, Board.getBlackPieces().size());
    }

    @Test
    public void captureOnePieceLikeBishop() {
        Piece pawn = Piece.setup(new Square(location.x + 1, location.y + 1), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);
        assertEquals(true, Board.getWhitePieces().isEmpty());
    }

    @Test
    public void captureOnePieceOfSeveralLikeBishop() {
        Piece pawn = Piece.setup(new Square(5, 5), Color.WHITE, Type.PAWN);
        Piece pawnToNotCapture = Piece.setup(new Square(6, 6), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);
        assertEquals(1, Board.getWhitePieces().size());

        Board.remove(pawnToNotCapture.getLocation());

        assertEquals(0, Board.getWhitePieces().size());
    }

    @Test
    public void captureOnePieceThenAnotherLikeBishop() {
        Piece pawn = Piece.setup(new Square(5, 5), Color.WHITE, Type.PAWN);
        Piece nextPawn = Piece.setup(new Square(6, 6), Color.WHITE, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(true, moved);
        assertEquals(5, queen.getLocation().x);
        assertEquals(5, queen.getLocation().y);
        assertEquals(1, Board.getWhitePieces().size());

        moved = Board.move(location, nextPawn.getLocation());

        assertEquals(true, moved);
        assertEquals(6, queen.getLocation().x);
        assertEquals(6, queen.getLocation().y);
        assertEquals(0, Board.getWhitePieces().size());
    }

    @Test
    public void attemptToCaptureTeammateLikeBishop() {
        Piece pawn = Piece.setup(new Square(5, 5), Color.BLACK, Type.PAWN);

        boolean moved = Board.move(location, pawn.getLocation());

        assertEquals(false, moved);
        assertEquals(4, queen.getLocation().x);
        assertEquals(4, queen.getLocation().y);
        assertEquals(2, Board.getBlackPieces().size());

        Board.remove(pawn.getLocation());

        assertEquals(1, Board.getBlackPieces().size());
    }
}