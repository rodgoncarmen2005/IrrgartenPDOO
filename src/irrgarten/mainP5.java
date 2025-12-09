package irrgarten;

import irrgarten.controller.Controller;
import irrgarten.UI.GraphicUI;

public class mainP5 {
    
    public static void main ( String [ ] args ) {
        
        final int NUM_PLAYERS = 2;

        GraphicUI vista = new GraphicUI();
        Game juego = new Game(NUM_PLAYERS);
        Controller controlador = new Controller(juego, vista);

        controlador.play();
    }
}
