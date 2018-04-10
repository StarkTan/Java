package characters;

/**
 * Created by Stark on 2017/12/14.
 * 能在单个catch代码块中捕获多个异常
 * 重新抛出异常的类型检测
 */
public class Character_4 {

    public static void main(String[] args) {
        int i = 1;
        try {
            try {
                if (i == 1) throw new FirstException();
                else throw new SecondException();
            } catch (Exception e) {
                //抛出后类型可以被捕捉
                throw e;
            }
        } catch (SecondException | FirstException e) {
            //不行 e是final的
            //e = new SecondException();
            e.printStackTrace();
        }
    }
}

class FirstException extends Exception {
}

class SecondException extends Exception {
}

