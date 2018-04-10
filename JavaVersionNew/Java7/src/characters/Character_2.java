package characters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Stark on 2017/12/14.
 * 泛型实例化自动推断
 */
public class Character_2 {
    public static void main(String[] args) {
        new ArrayList<>(); //默认类型是Object
        new HashMap<>();
        Set<String> set = new HashSet<>();
    }
}
