package irrgarten;

abstract class CombatElement {
    private float effect;
    private int uses;
    
    public CombatElement(float effect, int uses){
        this.effect = effect;
        this.uses = uses;
    }

    protected float produceEffect(){

        float salida = 0.0f;
        if(uses > 0){
            uses--;
            salida = effect;
        }
        return salida;
    }
    
    public boolean discard(){
        return Dice.discardElement(uses);
    }
    
    public String to_string(){
        return "Combat Element[" + effect + "," + uses + "]";
    }
}
