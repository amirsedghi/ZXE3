package com.badlogic.dd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

/**
 * Created by JosePerez on 4/4/16.
 */
public class Bullets {
    // Universals
    private Texture bulletTexture;
    private Sprite bulletSprite;
    private Vector3 mousepos;
    private int initX;
    private int initY;
    //private double angel;
    private double xVel;
    private double yVel;

    Bullets(Vector3 mousepos, int initX, int initY){
        this.mousepos = mousepos;
        this.initX = initX;
        this.initY = initY;
        //
        bulletTexture = new Texture("cannonBallImage.png");
        bulletSprite = new Sprite(bulletTexture);
        bulletSprite.setSize(60,60);
        bulletSprite.setOriginCenter();

        bulletSprite.setPosition(initX, initY);
        double angel = Math.atan((mousepos.y - initY)/(mousepos.x - initX));
        final int velocity = 5;
        xVel = velocity * Math.cos(angel);
        yVel = velocity * Math.sin(angel);
        if(yVel < 0){
            xVel *= -1;
            yVel *= -1;
        }
    }

    public Sprite getSprite(){
        return bulletSprite;
    }

    public boolean updateBullet(){
        // True if finished travelling, False if not.
        // Update the travel path of the bullet
        // Dispose if finished

        bulletSprite.translate((float)xVel, (float)yVel);

        // Return false if we've reached past the destination
        if(bulletSprite.getY() >= mousepos.y){
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
