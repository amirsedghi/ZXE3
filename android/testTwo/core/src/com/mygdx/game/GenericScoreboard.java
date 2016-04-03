package com.mygdx.game;

import java.util.Arrays;

// Generic scoreboard implementation
// Uses simple arrays to implement scoreboard
// Data is not saved once closed.

public class GenericScoreboard implements Scoreboard {
    // Private members
    private ScoreField[] entries;

    // Consturctor(s)
    public GenericScoreboard(){
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
        // Comparator for Arrays.sort();
        @Override
        public int compareTo(ScoreField comparison){
            return comparison.getScoreNum() - this.score;
        }
    }
}
