
package irrgarten;

public class WeaponCardDeck extends CardDeck<Weapon> {
    
    @Override
    protected void addCards(){
        for (int i = 0; i < WeaponCardDeck.TAM ; i++){
            addCard(new Weapon(Dice.weaponPower(), Dice.usesLeft()));
        }
    }
}
