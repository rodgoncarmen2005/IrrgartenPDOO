
package irrgarten;

public class TestP1 {
    
    public static void main (String args[]){
        
        //Creamos varias instancias de cada clase
        //WEAPON
        Weapon w1 = new Weapon(10, 5);
        Weapon w2 = new Weapon(7.5f, 7);
        Weapon w3= new Weapon(5.75f, 3);
        
        System.out.println("Se han creado 4 armas. Probando 4 ataques en cada una...");
        for(int i= 0; i < 4; ++i){
            w1.attack();
            w1.toString();
            
            w2.attack();
            w2.toString();
            
            w3.attack();
            w3.toString(); 
        }
        
    }
}
