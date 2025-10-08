
package irrgarten; 

public class Irrgarten {
    
    static void pruebaWeapon (float power, int uses) {
        Weapon w = new Weapon (power, uses);
        System.out.println(w.toString());
        for (int i = 0; i < 4; i++) {
            System.out.println("-Disparo " + i);
            System.out.println("Disparo con potencia " + w.attack());
            System.out.println(w.toString());
            System.out.println("¿Descartamos el arma? --> " + w.discard());
        }      
    }
    
    static void pruebaShield (float protection, int uses) {
        Shield s = new Shield (protection, uses);
        System.out.println(s.toString());
        for (int i = 0; i < 4; i++) {
            System.out.println("-Uso de escudo numero " + i);
            System.out.println("Usamos escudo con protección " + s.protect());
            System.out.println(s.toString());
            System.out.println("¿Descartamos el escudo? --> " + s.discard());
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
    
    public static void pruebaDice(int max, int nplayers, float competence){
         System.out.println("Posición aleatoria: " + Dice.randomPos(max));
         System.out.println("Empieza el jugador: " + Dice.whoStarts(nplayers));
         System.out.println("Nivel inteligencia: " + Dice.randomIntelligence());
         System.out.println("Nivel de fuerza: " + Dice.randomStrength());
         System.out.println("¿El jugador resucita? " + Dice.resurrectPlayer());
         System.out.println("Armas ganadas: " + Dice.weaponsReward());
         System.out.println("Escudos ganados: " + Dice.shieldsReward());
         System.out.println("Salud ganada: " + Dice.healthReward());
         System.out.println("Poder del arma: " + Dice.weaponPower());
         System.out.println("Poder del escudo: " + Dice.shieldPower());
         System.out.println("Usos restantes: " + Dice.usesLeft());
         System.out.println("Intensidad: " + Dice.intensity(competence));    
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Comenzando la prueba de la Práctica 1 de PDOO");
        
        //PRUEBA DE WEAPON
        System.out.println("Creando tres armas...");
        System.out.println("Arma 1:");
        pruebaWeapon(10f, 5); 
        System.out.println("Arma 2:");
        pruebaWeapon(7.5f, 7);
        System.out.println("Arma 3:");
        pruebaWeapon(5.75f, 3);
        System.out.println("------------");
        
        
        //PRUEBA DE SHIELD
        System.out.println("Creando tres escudos...");
        System.out.println("Escudo 1:");
        pruebaShield(10f, 5); 
        System.out.println("Escudo 2:");
        pruebaShield(7.5f, 7);
        System.out.println("Escudo 3");
        pruebaShield(5.75f, 3);
        System.out.println("------------");
        
        //PRUEBA DE GAMESTATE
        System.out.println("Visualizando el estado del juego...");
        pruebaGameState();
        System.out.println("------------");
        
        //PRUEBA DE DICE
        System.out.println("Probando el dado...");
        for(int i = 0; i < 2; ++i){
            System.out.println("---Prueba Dado " + i);
            pruebaDice(10, 5, 5); 
        }
         System.out.println("------------");
        
    }
}