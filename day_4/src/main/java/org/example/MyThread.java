package org.example;

import org.jsoup.Jsoup;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

public class MyThread extends Thread {

    private final String fileDest;
    private final String[] words;
    public ConcurrentSkipListSet<String> concurrentSkipListSetAllThread;
    private final long skip;

    public MyThread(String fileDest, String[] words, ConcurrentSkipListSet<String> concurrentSkipListSetAllThread) {
        this.fileDest = fileDest;
        this.words = words;
        this.concurrentSkipListSetAllThread = concurrentSkipListSetAllThread;
        this.skip = 0;
    }

    public MyThread(String fileDest, String[] words, ConcurrentSkipListSet<String> concurrentSkipListSetAllThread, long skip) {
        this.fileDest = fileDest;
        this.words = words;
        this.concurrentSkipListSetAllThread = concurrentSkipListSetAllThread;
        this.skip = skip;
    }

    @Override
    public void run() {
        StringBuilder buffer = new StringBuilder();
        InputStream is;
        try {
            is = new URL(fileDest).openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (InputStreamReader inputStreamReader = new InputStreamReader(is, "UTF-16")){
            BufferedReader reader = new BufferedReader(inputStreamReader);
            reader.skip(skip);
            int ch;
            while ((ch = reader.read())>-1){
                if (((char)ch != '.')){
                    buffer.append((char)ch);
                }
                else{
                    String s = buffer.toString().trim();
                    System.out.println(s);
                    String[] split = s.split("\\b+");
                    List<String> list = Arrays.asList(split);
                    for (String s1 : words){
                        if (list.contains(s1)) {
                            concurrentSkipListSetAllThread.add(s);
                        }
                    }
                    buffer.setLength(0);
                }
                System.out.println(concurrentSkipListSetAllThread.toString());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


