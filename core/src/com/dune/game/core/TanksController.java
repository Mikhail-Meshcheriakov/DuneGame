package com.dune.game.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TanksController extends ObjectPool<Tank> {
    private GameController gc;

    @Override
    protected Tank newObject() {
        return new Tank(gc);
    }

    public TanksController(GameController gc) {
        this.gc = gc;
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).render(batch);
        }
    }

    public void setup(float x, float y, Tank.Owner ownerType) {
        Tank t = getActiveElement();
        t.setup(ownerType, x, y);
    }

    public void update(float dt) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }
        checkPool();
    }

    public void checkCollisions(float dt) {
        int x = 1;
        for (int i = 0; i < activeList.size(); i++) {
            for (int j = x; j < activeList.size(); j++) {
                if (Math.abs(activeList.get(i).getCellX() - activeList.get(j).getCellX()) <= 1 && Math.abs(activeList.get(i).getCellY() - activeList.get(j).getCellY()) <= 1) {
                    activeList.get(i).obstacleDetour(activeList.get(j).position, dt);
                    activeList.get(j).obstacleDetour(activeList.get(i).position, dt);
                }
            }
            x++;
        }
    }
}
