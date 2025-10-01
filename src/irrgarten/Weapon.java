
package irrgarten;

public class Weapon {
    private float power;
    private int uses;
    
    public Weapon (float power, int uses) {
        this.power = power; 
        this.uses = uses;
    }
    
    public float attack (){
        float salida = 0.0f;
        
        if(uses > 0){
            salida = power;
            uses--;
        }
        
        return salida;
    }
    
    public boolean discard(){
        salida=discardElement(uses);
        return salida;
    }
    
    
    @Override
    public String toString () {
        return "W[" + power + "," + uses + "]";
    }
    
}
