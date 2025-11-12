package irrgarten;

import java.util.ArrayList; //https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html


public class Game {
    
    private static final int MAX_ROUNDS = 10; //(Máximo de rondas en el juego)
    
    private int currentPlayerIndex; //(Indice del jugador que posee el turno)
    
    private String log; //
    
    private Player currentPlayer; //Relacion con Player. (Jugador actual)
    
    private ArrayList<Player> players; //Relacion con Player. (Array de jugadores)
    
    private ArrayList<Monster> monsters; //Relacion con Monster. (Array de monstruos)
    
    private Labyrinth labyrinth; //Relacion con Labyrinth. (Laberinto del juego)
    
    private static final int ROWS = 10; //(Filas del laberinto)
    
    private static final int COLUMNS = 10; //(Columnas del laberinto)
    
    private static final int NUM_MONSTERS = 4; //(Numero de monstruos del juego)
    
    private static final int NUM_BLOCKS = 2; //(Numero de obstaculos del juego)
    
    /**
     * Constructor de la clase Player. Se crean los jugadores y se reparten, se crea y configura
     * el laberinto y se decide quien empieza. 
     * @param nplayers numero de jugadores de la partida.
     */
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
        log = "Empieza el juego";
    }
    
    /**
     * Indica si hay un ganador mediante el metodo de la clase Labyrinth.
     * @return true si hay un ganador y se acaba el juego.
     */
    public boolean finished(){
        return labyrinth.haveAWinner(); 
    }
    
    /**
     * Gestiona los turnos de la partida: comprueba si el jugador esta muerto, 
     * mueve el jugador en el laberinto si es posible, comprueba si hay un combate,
     * pregunta si se ha llegado al final de la partida y pasa de turno. 
     * 
     * @param peferredDirection direccion a la que se quiere mover el jugador.
     * @return true si finaliza el juego.
     */
    public boolean nextStep(Directions peferredDirection){
        //Reseteamos log
        log = "";
        
        boolean dead = currentPlayer.dead();
        
        if (!dead){
            Directions direction = actualDirection(peferredDirection);
            
            if (direction != peferredDirection){
                logPlayerNoOrders();
            }
            
            Monster monster = this.labyrinth.putPlayer(direction, currentPlayer);
            
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
    
    /**
     * Genera una instancia de GameState integrando toda la información del estado del juego.
     * @return objeto GameState con el estado actual del juego.
     */
    public GameState getGameState(){
       
        String players="";
        String monsters="";
        
        for (int i=0; i<this.players.size(); i++){
            players+="+ " + this.players.get(i).toString()+"\n";
        }
        for (int i=0; i<this.monsters.size(); i++){
            monsters+="+ " + this.monsters.get(i).toString()+"\n";
        }
        
        GameState game = new GameState (this.labyrinth.toString(), players,
                                monsters, this.currentPlayerIndex, this.finished(), this.log);
        
        return game;
    }

    /**
     * Configura el laberinto añadiendo bloques de obstáculos y monstruos.
     * Los monstruos se guardan en el contenedor del juego.
     */
    private void configureLabyrinth(){
        for(int i = 0; i < NUM_MONSTERS; i++){
            Monster monster = new Monster("Monster"+i, Dice.randomIntelligence(), Dice.randomStrength()); 
            monsters.add(monster); 
            labyrinth.addMonster(Dice.randomPos(ROWS), Dice.randomPos(COLUMNS), monster); 
        }
        
        labyrinth.addBlock(Orientation.HORIZONTAL, 5, 4, 2);
        labyrinth.addBlock(Orientation.VERTICAL, 9, 8, 1);
    }
    
    /**
     * Actualiza los valores de currentPlayerIndex y currentPlayer con el turno pasando 
     * al siguiente jugador.
     */
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
    
    /**
     * Mueve al jugador a preferredDirection si es posible. Se movera a otra casilla
     * si preferredDirection no es valida.
     * @param preferredDirection direccion a la que se quiere mover el jugador.
     * @return direccion a la que se mueve el jugador
     */
    private Directions actualDirection(Directions preferredDirection){
        
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();
        
        ArrayList<Directions> validMoves = labyrinth.validMoves(currentRow, currentCol);
        
        return currentPlayer.move(preferredDirection, validMoves);         
    }
    
    /**
     * Gestiona los combates. Se producen ataques y defensas por rondas con un max de 
     * MAX_ROUNDS. 
     * @param monster el monstruo que interviene en el combate.
     * @return ganador del combate.
     */
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
    
    /**
     * Gestiona las recompensas de ganar un combate. Actualiza el log con el 
     * ganador del combate (monstruo o jugador).
     * @param winner ganador del combate (monstruo o jugador).
     */
    private void manageReward(GameCharacter winner){
        if(winner == GameCharacter.PLAYER){
            currentPlayer.receivedReward();
            logPlayerWon();
        }
        else logMonsterWon();
    }
    
    /**
     * Maneja la resurrecion del jugador. Se decide con los resultados del dado.
     * Se aniade a log si el jugador resucita o no. Si no resucita, logPlayerSkipTurn().
     */
    private void manageResurrection(){
        boolean resurrect = Dice.resurrectPlayer();
        
        if (resurrect){
            currentPlayer.resurrect();
            logResurrected();
        }
        else logPlayerSkipTurn();
    }
    
    /**
     * Se añade a log el jugador ganador del combate.
     */
    private void logPlayerWon(){
          log += "Winner: player " + currentPlayerIndex + "\n"; 
    }
    
    /**
     * Se añade a log el monstruo ganador del combate.
     */
    private void logMonsterWon(){
           log += "Winner: monster" + "\n";
    }
    
    /**
     * Se añade a log que el jugador actual ha resucitado.
     */
    private void logResurrected(){
            log += "Resurrected: player " + currentPlayerIndex+ "\n";
    }
    
    /**
     * Se añade a log que el jugador a perdido su turno debido a que estaba muerto.
     */
    private void logPlayerSkipTurn(){
            log += "Player " + currentPlayerIndex + " skipped turn due to death" + "\n"; 
    }
    
    /**
     * Se añade a log que el jugador ha intentado un accion no permitida.
     */
    private void logPlayerNoOrders(){
            log += "The instruction for player" + currentPlayerIndex + " could not be followed." + "\n"; 
    }
    
    /**
     * Se añade a log que el jugador se ha movido a una casilla vacia o no ha sido posible moverse.
     */
    private void logNoMonster(){
            log += "Player " + currentPlayerIndex + " moved to an empty square or it was not possible to move.";
    }
    
    /**
     * Se añade a log que se han producido rounds/max rondas de combate.
     * @param rounds numero de rondas que se han producido.
     * @param max max numero de rondas permitidas en un combate.
     */
    private void logRounds(int rounds, int max){
            log += "Round " + rounds + " out of " + max + "\n"; 
    }
    
}
