
package irrgarten;


public class Monster {
    private static final int INITIAL_HEALTH = 5;
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    

    // This part of the code is the constructor of the `Monster` class in Java. When a new `Monster` object
    // is created, this constructor is called with the parameters `name`, `intelligence`, and `strength`.
    public Monster(String name, float intelligence,float strength){
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = INITIAL_HEALTH;
        this.row = -1;
        this.col = -1;
    }

    /**
     * @return The method `dead()` returns a boolean value indicating whether the health is less than or
     * equal to 0.
     */
    public boolean dead(){
        return (health <= 0);
    }

    /**
     * @return The `attack` method is returning the result of the `Dice.intensity` method called with the
     * `strength` parameter. The `Dice.intensity` method calculates the intensity of an attack based
     * on the strength parameter provided.
     */
    public float attack(){
        return Dice.intensity(strength);
    }   


    public boolean defend(float receivedAttack){
        throw new UnsupportedOperationException();
    }    


    /**
     * The function `setPos` sets the row and column values of an object.
     * 
     * @param row The `row` parameter in the `setPos` method is used to specify the row position where you
     * want to set the object.
     * @param col The `col` parameter in the `setPos` method represents the column position where you want
     * to set the object.
     */
    public void setPos (int row, int col){
        this.row = row;
        this.col = col;
    }

    /**
     * The `gotWounded` function decreases the `health` variable by one.
     */
    private void gotWounded(){
        health--;
    }    
    
    /**
     * @return A string representation of a "Monstruo" object is being returned. The string includes the
     * monster's name, intelligence level, strength level, health status, and position (row, col).
     */
    @Override
    public String toString(){
        return "Monstruo[Nombre: " + name+ ", Inteligencia; " + intelligence +", Fuerza: " + strength + " Salud: " + health + " Pos:(" + row + "," + col + ")]";
    }
}
