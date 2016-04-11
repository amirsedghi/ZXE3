package com.badlogic.dd;

/*
 * Scoreboard Interface
 * Interface for a scoreboard implementation
 * Scoreboards could be implemented in different ways on different platforms
 * Including using a special scoreboard libary, or storing in a particular way
 * By Jacob
 * 3/21/16
 */

public interface Scoreboard {
    /*
     * Name Submittable
     * Returns true if implementation allows for a name to be submitted
     * By Jacob
     * 3/21/16
     */
    boolean nameSubmittable();

    /*
     * Submit Score
     * Simply submits a score
     * Name is null if the implementer doesn't take a name
     * By Jacob
     * 3/21/16
     */
    void submitScore(String name, int score);

    // Get a requested scorefield
    // Returns null if implementation is external
    /*
     * Get Score
     * Returns a scorefield type (written below) of a given index
     * By Jacob
     * 3/21/16
     */
    ScoreField getScore(int index);

    /*
     * ScoreField class
     * Simple object for string a scorefield (just name and number)
     * By Jacob
     * 3/21/16
     */
    interface ScoreField{
        String getName();
        int getScoreNum();
    }
}
