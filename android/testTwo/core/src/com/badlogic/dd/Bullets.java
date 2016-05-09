package com.badlogic.dd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jose Perez and Jacob on 4/4/16.
 * The bullet class is used to give properties to a cannonball sprite
 * that will travel along a vector path given mouse position
 * relative to the cannons angle of direction
 */
public class Bullets {
    // Universals
    private Texture bulletTexture;
    private Sprite bulletSprite;
    private Vector3 mousepos;
    private int initX;
    private int initY;
    private Vector3 coordinates;
    private final int WIDTH = 60;
    private final int HEIGHT = 60;
    private double groundDisplacement = 0;
    private double time = 0;
    private double velocity = 5;
    private double heightDisplacement = 0;
    private double sumDisplacement;
    private Vector3 mousePosition;



    //private double angel;
    private double xVel;
    private double yVel;

    //coordinates of the center of the cannon ball
    private double xCC;
    private double yCC;

    //Cannonballs that will fire from the cannon,
    //each cannon will be given the position of the mouse click
    //on the screen and will use that to adjust the velocity of the cannonball
    Bullets(Vector3 mp, int initX, int initY, float a){
        this.mousepos = mp;
        this.initX = initX;
        this.initY = initY;
        //
        bulletTexture = new Texture("Cannonball.png");
        bulletSprite = new Sprite(bulletTexture);
        bulletSprite.setSize(WIDTH,HEIGHT);
        bulletSprite.setOrigin(WIDTH, HEIGHT);

        xCC = initX + WIDTH/2;
        yCC = initY + HEIGHT/2;


        bulletSprite.setPosition(initX, initY);

        // calculation for bullet size change
        double xDisplacement = Math.pow(mousepos.x - xCC,2);
        double yDisplacement = Math.pow(mousepos.y - yCC,2);
        sumDisplacement = xDisplacement + yDisplacement;
        mousePosition = mp;

        xVel = velocity * Math.sin(Math.PI*a/180);
        yVel = velocity * Math.cos(Math.PI*a/180);


        if(yVel < 0){
            xVel *= 1;
            yVel *= 1;
        }
    }

    public Sprite getSprite(){
        return bulletSprite;
    }


    public Vector2 getBulletPosition() {
        return new Vector2(bulletSprite.getX(), bulletSprite.getY());
    }

    public boolean updateBullet(){
        // True if finished travelling, False if not.
        // Update the travel path of the bullet
        // Dispose if finished
        // variables for bullet size change

        double bulletPosX = bulletSprite.getX();
        double bulletPosY = bulletSprite.getY();
        double currentXPos = Math.sqrt(Math.pow(bulletPosX-initX,2)+Math.pow(bulletPosY-initY,2));

        // move the bullet with the given speed
        bulletSprite.translate((float)xVel, (float)yVel);
        // calculate how far on the x axis the bullet needs to travel
        groundDisplacement = Math.sqrt(sumDisplacement);
        // a parabolic equations that gets the value of y as the bullet moves forward
        heightDisplacement = -8/Math.pow(groundDisplacement,2)*Math.pow(currentXPos-groundDisplacement/2,2) + 2;


        // change the size of the bullet based on how high it goes in the air
        if (heightDisplacement>0)
        this.getSprite().setSize((float) (1 + heightDisplacement/2) * WIDTH, (float) (1 + heightDisplacement/2) * HEIGHT);

        // Return false if we've reached past the destination
        if(bulletSprite.getY() - initY > mousePosition.y - yCC){
            return false;

        }
        else {
            return true;
        }


    }

    public void dispose(){
        bulletTexture.dispose();
    }
}
