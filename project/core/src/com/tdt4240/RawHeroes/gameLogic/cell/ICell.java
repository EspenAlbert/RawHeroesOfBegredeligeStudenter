package com.tdt4240.RawHeroes.gameLogic.cell;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;

import java.io.Serializable;

/**
 * Created by espen1 on 27.02.2015.
 */
public interface ICell extends Serializable{
    Vector2 getPos();

    IUnit getUnit();

    CellStatus getStatus();

    void setUnit(IUnit unit);

    void setStatus(CellStatus status);
}
