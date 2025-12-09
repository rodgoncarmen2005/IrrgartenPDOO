package irrgarten;

//NO SE INSTANCIA, NUNCA NEW COMBATELEMENT
//SI TIENE CONSTRUCTOR PORQUE HAY QUE INICIALIZAR ATRIBUTOS
//SI SE PUEDE USAR COMO TIPO DE DATO. 
    //P.E ARRAYLIST<COMBATELEMENT> LISTA = NEW ARRAYLIST<>(); 
    // LISTA.ADD(NEW WEAPON(...))

abstract class LabyrinthCharacter { 
    //COMPROBADOOO
    private String name; //(nombre del personaje)
    
    private float intelligence; //(inteligencia del personaje)
    
    private float strength; //(fuerza del personaje)
    
    private float health; //(salud del personaje)
    
    private int row; //(posicion del personaje en el laberinto: fila)
    
    private int col; //(posicion del personaje en el laberinto: columna)
    
    /**
     * Constructor para el objeto LabyrinthCharacter. Se inicializa con una posicion (-1,-1) en el tablero.
     * @param name nombre del personaje
     * @param intelligence inteligencia del personaje
     * @param strength fuerza del personaje
     * @param health salud del personaje
     */
    public LabyrinthCharacter(String name , float intelligence, float strength, float health){
        this.name = name; 
        this.intelligence = intelligence; 
        this.strength = strength; 
        this.health = health; 
        
    //Posicion invalida en el tablero
        row = -1; 
        col= -1; 
    }
    
    /**
     * Constructor de copia para el objeto LabyrinthCharacter.
     * @param other objeto LabyrinthCharacter a copiar.
     */
    public LabyrinthCharacter(LabyrinthCharacter other){
        //Copia profunda
        this.name = other.name; 
        this.intelligence = other.getIntelligence(); 
        this.strength = other.getStrength(); 
        this.health = other.getHealth(); 
        //Posicion en el tablero
        row = other.getRow(); 
        col= other.getCol();
    }
    
    /**
     * Indica si el jugador esta muerto, es decir, si no posee nada de salud.
     * @return true si el jugador esta muerto.
     */
    public boolean dead(){
        return health <= 0; 
    }
    
    /**
     * Getter de la fila en la que se encuentra posicionado el personaje.
     * @return fila de la posición del personaje.
     */
    public int getRow(){
        return row; 
    }
    
    /**
     * Getter de la columna en la que se encuentra posicionado el personaje.
     * @return columna de la posicion del personaje.
     */
    public int getCol(){
        return col; 
    }
    
    /**
     * Getter de la inteligencia del personaje.
     * @return inteligencia del personaje.
     */
    protected float getIntelligence(){
        return intelligence; 
    }
    
    /**
     * Getter de la fuerza del personaje.
     * @return fuerza del personaje.
     */
    protected float getStrength(){
        return strength; 
    }
    
    /**
     * Getter de la salud del personaje.
     * @return salud del personaje.
     */
    protected float getHealth(){
        return health; 
    }
    
    /**
     * Setter de la salud del personaje.
     * @param health cantidad de salud a asignar
     */
    protected void setHealth(float health){
        this.health = health;
    }
    
    /**
     * Setter de la posición del jugador en el laberinto.
     * @param row fila dentro de la posicion.
     * @param col columna dentro de la posicion.
     */
    public void setPos(int row, int col){
        this.row = row; 
        this.col = col; 
    }
    
    /**
     * Representacion del estado completo del personaje en una cadena.
     * @return cadena con el estado del personaje.
     */
    @Override
    public String toString(){
        String s = name + "[I:" + intelligence + ", S:" + strength + ", H" + health + ", Pos:" + row + "," + col + "\n";
        return s;
    }
    
    /**
     * Decrementa la salud en una unidad por herida.
     */
    protected void gotWounded(){
        health--; 
    }
    
    public abstract float attack();  //ABSTRACTA
    
    public abstract boolean defend(float attack);  //ABSTRACTA

}
