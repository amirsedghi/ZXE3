package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Circle;

public abstract class Enemy
{
    protected int health, maxHealth, currentHealth, power;
    protected boolean isDead;
    protected Sprite enemySprite; // enemy sprite
    protected Vector2 velocity; // velocity of the enemy
    protected Vector2 position;
    protected Rectangle rectangle; // rectangle object to detect collisions
//    protected Circle circle;

    public abstract void render(SpriteBatch batch);

    public abstract void update();
}
