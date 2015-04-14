package com.tdt4240.RawHeroes.gameLogic.controllers.unitController;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;

import java.util.ArrayList;

/**
 * Created by espen1 on 12.04.2015.
 */
public interface IUnitMovementController {
    ArrayList<Vector2> getMovementZone(IBoard board, Vector2 myPos, int movesLeft, int unitMaxMoves);
    ArrayList<Vector2> getMovementPath(IBoard board, Vector2 myPos, Vector2 targetPos);
}
