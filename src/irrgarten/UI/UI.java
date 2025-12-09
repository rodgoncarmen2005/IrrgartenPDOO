package irrgarten.UI;

import irrgarten.Directions;
import irrgarten.GameState;

/**
 * Interfaz que define los métodos que debe implementar una clase que se encargue
 * de la interacción con el usuario.
 *
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */
public interface UI {

    public Directions nextMove();
    
    
    public void showGame(GameState gameState);
}