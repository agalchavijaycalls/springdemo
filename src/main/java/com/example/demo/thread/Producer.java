package com.example.demo.thread;

public class Producer implements Runnable {
    private FileMock fileMock;
    private Buffer buffer;

    public Producer(FileMock fileMock, Buffer buffer) {
        this.fileMock = fileMock;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (fileMock.hasMoreLine()) {
            buffer.insert(fileMock.readLine());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
