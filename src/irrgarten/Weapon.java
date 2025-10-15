
package irrgarten;

public class Weapon {
    private float power;
    private int uses;
    
    // This is a constructor for the `Weapon` class in Java. It takes two parameters, `power` of type float
    // and `uses` of type int, and assigns the values of these parameters to the corresponding instance
    // variables `power` and `uses` of the `Weapon` object being created using the `this` keyword. This
    // constructor is used to initialize a new `Weapon` object with the specified power and number of uses.
    public Weapon (float power, int uses) {
        this.power = power; 
        this.uses = uses;
    }
    
    /**
     * @return The `attack` method returns a float value, which is either the `power` value if `uses` is
     * greater than 0, or 0.0 if `uses` is 0.
     */
    public float attack (){
        float salida = 0.0f;
        
        if(uses > 0){
            salida = power;
            uses--;
        }
        
        return salida;
    }
    
    /**
     * @return The `discard()` method is returning the result of calling the `discardElement()` method from
     * the `Dice` class with the `uses` parameter. The `discardElement()` method likely removes an element
     * from a collection and returns a boolean indicating whether the operation was successful.
     */
    public boolean discard(){
        return Dice.discardElement(uses);
    }
    
        
    /**
     * The `toString` function overrides the default behavior to return a custom string representation of
     * an object with the format "W[power, uses]".
     * 
     * @return The `toString` method is returning a string representation of an object of class `W`, which
     * includes the values of the `power` and `uses` variables enclosed in square brackets and separated by
     * a comma.
     */
    @Override
    public String toString () {
        return "W[" + power + "," + uses + "]";
    }
    
}
