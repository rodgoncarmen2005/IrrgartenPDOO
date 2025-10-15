
package irrgarten;

public class GameState {
    private String labyrinth;
    private String players;
    private String monsters;
    private int currentPlayer;
    private boolean winner;
    private String log;
    
    // This is a constructor for the `GameState` class in Java. It initializes the state of the game by
    // setting the values of the `labyrinth`, `players`, `monsters`, `currentPlayer`, `winner`, and `log`
    // variables based on the parameters passed to the constructor.
    public GameState(String labyrinth, String players, String monsters, int currentPlayer, boolean winner, String log){
        this.labyrinth = labyrinth;
        this.players = players;
        this. monsters = monsters;
        this.currentPlayer = currentPlayer;
        this.winner = winner;
        this.log = log;
    }
    

    /**
    * @return The method `getLabyrinth` is returning the `labyrinth` attribute of the current object,
    * which is likely a String representing a labyrinth.
    */

    /**
     * @return The method `getLabyrinth` is returning the `labyrinth` attribute of the current object.
     */
    public String getLabyrinth(){
        return this.labyrinth;
    }

    /**
     * @return The method `getPlayers()` is returning the value of the `players` field in the current
     * object.
     */
    public String getPlayers(){
        return this.players;
    }
    /**
     * @return The method `getMonsters()` is returning the value of the `monsters` attribute of the
     * current object.
     */
    public String getMonsters(){
        return this.monsters;
    }

    /**
     * @return The method `getCurrentPlayer()` is returning the value of the `currentPlayer` attribute
     * of the current object.
     */
    public int getCurrentPlayer(){
        return this.currentPlayer;
    }

    /**
     * @return The method `getWinner()` is returning the value of the `winner` attribute of the current
     * object, which is a boolean indicating whether there is a winner or not.
     */
    public boolean getWinner(){
        return this.winner;
    }

    /**
     * @return The method `getLog()` is returning the value of the `log` attribute of the current
     * object, which is a String.
     */
    public String getLog(){
        return this.log;
    }      
}
