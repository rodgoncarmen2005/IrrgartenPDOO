
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
    

    // This constructor method `public Labyrinth(int nRows, int nCols, int exitRow, int exitCol)` is
    // initializing a new instance of the `Labyrinth` class with the specified number of rows, columns, and
    // exit position.
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


    /**
     * The `spreadPlayers` function assigns random positions to each player in the given list.
     * 
     * @param players The `spreadPlayers` method takes an ArrayList of Player objects as a parameter. The
     * method iterates through the list of players and assigns each player a random position on a 2D grid
     * using the `putPlayer2D` method.
     */
    public void spreadPlayers(ArrayList<Player> players){
        for (int i = 0; i < players.size(); i++){
            int [] pos = this.randomEmptyPos();
            this.putPlayer2D(-1, -1, pos[ROW], pos[COL], players.get(i));
        }
    }

    /**
     * The function `haveAWinner` checks if a player has reached the exit position in a game.
     * 
     * @return The method `haveAWinner()` is returning a boolean value, which indicates whether there is a
     * player at the position specified by `exitRow` and `exitCol`. It returns `true` if there is a player
     * at that position, and `false` otherwise.
     */
    public boolean haveAWinner(){
        return players[this.exitRow][this.exitCol] != null;
    }
    
    /**
     * The `toString` method generates a string representation of a labyrinth with players displayed at
     * their respective positions.
     * 
     * @return The `toString` method is returning a string representation of the labyrinth with players
     * represented by their numbers or 'J' character. Each cell in the labyrinth is separated by a space
     * and each row is separated by a new line character.
     */
    @Override
    public String toString(){
        
        String salida = "";
        
        for (int fila = 0; fila < nRows; fila++) {
            for (int col = 0; col < nCols; col++) {
                char c = labyrinth[fila][col];
                // Si hay jugador en esta posición, lo mostramos
                if ((players[fila][col] != null)&&(c != COMBAT_CHAR)) {
                    c = players[fila][col].getNumber(); // o 'J' + número si quieres
                }

                salida += c + " ";
            }
            salida += "\n";
        }
        return salida;
    }
    
    /**
     * The `addMonster` function adds a monster to the labyrinth at a specified position if the position is
     * valid and empty.
     * 
     * @param row The `row` parameter specifies the row in the labyrinth grid where the monster will be
     * added.
     * @param col The `col` parameter in the `addMonster` method represents the column index where the
     * monster is to be added in the labyrinth grid. It is used to specify the horizontal position within
     * the grid where the monster will be placed.
     * @param monster The `monster` parameter in the `addMonster` method represents an instance of the
     * `Monster` class that you want to add to the labyrinth at the specified row and column coordinates.
     * This method checks if the position is valid (`posOK(row, col)`) and if the position is empty (`
     */
    public void addMonster(int row,int col, Monster monster){
        if (posOK(row,col) && emptyPos(row,col)){
            this.labyrinth[row][col] = MONSTER_CHAR;
            this.monsters[row][col] = monster;
            monster.setPos(row, col);
            
        }
    }
        
    /**
     * The `putPlayer` function moves a player in a specified direction and returns a Monster object.
     * 
     * @param direction The `direction` parameter in the `putPlayer` method is used to indicate the
     * direction in which the player is moving. It is of type `Directions`, which likely represents
     * different directions such as UP, DOWN, LEFT, and RIGHT. This parameter helps determine the new
     * position of the player based on
     * @param player The `player` parameter in the `putPlayer` method represents the player object that you
     * want to move. It contains information about the player's current position on the game board, such as
     * the row and column coordinates.
     * @return A Monster object is being returned.
     */
    public Monster putPlayer(Directions direction, Player player){
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        int [] newPos = this.dir2Pos(oldRow, oldCol,direction);
        Monster monster = putPlayer2D(oldRow, oldCol, newPos[ROW], newPos[COL], player);
        return monster;
    }
        
    /**
     * The `addBlock` function places a block in the labyrinth starting from a specified position and
     * extending in a specified direction for a given length.
     * 
     * @param orientation The `orientation` parameter specifies whether the block should be added
     * vertically or horizontally. If `orientation` is `Orientation.VERTICAL`, the block will be added
     * vertically, and if it is `Orientation.HORIZONTAL`, the block will be added horizontally.
     * @param startRow The `startRow` parameter in the `addBlock` method represents the starting row index
     * where the block will be added in the labyrinth grid. The block will be placed starting from this row
     * and extending either vertically or horizontally based on the orientation specified.
     * @param startCol The `startCol` parameter in the `addBlock` method represents the starting column
     * index where the block will be added in the labyrinth grid. This is the column index from where the
     * block will start extending either horizontally or vertically based on the orientation specified.
     * @param length The `length` parameter in the `addBlock` method represents the number of blocks to add
     * in the specified orientation starting from the given row and column. It determines how many
     * consecutive blocks will be added to the labyrinth in the specified direction (either horizontally or
     * vertically).
     */
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
    
    /**
     * The function `validMoves` returns a list of valid directions that can be taken from a given position
     * on a grid.
     * 
     * @param row The `row` parameter represents the current row position in a grid or matrix. The method
     * `validMoves` is checking for valid moves from the current position specified by the `row` and `col`
     * parameters. It checks if it can move down, up, right, or left from the current
     * @param col It seems like you were about to provide some information about the `col` parameter, but
     * the message got cut off. Could you please provide more details or let me know if you need assistance
     * with something specific related to the `col` parameter?
     * @return The `validMoves` method returns an ArrayList of Directions, which contains the valid
     * directions that can be taken from the specified row and column position.
     */
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
    
    /**
     * The function `posOK` checks if a given row and column are within the valid range of the grid
     * dimensions.
     * 
     * @param row The `row` parameter represents the row index in a 2D grid or matrix. It is used to check
     * if the row index is within the valid range of rows in the grid.
     * @param col The `col` parameter represents the column index in a grid or matrix. The `posOK` method
     * is checking if the given row and column indices are within the valid range of the grid, which is
     * defined by the number of rows (`nRows`) and number of columns (`nCols`).
     * @return The method `posOK` is returning a boolean value, which is determined by whether the
     * conditions `(0<=row && row<nRows && 0<=col && col<nCols)` are met.
     */
    private boolean posOK(int row, int col){
        return (0<=row && row<nRows && 0<=col && col<nCols);
    }

    /**
     * The function `emptyPos` checks if a specific position in a labyrinth is empty.
     * 
     * @param row The `row` parameter represents the row index in a 2D array where you want to check for an
     * empty position.
     * @param col The `col` parameter represents the column index in a 2D array. It is used to specify the
     * column position within the array where you want to check for a specific condition, such as whether
     * the position is empty in this case.
     * @return The method `emptyPos(int row, int col)` returns a boolean value indicating whether the
     * position in the labyrinth at the specified row and column is empty or not.
     */
    private boolean emptyPos(int row, int col){
        return (this.labyrinth[row][col]==EMPTY_CHAR);
    }
    
    /**
     * The function `monsterPos` checks if a monster is present at the specified row and column in the
     * labyrinth.
     * 
     * @param row The `row` parameter represents the row index in the labyrinth grid where you want to
     * check for the presence of a monster.
     * @param col The `col` parameter in the `monsterPos` method likely represents the column index in a 2D
     * array (such as a grid or a matrix) where the monster's position is being checked.
     * @return The method `monsterPos` is returning a boolean value that indicates whether the character at
     * the specified position in the `labyrinth` array is equal to the `MONSTER_CHAR` constant.
     */
    private boolean monsterPos(int row, int col){
        return (this.labyrinth[row][col]==MONSTER_CHAR);
    }
    
    /**
     * The function `exitPos` checks if a given position in a labyrinth contains the exit character.
     * 
     * @param row The `row` parameter represents the row index of a position in the labyrinth grid.
     * @param col The `col` parameter represents the column index in a 2D array. In this context, it is
     * used to specify the column index in the `labyrinth` array where you want to check for the exit
     * character.
     * @return The method `exitPos` is returning a boolean value that indicates whether the character at
     * the specified position in the `labyrinth` array is equal to the `EXIT_CHAR` constant.
     */
    private boolean exitPos(int row, int col){
        return (this.labyrinth[row][col]==EXIT_CHAR);
    }
    
    /**
     * The function `combatPos` checks if a specific position in a labyrinth contains a combat character.
     * 
     * @param row The `row` parameter in the `combatPos` method likely represents the row index in a
     * two-dimensional array (such as a grid or a matrix) where the `COMBAT_CHAR` is being checked for.
     * @param col The `col` parameter in the `combatPos` method likely represents the column index in a 2D
     * array (such as a grid or a matrix) where the character `COMBAT_CHAR` is being checked for combat.
     * @return The method `combatPos` is returning a boolean value that indicates whether the element at
     * the specified row and column in the `labyrinth` array is equal to the `COMBAT_CHAR` constant.
     */
    private boolean combatPos(int row, int col){
        return (this.labyrinth[row][col]==COMBAT_CHAR);
    }
    
    /**
     * The function `canStepOn` checks if a player can step on a specific position on the game board based
     * on certain conditions.
     * 
     * @param row The `row` parameter represents the row position in a grid or matrix where you want to
     * check if something can step on it. The `canStepOn` method is checking if it's possible to step on
     * the specified row and column position based on certain conditions like if it's a valid position,
     * @param col The `col` parameter represents the column index in a grid or matrix where you want to
     * check if it is a valid position to step on. The `canStepOn` method is checking if a given position
     * at the specified row and column is valid for stepping on.
     * @return The method `canStepOn` returns a boolean value, which indicates whether a player can step on
     * a specific position on the game board.
     */
    public boolean canStepOn(int row, int col){
        boolean puede = this.posOK(row,col);
        puede = puede && (this.monsterPos(row, col) || this.exitPos(row, col) || this.emptyPos(row, col));
        return puede;
    }
    
    /**
     * The function `updateOldPos` updates the position in a labyrinth grid with a monster character if
     * combat occurs at that position, or with an empty character otherwise.
     * 
     * @param row The `row` parameter in the `updateOldPos` method represents the row index of the position
     * in the labyrinth grid that needs to be updated.
     * @param col The `col` parameter in the `updateOldPos` method represents the column index in a 2D
     * array (labyrinth) where you want to update the position. It is used to specify the column where the
     * update operation should take place.
     */
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
    
    /**
     * The function `dir2Pos` takes a row, column, and direction as input, adjusts the row and column based
     * on the direction, and returns the updated row and column in an integer array.
     * 
     * @param row The `row` parameter in the `dir2Pos` method represents the current row position in a grid
     * or matrix where you want to move based on the specified direction.
     * @param col The `col` parameter represents the current column position in a grid. The `dir2Pos`
     * method takes this column position, along with a row position and a direction, and calculates the new
     * position based on the direction provided.
     * @param direction The `direction` parameter in the `dir2Pos` method is of type `Directions`, which is
     * an enum type representing different directions - LEFT, RIGHT, UP, and DOWN.
     * @return An array of integers containing the updated row and column positions based on the given
     * direction.
     */
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
    /**
     * The `randomEmptyPos` function generates random empty positions within a grid.
     * 
     * @return An array of integers containing the row and column of a random empty position is being
     * returned.
     */
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
    
    /**
     * The function `putPlayer2D` updates the position of a player on a 2D game board, handling player
     * movement, combat with monsters, and updating the game state accordingly.
     * 
     * @param oldRow The `oldRow` parameter in the `putPlayer2D` method represents the previous row
     * position of the player before moving to a new position.
     * @param oldCol The `oldCol` parameter in the `putPlayer2D` method represents the previous column
     * position of the player before moving to a new position in a 2D grid or matrix. It is used to track
     * the player's movement within the grid.
     * @param row The `row` parameter in the `putPlayer2D` method represents the row coordinate where the
     * player is being moved to on a 2D grid or matrix.
     * @param col The `col` parameter in the `putPlayer2D` method represents the column index where the
     * player is being moved to within a 2D grid or matrix. It is used to determine the new position of the
     * player within the game world.
     * @param player The `player` parameter in the `putPlayer2D` method represents the player object that
     * you want to place on the game board at the specified row and column coordinates. This method is
     * responsible for updating the player's position on the board while handling interactions with
     * monsters or combat scenarios.
     * @return The method `putPlayer2D` returns a `Monster` object.
     */
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player){
        Monster output = null;
        if(canStepOn(row, col)){
            if(posOK(oldRow,oldCol)){
                Player p = players[oldRow][oldCol];
                if(p == player){
                    this.updateOldPos(oldRow, oldCol);
                    players[oldRow][oldCol] = null;
                }
            }
        
        boolean monsterPos = this.monsterPos(row, col);
        if(monsterPos){
            labyrinth[row][col] = COMBAT_CHAR;
            output = monsters[row][col];
        }else{
            char number = player.getNumber();
            labyrinth[row][col] = number;
        }
        
        players[row][col] = player;
        player.setPos(row, col);
        }
        return output;
    }
    
    public void convertToFuzzy(FuzzyPlayer other){
        int row=other.getRow();
        int col=other.getCol();
        if(this.players[row][col].getNumber() == other.getNumber())
            this.players[row][col]=other;
    }
    
}
