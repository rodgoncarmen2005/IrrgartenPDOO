package irrgarten;

import java.util.ArrayList;

/**
 * Los FuzzyPlayer son jugadores que se mueven, atacan y defienden utilizando el azar y 
 * no de forma tan determinista como los jugadores existentes hasta ahora. 
 * Un FuzzyPlayer solo puede crearse a partir de otro jugador y dicha creación se produce
 * como resultado de la resurrección del mismo.
 */


public class FuzzyPlayer extends Player{
    
    /**
     * Constructor de la clase FuzzyPlayer. Llama al mismo metodo de su superclase. 
     * @param other instancia FuzzyPlayer que se va a copiar. 
     */
    public FuzzyPlayer(Player other){
        super(other); 
    }
    
    /**
     * Devuelve una direccion basada en: la dirección que devuelve move de Player pasada
     * al metodo de Dice::nextStep.     
     * @param direction direccion de movimiento preferida
     * @param validMoves array de las direccioness validas
     * @return direccion de movimiento elegida
     */
    @Override
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        Directions dir = super.move(direction, validMoves); 
        dir = Dice.nextStep(dir, validMoves, this.getIntelligence()); 
        return dir; 
    }
    
    /**
     * Ataque del jugador fuzzy: intensidad segun Dice + suma de la potencia de las armas.
     * @return valor correspondiente al ataque.
     */
    @Override
    public float attack(){ //IMPLEMENTACION DISTINTA DE PLAYER
        return sumWeapons() + Dice.intensity(this.getStrength()); 
    }
 
    /**
     * Defensa total del jugador como su intensidad segun Dice + proteccion de sus escudos.
     * @return suma de inteligencia + proteccion de sus escudos. 
     */
    @Override
    protected float defensiveEnergy(){//IMPLEMENTACION DISTINTA DE PLAYER
        return sumShields() + Dice.intensity(this.getIntelligence()); 
    }
    
    /**
     * Representacion del estado completo del FuzzyPLayer en una cadena.
     * @return cadena con el estado del FuzzyPLayer.
     */
    @Override
    public String toString(){
        String s = "Fuzzy "; 
        s += super.toString();
        return s; 
    }
}
