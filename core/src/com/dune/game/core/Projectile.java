package com.dune.game.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Projectile {
    private Vector2 position;
    private Vector2 velocity;
    private TextureRegion texture;

    public Projectile(TextureAtlas atlas) {
        this.texture = new TextureRegion(atlas.findRegion("bullet"));
        this.position = new Vector2(-100.0f, -100.0f);
        velocity = new Vector2(0, 0);
    }

    public void setup(Vector2 startPosition, float angle) {
        position.set(startPosition);
        velocity.set(500.0f * MathUtils.cosDeg(angle), 500.0f * MathUtils.sinDeg(angle));
    }

    public void update(float dt) {
        position.mulAdd(velocity, dt);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 8, position.y - 8);
    }

    public Vector2 getPosition() {
        return position;
    }
}
