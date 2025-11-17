
package irrgarten;

public class ShieldCardDeck extends CardDeck<Shield> {
    
    @Override
    protected void addCards(){
        for (int i = 0; i < ShieldCardDeck.TAM ; i++){
            addCard(new Shield(Dice.shieldPower(), Dice.usesLeft()));
        }
    }
}