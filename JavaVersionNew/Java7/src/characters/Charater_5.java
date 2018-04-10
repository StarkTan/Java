package characters;

import java.io.Closeable;

/**
 * Created by Stark on 2017/12/14.
 * try-with-resources 语句
 * 对于实现了AutoCloseable和Closeable接口的资源对象，如果在try语句中被声明，在try block 结束时被关闭
 */
public class Charater_5 {
    public static void main(String[] args) throws InterruptedException {
        try (ReSourceOne one = new ReSourceOne();
             ReSourceTwo two = new ReSourceTwo()) {
            System.out.println(one);
            System.out.println(two);
            System.out.println("结束");
        } catch (Exception e) {

        }
        Thread.sleep(1000);
        //只会关闭two
        try (ReSourceTwo two = new ReSourceTwo(new ReSourceOne())) {
            System.out.println(two);
            System.out.println("结束");
        } catch (Exception e) {
        }
    }
}

class ReSourceOne implements AutoCloseable {

    @Override
    public String toString() {
        return "its one";
    }

    @Override
    public void close() throws Exception {
        System.out.println(" one被关闭");
    }
}

class ReSourceTwo implements Closeable {

    ReSourceTwo() {

    }

    ReSourceTwo(ReSourceOne one) {

    }

    @Override
    public String toString() {
        return "its two";
    }

    @Override
    public void close() {
        System.out.println(" two被关闭");
    }
}

