package characters.character_1;

import sun.util.resources.LocaleData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stark on 2017/12/16.
 */
public class Person {
    public enum Sex {
        MALE, FEMALE
    }

    String name;
    LocaleData birthday;
    Sex gender;
    String emailAddress;

    public int getAge() {
        return 25;
    }

    public void printPerson() {

    }

    public static void printPersonsOlderThan(List<Person> roster, int age) {
        for (Person p : roster) {
            if (p.getAge() >= age) {
                p.printPerson();
            }
        }
    }

    public static void printPersonsWithinAgeRange(
            List<Person> roster, int low, int high) {
        for (Person p : roster) {
            if (low <= p.getAge() && p.getAge() < high) {
                p.printPerson();
            }
        }
    }

    public static void printPersons(
            List<Person> roster, CheckPerson tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    public static void printPersonsWithPredicate(
            List<Person> roster, Predicate<Person> tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    public static void main(String[] args) {
        printPersons(new ArrayList<Person>(), p -> p.gender == Sex.FEMALE && p.getAge() >= 18 && p.getAge() <= 25);
        printPersonsWithPredicate(new ArrayList<Person>(), p -> p.gender == Sex.FEMALE && p.getAge() >= 18 && p.getAge() <= 25);

    }
}
