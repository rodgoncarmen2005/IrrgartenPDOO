
package irrgarten;

public class WeaponCardDeck extends CardDeck<Weapon> {
        
    /**
     * The function adds a specified number of Weapon cards with random power and uses left to a card deck.
     */
    @Override
    protected void addCards(){
        for (int i = 0; i < WeaponCardDeck.TAM ; i++){
            addCard(new Weapon(Dice.weaponPower(), Dice.usesLeft()));
        }
    }
}
