package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OccurrencesImplement implements Occurrences {

    ConcurrentSkipListSet<String> concurrentSkipListSetAllThread = new ConcurrentSkipListSet<>();

    public void getOccurrences(String[] sources, String[] words, String res) {
        ExecutorService threadpool = Executors.newCachedThreadPool();
        int k = sources.length;
        int j = 0;
        while (k != 0) {
            long skip = 0;
            for(int i = 0; i < 10; i++) {
                File file = new File(sources[j]);
                ;
                if(file.length() > 100000000L) {
                    int sizeBigFileThread = 30;
                    skip = new File(sources[j]).getTotalSpace() / sizeBigFileThread;
                    for(int u = 0; u < sizeBigFileThread; u++) {
                        threadpool.submit(new MyThread(sources[j], words, concurrentSkipListSetAllThread, skip));
                        skip += skip;
                    }

                } else {
                    threadpool.submit(new MyThread(sources[j], words, concurrentSkipListSetAllThread, skip));
                }
                k -= 1;
                j += 1;
                if(k == 0) {
                    break;
                }
            }
            threadpool.shutdown();
            threadpool = Executors.newCachedThreadPool();
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(res));
            for(String s: concurrentSkipListSetAllThread) {
                writer.write(s);
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
