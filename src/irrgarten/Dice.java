
package irrgarten;

import java.util.Random;

public class Dice {
    private static final int MAX_USES = 5; //(número máximo de usos de armas y escudos)
    
    private static final float MAX_INTELLIGENCE = 10.0f; //(valor máximo para la inteligencia de jugadores y monstruos)
    
    private static final float MAX_STRENGTH = 10.0f;  //(valor máximo para la fuerza de jugadores y monstruos)
    
    private static final float RESURRECT_PROB = 0.3f; //(probabilidad de que un jugador sea resucitado en cada turno)
    
    private static final int WEAPONS_REWARD = 2; //(numero máximo de armas recibidas al ganar un combate)
    
    private static final int SHIELDS_REWARD = 3;  //(numero máximo de escudos recibidos al ganar un combate)
    
    private static final int HEALTH_REWARD = 5; //(numero máximo de unidades de salud recibidas al ganar un combate)
    
    private static final int MAX_ATTACK = 3; //(máxima potencia de las armas)
    
    private static final int MAX_SHIELD = 2; //(máxima potencia de los escudos) 
    
    private static final Random generator = new Random();
                  
     /**
      * Devuelve un número de fila o columna aleatoria entre 0 y max.
      * @param max El numero de filas o columnas del tablero.
      * @return número de fila o columna aleatoria entre [0, max).
      */       
    public static int randomPos(int max) { //https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
        return generator.nextInt(max);
    }

    /**
     * Devuelve el índice del jugador que comenzará la partida.
     * 
     * @param nplayers Número de jugadores en la partida
     * @return indice aleatorio del jugador que comenzará la partida [0, nplayers).
     */
    public static int whoStarts(int nplayers){
        return generator.nextInt(nplayers); 
    }
    
    /**
     * @return Valor aleatorio de inteligencia en el intervalo [0, MAX_INTELLIGENCE).
     */
    public static float randomIntelligence(){
       return generator.nextFloat(MAX_INTELLIGENCE); 
    }
    
    /**
     * @return Valor aleatorio de fuerza en el intervalo [0, MAX_STRENGTH).
     */
    public static float randomStrength(){
        return generator.nextFloat(MAX_STRENGTH);
    }
    
    /**
     * Determina si un jugador muerto debe ser resucitado o no con una probabilidad RESURRECT_PROB.
     * @return boolean indicando si el jugador debe ser resucitado.
     */
    public static boolean resurrectPlayer(){
        if(generator.nextFloat() <= RESURRECT_PROB){
            return true; 
        }
        else return false; 
    }
    
    /**
     * Indica la cantidad de armas que recibira el jugador por ganar el combate.
     * @return numero aleatorio [0, WEAPONS_REWARD]
     */
    public static int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD+1);
    }
    
    /**
     * Indica la cantidad de escudos que recibira el jugador por ganar el combate.
     * @return numero aleatorio [0, SHIELDS_REWARD]
     */
    public static int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD+1);
    }
    
    /**
     * Indica la cantidad de unidades de salud que recibira el jugador por ganar el combate.
     * @return numero aleatorio [0, HEALTH_REWARDú]
     */
    public static int healthReward(){
        return generator.nextInt(HEALTH_REWARD+1); 
    }
    
    /**
     * Indica la potencia que tendra un arma.
     * @return valor aleatorio [0, MAX_ATTACK). 
     */
    public static float weaponPower(){
        return generator.nextFloat(MAX_ATTACK); 
    }
    
    /**
     * Indica la potencia que tendra un escudo.
     * @return valor aleatorio [0, MAX_SHIELD). 
     */
    public static float shieldPower(){
        return generator.nextFloat(MAX_SHIELD); 
    }
    
    /**
     * Indica el numero de usos que se asignara a un arma o escudo.
     * @return numero aleatorio [0, MAX_USES].
     */
    public static int usesLeft(){
        return generator.nextInt(MAX_USES+1); 
    }
    
    /**
     * Devuelve la cantidad de competencia aplicada. 
     * @param competence la competencia aplicada
     * @return valor aleatorio en [0, competence).
     */
    public static float intensity(float competence){
        return generator.nextFloat(competence);
    }
    
    /**
     * Indica si se descarta un arma o escudo con una probabilidad inversamente proporcional 
     * a lo cercano que esté el parámetro del número máximo de usos que puede tener un arma o escudo. 
     * Las armas o escudos con más usos posibles es menos probable que sean descartados.
     * @param usesLeft el numero de usos del arma o escudo. 
     * @return boolean que indica si se descartara el elemento o no. 
     */
    public static boolean discardElement(int usesLeft){ 
        //Debe ser un float para que no haya division entera
        return generator.nextFloat() >= (usesLeft*1.0f/MAX_USES); 
    } 
}


