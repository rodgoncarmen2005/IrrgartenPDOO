
package irrgarten;

public class ShieldCardDeck extends CardDeck<Shield> {
    
    /**
     * The `addCards` method adds Shield cards with random power and uses left to a deck.
     */
    @Override
    protected void addCards(){
        for (int i = 0; i < ShieldCardDeck.TAM ; i++){
            addCard(new Shield(Dice.shieldPower(), Dice.usesLeft()));
        }
    }
}