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
    
    //Recordar: aunque es un constructor, no podemos instanciar la clase
    //por se abstracta
    public CardDeck(){
        cardDeck = new ArrayList<>();
    }
    
    /**
     * The `addCard` method adds a card to a card deck.
     * 
     * @param card The `addCard` method takes a parameter `card` of type `T`, which represents the card to
     * be added to the `cardDeck`.
     */
    protected void addCard(T card){
        this.cardDeck.add(card);
    }
    
    /**
     * The function "addCards" is declared as abstract and is intended to be implemented by Shield and Weapon.
     */
    protected abstract void addCards();
    
    /**
     * The `newCard` function returns and removes the first card from a deck, shuffling the deck if it is
     * empty.
     * 
     * @return The `newCard` method returns an object of type `T`. The method first checks if the `cardDeck` list is empty,
     * and if so, it adds cards to the deck and shuffles them using `Collections.shuffle`. Then, it
     * retrieves and removes the first card from the deck and returns it.
     */
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
