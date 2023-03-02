package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentSkipListSet;

public class MyThread extends Thread {
    private final String fileDest;
    private final String[] words;
    private final ConcurrentSkipListSet<String> concurrentSkipListSetAllThread;
    private final String res;

    public MyThread(String fileDest, String[] words, ConcurrentSkipListSet<String> concurrentSkipListSetAllThread, String res) {
        this.fileDest = fileDest;
        this.words = words;
        this.concurrentSkipListSetAllThread = concurrentSkipListSetAllThread;
        this.res = res;
    }

    @Override
    public void run() {
        StringBuilder buffer = new StringBuilder();
        try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(fileDest), StandardCharsets.UTF_8)) {
            BufferedReader reader = new BufferedReader(inputStreamReader);
            int ch;
            while ((ch = reader.read()) > - 1) {
                if(((char) ch != '.') && ((char) ch != '?') && ((char) ch != '!')) {
                    buffer.append((char) ch);
                } else {
                    for(String word: words) {
                        if(checkWord(String.valueOf(buffer), word)) {
                            String s = buffer.toString().trim();
                            concurrentSkipListSetAllThread.add(s);
                        }
                    }
                    buffer.setLength(0);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(res));
            writer.write(String.valueOf(concurrentSkipListSetAllThread));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean checkWord(String line, String words) {
        String[] strings = line.split(" ");
        for(String word: strings) {
            if(word.matches(words)) {
                return true;
            }
        }
        return false;
    }
}


