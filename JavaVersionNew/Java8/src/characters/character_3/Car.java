package characters.character_3;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by Stark on 2017/12/18.
 */
public class Car {
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    public static void main(String[] args) {
        //引用构造函数
        Car car = Car.create(Car::new);
        List<Car> cars = Arrays.asList(car);

        //对静态方法的引用
        cars.forEach(Car::collide);

        //对特定类的方法进行引用
        cars.forEach(Car::repair);
        Car other = Car.create(Car::new);
        //特定对象方法的引用
        cars.forEach(other::follow);
    }
}
