package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy
{
    int Health, maxHealth, currentHealth, power;
    Sprite enemySprite; // enemy sprite
    Vector2 velocity; // velocity of the enemy
    Rectangle rectangle; // rectangle object to detect collisions

    public abstract void render(SpriteBatch batch);

    public abstract void update();

    public abstract void attack();

    public abstract void die();
}
