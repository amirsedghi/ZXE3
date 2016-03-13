package com.mygdx.game;

/**
 * Created by pinso on 3/13/2016.
 */
public class Wall {
    private int maxHealth;
    private int health;
    private int radius;
    private int centerX;
    private int centerY;

    public Wall(int maxHealth, int radius, int centerX, int centerY){
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.radius = radius;
        this.centerX = centerX;
        this.centerY = centerY;
    }
    // damage should be a negative number and health increases are positive numbers.
    public void adjustHealth(int amount){
        health = Math.max(0, Math.min(health + amount, maxHealth));
    }
    public int getHealth() {
        return health;
    }
    public boolean collides(int x, int y, int r){
        int dx = x - centerX;
        int dy = y - centerY;
        int radii = r + radius;
        if((dx * dx) + (dy * dy) < (radii * radii)){
            return true;
        }
        return false;
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
