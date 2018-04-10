package characters.character_2;

/**
 * Created by Stark on 2017/12/18.
 */
public interface Defaulable {
    //默认方法
    default String notRequired() {
        return "default method";
    }
}
