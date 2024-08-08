/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui;

import java.util.Stack;

/**
 *
 * @author al-aqsa
 */
public class UndoMoves {
    private Stack<CheckPoint> undoStack;
    private Stack<CheckPoint> redoStack;
   
    
     public UndoMoves() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void saveMove(CheckPoint move) {
        undoStack.push(move);
        redoStack.clear(); // Clear redo stack when a new move is saved
    }

    public CheckPoint undo() {
        if (!undoStack.isEmpty()) {
            CheckPoint move = undoStack.pop();
            redoStack.push(move);
            return move;
        } else {
            return null;
        }
    }

    public CheckPoint redo() {
        if (!redoStack.isEmpty()) {
            CheckPoint move = redoStack.pop();
            undoStack.push(move);
            return move;
        } else {
            return null;
        }
    }
    
    /*public UndoMoves(){
    undoStack = new Stack<>();
    }
    public void saveMove (CheckPoint move){
    undoStack.push(move);
    }
    public CheckPoint undo(){
        if(!undoStack.empty())
            return undoStack.pop();
        else
            return null;
    }*/
}
