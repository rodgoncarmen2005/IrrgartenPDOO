
package irrgarten;

public class Shield {
    private float protection;
    private int uses;
    
    public Shield (float protection, int uses) {
        this.protection = protection; 
        this.uses = uses;
    }
    
    public float protect (){
        float salida = 0.0f;
        
        if(uses > 0){
            salida = protection;
            uses--;
        }
        
        return salida;
    }
    
    @Override
    public String toString () {
        return "S[" + protection + "," + uses + "]";
    }    
}
