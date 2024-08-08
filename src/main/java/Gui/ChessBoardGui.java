/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui;

import java.nio.file.Paths;
import ChessCore.BoardFile;
import ChessCore.BoardRank;
import ChessCore.ClassicChessGame;
import ChessCore.GameStatus;
import ChessCore.Move;
import ChessCore.PawnPromotion;
import ChessCore.Pieces.Pawn;
import ChessCore.Pieces.Piece;
import ChessCore.Player;
import ChessCore.Square;
import ChessCore.Utilities;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.Path;
import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;
/**
 *
 * @author zezoe
 */
public class ChessBoardGui extends JFrame {

    private JPanel chessBoardPanel;
    private JPanel undoButtonPanel;
    private JButton undo;
    
private JButton redoButton;
private JPanel redoButtonPanel;

    private JLabel[][] chessSquares;
    private ClassicChessGame classicChessGame;
    private Piece [][] backendBoard;
    public PawnPromotion blackPawnPromotion;
    private int flip;
    Square fromSquare;
    Square toSquare;
    CheckPoint checkPoint;
    private int clickedRow;
    private int clickedCol ;
    private UndoMoves undoMoves;
    private Piece fromPiece;
    private Piece toPiece;
    private Square toSquareToUndo;
    
    public ChessBoardGui() {
        setTitle("Chess Game");
        setSize(700, 720);
        chessSquares=new JLabel[8][8];
        undo = new JButton("UNDO");
        classicChessGame = new ClassicChessGame();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeChessBoard();
        initializeUndoButton();
        initializeRedoButton();
        setIconLabel();
        add(redoButtonPanel,BorderLayout.NORTH);
        add(undoButtonPanel,BorderLayout.SOUTH);
        add(chessBoardPanel,BorderLayout.CENTER);   
        setVisible(true);
        setResizable(false);
        undoMoves = new UndoMoves();
        
        
        
        
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int closeOption = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to exit the game?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (closeOption == JOptionPane.YES_OPTION) {
                    this.setVisible(false);
                System.exit(0);}
    }
    private void initializeUndoButton(){
    undoButtonPanel = new JPanel();
        undoButtonPanel.add(undo);
        this.getContentPane().add(undoButtonPanel);
        undo.setBounds(50, 100, 95, 65);
        undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if((checkPoint =undoMoves.undo())!=null){
                    classicChessGame.setEnPassant(checkPoint.isEnPassant());
                    classicChessGame.setCastling(checkPoint.isCastling());
                    if(!(checkPoint.isCastling()||checkPoint.isEnPassant())){
                classicChessGame.getBoard().setPieceAtSquare(checkPoint.getFromSquare(), checkPoint.getFromPiece());
                classicChessGame.getBoard().setPieceAtSquare(checkPoint.getToSquare(), checkPoint.getToPiece());
                classicChessGame.setWhoseTurn(checkPoint.getFromPiece().getOwner());}
                    else{                       
                classicChessGame.getBoard().setPieceAtSquare(checkPoint.getFromSquare(), checkPoint.getFromPiece());
                classicChessGame.getBoard().setPieceAtSquare(checkPoint.getToSquare(), checkPoint.getToPiece());
                classicChessGame.setWhoseTurn(checkPoint.getFromPiece().getOwner());
                classicChessGame.getBoard().setPieceAtSquare(checkPoint.getToSquareNull() , null);
                classicChessGame.getBoard().setPieceAtSquare(checkPoint.getToFrontToUndo() , null);
                classicChessGame.setLastMove(checkPoint.getMove());
                    if(checkPoint.isCastling()){
                    classicChessGame.setCanBlackCastleKingSide(true);
                    classicChessGame.setCanBlackCastleQueenSide(true);
                    classicChessGame.setCanWhiteCastleKingSide(true);
                    classicChessGame.setCanWhiteCastleQueenSide(true);}
                    
                    }
                }
            setIconLabel();
            changeKingColorInChecked();
            setColorLable();
            if(checkPoint!=null){
            chessSquares[Math.abs(7*(flip)-checkPoint.getFromSquare().getRank().getValue())][checkPoint.getFromSquare().getFile().getValue()].setBackground(Color.orange);
            chessSquares[Math.abs(7*(flip)-checkPoint.getToSquare().getRank().getValue())][checkPoint.getToSquare().getFile().getValue()].setBackground(Color.orange);
            }
            classicChessGame.setGameStatus(GameStatus.IN_PROGRESS); 
            
        }
        });

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    //____________________________________________________________________________________
    private void initializeRedoButton() {
    redoButtonPanel = new JPanel();
    redoButton = new JButton("REDO");
    redoButtonPanel.add(redoButton);
    this.getContentPane().add(redoButtonPanel);
    redoButton.setBounds(150, 100, 95, 65);

    redoButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if ((checkPoint = undoMoves.redo()) != null) {
                // Redo logic
                Square fromSquare = checkPoint.getFromSquare();
                Square toSquare = checkPoint.getToSquare();
                Piece fromPiece = checkPoint.getFromPiece();
                Piece toPiece = checkPoint.getToPiece();

               // classicChessGame.getBoard().setPieceAtSquare(fromSquare, fromPiece);
                //classicChessGame.getBoard().setPieceAtSquare(toSquare, toPiece);
                classicChessGame.makeMove(new Move.MoveBuilder(fromSquare, toSquare).build());
                
               // classicChessGame.setWhoseTurn(fromPiece.getOwner());


                setIconLabel();
                setColorLable();
                changeKingColorInChecked();
                chessSquares[Math.abs(7 * flip - fromSquare.getRank().getValue())][fromSquare.getFile().getValue()].setBackground(Color.orange);
                chessSquares[Math.abs(7 * flip - toSquare.getRank().getValue())][toSquare.getFile().getValue()].setBackground(Color.orange);
                classicChessGame.setGameStatus(GameStatus.IN_PROGRESS);
            }
        }
    });
}

    //____________________________________________________________________________________
    
    
    
    
    
    
    private void initializeChessBoard() {
        chessBoardPanel = new JPanel(new GridLayout(8, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessSquares[i][j] = new JLabel();
                if((i+j)%2==0){
                chessSquares[i][j].setBackground(new Color(0x833101));
                }else
                    chessSquares[i][j].setBackground(new Color(0xffffff));

                chessSquares[i][j].setOpaque(true);
                chessSquares[i][j].setHorizontalAlignment(JLabel.CENTER);
                chessSquares[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                chessSquares[i][j].setPreferredSize(new Dimension(65,65));

                final int currentRow = i;
                final int currentCol = j;
                chessSquares[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    Component source = e.getComponent();
                        setFlip();
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (source == chessSquares[i][j]) {
                                clickedRow = i;
                                clickedCol = j;
                                break;
                            }
                        }}
        if(isRightMouseButton(e)){
        fromSquare = null;
        toSquare = null;
        setColorLable();
        }
        if(isLeftMouseButton(e)){
        if(fromSquare == null){
        setColorLable();
        chessSquares[currentRow][currentCol].setBackground(Color.LIGHT_GRAY);
        validMoveColor();
        fromSquare = new Square(BoardFile.fromValue(clickedCol), BoardRank.fromValue(Math.abs(7*flip-clickedRow)));
        }
        else{
        toSquare = new Square(BoardFile.fromValue(clickedCol), BoardRank.fromValue(Math.abs(7*flip-clickedRow)));
        fromPiece = classicChessGame.getPieceAtSquare(fromSquare);
        if ((classicChessGame.getPieceAtSquare(fromSquare)!=null)&&(classicChessGame.getPieceAtSquare(fromSquare) instanceof Pawn)&&(classicChessGame.isValidMove(new Move.MoveBuilder(fromSquare, toSquare)
                .pawnPromotion(blackPawnPromotion)
                .build()))&&(toSquare.getRank() == BoardRank.FIRST || toSquare.getRank() == BoardRank.EIGHTH)) {       
int result;
            Object[] options = {"Queen", "Knight", "Rook", "Bishop"};

            do{

        result = JOptionPane.showOptionDialog(
                null,
                "Select a chess piece:",
                "Chess Piece Selector",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                null);

            }while(result==JOptionPane.CLOSED_OPTION);
            String selectedPiece = (String) options[result];
        
        //-----------------------------------------------------------------------
        if(selectedPiece.equals("Queen"))
            blackPawnPromotion=PawnPromotion.Queen;
        if(selectedPiece.equals("Knight"))
            blackPawnPromotion=PawnPromotion.Knight;
        if(selectedPiece.equals("Rook"))
            blackPawnPromotion=PawnPromotion.Rook;
        if(selectedPiece.equals("Bishop"))
            blackPawnPromotion=PawnPromotion.Bishop;
        if(classicChessGame.makeMove(new Move.MoveBuilder(fromSquare, toSquare)
                .pawnPromotion(blackPawnPromotion)
                .build())){
            undoMoves.saveMove(new CheckPoint(fromPiece,classicChessGame.getToPieceToFront(),fromSquare,classicChessGame.getToSquareToFront(),classicChessGame.isCastling(),classicChessGame.isEnPassant(),toSquare,classicChessGame.getToSquareNull(),classicChessGame.getLastMoveToFront()));       
            fromSquare = null;
            toSquare = null;
            setColorLable();
            changeKingColorInChecked();            
            setIconLabel();
            setColorLable();
            checkIsGameEnded();
        }
        else{
        setColorLable();
        chessSquares[currentRow][currentCol].setOpaque(true);
        chessSquares[currentRow][currentCol].setBackground(Color.LIGHT_GRAY);
        validMoveColor();
        fromSquare = new Square(BoardFile.fromValue(clickedCol), BoardRank.fromValue(Math.abs(7*flip-clickedRow)));
            }
        }
            
        else if(classicChessGame.makeMove(new Move.MoveBuilder(fromSquare, toSquare).build())){
        undoMoves.saveMove(new CheckPoint(fromPiece,classicChessGame.getToPieceToFront(),fromSquare,classicChessGame.getToSquareToFront(),classicChessGame.isCastling(),classicChessGame.isEnPassant(),toSquare,classicChessGame.getToSquareNull(),classicChessGame.getLastMoveToFront()));
        setIconLabel();
        if(classicChessGame.isCastling()){
        toSquareToUndo = toSquare;
        }
        fromSquare = null;
        toSquare = null;
        changeKingColorInChecked();       
        setIconLabel();
        setColorLable();
        checkIsGameEnded();
        }
        else{
        setColorLable();
        setIconLabel();
        chessSquares[currentRow][currentCol].setOpaque(true);
        chessSquares[currentRow][currentCol].setBackground(Color.LIGHT_GRAY);
        validMoveColor();
        fromSquare = new Square(BoardFile.fromValue(clickedCol), BoardRank.fromValue(Math.abs(7*flip-clickedRow)));       
        }
        }}
    }
                    @Override
                    public void mousePressed(MouseEvent e) {
                       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }
                });

                chessBoardPanel.add(chessSquares[i][j]);
            }
            
        }


    }  
    
    public void setColorLable(){
        Color color2= classicChessGame.getWhoseTurn().equals(Player.WHITE)?new Color(0x833101):new Color(0xffffff);
        Color color1= classicChessGame.getWhoseTurn().equals(Player.WHITE)?new Color(0xffffff):new Color(0x833101);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if((i+j)%2==0){                  
                chessSquares[i][j].setBackground(color2);
                changeKingColorInChecked();
                }else
                {
                    chessSquares[i][j].setBackground(color1);
                changeKingColorInChecked();
                }
            }
        }}
    public void setFlip(){
    if (classicChessGame.getWhoseTurn().equals(Player.BLACK))
        flip = 0;
    else
        flip = 1;
    }
    
    public void setIconLabel(){
        setFlip();
    for(int i = 0; i<8;i++)
        for(int j=0 ; j<8 ;j++){
        Square square =  new Square(BoardFile.fromValue(j),BoardRank.fromValue(Math.abs(7*flip-i)));
        Piece piece = classicChessGame.getPieceAtSquare(square);
        if(piece == null){
        chessSquares[i][j].setIcon(null);
        }else{
            Path relativePath = Paths.get("src");
            String type=piece.getName();
            String color;
            String path=relativePath.toString()+"/main/java/ChessImages/";
            if(piece.getOwner().equals(Player.WHITE)){
                color="White";
            }else
                color="Black";         
            String logoPath =path+color+type+".png";
            chessSquares[i][j].setIcon(new ImageIcon(logoPath)); // NOI18N
        
        }
        }  
    }
public void validMoveColor(){
        setFlip();
        Square a2 = new Square(BoardFile.fromValue(clickedCol), BoardRank.fromValue(Math.abs(7*flip-clickedRow)));
        List<Square> allValidMoves=classicChessGame.getAllValidMovesFromSquare(a2);
        if(allValidMoves!= null){
        for(int i = 0 ; i<allValidMoves.size();i++){
        Square square = allValidMoves.get(i);
        chessSquares[Math.abs(7*flip-square.getRank().getValue())][square.getFile().getValue()].setBackground(Color.green);
        }
        }
    }
public void changeKingColorInChecked(){
    if(Utilities.isInCheck(classicChessGame.getWhoseTurn(),classicChessGame.getBoard())){           
        Square square=Utilities.getKingSquare(classicChessGame.getWhoseTurn(),classicChessGame.getBoard());
        chessSquares[Math.abs(7*flip-square.getRank().getValue())][square.getFile().getValue()].getComponentAt(0, 0).setBackground(Color.red);
    }
    }
public void checkIsGameEnded(){
if(classicChessGame.isGameEnded()){
            Object[] options1 = {"Play Again", "Exit"};

        int result1 = JOptionPane.showOptionDialog(
                null,
                classicChessGame.getGameStatus().toString(),
                "Game Ended",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options1,
                null); 
            String endGameOption = (String) options1[result1];
            if(endGameOption.equals("Play Again")){
                classicChessGame=new ClassicChessGame();
                        setIconLabel();
                        setColorLable();
            }else {
                this.setVisible(false);
            }
        } 
}
}