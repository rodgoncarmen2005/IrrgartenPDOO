
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
        
        this.intelligence = intelligence; 
        this.strength = strength; 
        health = INITIAL_HEALTH; 
        //row = 
        //col = 
    }
    
    public void resurrect(){
        
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
        
    }
    
    public Directions move(Directions direction, ArrayList<Directions> validMoves){//ARRAYLIST O ARRAY NORMAL[]
        throw new UnsupportedOperationException();
    }
    
    public float attack(){
        
    }    
    
    public boolean defend(float receivedAttack){
        
    }
    
    public void receivedReward(){
        throw new UnsupportedOperationException();
    }
    
    public String toString(){
        
    }
    
    private void receivedWeapon(Weapon w){
        throw new UnsupportedOperationException();
    }
    
    private Weapon newWeapon(){
        
    }
    
    private Shield newShield(){
        throw new UnsupportedOperationException();
    }
    
    private float sumWeapons(){
        
    }
    
    private float sumShields(){
        
    }
    
    private float defensiveEnergy(){
        
    }
    
    private boolean manageHit(float recivedAttack){
        throw new UnsupportedOperationException();
    }
    
    private void resetHits(){
        
    }
    
    private void gotWounded(){
        
    }
    
    private void incConsecutiveHits(){
        
    }
    
}
