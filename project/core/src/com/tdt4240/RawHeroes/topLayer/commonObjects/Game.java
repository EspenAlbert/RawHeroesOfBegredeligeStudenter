package com.tdt4240.RawHeroes.topLayer.commonObjects;

import com.tdt4240.RawHeroes.createGame.winConditions.IWinCondition;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.event.move.Move;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by espen1 on 27.02.2015.
 */
public abstract class Game implements Serializable{
    private int id;
    private String player1Nickname, player2Nickname;
    IBoard board;
    ArrayList<Move> lastMoves;
    boolean nextTurnIsPlayer1;
    private int moveCount;
    private GameState gameState;
    private final IWinCondition winCondition;

    public Game(String player1Nickname, String player2Nickname, IWinCondition winCondition) {
        this.player1Nickname = player1Nickname;
        this.player2Nickname = player2Nickname;
        this.winCondition = winCondition;
        gameState = GameState.CHALLENGE;
    }

    public String getPlayer1Nickname() {
        return player1Nickname;
    }

    public IBoard getBoard() {
        return board;
    }

    public ArrayList<Move> getLastMoves() {
        return lastMoves;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public String getPlayer2Nickname() {
        return player2Nickname;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    public boolean player1IsWinner() {
        return winCondition.player1IsWinner(board);
    }
    public boolean player2IsWinner() {
        return winCondition.player2IsWinner(board);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBoard(IBoard board) {
        this.board = board;
    }
}
