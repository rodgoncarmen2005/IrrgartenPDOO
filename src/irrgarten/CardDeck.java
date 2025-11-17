/*Una clase paramétrica en Java, también conocida como clase genérica,
es una plantilla que define una clase con uno o más parámetros de tipo. 
Estos parámetros, representados por letras como T o E, se reemplazan por tipos de clase 
concretos (como String o Integer) al crear una instancia de la clase, permitiendo que 
la clase maneje diferentes tipos de datos de manera segura y reutilizable. */
package irrgarten;

import java.util.Collections;
import java.util.ArrayList;



abstract class CardDeck <T extends CombatElement> {
    
    private ArrayList<T> cardDeck;
    
    protected static final int TAM = 20;
    
    public CardDeck(){
        cardDeck = new ArrayList<>();
    }
    
    protected void addCard(T card){
        this.cardDeck.add(card);
    }
    
    protected abstract void addCards();
    
    public T newCard(){
        if(cardDeck.size()== 0){
            this.addCards();
            Collections.shuffle(cardDeck);//This method randomly permutes elements 
                                          //randomly in a list.Using the pre-defined 
                                          //source of randomness
        }
        
        T output = cardDeck.get(0);
        cardDeck.remove(0);
        return output;
    }
}
