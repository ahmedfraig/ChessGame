package ChessCore;

public final class Move {
    private final Square fromSquare;
    private final Square toSquare;
    private final PawnPromotion pawnPromotion;

    private Move(MoveBuilder moveBuilder) {
        this.fromSquare = moveBuilder.fromSquare;
        this.toSquare = moveBuilder.toSquare;
        this.pawnPromotion = moveBuilder.pawnPromotion;
    }

    public static class MoveBuilder{
    private Square fromSquare;
    private Square toSquare;
    private PawnPromotion pawnPromotion;
    public MoveBuilder(Square fromSquare, Square toSquare){
    this.fromSquare = fromSquare;
    this.toSquare = toSquare;
    this.pawnPromotion = PawnPromotion.None;
    }
    public MoveBuilder pawnPromotion(PawnPromotion pawnPromotion){
    this.pawnPromotion = pawnPromotion;
    return this;
    }
    public Move build(){
    Move move = new Move(this);
    return move;
    }
}

    public Square getFromSquare() {
        return fromSquare;
    }

    public Square getToSquare() {
        return toSquare;
    }

    public PawnPromotion getPawnPromotion() {
        return pawnPromotion;
    }

    public boolean isHorizontalMove() {
        return getDeltaX() != 0 && getDeltaY() == 0;
    }

    public boolean isVerticalMove() {
        return getDeltaX() == 0 && getDeltaY() != 0;
    }

    public boolean isDiagonalMove() {
        int deltaX = getAbsDeltaX();
        int deltaY = getAbsDeltaY();

        return deltaX == deltaY && deltaX != 0;
    }

    public int getDeltaX() {
        return getToSquare().getFile().getValue() - getFromSquare().getFile().getValue();
    }

    public int getDeltaY() {
        return getToSquare().getRank().getValue() - getFromSquare().getRank().getValue();
    }

    public int getAbsDeltaX() {
        return Math.abs(getDeltaX());
    }

    public int getAbsDeltaY() {
        return Math.abs(getDeltaY());
    }
}
