//package irrgarten.controller;
package irrgarten;

import irrgarten.Directions;
import irrgarten.Game;
//import irrgarten.UI.TextUI;
import irrgarten.TextUI;

public class Controller {
    
    private Game game;
    private TextUI view;
    
    public Controller(Game game, TextUI view) {
        this.game = game;
        this.view = view;
    }
    
    public void play() {
        boolean endOfGame = false; 
        while (!endOfGame) {
            view.showGame(game.getGameState()); 
            Directions direction = view.nextMove(); //Pide un movimiento al usuario
            endOfGame = game.nextStep(direction);
        }
      view.showGame(game.getGameState()); //Muestra la informacion una vez terminado el juego  
    }
    
}


