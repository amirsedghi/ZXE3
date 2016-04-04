package com.badlogic.dd;

import java.util.ArrayList;

/**
 * Created by JosePerez on 4/1/16.
 */
public class cannonPlayer extends GameObjects {
    private final int ammo = 12;
    private ArrayList<Cannonballs> Ammo;
    private boolean click;

    public cannonPlayer(ArrayList<Cannonballs> Ammo){
        this.Ammo = Ammo;
        //x = GameScreen.width/2;
        //y = GameScreen.height/2;

        //Cannon cannon1 = Cannon();


    }
    public void shoot(){
        if(Ammo.size() == ammo) return;
        Ammo.add(new Cannonballs(x,y,radians));
    }

}
