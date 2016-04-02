package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Wall;
import com.mygdx.game.WallDefender;

/**
 * Created by pinso on 3/13/2016.
 */
public class PlayState extends State {
    private Texture wallImage;
    private Wall wall;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        wall = new Wall(100, 168, WallDefender.WIDTH/2, 0);

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                // check collision
                int x = Gdx.graphics.getWidth() - Gdx.input.getX();
                int y = Gdx.graphics.getHeight() - Gdx.input.getY();
                if (wall.collides(x, y, 20)) {
                    System.out.println("Collision on " + x + ", " + y);
                } else {
                    System.out.println("NO collision on " + x + ", " + y);
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.H)){
            // heal wall
            wall.adjustHealth(10);
            System.out.println("Healed --> " + wall.getHealth());
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            // do wall damage
            wall.adjustHealth(-10);
            System.out.println("Damaged --> " + wall.getHealth());
        }
    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        wallImage = new Texture(wall.getImage());
        sb.begin();
        sb.draw(wallImage, (WallDefender.WIDTH/2) - (wallImage.getWidth()/2), 0);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
