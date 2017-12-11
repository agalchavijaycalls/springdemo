package com.example.demo.thread;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private final LinkedList<String> buffer;
    private final int maxSize;
    private final ReentrantLock lock;
    private final Condition space;
    private final Condition lines;
    private boolean pendingLine;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        this.buffer = new LinkedList<>();
        this.lock = new ReentrantLock();
        this.lines = this.lock.newCondition();
        this.space = this.lock.newCondition();
        this.pendingLine = true;
    }

    public void insert(String line) {
        lock.lock();
        try {
            while (buffer.size() >= maxSize) {
                space.await();
            }
            buffer.offer(line);
            System.out.printf("%s: Inserted line: %d\n", Thread.currentThread().getName(), buffer.size());
            lines.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String get() {
        String line = null;
        lock.lock();
        try {
            while (buffer.size() <= 0) {
                lines.await();
            }
            line = buffer.poll();
            System.out.printf("%s: Line readed: %d\n", Thread.currentThread().getName(), buffer.size());
            space.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return line;
    }

    public void setPendingLine(boolean pendingLine) {
        this.pendingLine = pendingLine;
    }
}
