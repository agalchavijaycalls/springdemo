package com.example.demo.thread;

public class Main {
    public static void main(String[] args) {
        FileMock mock = new FileMock(100, 10);
        Buffer buffer = new Buffer(20);
        Producer producer = new Producer(mock, buffer);
        Thread producerThread = new Thread(producer, "Producer");
        Consumer[] consumer = new Consumer[3];
        Thread[] consumerThreads = new Thread[3];
        for (int i = 1; i <= 3; i++) {
            consumer[i - 1] = new Consumer(buffer);
            consumerThreads[i - 1] = new Thread(consumer[i - 1], "Consumer" + i);
        }
        producerThread.start();
        for (int i = 0; i < 3; i++) {
            consumerThreads[i].start();
        }
    }
}
