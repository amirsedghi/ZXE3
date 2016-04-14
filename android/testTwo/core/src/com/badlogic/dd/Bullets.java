package com.badlogic.dd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

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
        bulletTexture = new Texture("cannonBallImage.png");
        bulletSprite = new Sprite(bulletTexture);
        bulletSprite.setSize(60,60);
        bulletSprite.setOrigin(30, 30);

        xCC = initX + 30;
        yCC = initY + 30;


        bulletSprite.setPosition(initX, initY);
        //double angel = Math.atan((mousepos.y - initY)/(mousepos.x - initX));

        final double velocity = 5;
        xVel = velocity * Math.sin(Math.PI*a/180);
        yVel = velocity * Math.cos(Math.PI*a/180);
        if(yVel < 0){
            xVel *= -1;
            yVel *= -1;
        }
    }

    public Sprite getSprite(){
        return bulletSprite;
    }

    public Vector3 getCoordinates(){
        coordinates = new Vector3((float) (mousepos.x - xCC+ initX),(float) (mousepos.y-yCC+initY),0);
        return coordinates;
    }


    public Vector2 getBulletPosition() {
        return new Vector2(bulletSprite.getX(), bulletSprite.getY());
    }

    public boolean updateBullet(){
        // True if finished travelling, False if not.
        // Update the travel path of the bullet
        // Dispose if finished

        bulletSprite.translate((float)xVel, (float)yVel);

        // Return false if we've reached past the destination
        if(bulletSprite.getY() - initY > mousepos.y - yCC){
            return false;
            //-30*Math.cos(Math.PI*a/180)
        }
        else {
            return true;
        }

        // if(bulletSprite.getY() >= mousepos.y-30*Math.cos(Math.PI*a/180))
    }

    public void dispose(){
        bulletTexture.dispose();
    }
}
