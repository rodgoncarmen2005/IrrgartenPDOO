
package irrgarten;


public class Monster extends LabyrinthCharacter {
    private static final int INITIAL_HEALTH = 5;
    //private String name;
    //private float intelligence;
    //private float strength;
    //private float health;
    //private int row;
    //private int col;
    

    // This part of the code is the constructor of the `Monster` class in Java. When a new `Monster` object
    // is created, this constructor is called with the parameters `name`, `intelligence`, and `strength`.
    public Monster(String name, float intelligence,float strength){
        super(name,intelligence, strength, INITIAL_HEALTH);
    }

    /**
     * @return The `attack` method is returning the result of the `Dice.intensity` method called with the
     * `strength` parameter. The `Dice.intensity` method calculates the intensity of an attack based
     * on the strength parameter provided.
     */
    @Override
    public float attack(){
        return Dice.intensity(getStrength());
    }   


    /**
     * The defend function checks if the character is dead, calculates defense based on intelligence, and
     * updates the character's status accordingly.
     * 
     * @param receivedAttack The `receivedAttack` parameter represents the amount of damage the entity
     * has received from an attack. The method calculates the defense based on the entity's intelligence
     * level and determines if the entity is dead
     * @return The method `defend` returns a boolean value indicating whether the character is dead after
     * defending against a received attack.
     */
    @Override
    public boolean defend(float receivedAttack){
        boolean isDead;
        if(this.dead()){
            return true;
        }else{
            float defensa = Dice.intensity(getIntelligence());
            if (defensa < receivedAttack){
                this.gotWounded();
            }
            isDead = this.dead();
        }
        return isDead;
    }    
    
}
