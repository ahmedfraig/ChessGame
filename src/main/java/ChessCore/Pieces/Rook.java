package ChessCore.Pieces;

import ChessCore.*;

public final class Rook extends Piece {
    public Rook(Player owner) {
        super(owner);
        super.setName("Rook");
    }

    @Override
    public boolean isValidMove(Move move, ChessGame game) {
        return (move.isHorizontalMove() || move.isVerticalMove()) && !game.isTherePieceInBetween(move);
    }

    @Override
    public boolean isAttackingSquare(Square pieceSquare, Square squareUnderAttack, ChessBoard board) {
        Move move = new Move.MoveBuilder(pieceSquare, squareUnderAttack).build();
        return (move.isHorizontalMove() || move.isVerticalMove()) && !board.isTherePieceInBetween(move);
    }
}
