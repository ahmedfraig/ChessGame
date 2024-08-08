/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui;

import ChessCore.Move;
import ChessCore.Pieces.Piece;
import ChessCore.Square;

/**
 *
 * @author al-aqsa
 */
public class CheckPoint {
    private Piece fromPiece;
    private Piece toPiece;
    private Square fromSquare;
    private Square toSquare;
    private boolean castling;
    private boolean enPassant;
    private Square toSquareNull;
    private Square toFrontToUndo;
    private Move move;
    public CheckPoint(Piece fromPiece,Piece toPiece,Square fromSquare,Square toSquare,boolean castling,boolean enPassant,Square toFrontToUndo ,Square toSquareNull,Move lastMove){
    this.fromPiece = fromPiece;
    this.toPiece = toPiece;
    this.fromSquare = fromSquare;
    this.toSquare = toSquare;
    this.castling = castling;
    this.enPassant = enPassant;
    this.toSquareNull = toSquareNull;
    this.toFrontToUndo = toFrontToUndo;
    this.move = lastMove;
    }

    public Move getMove() {
        return move;
    }

    public Piece getFromPiece() {
        return fromPiece;
    }

    public Piece getToPiece() {
        return toPiece;
    }

    public Square getFromSquare() {
        return fromSquare;
    }

    public Square getToSquareNull() {
        return toSquareNull;
    }

    public Square getToFrontToUndo() {
        return toFrontToUndo;
    }

    public Square getToSquare() {
        return toSquare;
    }

    public boolean isCastling() {
        return castling;
    }

    public boolean isEnPassant() {
        return enPassant;
    }
    
}
