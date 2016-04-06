package com.badlogic.dd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by pinso on 3/13/2016.
 * Name of Class: Wall
 * Purpose of the class is to define the Wall component
 * of the game. The wall has a health, and a position on the screen.
 * It also has a heigth or "thickness" on the screen.
 * The position and height allow other classes, to establish contact
 * with the wall.
 * Author: John Pinson
 * Created: 3/13/2016
 */
public class Wall {
    private int maxHealth;
    private int health;
    private int positionY;
    private final int WALL_HEIGHT = 55;
    /*  Name of Module: Wall constructor
        Purpose: Gives the wall its attributes.
        List of input params: int maxHealth, int positionY
        List of output params:
        Return value:
        Author: John Pinson
        Created: 3/13/2016, modified: 4/2/2016
    */
    public Wall(int maxHealth, int positionY){
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.positionY = positionY;
    }
    /*  Name of Module: adjustHealth
        Purpose:To adjust the health of the wall. damage should be a negative number and health increases are
                positive numbers.
        List of input params: int amount
        List of output params: n/a
        Return value: n/a
        Author: John Pinson
        Created: 3/13/2016,
    */
    public void adjustHealth(int amount){
        health = Math.max(0, Math.min(health + amount, maxHealth));
    }
    /*  Name of Module: getHealth
        Purpose: Health getter
        List of input params: n/a
        List of output params: n/a
        Return value: health
        Author: John Pinson
        Created: 3/13/2016
    */
    public int getHealth() {
        return health;
    }
    /*  Name of Module: render
        Purpose: To display or render the wall images
        List of input params: SpriteBatch batch
        List of output params: n/a
        Return value: n/a
        Author: John Pinson
        Created: 3/13/2016, modified: 4/2/2016
    */
    public void render(SpriteBatch batch) {
        Texture wallImage = new Texture(getImage());
        batch.draw(wallImage, 0, positionY);
    }
    /*  Name of Module: getDistanceToWall
        Purpose: To check distance to/from Wall, so as to check for collision/interaction
        List of input params: int y
        List of output params: n/a
        Return value: y - positionY - WALL_HEIGHT
        Author: John Pinson
        Created: 3/13/2016, modified: 4/2/2016
    */
    // Assuming y-up
    public int getDistanceToWall(int y){
        return y - positionY - WALL_HEIGHT;
    }
    /*  Name of Module: getImage
        Purpose: Image getter, There are 4 different wall images depending on the wall health.
        List of input params: n/a
        List of output params: n/a
        Return value: will return the wall image corresponding to the health as indicated below.
        Author: John Pinson
        Created: 3/13/2016
    */
    public String getImage(){
        if(health == 0){
            return "wall0.png";         //Game over. The wall has been destroyed.
        }
        if(health < maxHealth/4){       //The wall is a 25% health.
            return "wall25.png";
        }
        if(health < maxHealth/2){       //The wall is at 50% health.
            return "wall50.png";
        }
        if(health < maxHealth * .75){   //The wall is at 75% health.
            return "wall75.png";
        }
        return "wall.png";              //The wall is at 100% health.
    }
}
