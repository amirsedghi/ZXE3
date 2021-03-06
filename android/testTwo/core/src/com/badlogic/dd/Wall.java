package com.badlogic.dd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    private Sprite wallSprite;
    private ParticleEffect fireEffect1 = new ParticleEffect();
    private ParticleEffect fireEffect2 = new ParticleEffect();
    private ParticleEffect fireEffect3 = new ParticleEffect();
    private ParticleEffect fireEffect4 = new ParticleEffect();
    private ParticleEffect fireEffect5 = new ParticleEffect();
    private ParticleEffect fireEffect6 = new ParticleEffect();

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
        fireEffect1.load(Gdx.files.internal("fireEffect.p"), Gdx.files.internal("img"));
        fireEffect1.setPosition(10, 80);
        fireEffect1.start();
        fireEffect2.load(Gdx.files.internal("fireEffect.p"), Gdx.files.internal("img"));
        fireEffect2.setPosition(600, 85);
        fireEffect2.start();
        fireEffect3.load(Gdx.files.internal("fireEffect.p"), Gdx.files.internal("img"));
        fireEffect3.setPosition(400, 80);
        fireEffect3.start();
        fireEffect4.load(Gdx.files.internal("fireEffect.p"), Gdx.files.internal("img"));
        fireEffect4.setPosition(750, 90);
        fireEffect4.start();
        fireEffect5.load(Gdx.files.internal("fireEffect.p"), Gdx.files.internal("img"));
        fireEffect5.setPosition(300, 92);
        fireEffect5.start();
        fireEffect6.load(Gdx.files.internal("fireEffect.p"), Gdx.files.internal("img"));
        fireEffect6.setPosition(200, 85);
        fireEffect6.start();
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
    public int getWallHeight(){return WALL_HEIGHT;}

    public void render(SpriteBatch batch, float delta) {
        Texture wallImage = new Texture(getImage());
        wallSprite = new Sprite(wallImage,800, 45);
        wallSprite.setSize(800, 80);
        wallSprite.setX(0);
        wallSprite.setY(positionY);
        wallSprite.draw(batch);


        if(health < maxHealth/4){       //The wall is a 25% health.
            fireEffect5.draw(batch, delta);
            fireEffect6.draw(batch, delta);
        }
        if(health < maxHealth/2){       //The wall is at 50% health.
            fireEffect3.draw(batch, delta);
            fireEffect4.draw(batch, delta);
        }
        if(health < maxHealth * .75){   //The wall is at 75% health.
            fireEffect1.draw(batch, delta);
            fireEffect2.draw(batch, delta);
        }

        //batch.draw(wallImage, 0, positionY);
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
        return "thewall.png";         //Game over. The wall has been destroyed.

    }


    public Color getHealthBarColor(){
        if(health < maxHealth/4){
            return Color.RED;
        }
        if(health < maxHealth/2){
            return Color.YELLOW;
        }
        return Color.GREEN;
    }
}
