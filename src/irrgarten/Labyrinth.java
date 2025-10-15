
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
        throw new UnsupportedOperationException();
    }
    public boolean haveAWinner(){
        return players[this.exitRow][this.exitCol] != null;
    }
    
    @Override
    public String toString(){
        
        String salida = "";
        int nRows = labyrinth.length;
        int nCols = labyrinth[0].length;
        
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
        throw new UnsupportedOperationException();
    }
    
    public void addBlock(Orientation orientation, int startRow,int startCol, int length){
        throw new UnsupportedOperationException();
    }
    
    public ArrayList<Directions> validMoves(int row,int col){
        throw new UnsupportedOperationException();
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
        return posOK(row, col) && (emptyPos(row,col) || monsterPos(row,col) || combatPos(row,col));
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
        throw new UnsupportedOperationException();
    }
    
    
}
