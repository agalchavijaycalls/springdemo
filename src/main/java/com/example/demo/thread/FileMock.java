package com.example.demo.thread;

public class FileMock {
    private String[] content;
    private int index;

    public FileMock(int size, int length) {
        this.content = new String[size];
        for (int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                int randomChar = (int) Math.random() * 255;
                sb.append((char) randomChar);
            }
            content[i] = sb.toString();
        }
        index = 0;
    }

    public boolean hasMoreLine() {
        return index < content.length;
    }

    public String readLine() {
        if (hasMoreLine()) {
            System.out.println("FileMock : " + (content.length - index));
            return content[index++];
        } else {
            return null;
        }
    }

}
