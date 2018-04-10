package characters.character_2;

/**
 * Created by Stark on 2017/12/18.
 */
public class DefaulableImp implements Defaulable {

    @Override
    public String notRequired() {
        return "default implement";
    }

    public static void main(String[] args) {
        String s = new DefaulableImp().notRequired();
        System.out.println(s);
    }
}
