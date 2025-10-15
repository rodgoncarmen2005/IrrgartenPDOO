
package irrgarten;


public class Monster {
    private static final int INITIAL_HEALTH = 5;
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    public Monster(String name, float intelligence,float strength){
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = this.INITIAL_HEALTH;
        this.row = -1;
        this.col = -1;
    }
    public boolean dead(){
        return (health <= 0);
    }
    public float attack(){
        Dice d = new Dice();
        return d.intensity(strength);
    }   
    public boolean defend(float receivedAttack){
        throw new UnsupportedOperationException();
    }    
    public void setPos (int row, int col){
        this.row = row;
        this.col = col;
    }

    private void gotWounded(){
        health--;
    }    
    
    @Override
    public String toString(){
        return "Monstruo[Nombre: " + name+ ", Inteligencia; " + intelligence +", Fuerza: " + strength + " Salud: " + health + " Pos:(" + row + "," + col + ")]";
    }
}
