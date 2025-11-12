
package irrgarten.UI;

import irrgarten.Directions;
import irrgarten.GameState;
import java.util.Scanner;


public class TextUI {
    
    private static Scanner in = new Scanner(System.in);
    
    private char readChar() {
        String s = in.nextLine();     
        return s.charAt(0);
    }
    

    public Directions nextMove() {
        System.out.print("Where? ");
        
        Directions direction = Directions.DOWN;
        boolean gotInput = false;
        
        while (!gotInput) { //Sigue leyendo hasta que reciba una entrada correcta
            char c = readChar();
            switch(c) {
                case 'w':
                    System.out.print(" UP\n");
                    direction = Directions.UP;
                    gotInput = true;
                    break;
                case 's':
                    System.out.print(" DOWN\n");
                    direction = Directions.DOWN;
                    gotInput = true;
                    break;
                case 'd':
                    System.out.print("RIGHT\n");
                    direction = Directions.RIGHT;
                    gotInput = true;
                    break;
                case 'a':
                    System.out.print(" LEFT\n");
                    direction = Directions.LEFT;
                    gotInput = true;    
                    break;
            }
        }    
        return direction;
    }
    
    public void showGame(GameState gameState) {   
        
        //Secuencia de cadenas por consola con los atributos de GameState
        System.out.print(gameState.getPlayers() + "\n");
        System.out.print(gameState.getMonsters() + "\n");
        System.out.print(gameState.getLabyrinth() + "\n");
        System.out.print(gameState.getLog() + "\n");

        if (gameState.getWinner()) {
            System.out.print("Winner: Player " + gameState.getCurrentPlayer() + "\n");
        }else{
            System.out.print("--------------------------------------- \n"); //Separador de turnos
            System.out.print("Current player: " + gameState.getCurrentPlayer() + "\n");
        }
    }  
}