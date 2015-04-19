package com.tdt4240.RawHeroes.event.move;

import com.badlogic.gdx.math.Vector2;
import com.tdt4240.RawHeroes.gameLogic.cell.ICell;
import com.tdt4240.RawHeroes.gameLogic.controllers.cameraController.CellConverter;
import com.tdt4240.RawHeroes.gameLogic.models.IBoard;
import com.tdt4240.RawHeroes.gameLogic.models.IUnit;
import com.tdt4240.RawHeroes.independent.Position;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by espen1 on 27.02.2015.
 */
public class AttackMove extends Move {

    Position attackerPos, targetPos;
    HashMap<Position, Integer> damages;

    private ArrayList<ICell> victims;

    public AttackMove(ICell selectedCell, ICell target) {
        super(selectedCell, target);
        this.damages = new HashMap<Position, Integer>();
        this.attackerPos = selectedCell.getPos();
        this.targetPos = target.getPos();
    }


    @Override
    public int getCost(){
        return this.getStartCell().getUnit().getWeight();
    }
    @Override
    public void execute(IBoard board) {
        if (damages != null) {
            for (Position key : damages.keySet()) {
                board.getCell(key).getUnit().attacked(damages.get(key));
            }
        }
        getDamages(board);
    }

    @Override
    public void undo(IBoard board) {

    }

    @Override
    public void convertPositions(int boardWidth, int boardHeight) {
        HashMap<Position, Integer> newDamages = new HashMap<Position, Integer>();
        for(Position pos : damages.keySet()) {
            newDamages.put(CellConverter.switchPosition(pos, boardWidth, boardHeight), damages.get(pos));
        }
        damages = newDamages;
    }

    private void getDamages(IBoard board) {
        IUnit attacker = board.getCell(attackerPos).getUnit();
        ArrayList<Position> inflictionZone = attacker.getInflictionZone(attackerPos, targetPos);
        for (Position victimPos : inflictionZone) {
            IUnit victim = board.getCell(victimPos).getUnit();
            if (victim != null) {
                int dmg = attacker.inflictDamage(attackerPos, targetPos);
                victim.attacked(dmg);
                damages.put(victimPos, dmg);
            }
        }
    }

    public ArrayList<ICell> getVictims(){
        return this.victims;
    }
}