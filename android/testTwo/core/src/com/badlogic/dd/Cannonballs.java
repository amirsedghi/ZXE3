package com.badlogic.dd;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by JosePerez on 4/1/16.
 */

public class Cannonballs extends GameObjects {

    private float lifeTime;
    private float lifeTimer;

    private boolean remove;

    Cannonballs(float x, float y, float radians){
        this.x = x;
        this.y = y;
        this.radians = radians;

        float speed = 350;
        dx = MathUtils.cos(radians);
        dy = MathUtils.sin(radians);

        height = width = 16;
        lifeTime = 0;
        lifeTimer = 1;
    }

    public boolean shouldRemove(){
        return remove;
    }

    //movement of ball
    public void update(float dt){

        x += dx * dt;
        y += dy * dt;



        lifeTimer += dt;
        if(lifeTimer > lifeTime){
            remove = true;
        }
    }
    //Used to draw bullet on screen
    public void draw(ShapeRenderer sr){
        sr.setColor(1,0,0,1);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.circle(x - width/2,y - height / 2,width/2);
        sr.end();
    }

}

