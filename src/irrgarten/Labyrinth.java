
package irrgarten;

import java.util.ArrayList;

public class Labyrinth {
    private static final char BLOCK_CHAR = 'X';
    private static final char EMPTY_CHAR = '-';
    private static final char MONSTER_CHAR = 'M';
    private static final char COMBAT_CHAR = 'C';
    private static final char EXIT_CHAR = 'E';
    private static final int ROW = 0;
    private static final int COL = 1;
    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;
    private Monster [][] monsters;
    private Player [][] players;
    private char [][] labyrinth;
    
    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol){
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
        
        this.monsters   = new Monster[nRows][nCols];
        this.labyrinth  = new char   [nRows][nCols];
        this.players    = new Player [nRows][nCols];
        
        for(int i = 0; i<this.nRows;i++){
            for (int j = 0; j<this.nCols;j++){
                this.labyrinth[i][j] = EMPTY_CHAR;
                this.players[i][j] = null;
                this.monsters[i][j] = null;
            }
        }
        
        labyrinth[this.exitRow][this.exitCol] = EXIT_CHAR;
        
    }
    public void spreadPlayers(ArrayList<Player> players){
        for (int i = 0; i < players.size(); i++){
            int [] pos = this.randomEmptyPos();
            this.putPlayer2D(-1, -1, pos[ROW], pos[COL], players.get(i));
        }
    }
    public boolean haveAWinner(){
        return players[this.exitRow][this.exitCol] != null;
    }
    
    @Override
    public String toString(){
        
        String salida = "";
        
        for (int fila = 0; fila < nRows; fila++) {
            for (int col = 0; col < nCols; col++) {
                salida += labyrinth[fila][col] + " ";
                if(this.players[fila][col] != null){
                    salida+=this.players[fila][col];
                }
            }
            salida += "\n";
        }

        return salida;
    }
    
    public void addMonster(int row,int col, Monster monster){
        if (posOK(row,col) && emptyPos(row,col)){
            this.labyrinth[row][col] = MONSTER_CHAR;
            this.monsters[row][col] = monster;
            monster.setPos(row, col);
            
        }
    }
    
    public Monster putPlayer(Directions direction, Player player){
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        int [] newPos = this.dir2Pos(oldRow, oldCol,direction);
        Monster monster = putPlayer2D(oldRow, oldCol, newPos[ROW], newPos[COL], player);
        return monster;
    }
    
    public void addBlock(Orientation orientation, int startRow,int startCol, int length){
        int incCol = 0, incRow = 0;
        if (orientation == Orientation.VERTICAL){
            incRow++;
        }
        if (orientation == Orientation.HORIZONTAL){
            incCol++;
        }
        
        int row = startRow;
        int col = startCol;
        
        while (posOK(row, col) && emptyPos(row, col) && length>0){
            this.labyrinth[row][col]=BLOCK_CHAR;
            row+=incRow;
            col+=incCol;
            length--;
        }
    }
    
    public ArrayList<Directions> validMoves(int row,int col){
        ArrayList<Directions> output;
        output = new ArrayList<>(); 
        
        if(canStepOn(row+1,col)){
            output.add(Directions.DOWN);
        }
        if(canStepOn(row-1,col)){
            output.add(Directions.UP);
        }
        if(canStepOn(row,col+1)){
            output.add(Directions.RIGHT);
        }
        if(canStepOn(row,col-1)){
            output.add(Directions.LEFT);
        }
        
        return output; 
    }
    
    private boolean posOK(int row, int col){
        return (0<=row && row<=nRows && 0<=col && col<=nCols);
    }

    private boolean emptyPos(int row, int col){
        return (this.labyrinth[row][col]==EMPTY_CHAR);
    }
    
    private boolean monsterPos(int row, int col){
        return (this.labyrinth[row][col]==MONSTER_CHAR);
    }
    
    private boolean exitPos(int row, int col){
        return (this.labyrinth[row][col]==EXIT_CHAR);
    }
    
    private boolean combatPos(int row, int col){
        return (this.labyrinth[row][col]==COMBAT_CHAR);
    }
    
    public boolean canStepOn(int row, int col){
        return posOK(row, col) && (emptyPos(row,col) || monsterPos(row,col) || exitPos(row,col));
    }
    
     private void updateOldPos(int row, int col){
        if (this.posOK(row, col)){
            if(combatPos(row, col)){
                this.labyrinth[row][col]=MONSTER_CHAR;
            }
            else{
                this.labyrinth[row][col]=EMPTY_CHAR;
            }
        }
    }   
    
    private int[] dir2Pos(int row, int col, Directions direction){
        int[] salida= new int[2];
        
        switch(direction){
            case LEFT:
                col--;
                break;
            case RIGHT:
                col++;
                break;
            case UP:
                row--;
                break;
            case DOWN:
                row++;
                break;
        }
        
        salida[ROW]=row;
        salida[COL]=col;
        
        return salida;
    } 
    private int[] randomEmptyPos(){
        int row = -1 , col = -1;
        int[] salida= new int[2];
        row=Dice.randomPos(this.nRows);
        col=Dice.randomPos(this.nCols);
        while (!this.emptyPos(row, col)){
            row=Dice.randomPos(this.nRows);
            col=Dice.randomPos(this.nCols);
        }
        salida[ROW]=row;
        salida[COL]=col;
        
        return salida;
    }
    
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player){
        Monster output = null;
        if(canStepOn(row, col)){
            if(canStepOn(oldRow,oldCol)){
                Player p = players[oldRow][oldCol];
                if(p == player){
                    this.updateOldPos(oldRow, oldCol);
                    players[oldRow][oldCol] = null;
                }
            }
        }
        
        
        boolean monsterPos = this.monsterPos(row, col);
        if(monsterPos){
            labyrinth[row][col] = COMBAT_CHAR;
            output = monsters[row][col];
        }else{
            char number = Character.forDigit(player.getNumber(), 10);
            labyrinth[row][col] = number;
        }
        
        players[row][col] = player;
        player.setPos(row, col);
        
        return output;
    }
    

}
