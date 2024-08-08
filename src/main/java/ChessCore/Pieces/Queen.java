package ChessCore.Pieces;

import ChessCore.*;

public final class Queen extends Piece {
    public Queen(Player owner) {
        super(owner);
                        super.setName("Queen");

    }

    @Override
    public boolean isValidMove(Move move, ChessGame game) {
        return (move.isDiagonalMove() || move.isHorizontalMove() || move.isVerticalMove()) && !game.isTherePieceInBetween(move);
    }

    @Override
    public boolean isAttackingSquare(Square pieceSquare, Square squareUnderAttack, ChessBoard board) {
        Move move = new Move.MoveBuilder(pieceSquare, squareUnderAttack).build();
        return (move.isDiagonalMove() || move.isHorizontalMove() || move.isVerticalMove()) && !board.isTherePieceInBetween(move);
    }
}
