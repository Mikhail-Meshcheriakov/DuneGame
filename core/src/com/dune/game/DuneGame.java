package com.dune.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class DuneGame extends ApplicationAdapter {
    private static class Tank {
        private Vector2 position;
        private Texture texture;
        private float angle;
        private float speed;
        private float radius;

        public Tank(float x, float y) {
            this.position = new Vector2(x, y);
            this.texture = new Texture("tank.png");
            this.speed = 200.0f;
            this.radius = 35;
        }

        public Vector2 getPosition() {
            return position;
        }

        public float getRadius() {
            return radius;
        }

        public void update(float dt) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                angle += 180.0f * dt;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                angle -= 180.0f * dt;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                position.x += speed * MathUtils.cosDeg(angle) * dt;
                position.y += speed * MathUtils.sinDeg(angle) * dt;
            }

            if (position.x >= 1250) position.x = 1250;
            if (position.x <= 30) position.x = 30;
            if (position.y >= 690) position.y = 690;
            if (position.y <= 30) position.y = 30;
        }

        public void render(SpriteBatch batch) {
            batch.draw(texture, position.x - 40, position.y - 40, 40, 40, 80, 80, 1, 1, angle, 0, 0, 80, 80, false, false);
        }

        private void dispose() {
            texture.dispose();
        }
    }

    private static class Circle {
        private Vector2 position;
        private Texture texture;
        private float radius;

        public Circle(float x, float y) {
            this.position = new Vector2(x, y);
            this.texture = new Texture("circle.png");
            this.radius = 50;
        }

        public void update(float dt, Tank tank) {
            float distance = radius + tank.getRadius();
            if (Math.abs(position.x - tank.getPosition().x) <= distance && Math.abs(position.y - tank.getPosition().y) <= distance) {
                position.x = MathUtils.random(50, 1230);
                position.y = MathUtils.random(50, 670);
            }
        }

        public void render(SpriteBatch batch) {
            batch.draw(texture, position.x - 50, position.y - 50);
        }

        private void dispose() {
            texture.dispose();
        }
    }

    private SpriteBatch batch;
    private Tank tank;
    private Circle circle;

    @Override
    public void create() {
        batch = new SpriteBatch();
        tank = new Tank(200, 200);
        circle = new Circle(900, 520);
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);
        Gdx.gl.glClearColor(0, 0.4f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        circle.render(batch);
        tank.render(batch);
        batch.end();
    }

    public void update(float dt) {
        circle.update(dt, tank);
        tank.update(dt);
    }

    @Override
    public void dispose() {
        batch.dispose();
        tank.dispose();
        circle.dispose();
    }

    // Домашнее задание:
    // - Задать координаты точки, и нарисовать в ней круг (любой круг, радиусом пикселей 50)
    // - Если "танк" подъедет вплотную к кругу, то круг должен переместиться в случайную точку
    // - * Нельзя давать танку заезжать за экран
}