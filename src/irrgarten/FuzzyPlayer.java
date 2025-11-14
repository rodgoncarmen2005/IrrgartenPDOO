package irrgarten;

public class FuzzyPlayer extends Player{
    
    public FuzzyPlayer(Player other){
        
    }
    
    public Directions move(Directions directiion, Directions[] validMoves){
        
    }
    
    @Override
    public float attack(){
        return sumWeapons() + Dice.intensity(strength); //Getter de strength??
    }
    
    //@Override???
    protected float defensiveEnergy(){
        return sumShields() + Dice.intensity(intelligence); //Getter de intelligence??
    }
    
    @Override
    public String toString(){
        String s = "Fuzzy "; 
        s += super.toString();//????
        return s; 
    }
}
