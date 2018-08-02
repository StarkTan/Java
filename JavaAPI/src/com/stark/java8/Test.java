package com.stark.java8;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Test {
    public static void main(String[] args) {
/*        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println(nums.stream().map(n -> n * n).collect(toList()));

        List<Integer> nums1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> nums2 = Arrays.asList(3, 4);
//        List<int[]> res = nums1.stream().flatMap(i -> nums2.stream().map(j -> new int[]{i, j}))
//                .filter(item->(item[0]+item[1])%3==0).collect(toList());
        List<int[]> res = nums1.stream().flatMap(i -> nums2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j}))
                .collect(toList());
        res.forEach(item -> {
            System.out.println(item[0] + " , " + item[1]);
        });
        Dish.getDishs().stream().map(Dish::getCaloaries).reduce(Integer::min)
                .ifPresent(System.out::println);*/
        test_5_7();
    }

    private static void test_5_5() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        System.out.println("第一题");
        System.out.println(transactions.stream().filter(t -> t.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue)).collect(toList()));
        System.out.println("第二题");
        System.out.println(transactions.stream().map(t -> t.getTrader().getCity()).distinct().collect(toList()));
        System.out.println("第三题");
        System.out.println(transactions.stream().map(Transaction::getTrader).distinct()
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(toList()));
        System.out.println("第四题");
        System.out.println(transactions.stream().map(Transaction::getTrader).distinct().map(Trader::getName)
                .sorted().collect(toList()));
        System.out.println("第五题");
        transactions.stream().map(Transaction::getTrader).distinct().filter(t -> t.getCity().equals("Milan"))
                .findAny().ifPresent(System.out::println);
        System.out.println("第六题");
        int cambridge = transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).reduce(0, (a, b) -> a + b);
        System.out.println(cambridge);
        System.out.println("第七题");
        int max = transactions.stream().map(Transaction::getValue).reduce(Integer::max).get();
        System.out.println(max);
        System.out.println("第八题");
        System.out.println(transactions.stream().min(Comparator.comparing(Transaction::getValue)).get());

    }

    private static void test_5_6() {
        IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})).forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
        IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100)
                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)}))
                .filter(t -> t[2] % 1 == 0).forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }

    private static void test_5_7() {
        //迭代
        Stream.iterate(0, n -> n + 2).limit(5).forEach(System.out::println);
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).map(t -> t[0])
                .limit(20).forEach(System.out::println);
        //生成
        Stream.generate(Math::random).limit(20).forEach(System.out::println);
    }
}
