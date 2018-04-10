package characters;

/**
 * Created by Stark on 2017/12/14.
 * 新的整数字面表达式
 * -"0b" 二进制前缀
 * -"_" 连接符
 */
public class Character_3 {
    public static void main(String[] args) {
        //下面的值相同
        byte b1 = 0b00100001; //二进制表示
        byte b2 = 040;//八进制表示
        byte b3 = 0x21;//16进制
        byte b4 = 32; //十进制

        /**
         * 用下划线链接整数提高其可读性
         * 约束：
         *      不能在数的开头和结尾
         *      不能在浮点型数组的小数点左右
         *      不能在F或L下标的前面
         *      字符串数字不行
         */
        //float f1 = 3._14f;
        float f2 = 3.1_4f;
        long l = 11_11_11_11_11_11L;
        //int a1 =_52;
        //int a2 =52_;
        int a3 = 5__________2;
        int a4 = 0________40;
    }
}
