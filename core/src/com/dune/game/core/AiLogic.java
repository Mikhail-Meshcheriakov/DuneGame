package com.dune.game.core;

import com.badlogic.gdx.math.Vector2;
import com.dune.game.core.units.AbstractUnit;
import com.dune.game.core.units.Owner;
import com.dune.game.core.units.UnitType;

public class AiLogic {

    private GameController gc;

    public AiLogic(GameController gc) {
        this.gc = gc;
    }

    public void update(float dt) {
        for (int i = 0; i < gc.getUnitsController().getAiUnits().size(); i++) {
            AbstractUnit uAI = gc.getUnitsController().getAiUnits().get(i);
            if (uAI.getUnitType() == UnitType.BATTLE_TANK) {
                btUpdate(uAI, dt);
            }
            if (uAI.getUnitType() == UnitType.HARVESTER) {
                harvUpdate(uAI, dt);
            }
        }
    }

    private void btUpdate(AbstractUnit unit, float dt) {
        AbstractUnit uP = gc.getUnitsController().getNearestUnit(unit, UnitType.BATTLE_TANK, Owner.PLAYER);
        if (uP != null) {
            unit.commandAttack(uP);
        }
    }

    private void harvUpdate(AbstractUnit unit, float dt) {
        AbstractUnit u2 = gc.getUnitsController().getNearestUnit(unit, UnitType.BATTLE_TANK, Owner.AI);
        if (unit.isAttacked() && u2 != null) {
            unit.commandMoveTo(u2.getPosition());
            if (unit.getPosition().dst(u2.getPosition()) < 100) {
                unit.commandMoveTo(unit.getPosition());
            }
        } else {
            Vector2 resource = gc.getMap().getNearestResourcePosition(unit);
            if (!resource.isZero()) {
                unit.commandMoveTo(resource);
            } else {
                unit.commandMoveTo(unit.getPosition());
            }
        }
    }
}
