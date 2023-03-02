package org.example;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String[] words = {"like", "place", "first"};
        String[] resources = {
                "./test/lp.txt",
                "./test/lp.txt",
                "./test/lp.txt",
                "./test/lp.txt",
                "./test/lp.txt",
                "./test/lp.txt",
                "./test/lp.txt",
                "./test/lp.txt",
                "./test/lp.txt",
                "http://www.gutenberg.org/cache/epub/17168/pg17168.txt"};
        Occurrences occurrences = new OccurrencesImplement();
        occurrences.getOccurrences(resources, words, "./test/test.txt");

        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("Time gone, ms: " + elapsed);
    }
}