
package irrgarten;

import java.util.ArrayList;

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
    static void pruebaMonster (String name, float intelligence,float strength){
        Monster m = new Monster (name, intelligence, strength);
        System.out.println(m.toString());
        System.out.println("¿Muerto? --> " + m.dead());
        System.out.println("Ataque --> " + m.attack());
        m.setPos(0,0);
        System.out.println(m.toString());
    }
    
    static void pruebaPlayer(char number, float intelligence, float strength){
        Player p = new Player(number, intelligence, strength);
        System.out.println("-Posicion elegida: 3,3"); 
        p.setPos(3, 3);
        System.out.println("-¿El jugador está muerto?");
        System.out.println(p.dead());
        System.out.println("-Informacion completa del jugador:");        
        p.toString(); 
    }
    
    static void pruebaGame(int nplayers){
        Game g = new Game(nplayers);
        System.out.println("-¿Hay un ganador?"); 
        System.out.println(g.finished());
        System.out.println("-El estado del juego es:");
        System.out.println("---Jugador actual:");
        System.out.println(g.getGameState().getCurrentPlayer());
        System.out.println("---Laberinto:");
        System.out.println(g.getGameState().getLabyrinth());
        System.out.println("---El registro es:");
        System.out.println(g.getGameState().getLog());
        System.out.println("---Los monstruos son:");
        System.out.println(g.getGameState().getMonsters());
        System.out.println("---Los jugadores son:");
        System.out.println(g.getGameState().getPlayers());
        System.out.println("---El ganador es:");
        System.out.println(g.getGameState().getWinner());
    }
    
    static void pruebaLaberinth(){
        Labyrinth l = new Labyrinth(10,10,9,9);
        System.out.println("Laberinto inicial:");
        System.out.println(l.toString());
        System.out.println("Añadiendo bloques...");
        l.addBlock(Orientation.HORIZONTAL, 0, 5, 5);
        l.addBlock(Orientation.VERTICAL, 2, 2, 6);
        System.out.println(l.toString());
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player('1',5.0f,7.0f));
        players.add(new Player('2',6.0f,6.5f));
        System.out.println("Añadiendo jugadores...");
        l.spreadPlayers(players);
        System.out.println(l.toString());
        System.out.println("Añadiendo un monstruo...");
        Monster m = new Monster("Ogro",5.0f,6.0f);
        l.addMonster(5,5,m);
        System.out.println(l.toString());
        System.out.println("Moviendo al jugador 1 hacia abajo...");
        l.putPlayer(Directions.DOWN, players.get(0));
        System.out.println(l.toString());
        System.out.println("Calculando movimientos validos para el jugador 1...");
        Player player = players.get(0);
        int row = player.getRow();
        int col = player.getCol();
        ArrayList<Directions> array = l.validMoves(row, col);
        System.out.println("Movimientos validos para el jugador 1 en la posicion (" + row + "," + col + "): " + array);
        l.putPlayer(Directions.RIGHT, player);
        System.out.println(l.toString());   
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
        
        //PRUEBA MONSTRUO
        System.out.println("Probando un mosntruo...");
        pruebaMonster("m1", 5.0f , 6.5f); 
        System.out.println("------------");
        
        //PRUEBA PLAYER
        System.out.println("Probando un jugador...");
        pruebaPlayer('2', 5.5f, 7.32f); 
        System.out.println("------------");
        
        //PRUEBA GAME
        System.out.println("Probando un juego...");
        pruebaGame(5);      
        System.out.println("------------");

        //PRUEBA LABERINTO
        System.out.println("Probando un laberinto...");
        pruebaLaberinth();
        System.out.println("------------");
    }
}