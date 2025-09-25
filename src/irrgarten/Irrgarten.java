
package irrgarten; 

public class Irrgarten {
    
    public enum Directions{
        LEFT, RIGHT,UP,DOWN
    }
    
    public enum Orientation{
        VERTICAL, HORIZONTAL
    }
    
    public enum GameCharacter{
        PLAYER,MONSTER
    }
    
    static void pruebaWeapon () {
        Weapon w = new Weapon (2.0f, 3);
        System.out.println(w.toString());
        for (int i = 0; i < 4; i++) {
            System.out.println("Disparo con potencia " + w.attack());
            System.out.println(w.toString());
        }
        
    }
    
    static void pruebaShield () {
        Shield s = new Shield (2.0f, 3);
        System.out.println(s.toString());
        for (int i = 0; i < 4; i++) {
            System.out.println("Usamos escudo con protección " + s.protect());
            System.out.println(s.toString());
        }
        
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        //System.out.println("Archivo para hacer pruebas");
        //pruebaWeapon ();
        pruebaShield ();
    }
}
