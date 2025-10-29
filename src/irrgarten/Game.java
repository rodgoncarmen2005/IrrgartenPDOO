package irrgarten;

import java.util.ArrayList; //https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html


public class Game {
    
    private static final int MAX_ROUNDS = 10; //Máximo de rondas en el juego
    
    private int currentPlayerIndex; //El juegador que posee el turno
    
    private String log; //????
    
    private Player currentPlayer; //??? Relacion con Player
    
    private ArrayList<Player> players; //?? Relacion con Player
    
    private ArrayList<Monster> monsters; //?? Relacion con Monster
    
    private Labyrinth labyrinth; //?? Relacion con Labyrinth
    
    private static final int ROWS = 10; 
    
    private static final int COLUMNS = 10; 
    
    private static final int NUM_MONSTERS = 2; 
    
    private static final int NUM_BLOCKS = 2; 
    
    
    public Game (int nplayers){
        
        players = new ArrayList<>(); 
        monsters = new ArrayList<>(); 
        
        for (int i = 0; i < nplayers; ++i){
            Player p = new Player(Character.forDigit(i, 10), Dice.randomIntelligence(), Dice.randomStrength());
            players.add(p); 
        }
        
        currentPlayerIndex = Dice.whoStarts(nplayers);
     
        currentPlayer = players.get(currentPlayerIndex);
        
        log = "The game begins \n"; 
        
        labyrinth = new Labyrinth(ROWS, COLUMNS, Dice.randomPos(ROWS), Dice.randomPos(COLUMNS));
        configureLabyrinth(); 
        labyrinth.spreadPlayers(players);   
    }
    
    public boolean finished(){
        return labyrinth.haveAWinner(); 
    }
    
    public boolean nextStep(Directions peferredDirection){
        //Reseteamos log
        log = "";
        
        boolean dead = currentPlayer.dead();
        
        if (!dead){
            Directions direction = actualDirection(peferredDirection);
            
            if (direction != peferredDirection){
                logPlayerNoOrders();
            }
            
            Monster monster = labyrinth.putPlayer(direction, currentPlayer);
            
            if (monster == null){
                logNoMonster();
            }
            else{
                GameCharacter winner = combat(monster);
                manageReward(winner);
            }
        }
        else manageResurrection();
        
        boolean endGame = finished();
        
        if(!endGame) nextPlayer();
        
        return endGame;
    }
    
    public GameState getGameState(){
        GameState game = new GameState(labyrinth.toString(), players.toString(), monsters.toString(), currentPlayerIndex, finished(), log); 
        return game; 
    }
    
    //REVISAR
    private void configureLabyrinth(){
        for(int i = 0; i < NUM_MONSTERS; i++){
            Monster monster = new Monster("Monster"+i, Dice.randomIntelligence(), Dice.randomStrength()); 
            monsters.add(monster); 
            labyrinth.addMonster(Dice.randomPos(ROWS), Dice.randomPos(COLUMNS), monster); 
        }
        
        //Configuramos las paredes del laberinto
        /*labyrinth.addBlock(Orientation.HORIZONTAL, 0, 0, COLUMNS);
        labyrinth.addBlock(Orientation.HORIZONTAL, ROWS-1, 0, COLUMNS);
        labyrinth.addBlock(Orientation.VERTICAL, 0, 0, ROWS);
        labyrinth.addBlock(Orientation.VERTICAL, 0, COLUMNS-1, ROWS);*/
        
        //PREFIJADO
        /*for(int i = 0; i < NUM_BLOCKS; i++){
            labyrinth.addBlock(Dice.randomOrientation(), Dice.randomPos(ROWS), Dice.randomPos(COLUMNS), 2);//REVISAR EL LARGO
        }*/
        labyrinth.addBlock(Orientation.HORIZONTAL, 5, 4, 2); //EJEMPLO, PONER MAS
    }
    
    private void nextPlayer(){
        if (currentPlayerIndex == players.size()-1){
            currentPlayerIndex = 0; 
            currentPlayer = players.get(0); 
        }
        else{
            currentPlayerIndex++;
            currentPlayer = players.get(currentPlayerIndex); 
        }   
    }
    
    private Directions actualDirection(Directions preferredDirection){
        
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();
        
        ArrayList<Directions> validMoves = labyrinth.validMoves(currentRow, currentCol);
        
        return currentPlayer.move(preferredDirection, validMoves);         
    }
    
    private GameCharacter combat(Monster monster){
        
        int rounds = 0; 
        GameCharacter winner = GameCharacter.PLAYER;
        
        float playerAttack = currentPlayer.attack();
        boolean lose = monster.defend(playerAttack);
        
        while((!lose) && (rounds < MAX_ROUNDS)){
            winner = GameCharacter.MONSTER;
            rounds++;
            
            float monsterAttack = monster.attack();
            lose = currentPlayer.defend(monsterAttack);
            
            if(!lose){
                playerAttack = currentPlayer.attack();
                winner = GameCharacter.PLAYER;
                lose = monster.defend(playerAttack);
            }
        }
        
        logRounds(rounds, MAX_ROUNDS);
        
        return winner;
    }
    
    private void manageReward(GameCharacter winner){
        if(winner == GameCharacter.PLAYER){
            currentPlayer.receivedReward();
            logPlayerWon();
        }
        else logMonsterWon();
    }
    
    private void manageResurrection(){
        boolean resurrect = Dice.resurrectPlayer();
        
        if (resurrect){
            currentPlayer.resurrect();
            logResurrected();
        }
        else logPlayerSkipTurn();
    }
    
    private void logPlayerWon(){
          log.concat("Winner: player " + currentPlayerIndex + "\n"); 
    }
    
    private void logMonsterWon(){
           log.concat("Winner: monster" + "\n");
    }
    
    private void logResurrected(){
            log.concat("Resurrected: player " + currentPlayerIndex+ "\n");
    }
    
    private void logPlayerSkipTurn(){
            log.concat("Player " + currentPlayerIndex + "skipped turn due to death" + "\n"); 
    }
    
    private void logPlayerNoOrders(){
            log.concat("The instruction for player" + currentPlayerIndex + "could not be followed." + "\n"); 
    }
    
    private void logNoMonster(){
            log.concat("Player " + currentPlayerIndex + "moved to an empty square or it was not possible to move.");
    }
    
    private void logRounds(int rounds, int max){
            log.concat("Round " + rounds + "out of " + max + "\n"); 
    }
    
    
    
}
