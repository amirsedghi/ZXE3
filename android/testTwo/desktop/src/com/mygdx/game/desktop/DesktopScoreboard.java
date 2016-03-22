package com.mygdx.game.desktop;
import com.mygdx.game.Scoreboard;

import java.awt.*;
import java.util.*;

/**
 * Created by ghost_000 on 3/21/2016.
 */
public class DesktopScoreboard implements Scoreboard {
    // Temporary implementation using simple insertion array list
    // Private members
    ArrayList<ScoreField> entries= new ArrayList<ScoreField>();

    public DesktopScoreboard(){
        // Set with 10 blank entries
        for (int i = 0; i < 10; i++){
            entries.add(i, new ScoreField("???", 0));
        }
    }

    @Override
    public boolean nameSubmittable(){
        return true;
    }
    @Override
    public void submitScore (String name, int score){
        // Store current entry size
        int scoreboardSize = entries.size();
        // Check for spot to submit
        for (int i = 0; i < scoreboardSize; i++){
            if(entries.get(i).getScoreNum() <= score
                    && i != scoreboardSize - 1 && (entries.get(i+1).getScoreNum() >= score ||
                    entries.get(i+1).getScoreNum() == 0)){
                entries.add(i, new ScoreField(name, score));
                break;
            }
            else{
                // Do nothing, don't add anything
            }
        }
        // Delete excess
        if (entries.size() > scoreboardSize){
            entries.remove(scoreboardSize);
        }
    }
    @Override
    public ScoreField getScore(int index){
        return entries.get(index);
    }

    // Simple ScoreField object
    class ScoreField implements Scoreboard.ScoreField{
        // Fields for scorefield
        private String name;
        private int score;
        // Constructor
        private ScoreField(String name, int score) {
            this.name = name;
            this.score = score;
        }
        // Getters defined in the interface
        @Override
        public String getName(){
            return name;
        }
        @Override
        public int getScoreNum(){
            return score;
        }
    }
}
