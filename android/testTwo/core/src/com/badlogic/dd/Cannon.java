package com.badlogic.dd;

import java.beans.VetoableChangeListener;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.math.*;
/**
 * Created by evolerup on 3/31/16.
 */

/*
class Cannon:
Purpose: to instantiate the texture of the cannon and calculate the angle at which the cannon should point to
Author: Amir Sedghi
Date: 3/28/16
 */
public class Cannon{

    // create the variables
    private Texture cannonImage;
    private TextureRegion cannonRegion;
    Vector3 mousePos;
    private double tan =0;
    private float angle=0;

    private Sprite cannonSprite;

    // constructor, initiating the texture and texture region
    Cannon(){
        cannonImage = new Texture(Gdx.files.internal("cannonImage.png"));
        //cannonRegion = new TextureRegion(cannonImage, 120, 108);
        cannonSprite = new Sprite(cannonImage, 120 , 108);

    }

    /*
    Name: setVector
    purpose: receives the location of the mouse during the game for angle calculation
    input parameter: Vector3 which has three elements, x, y, and z
    no return value
    author: Amir Sedghi
    date: 3/28/16
     */
    public void setVector(Vector3 v){
        mousePos = v;
    }


    /*
       Name: getAngle
       purpose: calculates the angle based on the vector that is passed to mousePos
       input parameter: no input
       return: returns a float value, which is the calculated angle
       author: Amir Sedghi
       date: 3/28/16
    */
    public float getAngle(){

        // this calculate the tangent of the angle relative to y
        // and inverse tangent is calculated to get the angle
        // based on the position of the mouse
        tan = (mousePos.x-400)/(mousePos.y-64);
        angle = (float)(180*Math.atan(tan)/Math.PI);


        // this sets an angle to a fixed value, 90, if the mouse exits
        // the coordinates allowed for the game
        if ((mousePos.y-64 == 0 || mousePos.y-64 <0) && mousePos.x-380 > 0){
            angle = 90;
        }

        if ((mousePos.y-64 == 0 || mousePos.y-64 <0) && mousePos.x-380 < 0){
            angle = -90;
        }

        return angle;
    }

    /*
   Name: getSprite
   purpose: returns the sprite belonging to the cannon
   input parameter: no input
   return: returns the sprite
   author: Amir Sedghi
   date: 3/28/16
    */

    public Sprite getSprite(){
        return cannonSprite;
    }

}
