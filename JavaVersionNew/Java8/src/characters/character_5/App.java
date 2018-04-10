package characters.character_5;

/**
 * Created by Stark on 2017/12/18.
 */
public class App {
    public static void main(String[] args) {
        final Value<String> value = new Value<>();
        String orDefault = value.getOrDefault("22", Value.defaultValue());
        System.out.println(orDefault);
    }
}
