
        package com.badlogic.dd;

        import java.beans.VetoableChangeListener;
        import java.util.ArrayList;
        import java.util.Iterator;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Input;
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
        import com.badlogic.gdx.scenes.scene2d.Actor;
        import com.badlogic.gdx.scenes.scene2d.InputEvent;
        import com.badlogic.gdx.scenes.scene2d.InputListener;
        import com.badlogic.gdx.scenes.scene2d.Touchable;
        import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
        import com.badlogic.gdx.utils.Array;
        import com.badlogic.gdx.utils.TimeUtils;
        import com.badlogic.gdx.math.*;


        /**
 * Created by evolerup on 3/31/16.
 */
public class Cannon extends Actor{

    // create the variables
    private Texture cannonImage;
    private Sprite cannonRegion;
    Vector3 mousePos;
    private double tan = 0;
    private float angle= 0;
    private final int ammo = 12;
    private ArrayList<Cannonballs> Ammo;

    // constructor, initiating the texture and texture region
    Cannon(ArrayList<Cannonballs> cannonballs){
        this.Ammo = cannonballs;
        cannonImage = (new Texture(Gdx.files.internal("cannonImage.png")));
        cannonRegion = new Sprite(cannonImage);


        setBounds(cannonRegion.getX(),cannonRegion.getY(),
                cannonRegion.getWidth(), cannonRegion.getHeight());

        setTouchable(Touchable.enabled);
        addListener(new InputListener(){
        //will eventually handle space bar to shoot
         @Override
            public boolean keyDown(InputEvent event, int keycode){
             if(keycode == Input.Keys.SPACE){
                 //move actor cannon right
                 MoveByAction mba = new MoveByAction();
                 mba.setAmount(100f, 0f);
                 mba.setDuration(5f);

                 Cannon.this.addAction(mba);
             }
             return true;
         }
    });
    }

    // set the vector
    public void setVector(Vector3 v){
        mousePos = v;
    }

    // return the calculated angle
    public float getAngle(){

        // this calculate the tangent of the angle relative to y
        // and inverse tangent is calculated to get the angle
        // based on the position of the mouse
        tan = (mousePos.x-380)/(mousePos.y-64);
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

//    public void shoot(){
//        if(Ammo.size() == ammo) return;
//        Ammo.add(new Cannonballs(x,y,radians));
//    }

    // this returns the texture region
    public TextureRegion getTextureRegion(){
        return cannonRegion;
    }
    @Override
    protected void positionChanged(){
        cannonRegion.setPosition(getX(),getY());
        super.positionChanged();
            }

    @Override
    public void draw(Batch batch, float parentAlpha){
        cannonRegion.draw(batch);
    }
    @Override
    public void act(float delta){
        super.act(delta);
    }

}