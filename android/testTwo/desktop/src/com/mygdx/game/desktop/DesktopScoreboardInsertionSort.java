package com.mygdx.game.desktop;
import com.mygdx.game.Scoreboard;

import java.util.Arrays;

/**
 * Created by ghost_000 on 3/21/2016.
 */

// Implement scoreboard using an array
public class DesktopScoreboardInsertionSort implements Scoreboard{
    // Private members
    private ScoreField[] entries;

    // Consturctor(s)
    public DesktopScoreboardInsertionSort(){
        // Create blank entries
        entries = new ScoreField[11];    // Leave 1 extra
        for (int i = 0; i < entries.length; i++){
            entries[i] = new ScoreField("None", 0);
        }
    }
    @Override
    public boolean nameSubmittable(){
        return true;
    }
    @Override
    public void submitScore (String name, int score){
        // Enter into the end of the array.
        entries[entries.length - 1] = new ScoreField(name, score);
        // Sort it
        /*for(int i = 1; i < entries.length; i++){
            int j;
            int key = entries[i].getScoreNum();
            for(j = i-1; (j >= 0) && (entries[j].getScoreNum() < key); j--){
                entries[j+1] = entries[j];
            }
            entries[j+1] =  entries[i];
        }*/
        Arrays.sort(entries);
        // End
    }
    @Override
    public ScoreField getScore (int index){
        return entries[index];
    }

    class ScoreField implements Scoreboard.ScoreField, Comparable<ScoreField>{
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
        // Comparator
        @Override
        public int compareTo(ScoreField comparison){
            return comparison.getScoreNum() - this.score;
        }
    }
}
