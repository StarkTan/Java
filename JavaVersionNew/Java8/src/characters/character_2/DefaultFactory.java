package characters.character_2;

import java.util.function.Supplier;

/**
 * Created by Stark on 2017/12/18.
 */
public interface DefaultFactory {

    //接口现在也支持静态方法
    static Defaulable create(Supplier<Defaulable> supplier) {
        return supplier.get();
    }
}
