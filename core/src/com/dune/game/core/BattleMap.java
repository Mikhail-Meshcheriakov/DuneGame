package com.dune.game.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class BattleMap {
    private TextureRegion grassTexture;
    private TextureRegion oilTexture;
    private int[][] data;

    public BattleMap() {
        this.grassTexture = Assets.getInstance().getAtlas().findRegion("grass");
        this.oilTexture = Assets.getInstance().getAtlas().findRegion("oil");
        data = new int[16][9];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = MathUtils.random(0, 10);
            }
        }
    }

    public int[][] getData() {
        return data;
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                batch.draw(grassTexture, i * 80, j * 80);
                if (data[i][j] == 5) {
                    batch.draw(oilTexture, i * 80 + 24, j * 80 + 24);
                }
            }
        }
    }
}
