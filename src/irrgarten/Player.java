
package irrgarten;

import java.util.ArrayList; //https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
//import java.util.Iterator; Para usar iteradores en receivedWeapon(Weapon w)


public class Player extends LabyrinthCharacter {
    
    private static final int MAX_WEAPONS = 2; //(max armas por jugador)
    
    private static final int MAX_SHIELDS = 3; //(max escudos por jugador)
    
    private static final int INITIAL_HEALTH = 10; //(salud inicial del jugador)
    
    private static final int HITS2LOSE = 3; //(num de golpes que puede recibir antes de morir)
    
    private String name; //(nombre del jugador)
    
    private char number; //(numero identificador del jugador)
    
    private float intelligence; //(inteligencia actual del jugador)
    
    private float strength; //(fuerza actual del jugador)
    
    private float health; //(salud actual del jugador)
    
    private int row; //(fila en la que se encuentra dentro del laberinto)
    
    private int col; //(columna en la que se encuentra dentro del laberinto)
    
    private int consecutiveHits=0; //(num golpes consecutivos que ha recibido un jugador)
    
    private ArrayList<Weapon> weapons; //Relacion con Weapon (array de las armas)
    
    private ArrayList<Shield> shields; //Relacion con Shield (array de los escudos)
    
    private WeaponCardDeck weaponCardDeck;
    
    private ShieldCardDeck shieldCardDeck;
    /**
     * Constructor de la clase Player. Se inicializa con una posicion (-1,-1) en el tablero.
     * Actualmente no tiene armas ni escudos.
     * @param number numero identificador del jugador
     * @param intelligence inteligencia
     * @param strength fuerza
     */
    public Player(char number, float intelligence, float strength){
        
        super("Player " + number,intelligence, strength, INITIAL_HEALTH);
        this.number = number; 
        this.intelligence = intelligence; 
        this.strength = strength; 
        health = INITIAL_HEALTH; 
        row = -1;  
        col = -1; 
        weapons = new ArrayList<Weapon>(); 
        shields = new ArrayList<Shield>(); 
      
    }
    
    /**
     * Tareas asociadas a la resurreccion: reestablece la salud, el numero de golpes 
     * consecutivos y elimina las armas y escudos.
     */
    public void resurrect(){
        weapons.clear();
        shields.clear();
        health = INITIAL_HEALTH; 
        resetHits(); 
    }
    
    
    /**
     * Getter del numero identificador del jugador.
     * @return numero de jugador.
     */
    public char getNumber(){
        return number; 
    }
    
    
    /**
     * Comprueba si la direccion a la que se quiere mover al jugador es valida o no. 
     * Si es valida, la devuelve. Si no es valida, devuelve la primera direccion de
     * validMoves. 
     * @param direction direccion a la que se quiere mover el jugador.
     * @param validMoves conjunto de movimientos validos
     * @return la direccion a la que se movera el jugador
     */
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);
        
        if ((size > 0) && !contained){
            Directions firstElement = validMoves.get(0); 
            return firstElement; 
        }
        else return direction; 
       
    }
    
    /**
     * Ataque del jugador: fuerza + suma de la potencia de las armas.
     * @return valor correspondiente al ataque.
     */
    @Override
    public float attack(){
        return strength + sumWeapons(); 
    }    
    
    /**
     * Defensa del jugador. El metodo manageHit lo gestiona.
     * @param receivedAttack ataque recibido del monstruo.
     * @return true si se defiende del ataque.
     */
    @Override
    public boolean defend(float receivedAttack){
        return manageHit(receivedAttack); 
    }
    
    /**
     * Gestiona la recompensa: crea y guarda armas escudos determinadas por el dado 
     * (weaponsReward() y shieldsReward()) y aumenta la salud segun los resultados del
     * dado (healthReward()). 
     */
    public void receivedReward(){
       
        int wReward = Dice.weaponsReward(); 
        int sReward = Dice.shieldsReward(); 
        
        for(int i = 1; i < wReward; i++){
            Weapon wnew = newWeapon(); 
            receivedWeapon(wnew);
        }
        
        for(int i = 1; i < sReward; i++){
            Shield snew = newShield(); 
            receivedShield(snew);
        }
        
        int extraHealth = Dice.healthReward();
        health += extraHealth;
    }
    
    
    /**
     * Actualiza las armas de un jugador descartando las necesarias y añadiendo w si cabe.
     * @param w el arma que se aniade.
     */
    private void receivedWeapon(Weapon w){
        //Recorrido del inicio al final
        /*for(int i = 0; i < weapons.size(); ++i){
            Weapon wi = weapons.get(i); 
            boolean discard = wi.discard();
            
            if (discard){
                weapons.remove(wi);
                i--; //Para no saltarnos ningun elemento si eliminamos uno.
            }
        }*/
        
        //Usando iteradores
        /*Iterator<Weapon> it = weapons.iterator();

        while (it.hasNext()) {
            Weapon wi = it.next();   // <- la 1ª vez devuelve el primer elemento

            if (wi.discard()) {
                it.remove();         // elimina el elemento devuelto por next()
            }
        }*/
        
        
        //Recorrido del final al inicio
        for(int i = weapons.size()-1; i >= 0; --i){
            Weapon wi = weapons.get(i); 
            boolean discard = wi.discard();
            
            if (discard){
                weapons.remove(wi);
            }
        }
        
        if(weapons.size() < MAX_WEAPONS){
            weapons.add(w);
        }
    }
    
    /**
     * Actualiza los escudos de un jugador descartando los necesarias y añadiendo s si cabe.
     * @param s el escudo que se quiere aniadir. 
     */
    private void receivedShield(Shield s){ 
               
        for(int i = shields.size()-1; i >= 0; --i){
            Shield si = shields.get(i); 
            boolean discard = si.discard();
            
            if (discard){
                shields.remove(si); //RUBY: @weapons.delete{|wi| wi.discard()}
            }
        }
        
        if(shields.size() < MAX_SHIELDS){
            shields.add(s);
        }
    }
    
    /**
     * Crea un arma con los parametros que se decidan con el dado.
     * @return una nueva arma.
     */
    private Weapon newWeapon(){
        Weapon w = new Weapon(Dice.weaponPower(), Dice.usesLeft()); 
        return w; 
    }
    
    /**
     * Crea un escudo con los parametros que se decidan con el dado.
     * @return un nuevo escudo.
     */
    private Shield newShield(){
        Shield s = new Shield(Dice.shieldPower(), Dice.usesLeft()); 
        return s; 
    }
    
    /**
     * Attack de todas las armas del jugador. 
     * @return suma del resultado del metodo attack de todas las armas
     */
    protected float sumWeapons(){
        float sum = 0.0f;
        for(int i = 0; i < weapons.size(); ++i){
            sum += weapons.get(i).attack(); 
        }
        return sum; 
    }
    
    /**
     * Protect de todos los escudos.
     * @return suma del resultado de llamar al método protect de todos sus escudos.
     */
    protected float sumShields(){
        float sum = 0.0f; 
        for (int i = 0; i < shields.size(); ++i){
            sum += shields.get(i).protect(); 
        }
        return sum; 
    }
    
    /**
     * Defensa total del jugador como su inteligencia + proteccion de sus escudos.
     * @return suma de inteligencia + proteccion de sus escudos. 
     */
    protected float defensiveEnergy(){
        return intelligence + sumShields(); 
    }
    
    /**
     * Gestiona los ataques recibidos.
     * @param recivedAttack la instensidad del ataque recibido.
     * @return true si el jugador ha muerto o llega al max de ataques consecutivos HIT2LOSE.
     */
    private boolean manageHit(float recivedAttack){
        float defense = defensiveEnergy(); 
        boolean lose = false; 
        
        if (defense < recivedAttack){
            gotWounded(); 
            incConsecutiveHits(); 
        }
        else resetHits(); 
        
        if ((consecutiveHits == HITS2LOSE) || (dead())){ //SE PUEDE LLAMAR DIRECTAMENTE A HITS2LOSE AUN SIENDO DE CLASE??
            resetHits(); 
            lose = true; 
        }
        
        return lose;     
    }
    
    /**
     * Fija el valor de impactos consecutivos a 0.
     */
    private void resetHits(){
        consecutiveHits = 0; 
    }
    
    
    /**
     * Incrementa en una unidad el contador de impactos consecutivos.
     */
    private void incConsecutiveHits(){
        consecutiveHits++; 
    }
    
}
