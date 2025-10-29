
package irrgarten;

public class main {

    public static void main(String[] args){
            Game game = new Game(2);
            TextUI view = new TextUI();
            Controller controller = new Controller(game, view);
            controller.play();
    }
}
    

