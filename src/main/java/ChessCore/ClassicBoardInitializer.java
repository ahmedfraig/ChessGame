package ChessCore;

import ChessCore.Pieces.*;

public final class ClassicBoardInitializer implements BoardInitializer {
    private static final BoardInitializer instance = new ClassicBoardInitializer();
ChessPieceFactory chessPieceFactory = new ChessPieceFactory();
    private ClassicBoardInitializer() {
    }

    public static BoardInitializer getInstance() {
        return instance;
    }

    @Override
    public Piece[][] initialize() {
        Piece[][] initialState =new Piece[8][8]  ;
     /*   {
            {new Rook(Player.WHITE), new Knight(Player.WHITE), new Bishop(Player.WHITE), new Queen(Player.WHITE), new King(Player.WHITE), new Bishop(Player.WHITE), new Knight(Player.WHITE), new Rook(Player.WHITE)},
            {new Pawn(Player.WHITE), new Pawn(Player.WHITE), new Pawn(Player.WHITE), new Pawn(Player.WHITE), new Pawn(Player.WHITE), new Pawn(Player.WHITE), new Pawn(Player.WHITE), new Pawn(Player.WHITE)},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {new Pawn(Player.BLACK), new Pawn(Player.BLACK), new Pawn(Player.BLACK), new Pawn(Player.BLACK), new Pawn(Player.BLACK), new Pawn(Player.BLACK), new Pawn(Player.BLACK), new Pawn(Player.BLACK)},
            {new Rook(Player.BLACK), new Knight(Player.BLACK), new Bishop(Player.BLACK), new Queen(Player.BLACK), new King(Player.BLACK), new Bishop(Player.BLACK), new Knight(Player.BLACK), new Rook(Player.BLACK)}
        };*/
     
    
     for(int i=0;i<8;i++)
         for(int j=0;j<8;j++){
             initialState[i][j]=chessPieceFactory.createPiece(i,j);
         }
     
     
     
     
     
     
     
     
     
       
        /*Piece[][] initialState = {
            {new Rook(Player.BLACK), new Knight(Player.BLACK), new Bishop(Player.BLACK), new Queen(Player.BLACK), new King(Player.BLACK), new Bishop(Player.BLACK), new Knight(Player.BLACK), new Rook(Player.BLACK)},
            {new Pawn(Player.BLACK), new Pawn(Player.BLACK), new Pawn(Player.BLACK), new Pawn(Player.BLACK), new Pawn(Player.BLACK), new Pawn(Player.BLACK), new Pawn(Player.BLACK), new Pawn(Player.BLACK)},

            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
                        {new Pawn(Player.WHITE), new Pawn(Player.WHITE), new Pawn(Player.WHITE), new Pawn(Player.WHITE), new Pawn(Player.WHITE), new Pawn(Player.WHITE), new Pawn(Player.WHITE), new Pawn(Player.WHITE)},

                    {new Rook(Player.WHITE), new Knight(Player.WHITE), new Bishop(Player.WHITE), new Queen(Player.WHITE), new King(Player.WHITE), new Bishop(Player.WHITE), new Knight(Player.WHITE), new Rook(Player.WHITE)}

        };*/
       
       
        return initialState;
    }
}
