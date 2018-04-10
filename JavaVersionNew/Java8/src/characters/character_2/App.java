package characters.character_2;

/**
 * Created by Stark on 2017/12/18.
 */
public class App {
    public static void main(String[] args) {
        Defaulable defaulable = DefaultFactory.create(DefaulableImp::new);
        System.out.println(defaulable.notRequired());
    }
}
