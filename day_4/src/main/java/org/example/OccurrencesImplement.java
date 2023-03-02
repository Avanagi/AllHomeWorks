package org.example;

import java.io.File;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OccurrencesImplement implements Occurrences {

    private final ConcurrentSkipListSet<String> concurrentSkipListSetAllThread = new ConcurrentSkipListSet<>();

    public void getOccurrences(String[] sources, String[] words, String res) {
        ExecutorService threadpool = Executors.newCachedThreadPool();
        int sourcesLength = sources.length;
        int sourceNumber = 0;
        while (sourcesLength != 0) {
            long skip = 0;
            for(int i = 0; i < 10; i++) {
                File file = new File(sources[sourceNumber]);
                if(file.length() > 100000000) {
                    int sizeBigFileThread = 30;
                    skip = new File(sources[sourceNumber]).getTotalSpace() / sizeBigFileThread;
                    for(int j = 0; j < sizeBigFileThread; j++) {
                        threadpool.submit(new MyThread(sources[sourceNumber], words, concurrentSkipListSetAllThread, res));
                        skip += skip;
                    }

                } else {
                    threadpool.submit(new MyThread(sources[sourceNumber], words, concurrentSkipListSetAllThread, res));
                }
                sourcesLength -= 1;
                sourceNumber += 1;
                if(sourcesLength == 0) {
                    break;
                }
            }
            threadpool.shutdown();
            threadpool = Executors.newCachedThreadPool();
        }
    }
}
