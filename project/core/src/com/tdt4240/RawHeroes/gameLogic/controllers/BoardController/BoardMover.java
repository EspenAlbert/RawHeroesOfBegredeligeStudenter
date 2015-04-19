package com.tdt4240.RawHeroes.gameLogic.controllers.boardController;

import com.tdt4240.RawHeroes.event.move.AttackMove;
import com.tdt4240.RawHeroes.event.move.MovementMove;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.event.listener.IMoveListener;
import com.tdt4240.RawHeroes.event.move.Move;

import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public class BoardMover implements IBoardMover {

    private ArrayList<Move> moves;
    private final IBoard board;
    private final IBoard initialBoard;
    private ArrayList<IMoveListener> listeners;

    public BoardMover(IBoard board) {
        this.board = board;
        moves = new ArrayList<Move>();
        listeners = new ArrayList<IMoveListener>();
        initialBoard = board.deepCopy();

    }
    @Override
    public void addMoveListener(IMoveListener listener) {
        listeners.add(listener);
    }

    @Override
    public ArrayList<Move> confirmMoves() {
        executeMovesFromBeginning();
        return moves;
    }

    private void executeMovesFromBeginning() {
        for(Move move: moves) {
            move.execute(initialBoard);
        }
    }

    @Override
    public void add(Move move) {
        moves.add(move);
        doMove(move);
    }

    private void doMove(Move move) {
        move.execute(board);
        for(IMoveListener listener: listeners) {
            listener.moveExecuted(move);
        }
    }

    @Override
    public Move undo() {
        if(moves.size() > 0) {
            moves.get(moves.size()-1).undo(board);
            doMove(moves.get(moves.size()-1));
            return moves.remove(moves.size() -1);
        }
        return null;
    }



    @Override
    public void executeMoves(ArrayList<Move> lastMoves) {
        if(lastMoves == null) return;
        for(Move move : lastMoves) {
            doMove(move);
        }
    }
    @Override
    public void executeMovesFromOtherPlayer(ArrayList<Move> lastMoves) {
        if(lastMoves == null) return;
        for(Move move : lastMoves) {
            move.convertPositions(board.getWidth(), board.getHeight());
            doMove(move);
        }
    }


}
