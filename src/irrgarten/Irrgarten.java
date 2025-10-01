
package irrgarten; 

public class Irrgarten {
    
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
    
    public static void pruebaGameState() {
        String laberinto = "Laberinto prueba";
        String jugadores = "j1, j2";
        String monstruos = "araña, ogro";
        int numJugadores = 2;
        boolean ganador = false;
        String historia = "nada";

        GameState gs = new GameState(laberinto, jugadores, monstruos, numJugadores, ganador, historia);

        System.out.println("Labyrinth: " + gs.getLabyrinth());
        System.out.println("Players: " + gs.getPlayers());
        System.out.println("Monsters: " + gs.getMonsters());
        System.out.println("Current Player: " + gs.getCurrentPlayer());
        System.out.println("Winner: " + gs.getWinner());
        System.out.println("Log: " + gs.getLog());
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        //System.out.println("Archivo para hacer pruebas");
        //pruebaWeapon ();
        pruebaShield();
        pruebaWeapon();
        pruebaGameState();
    }
}
