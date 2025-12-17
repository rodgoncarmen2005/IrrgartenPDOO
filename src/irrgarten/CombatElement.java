package irrgarten;

abstract class CombatElement {
    private float effect;
    private int uses;
    
    
    // The constructor `public CombatElement(float effect, int uses)` is initializing the `effect` and
    // `uses` instance variables of the `CombatElement` class with the values passed as arguments to the
    // constructor. This allows an object of the `CombatElement` class to be created with specific initial
    // values for its effect and uses properties.
    
    //Recordar: aunque es un constructor, no podemos instanciar la clase por ser abstracta
    public CombatElement(float effect, int uses){ 
        this.effect = effect;
        this.uses = uses;
    }

    /**
     * This function decrements the "uses" variable and returns the "effect" value if "uses" is
     * greater than 0.
     * 
     * @return a float value which is the effect of the combat element if there are uses left.
     */
    protected float produceEffect(){

        float output = 0.0f;
        if(uses > 0){
            uses--;
            output = effect;
        }
        return output;
    }
    
    /**
     * The `discard` function discards an element from a list using a helper method from the `Dice`
     * class.
     * 
     * @return The `discard()` method is returning the result of calling the `discardElement()` method from
     * the `Dice` class with the `uses` parameter.
     */
    public boolean discard(){
        return Dice.discardElement(uses);
    }
    
    /**
     * The `to_string` function returns a string representation of a Combat Element object with its effect
     * and uses.
     * 
     * @return A string representation of a Combat Element object, including its effect and uses.
     */
    @Override
    public String toString(){
        return "[Effect: " + effect + ", Uses: " + uses + "]";
    }
}
