
package irrgarten;

import java.util.ArrayList; //https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
//import java.util.Iterator; Para usar iteradores en receivedWeapon(Weapon w)


public class Player {
    
    private static final int MAX_WEAPONS = 2; 
    
    private static final int MAX_SHIELDS = 3;
    
    private static final int INITIAL_HEALTH = 10; 
    
    private static final int HITS2LOSE = 3; 
    
    private String name; 
    
    private char number; 
    
    private float intelligence; 
    
    private float strength; 
    
    private float health; 
    
    private int row; 
    
    private int col; 
    
    private int consecutiveHits=0; 
    
    private ArrayList<Weapon> weapons; //Relacion con Weapon
    
    private ArrayList<Shield> shields; //Relacion con Shield
    
    
    
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
    
    public void resurrect(){
        if (Dice.resurrectPlayer()){
            weapons.clear();
            shields.clear();
            health = INITIAL_HEALTH; 
            resetHits(); 
        }
    }
    
    public int getRow(){
        return row; 
    }
    
    public int getCol(){
        return col; 
        
    }
    
    public char getNumber(){
        return number; 
    }
    
    public void setPos(int row, int col){
       this.row = row; 
       this.col = col; 
    }
    
    public boolean dead(){
       return health <= 0; 
    }
    
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);
        
        if ((size > 0) && !contained){
            Directions firstElement = validMoves.get(0); 
            return firstElement; 
        }
        else return direction; 
       
    }
    
    
    public float attack(){
        return strength + sumWeapons(); 
    }    
    
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
    
    private Weapon newWeapon(){
        Weapon w = new Weapon(Dice.weaponPower(), Dice.usesLeft()); 
        return w; 
    }
    
    private Shield newShield(){
        Shield s = new Shield(Dice.shieldPower(), Dice.usesLeft()); 
        return s; 
    }
    
    private float sumWeapons(){
        float sum = 0.0f;
        for(int i = 0; i < weapons.size(); ++i){
            sum += weapons.get(i).attack(); 
        }
        return sum; 
    }
    
    private float sumShields(){
        float sum = 0.0f; 
        for (int i = 0; i < shields.size(); ++i){
            sum += shields.get(i).protect(); 
        }
        return sum; 
    }
    
    private float defensiveEnergy(){
        return intelligence + sumShields(); 
    }
    
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
    
    private void resetHits(){
        consecutiveHits = 0; 
    }
    
    private void gotWounded(){
        health--; 
    }
    
    private void incConsecutiveHits(){
        consecutiveHits++; 
    }
    
}
