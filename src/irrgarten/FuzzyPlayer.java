package irrgarten;

public class FuzzyPlayer extends Player{
    
    /**
     * Constructor de la clase FuzzyPlayer. 
     * @param other 
     */
    public FuzzyPlayer(Player other){
        
    }
    
    /**
     * 
     * @param directiion
     * @param validMoves
     * @return 
     */
    public Directions move(Directions directiion, Directions[] validMoves){
        
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public float attack(){
        return sumWeapons() + Dice.intensity(strength); //Getter de strength??
    }
    
    /**
     * 
     * @return 
     */
    //@Override???
    protected float defensiveEnergy(){
        return sumShields() + Dice.intensity(intelligence); //Getter de intelligence??
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString(){
        String s = "Fuzzy "; 
        s += super.toString();//????
        return s; 
    }
}
