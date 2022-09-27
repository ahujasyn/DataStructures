package com.java.java8.examples;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * So here we will take basic list and will display and sort it
 */
public class ListToStream {

    public static void main(String[] args) {
        List<String> listTSort = new ArrayList<>();

        listTSort.add("nilesh");
        listTSort.add("ahuja");
        listTSort.add("vipin");
        listTSort.add("mahesh");
        listTSort.add("angush");
        listTSort.add("aangush");

        // display All the members of the list
        listTSort.stream().forEach(System.out::println);

        // display all the elements  start with "a"
        System.out.println("All Elements starting with a ::: -----");
        listTSort.stream().filter(s->s.startsWith("a")).forEach(System.out::println);

        // display all the elements in sorted order starts with a
        System.out.println("All Elements starting with a sorted asc ::: -----");
        listTSort.stream().filter(s->s.startsWith("a")).sorted().forEach(System.out::println);

        // display the list in descending order

        System.out.println("Display the list in desc order -------------- ");
        // (a,b)->b.compareTo(a)
        // this will do the sorting thing for you.
        listTSort.stream().
                sorted(Comparator.reverseOrder()).forEach(System.out::println);

        //uppercase starting with a
        System.out.println("Upper case with words starting with a  -------------- ");
        // map(String::toUpperCase) used for upper case
        List<String> uppercase = listTSort.stream().filter(s->s.startsWith("a")).map(String::toUpperCase).collect(Collectors.toList());
        // for getting into list

        System.out.println("---"+uppercase);

        //count the number of recrords starting with a
        long coount = listTSort.stream().filter(s->s.startsWith("a")).count();
        System.out.println("Count ---"+coount);
    }
}
