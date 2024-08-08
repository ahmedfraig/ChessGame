/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChessCore;

import ChessCore.Pieces.Piece;
import ChessCore.Pieces.*;

/**
 *
 * @author zezoe
 */
public class ChessPieceFactory implements PieceFactory{
    
    @Override
     public Piece createPiece(int i,int j){
         if((i==0) &&(j==0))
             return new Rook(Player.WHITE);
         else
             if((i==0) &&(j==1))
             return new Knight(Player.WHITE);
          else
             if((i==0) &&(j==2))
             return new Bishop(Player.WHITE);
          else
             if((i==0) &&(j==3))
             return new Queen(Player.WHITE);
          else
             if((i==0) &&(j==4))
             return new King(Player.WHITE);
          else
             if((i==0) &&(j==5))
             return new Bishop(Player.WHITE);
          else
             if((i==0) &&(j==6))
             return new Knight(Player.WHITE);
          else
             if((i==0) &&(j==7))
             return new Rook(Player.WHITE);
          else
             if((i>1)&&(i<6))
             return null;
             else
                 if(i==1)
                     return new Pawn(Player.WHITE);
         else
                  if((i==7) &&(j==0))
             return new Rook(Player.BLACK);
         else
             if((i==7) &&(j==1))
             return new Knight(Player.BLACK);
          else
             if((i==7) &&(j==2))
             return new Bishop(Player.BLACK);
          else
             if((i==7) &&(j==3))
             return new Queen(Player.BLACK);
          else
             if((i==7) &&(j==4))
             return new King(Player.BLACK);
          else
             if((i==7) &&(j==5))
             return new Bishop(Player.BLACK);
          else
             if((i==7) &&(j==6))
             return new Knight(Player.BLACK);
          else
             if((i==7) &&(j==7))
             return new Rook(Player.BLACK);
             else 
                 if(i==6)
                     return new Pawn(Player.BLACK);
         else
                                     throw new IllegalArgumentException("Invalid piece type");

         
     }
}
