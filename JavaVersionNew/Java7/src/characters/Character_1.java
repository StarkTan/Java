package characters;

/**
 * Created by Stark on 2017/12/14.
 * 第一个特性 switch 1.7 switc 支持字符串变量
 * 在1.7 之前只能当数据类型能转换成int时可以使用
 * 优点：生成更高效的字节码
 * 注意：字符串大小写敏感
 */
public class Character_1 {

    public static void main(String[] args) {

        String str = "Xxxx";
        switch (str) {
            case "X":
                System.out.println(1);
                break;
            case "XX":
                System.out.println(2);
                break;
            case "XXX":
                System.out.println(3);
                break;
            case "XXXX":
                System.out.println(4);
                break;
            default:
                System.out.println(Integer.MAX_VALUE);
                break;
        }
    }
}
