
package irrgarten;

import java.util.ArrayList; //https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
//import java.util.Iterator; Para usar iteradores en receivedWeapon(Weapon w)


public class Player {
    
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
    
    
    /**
     * Constructor de la clase Player. Se inicializa con una posicion (-1,-1) en el tablero.
     * Actualmente no tiene armas ni escudos.
     * @param number numero identificador del jugador
     * @param intelligence inteligencia
     * @param strength fuerza
     */
    public Player(char number, float intelligence, float strength){
        
        name = "Player#" + number; 
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
        if (Dice.resurrectPlayer()){
            weapons.clear();
            shields.clear();
            health = INITIAL_HEALTH; 
            resetHits(); 
        }
    }
    
    /**
     * Getter de la fila en la que se encuentra posicionado el jugador.
     * @return fila de la posición del jugador.
     */
    public int getRow(){
        return row; 
    }
    
    /**
     * Getter de la columna en la que se encuentra posicionado el jugador.
     * @return columna de la posicion del jugador.
     */
    public int getCol(){
        return col; 
        
    }
    
    /**
     * Getter del numero identificador del jugador.
     * @return numero de jugador.
     */
    public char getNumber(){
        return number; 
    }
    
    /**
     * Setter de la posición del jugador en el laberinto.
     * @param row fila dentro de la posicion.
     * @param col columna dentro de la posicion.
     */
    public void setPos(int row, int col){
       this.row = row; 
       this.col = col; 
    }
    
    /**
     * Indica si el jugador esta muerto.
     * @return true si el jugador esta muerto.
     */
    public boolean dead(){
       return health <= 0; 
    }
    
    /**
     * 
     * @param direction
     * @param validMoves
     * @return 
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
    public float attack(){
        return strength + sumWeapons(); 
    }    
    
    /**
     * Defensa del jugador. El metodo manageHit lo gestiona.
     * @param receivedAttack ataque recibido del monstruo.
     * @return true si se defiende del ataque.
     */
    public boolean defend(float receivedAttack){
        return manageHit(receivedAttack); 
    }
    
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
     * Representacion del estado completo del jugador en una cadena.
     * @return cadena con el estado del jugador.
     */
    @Override
    public String toString(){
        return name + "[I:" + intelligence + ", S:" + strength + ", H" + health + ", Pos:" + row + "," + col + ", Hits:" + consecutiveHits + ", W:" + weapons.size() + ", S:" + shields.size() + "]"; 
    }
    
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
    private float sumWeapons(){
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
    private float sumShields(){
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
    private float defensiveEnergy(){
        return intelligence + sumShields(); 
    }
    
    /**
     * ó
     * @param recivedAttack
     * @return 
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
     * Decrementa la salud en una unidad por herida.
     */
    private void gotWounded(){
        health--; 
    }
    
    /**
     * Incrementa en una unidad el contador de impactos consecutivos.
     */
    private void incConsecutiveHits(){
        consecutiveHits++; 
    }
    
}
