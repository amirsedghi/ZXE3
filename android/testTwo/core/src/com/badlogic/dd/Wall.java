package com.badlogic.dd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by pinso on 3/13/2016.
 */
public class Wall {
    private int maxHealth;
    private int health;
    private int positionY;
    private final int WALL_HEIGHT = 55;

    public Wall(int maxHealth, int positionY){
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.positionY = positionY;
    }
    // damage should be a negative number and health increases are positive numbers.
    public void adjustHealth(int amount){
        health = Math.max(0, Math.min(health + amount, maxHealth));
    }
    public int getHealth() {
        return health;
    }

    public void render(SpriteBatch batch) {
        Texture wallImage = new Texture(getImage());
        batch.draw(wallImage, 0, positionY);
    }

    // Assuming y-up
    public int getDistanceToWall(int y){
        return y - positionY - WALL_HEIGHT;
    }

    public String getImage(){
        if(health == 0){
            return "wall0.png";
        }
        if(health < maxHealth/4){
            return "wall25.png";
        }
        if(health < maxHealth/2){
            return "wall50.png";
        }
        if(health < maxHealth * .75){
            return "wall75.png";
        }
        return "wall.png";
    }
}
