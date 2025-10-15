
package irrgarten;

import java.util.ArrayList; //https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
import java.lang.UnsupportedOperationException;

public class Player {
    
    private static final int MAX_WEAPONS=2; 
    
    private static final int MAX_SHIELD=3;
    
    private static final int INITIAL_HEALTH=10; 
    
    private static final int HITS2LOSE=3; 
    
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
    
    public int getNumber(){
        return number; 
    }
    
    public void setPos(int row, int col){
       this.row = row; 
       this.col = col; 
    }
    
    public boolean dead(){
       return health > 0; 
    }
    
    public Directions move(Directions direction, ArrayList<Directions> validMoves){//ARRAYLIST O ARRAY NORMAL[]
        throw new UnsupportedOperationException();
    }
    
    public float attack(){
        return strength + sumWeapons(); 
    }    
    
    public boolean defend(float receivedAttack){
        return manageHit(receivedAttack); 
    }
    
    public void receivedReward(){
        throw new UnsupportedOperationException();
    }
    
    @Override
    public String toString(){
        return name + "[I:" + intelligence + ", S:" + strength + ", H" + health + ", Pos:" + row + "," + col + ", Hits:" + consecutiveHits + ", W:" + weapons.size() + ", S:" + shields.size() + "]"; 
    }
    
    private void receivedWeapon(Weapon w){
        throw new UnsupportedOperationException();
    }
    
    private void receivedShield(Shield s){
        throw new UnsupportedOperationException();
    }
    
    //REVISAR
    private Weapon newWeapon(){
        Weapon w = new Weapon(Dice.weaponPower(), Dice.usesLeft()); 
        //weapons.add(w);
    }
    
    //REVISAR
    private Shield newShield(){
        Shield s = new Shield(Dice.shieldPower(), Dice.usesLeft()); 
        //shields.add(s); 
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
        throw new UnsupportedOperationException();
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
