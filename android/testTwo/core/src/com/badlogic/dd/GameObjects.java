package com.badlogic.dd;

/**
 * Created by JosePerez on 4/1/16.
 */
//This class is used for coordinates of objects on the active gamescreen
    //probably will delete if use scene2d

public class GameObjects {
    protected float x;
    protected float y;
    protected float dx;
    protected float dy;

    protected float radians;
    protected float speed;
    protected float rotationSpeed;

    protected int width;
    protected int height;

    protected float[] shapeX;
    protected float[] shapeY;

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }

    public float[] getShapeX(){
        return shapeX;
    }

    public float[] getShapeY(){
        return shapeY;
    }
}
