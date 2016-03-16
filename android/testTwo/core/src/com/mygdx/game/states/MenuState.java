package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.WallDefender;

/**
 * Created by pinso on 3/13/2016.
 */
public class MenuState extends State{
    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("menubg.png");
        playBtn = new Texture("play_button.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            dispose();
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, WallDefender.WIDTH, WallDefender.HEIGHT);
        sb.draw(playBtn, (WallDefender.WIDTH/2) - (playBtn.getWidth()/2), (WallDefender.HEIGHT/2) - (playBtn.getHeight()/2));
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
