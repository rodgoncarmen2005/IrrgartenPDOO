package irrgarten;

import java.util.ArrayList; //https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
import java.lang.UnsupportedOperationException;


public class Game {
    
    private static final int MAX_ROUNDS = 10; //Máximo de rondas en el juego
    
    private int currentPlayerIndex; //El juegador que posee el turno
    
    private String log; //????
    
    private Player currentPlayer; //??? Relacion con Player
    
    private ArrayList<Player> players; //?? Relacion con Player
    
    private ArrayList<Monster> monster; //?? Relacion con Monster
    
    private Labyrinth labyrinth; //?? Relacion con Labyrinth
    
    //La relaciones de dependencia debiles no generan atributos verdad???
    
    
    public Game (int nplayers){
        Player p = new Player; 
        for (int i = 0; i < nplayer; ++i){
            players.add(p); 
        }
        
        currentPlayerIndex = Dice.whoStarts(nplayers);
        
        //monster
        
        labyrinth = new Labyrinth(); //Que tam???
        
    }
    
    public boolean finished(){
        return labyrinth.haveAWinner(); 
    }
    
    public boolean nextStep(Directions peferredDirection){
        throw new UnsupportedOperationException(); //https://docs.oracle.com/javase/8/docs/api/java/lang/UnsupportedOperationException.html
    }
    
    public GameState getGameState(){
        
    }
    
    private void configureLabyrinth(){
        
    }
    
    private void nextPlayer(){
        currentPlayerIndex = /*siguiente jugador*/;
    }
    
    private Directions actualDirection(Directions preferredDirection){
        throw new UnsupportedOperationException();
    }
    
    private GameCharacter combat(Monster monster){
        throw new UnsupportedOperationException();
    }
    
    private void manageReward(GameCharacter winner){
        throw new UnsupportedOperationException();
    }
    
    private void manageResurrection(){
        throw new UnsupportedOperationException();
    }
    
    private void logPlayerWon(){
          log.concat("Winner: " + /*ganador*/); 
    }
    
    private void logMonsterWon(){
        
    }
    
    private void logResurrected(){

    }
    
    private void logPlayerSkipTurn(){
        
    }
    
    private void logPlayerNoOrders(){
        
    }
    
    private void logNoMonster(){
        
    }
    
    private void logRounds(int rounds, int max){
        
    }
    
    
    
}
