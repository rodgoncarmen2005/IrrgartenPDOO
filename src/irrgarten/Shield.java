
package irrgarten;

public class Shield extends CombatElement{
    private float protection; //HAY QUE BORRARLO?
    private int uses;
    

    // This is a constructor for the `Shield` class in Java. It takes two parameters, `protection` of type
    // `float` and `uses` of type `int`, and initializes the `protection` and `uses` instance variables of
    // the `Shield` object with the values passed as arguments to the constructor.
    public Shield (float protection, int uses) {
        super(protection, uses);
    }
    
    /**
     * @return The method `protect` is returning a float value. The value being returned is either the
     * `protection` value if `uses` is greater than 0, or the protection if `uses` is not greater than 0.
     * Additionally, the `uses` variable is decremented by 1 after the `protection` value is assigned to
     * `salida`.
     */    
    public float protect (){
        float salida = 0.0f; //HAY QUE DEJAR ESTA IMPLEMENTACION??
        
        if(uses > 0){
            salida = protection;
            uses--;
        }
        
        return salida;
    }
    
    /**

     * @return The `discard()` method is returning a boolean value, which is the result of calling the
     * `discardElement()` method from the `Dice` class with the `uses` parameter.
     */
    public boolean discard(){
        return Dice.discardElement(uses);//HAY QUE DEJAR ESTA IMPLEMENTACION??
    }
        

    /**
     * @return The `toString` method is returning a string representation of an object of type `S`, which
     * includes the values of the `protection` and `uses` variables enclosed in square brackets.
     */
    @Override
    public String toString () {
        return "S[" + protection + "," + uses + "]";
    }    
}
